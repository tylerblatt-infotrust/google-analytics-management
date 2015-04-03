package com.pg.google.api.management.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.knime.core.node.NodeLogger;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.analytics.Analytics.Management.AccountUserLinks;
import com.google.api.services.analytics.Analytics.Management.CustomDimensions;
import com.google.api.services.analytics.Analytics.Management.CustomDimensions.Insert;
import com.google.api.services.analytics.Analytics.Management.CustomDimensions.Update;
import com.google.api.services.analytics.Analytics.Management.ProfileFilterLinks;
import com.google.api.services.analytics.Analytics.Management.ProfileUserLinks;
import com.google.api.services.analytics.Analytics.Management.Profiles.Patch;
import com.google.api.services.analytics.Analytics.Management.WebpropertyUserLinks;
import com.google.api.services.analytics.AnalyticsRequest;
import com.google.api.services.analytics.model.CustomDimension;
import com.google.api.services.analytics.model.EntityUserLink;
import com.google.api.services.analytics.model.Filter;
import com.google.api.services.analytics.model.Profile;
import com.google.api.services.analytics.model.ProfileFilterLink;
import com.google.api.services.analytics.model.UserRef;
import com.google.api.services.analytics.model.EntityUserLink.Permissions;
import com.google.api.services.analytics.model.EntityUserLinks;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.GaData.ProfileInfo;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnection;

public class GoogleAnalyticsManagementClient {

	private GoogleAnalyticsConnection analyticsConnection;
	
	//private static final String ALL = "~all";
	private static final NodeLogger LOGGER = NodeLogger.getLogger(GoogleAnalyticsManagementClient.class);
	private static final int MAX_ATTEMPTS = 5;
	
	protected static HashMap<String, ProfileInfo> PROFILE_CACHE = new HashMap<String, ProfileInfo>();
	
	public GoogleAnalyticsManagementClient( GoogleAnalyticsConnection connection ) {
		this.analyticsConnection = connection;
	}
	
	// Execute very simple query to acquire Profile MetaData
	public ProfileInfo getProfileInfo(String profileId) {
		
		ProfileInfo cachedInfo = PROFILE_CACHE.get(profileId);
		if ( cachedInfo != null ) return cachedInfo;
		
		try {
			analyticsConnection.query("2014-01-01", "2014-01-01", new String[] {"ga:sessions"}, new String[] {} , null);
			GaData model = analyticsConnection.getDataModel();
			PROFILE_CACHE.put(profileId, model.getProfileInfo());
			return model.getProfileInfo();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	public ProfileInfo getProfileInfo() {
		return getProfileInfo(analyticsConnection.getProfileId());
	}
	
	public void patchProfile ( String profileName ) throws IOException {
		
		ProfileInfo profile = getProfileInfo();
		
		Profile body = new Profile();
		body.setName(profileName);
		
		Patch request = analyticsConnection.getAnalytics().management().profiles().patch(profile.getAccountId(), profile.getWebPropertyId(), profile.getProfileId(), body);
		protectedQuery ( request );
		
	}
	
	public void updateCustomDimension ( String customDimensionId, String dimensionName, String dimensionScope ) throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		
		CustomDimension dimension = new CustomDimension();
		dimension.setScope(dimensionScope);
		dimension.setName(dimensionName);
		dimension.setActive(true);
		
		Update request = analyticsConnection.getAnalytics().management().customDimensions().update(profile.getAccountId(), profile.getWebPropertyId(), customDimensionId, dimension );
		protectedQuery(request);
		
	}
	
	public void insertCustomDimension (String customDimensionId, String dimensionName, String dimensionScope ) throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		
		CustomDimension dimension = new CustomDimension();
		dimension.setId(customDimensionId);
		dimension.setScope(dimensionScope);
		dimension.setName(dimensionName);
		dimension.setActive(true);
		
		Insert request = analyticsConnection.getAnalytics().management().customDimensions().insert(profile.getAccountId(), profile.getWebPropertyId(), dimension);
		protectedQuery(request);
		
	}
	
	public void addUserToProfile ( String emailaddress ) throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		UserRef userRef = new UserRef();
		userRef.setEmail(emailaddress);
		
		
		Permissions perms = new Permissions();
		perms.setLocal(Arrays.asList("READ_AND_ANALYZE"));
		
		EntityUserLink link = new EntityUserLink();
		link.setPermissions(perms);
		link.setUserRef(userRef);
		
		protectedQuery(
				analyticsConnection.getAnalytics().management().profileUserLinks().insert(profile.getAccountId(), profile.getWebPropertyId(), profile.getProfileId(), link)
		);
	}
	
	protected <T> T protectedQuery( AnalyticsRequest<T> request) throws IOException {
		int attempt = 0;
		T result = null;
		boolean complete = false;
		
		do {
			try {
				result = request.execute();
				complete = true;
			} catch (GoogleJsonResponseException exc ) {
				String reason = exc.getDetails().getErrors().get(0).getReason();
				LOGGER.debug(reason);
				
				// GUARD STATEMENT: Exceeded maximum attempts
				if ( attempt++ > MAX_ATTEMPTS ) throw exc;
				
				// GUARD STATEMENT: Unknown exception
				if ( !( ("rateLimitExceeded".equals(reason) || "dailyLimitExceeded".equals(reason) || "userRateLimitExceeded".equals(reason) || "backendError".equals(reason) ) ) ) {
					throw exc;
				}
				
				try { Thread.sleep(1000*attempt); } catch ( Exception e ) {}
			}
			
		} while (!complete);
		
		return result;
	}
	
	public List<CustomDimension> getCustomDimensions() throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		
		CustomDimensions.List request = analyticsConnection.getAnalytics().management().customDimensions().list(profile.getAccountId(), profile.getWebPropertyId());
		
		com.google.api.services.analytics.model.CustomDimensions dimensions = protectedQuery(request);
		if ( dimensions == null ) return null;
		
		return dimensions.getItems();
	}
	
	public List<ProfileFilterLink> getFilters() throws IOException {
		
		ProfileInfo profile = getProfileInfo();
		
		ProfileFilterLinks.List request = analyticsConnection.getAnalytics().management().profileFilterLinks().list(profile.getAccountId(), profile.getWebPropertyId(), profile.getProfileId());
		com.google.api.services.analytics.model.ProfileFilterLinks links = protectedQuery(request);
		if ( links == null ) return null;
		
		return links.getItems();
		
	}
	
	public Filter getFilter(String filterId ) throws IOException {
		ProfileInfo profile = getProfileInfo();
		return protectedQuery(analyticsConnection.getAnalytics().management().filters().get(profile.getAccountId(), filterId));
	}
	
	public void updateAccountPermission ( String userId, String[] permissions ) throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		
		Permissions perms = new Permissions();
		perms.setLocal(Arrays.asList(permissions));
		
		EntityUserLink link = new EntityUserLink();
		link.setPermissions(perms);
		
		analyticsConnection.getAnalytics().management().accountUserLinks().update(profile.getAccountId(), profile.getAccountId() + ":" + userId, link).execute();
	}
	
	public void updatePropertyPermission ( String userId, String[] permissions ) throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		
		Permissions perms = new Permissions();
		perms.setLocal(Arrays.asList(permissions));
		
		EntityUserLink link = new EntityUserLink();
		link.setPermissions(perms);
		
		analyticsConnection.getAnalytics().management().webpropertyUserLinks().update(profile.getAccountId(), profile.getWebPropertyId(), profile.getWebPropertyId() + ":" + userId, link).execute();
	}
	
	public void updateProfilePermission ( String userId, String[] permissions ) throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		
		Permissions perms = new Permissions();
		perms.setLocal(Arrays.asList(permissions));
		
		EntityUserLink link = new EntityUserLink();
		link.setPermissions(perms);
		
		analyticsConnection.getAnalytics().management().profileUserLinks().update(profile.getAccountId(), profile.getWebPropertyId(), profile.getProfileId(), profile.getProfileId() + ":" + userId, link).execute();
	}
	
	public void removeProfileUser ( String userId ) throws IOException {
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		
		protectedQuery(
				analyticsConnection.getAnalytics().management().profileUserLinks().delete(profile.getAccountId(), profile.getWebPropertyId(), profile.getProfileId(), profile.getProfileId() + ":" + userId)
		);
	}
	
	public void removeAccountUser ( String userId ) throws IOException {
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		
		protectedQuery(
				analyticsConnection.getAnalytics().management().accountUserLinks().delete(profile.getAccountId(), profile.getAccountId() + ":" + userId)
		);
	}

	public void removePropertyUser ( String userId ) throws IOException {
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		
		protectedQuery(
				analyticsConnection.getAnalytics().management().webpropertyUserLinks().delete(profile.getAccountId(), profile.getWebPropertyId(), profile.getWebPropertyId() + ":" +userId)
		);
	}
	
	public List<GoogleAnalyticsUser> getProfileUsers() throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		List<GoogleAnalyticsUser> users = new ArrayList<GoogleAnalyticsUser>();
		
		ProfileUserLinks.List request = analyticsConnection.getAnalytics().management().profileUserLinks().list(profile.getAccountId(), profile.getWebPropertyId(), profile.getProfileId());
		EntityUserLinks results = null;
		
		int attempt = 0;
		int index = 1;
		do {
			request.setStartIndex(index);
			
			try {
				attempt++;
				results = request.execute();
				
				for ( EntityUserLink user : results.getItems() ) {
					GoogleAnalyticsUser gUser = new GoogleAnalyticsUser();
					gUser.load(user);
					users.add(gUser);
				}
				
				index = index + results.getItemsPerPage();
			
			} catch ( GoogleJsonResponseException exc ) {
				String reason = exc.getDetails().getErrors().get(0).getReason();

				// GUARD STATEMENT: Exceeded maximum attempts
				if ( attempt++ > MAX_ATTEMPTS ) throw exc;
				
				// GUARD STATEMENT: Unknown exception
				if ( !( ("rateLimitExceeded".equals(reason) || "dailyLimitExceeded".equals(reason) || "userRateLimitExceeded".equals(reason) || "backendError".equals(reason) ) ) ) {
					throw exc;
				}
				
				try { Thread.sleep(2000); } catch ( Exception e ) {}
			}			
			
		} while ( users.size() < results.getTotalResults() );
		
		return users;
	}
	
	public List<GoogleAnalyticsUser> getPropertyUsers() throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		List<GoogleAnalyticsUser> users = new ArrayList<GoogleAnalyticsUser>();
		
		WebpropertyUserLinks.List request = analyticsConnection.getAnalytics().management().webpropertyUserLinks().list(profile.getAccountId(), profile.getWebPropertyId());
		EntityUserLinks results = null;
		
		int attempt = 0;
		int index = 1;
		do {
			request.setStartIndex(index);
			
			try {
				attempt++;
				results = request.execute();
				
				for ( EntityUserLink user : results.getItems() ) {
					GoogleAnalyticsUser gUser = new GoogleAnalyticsUser();
					gUser.load(user);
					users.add(gUser);
				}
				
				index = index + results.getItemsPerPage();
			
			} catch ( GoogleJsonResponseException exc ) {
				String reason = exc.getDetails().getErrors().get(0).getReason();

				// GUARD STATEMENT: Exceeded maximum attempts
				if ( attempt++ > MAX_ATTEMPTS ) throw exc;
				
				// GUARD STATEMENT: Unknown exception
				if ( !( ("rateLimitExceeded".equals(reason) || "dailyLimitExceeded".equals(reason) || "userRateLimitExceeded".equals(reason) || "backendError".equals(reason) ) ) ) {
					throw exc;
				}
				
				try { Thread.sleep(2000); } catch ( Exception e ) {}
			}			
			
		} while ( users.size() < results.getTotalResults() );
		
		return users;
	}	
	
	public List<GoogleAnalyticsUser> getAccountUsers() throws IOException {
		
		ProfileInfo profile = getProfileInfo(analyticsConnection.getProfileId());
		List<GoogleAnalyticsUser> users = new ArrayList<GoogleAnalyticsUser>();
		
		AccountUserLinks.List request = analyticsConnection.getAnalytics().management().accountUserLinks().list(profile.getAccountId());
		EntityUserLinks results = null;
		
		int attempt = 0;
		int index = 1;
		do {
			request.setStartIndex(index);
			
			try {
				attempt++;
				results = request.execute();
				
				for ( EntityUserLink user : results.getItems() ) {
					GoogleAnalyticsUser gUser = new GoogleAnalyticsUser();
					gUser.load(user);
					users.add(gUser);
				}
				
				index = index + results.getItemsPerPage();
			
			} catch ( GoogleJsonResponseException exc ) {
				String reason = exc.getDetails().getErrors().get(0).getReason();

				// GUARD STATEMENT: Exceeded maximum attempts
				if ( attempt++ > MAX_ATTEMPTS ) throw exc;
				
				// GUARD STATEMENT: Unknown exception
				if ( !( ("rateLimitExceeded".equals(reason) || "dailyLimitExceeded".equals(reason) || "userRateLimitExceeded".equals(reason) || "backendError".equals(reason) ) ) ) {
					throw exc;
				}
				
				try { Thread.sleep(2000); } catch ( Exception e ) {}
			}			
			
		} while ( users.size() < results.getTotalResults() );
		
		return users;
	}
	
}

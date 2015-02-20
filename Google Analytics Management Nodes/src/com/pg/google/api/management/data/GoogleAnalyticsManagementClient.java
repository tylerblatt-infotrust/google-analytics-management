package com.pg.google.api.management.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.knime.core.node.NodeLogger;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.analytics.Analytics.Management.AccountUserLinks;
import com.google.api.services.analytics.Analytics.Management.ProfileUserLinks;
import com.google.api.services.analytics.Analytics.Management.WebpropertyUserLinks;
import com.google.api.services.analytics.model.EntityUserLink;
import com.google.api.services.analytics.model.EntityUserLinks;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.GaData.ProfileInfo;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnection;

public class GoogleAnalyticsManagementClient {

	private GoogleAnalyticsConnection analyticsConnection;
	
	private static final String ALL = "~all";
	private static final NodeLogger LOGGER = NodeLogger.getLogger(GoogleAnalyticsManagementClient.class);
	private static final int MAX_ATTEMPTS = 5;
	
	public GoogleAnalyticsManagementClient( GoogleAnalyticsConnection connection ) {
		this.analyticsConnection = connection;
	}
	
	// Execute very simple query to acquire Profile MetaData
	private ProfileInfo getProfileInfo(String profileId) {
		
		try {
			analyticsConnection.query("2014-01-01", "2014-01-01", new String[] {"ga:sessions"}, new String[] {} , null);
			GaData model = analyticsConnection.getDataModel();
			return model.getProfileInfo();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	public void removePermission ( String permission ) {
		
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

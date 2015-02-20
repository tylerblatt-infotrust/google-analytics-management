package com.pg.google.api.management.data;

import java.util.List;

import com.google.api.services.analytics.model.EntityUserLink;

public class GoogleAnalyticsUser {

	private String accountId = "";
	private String propertyId = "";
	private String profileId = "";
	private String profileName = "";
	
	private String userId = "";
	private String email = "";
	private List<String> localPermissions;
	private List<String> effectivePermissions;
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getLocalPermissions() {
		return localPermissions;
	}
	public void setLocalPermissions(List<String> localPermissions) {
		this.localPermissions = localPermissions;
	}
	public List<String> getEffectivePermissions() {
		return effectivePermissions;
	}
	public void setEffectivePermissions(List<String> effectivePermissions) {
		this.effectivePermissions = effectivePermissions;
	}
	
	public void load ( EntityUserLink user ) {
		
		if ( user.getEntity().getAccountRef() != null )
			setAccountId(user.getEntity().getAccountRef().getId());
		
		if ( user.getEntity().getWebPropertyRef() != null ) {
			setAccountId(user.getEntity().getWebPropertyRef().getAccountId());
			setPropertyId(user.getEntity().getWebPropertyRef().getId() );
		}
		
		if ( user.getEntity().getProfileRef() != null ) {
			setAccountId(user.getEntity().getProfileRef().getAccountId());
			setPropertyId(user.getEntity().getProfileRef().getWebPropertyId());
			setProfileId(user.getEntity().getProfileRef().getId());
			setProfileName(user.getEntity().getProfileRef().getName());	
		}
		
		setUserId(user.getUserRef().getId());
		setEmail(user.getUserRef().getEmail());
		
		setLocalPermissions(user.getPermissions().getLocal());
		setEffectivePermissions(user.getPermissions().getEffective());
	}
	
}

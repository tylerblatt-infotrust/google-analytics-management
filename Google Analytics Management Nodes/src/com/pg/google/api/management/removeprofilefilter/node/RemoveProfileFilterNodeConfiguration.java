package com.pg.google.api.management.removeprofilefilter.node;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class RemoveProfileFilterNodeConfiguration {
	
	private String profileID = "";
	private String accountID = "";
	private String propertyID = "";
	private String filterID = "";
	
	private final static String CFG_PROFILE_ID = "cfg.profile.id";
	private final static String CFG_ACCOUNT_ID = "cfg.account.id";
	private final static String CFG_PROPERTY_ID = "cfg.property.id";
	private final static String CFG_FILTER_ID = "cfg.filter.id";
	
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_PROFILE_ID, getProfileID());
		settings.addString(CFG_ACCOUNT_ID, getAccountID());
		settings.addString(CFG_PROPERTY_ID, getPropertyID());
		settings.addString(CFG_FILTER_ID, getFilterID());
	}
	
	public void load ( NodeSettingsRO settings ) {
		setProfileID(settings.getString(CFG_PROFILE_ID, ""));
		setAccountID(settings.getString(CFG_ACCOUNT_ID, ""));
		setPropertyID(settings.getString(CFG_PROPERTY_ID, ""));
		setFilterID(settings.getString(CFG_FILTER_ID, ""));
	}
	
	public String getProfileID() {
		return profileID;
	}
	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getPropertyID() {
		return propertyID;
	}
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	public String getFilterID() {
		return filterID;
	}
	public void setFilterID(String filterID) {
		this.filterID = filterID;
	} 
	

}

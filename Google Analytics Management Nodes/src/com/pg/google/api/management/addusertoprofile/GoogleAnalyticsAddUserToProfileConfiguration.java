package com.pg.google.api.management.addusertoprofile;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class GoogleAnalyticsAddUserToProfileConfiguration {

	private String emailAddress;
	private static final String CFG_EMAIL_ADDRESS = "cfg.emailaddress";
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_EMAIL_ADDRESS, getEmailAddress());
	}
	
	public void load ( NodeSettingsRO settings ) {
		setEmailAddress(settings.getString(CFG_EMAIL_ADDRESS, ""));
	}
	
}

package com.pg.google.api.management.patchprofile;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class PatchProfileConfiguration {

	private String profileName = "";
	
	private static final String CFG_PROFILE_NAME = "cfg.profile.name";

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_PROFILE_NAME, getProfileName());
	}
	
	public void load ( NodeSettingsRO settings ) {
		setProfileName(settings.getString(CFG_PROFILE_NAME, ""));
	}
	
}

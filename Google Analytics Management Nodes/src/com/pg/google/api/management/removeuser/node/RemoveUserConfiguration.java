package com.pg.google.api.management.removeuser.node;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class RemoveUserConfiguration {

	private String userId = "";
	private String level = "";
	
	private static final String CFG_USER_ID = "cfg.user.id";
	private static final String CFG_LEVEL = "cfg.level";
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_USER_ID, getUserId());
		settings.addString(CFG_LEVEL, getLevel());
	}
	
	public void load ( NodeSettingsRO settings ) {
		setUserId(settings.getString(CFG_USER_ID, ""));
		setLevel(settings.getString(CFG_LEVEL,"Profile Level"));
	}
	
	public boolean isAccountLevel() {
		return "Account Level".equals(getLevel());
	}
	public boolean isPropertyLevel() {
		return "Property Level".equals(getLevel());
	}
	public boolean isProfileLevel() {
		return "Profile Level".equals(getLevel());
	}
}

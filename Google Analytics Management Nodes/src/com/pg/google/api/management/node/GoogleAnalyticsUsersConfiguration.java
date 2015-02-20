package com.pg.google.api.management.node;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class GoogleAnalyticsUsersConfiguration {

	private String level;

	private static final String CFG_LEVEL = "cfg.level";
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public void load ( NodeSettingsRO settings ) {
		setLevel(settings.getString(CFG_LEVEL, "Profile Level"));
	}
	
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_LEVEL, getLevel());
	}
	
	
}

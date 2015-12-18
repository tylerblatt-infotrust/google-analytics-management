package com.pg.google.api.management.insertprofilefilter;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class ProfileFilterConfiguration {

	private String filterName = "";
	
	private static String CFG_NAME = "cfg.filter.id";

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_NAME, getFilterName() );
		
	}
	
	public void load ( NodeSettingsRO settings ) {
		setFilterName(settings.getString(CFG_NAME, ""));
	}
	
	
}

package com.pg.google.api.management.updatecustomdimensions.node;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class GoogleAnalyticsUpdateCustomDimensionConfig {

	private String cd_name = "";
	private String cd_scope = "";
	private String cd_id = "";
	private Boolean cd_active = new Boolean ( false );
	
	private final static String CFG_SCOPE_ID = "cfg.scope.id";
	private final static String CFG_ID = "cfg.id";
	private final static String CFG_ACTIVE_ID = "cfg.active.id";
	private final static String CFG_NAME_ID = "cfg.name.id";
	
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_SCOPE_ID, getCd_scope());
		settings.addString(CFG_ID, getCd_id());
		settings.addBoolean(CFG_ACTIVE_ID, getCd_active());
		settings.addString(CFG_NAME_ID, getCd_name());
	}
	
	public void load ( NodeSettingsRO settings ) {
		setCd_scope(settings.getString(CFG_SCOPE_ID, ""));
		setCd_id(settings.getString(CFG_ID, ""));
		setCd_active(settings.getBoolean(CFG_ACTIVE_ID, false));
		setCd_name(settings.getString(CFG_NAME_ID, ""));
	}
	
	
	public String getCd_name() {
		return cd_name;
	}
	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}
	public String getCd_scope() {
		return cd_scope;
	}
	public void setCd_scope(String cd_scope) {
		this.cd_scope = cd_scope;
	}
	public String getCd_id() {
		return cd_id;
	}
	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}
	public Boolean getCd_active() {
		return cd_active;
	}
	public void setCd_active(Boolean cd_active) {
		this.cd_active = cd_active;
	}
	
}

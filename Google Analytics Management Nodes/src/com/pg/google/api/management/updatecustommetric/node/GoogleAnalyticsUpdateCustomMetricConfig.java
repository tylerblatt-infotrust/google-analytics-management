package com.pg.google.api.management.updatecustommetric.node;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class GoogleAnalyticsUpdateCustomMetricConfig {

	private String cm_name = "";
	private String cm_scope = "";
	private String cm_id = "";
	private String cm_format = "";
	private String cm_min = "";
	private String cm_max = "";
	
	private Boolean cm_active = new Boolean ( false );
	
	private final static String CFG_SCOPE_ID = "cfg.scope.id";
	private final static String CFG_ID = "cfg.id";
	private final static String CFG_ACTIVE_ID = "cfg.active.id";
	private final static String CFG_NAME_ID = "cfg.name.id";
	private final static String CFG_MIN_ID = "cfg.min.id";
	private final static String CFG_MAX_ID = "cfg.max.id";
	private final static String CFG_FORMAT_ID = "cfg.format.id";
	
		
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_SCOPE_ID, getCm_scope());
		settings.addString(CFG_ID, getCm_id());
		settings.addBoolean(CFG_ACTIVE_ID, getCm_active());
		settings.addString(CFG_NAME_ID, getCm_name());
		settings.addString(CFG_FORMAT_ID, getCm_format());
		settings.addString(CFG_MIN_ID, getCm_min());
		settings.addString(CFG_MAX_ID, getCm_max());
	}
	
	public void load ( NodeSettingsRO settings ) {
		setCm_scope(settings.getString(CFG_SCOPE_ID, ""));
		setCm_id(settings.getString(CFG_ID, ""));
		setCm_active(settings.getBoolean(CFG_ACTIVE_ID, false));
		setCm_name(settings.getString(CFG_NAME_ID, ""));
		setCm_format(settings.getString(CFG_FORMAT_ID, ""));
		setCm_min(settings.getString(CFG_MIN_ID, ""));
		setCm_max(settings.getString(CFG_MAX_ID, ""));
	}

	public String getCm_id() {
		return cm_id;
	}

	public void setCm_id(String cm_id) {
		this.cm_id = cm_id;
	}

	public String getCm_name() {
		return cm_name;
	}

	public void setCm_name(String cm_name) {
		this.cm_name = cm_name;
	}

	public String getCm_scope() {
		return cm_scope;
	}

	public void setCm_scope(String cm_scope) {
		this.cm_scope = cm_scope;
	}

	public String getCm_format() {
		return cm_format;
	}

	public void setCm_format(String cm_format) {
		this.cm_format = cm_format;
	}

	public Boolean getCm_active() {
		return cm_active;
	}

	public void setCm_active(Boolean cm_active) {
		this.cm_active = cm_active;
	}

	public String getCm_min() {
		return cm_min;
	}

	public void setCm_min(String cm_min) {
		this.cm_min = cm_min;
	}

	public String getCm_max() {
		return cm_max;
	}

	public void setCm_max(String cm_max) {
		this.cm_max = cm_max;
	}
}

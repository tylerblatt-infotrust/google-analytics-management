package com.pg.google.api.management.removeprofile.node;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class RemoveProfileConfiguration {

	private Boolean agreed = new Boolean ( false );
	private static final String CFG_AGREED = "cfg.agreed";
	
	public void load ( NodeSettingsRO settings ) {
		setAgreed( settings.getBoolean(CFG_AGREED, false) );
	}

	public void save ( NodeSettingsWO settings ) {
		settings.addBoolean(CFG_AGREED, getAgreed() );
	}
	
	public Boolean getAgreed() {
		return agreed;
	}

	public void setAgreed(Boolean agreed) {
		this.agreed = agreed;
	}
	
}

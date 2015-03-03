package com.pg.google.api.management.insertcustomdimensions.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

import com.google.api.services.analytics.model.CustomDimension;

public class GoogleAnalyticsInsertCustomDimensionsConfiguration {

	private List<String> dimensionNames = new ArrayList<String>(50);
	private List<String> dimensionScopes = new ArrayList<String>(50);
	
	private static final String CFG_NAMES = "cfg.names";
	private static final String CFG_SCOPES = "cfg.scopes";
	
	public static final String DEFAULT_NAME = "Not Set";
	public static final String DEFAULT_SCOPE = "HIT";
	
	private static final int MAX_DIMENSIONS = 50;
	
	public int getMaxDimensions() {
		return MAX_DIMENSIONS;
	}
	
	public List<String> getDimensionNames() {
		return dimensionNames;
	}
	public void setDimensionNames(List<String> dimensionNames) {
		this.dimensionNames = dimensionNames;
	}
	public List<String> getDimensionScopes() {
		return dimensionScopes;
	}
	public void setDimensionScopes(List<String> dimensionScopes) {
		this.dimensionScopes = dimensionScopes;
	}
	
	public boolean isSetup() {
		return !( 
			getDimensionNames() == null ||
			getDimensionScopes() == null ||
			getDimensionNames().size() == 0 ||
			getDimensionScopes().size() == 0 ||
			StringUtils.isEmpty(getDimensionNames().get(0)) ||
			StringUtils.isEmpty(getDimensionScopes().get(0))
		);
	}
	
	public void save ( NodeSettingsWO settings ) {
		settings.addStringArray(CFG_NAMES, getDimensionNames().toArray(new String[]{}));
		settings.addStringArray(CFG_SCOPES, getDimensionScopes().toArray(new String[]{}));
	}
	
	public void load ( NodeSettingsRO settings ) {
		setDimensionNames(Arrays.asList(settings.getStringArray(CFG_NAMES, new String[50] )));
		setDimensionScopes(Arrays.asList(settings.getStringArray(CFG_SCOPES, new String[50] )));
	}
	
	public void load ( List<CustomDimension> dimensions ) {
		if ( dimensions == null ) return;
		
		for ( int i = 1; i <= getMaxDimensions(); i++ ) {
			String dimensionId = "ga:dimension"+i;
			
			// Find dimension for id
			CustomDimension match = getDimension(dimensionId, dimensions);
			
			if ( match != null ) {
				dimensionNames.set(i-1, match.getName());
				dimensionScopes.set(i-1, match.getScope());
			} else {
				dimensionNames.set(i-1, DEFAULT_NAME);
				dimensionScopes.set(i-1, DEFAULT_SCOPE);
			}
		}
		
	}
	
	public CustomDimension getDimension(String dimensionId, List<CustomDimension> dimensions ) {
		for ( CustomDimension dimension : dimensions ) {
			if ( dimension!=null && dimension.getId() !=null && dimensionId.equals(dimension.getId())) {
				return dimension;
			}
		}
		
		return null;
	}
	
}

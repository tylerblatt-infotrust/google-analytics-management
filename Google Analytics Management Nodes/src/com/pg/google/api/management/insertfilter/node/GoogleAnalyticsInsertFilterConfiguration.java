package com.pg.google.api.management.insertfilter.node;

import java.util.ArrayList;
import java.util.List;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class GoogleAnalyticsInsertFilterConfiguration {

	private String filterName = "";
	private String filterType = "";
	private String detailMatchType = "";
	private String detailExpressionValue = "";
	private String detailField = "";
	private String searchString = "";
	private String fieldAText = "";
	private String fieldBText = "";
	private String outputText = "";
	private String replaceString = "";
	private String fieldABox = "";
	private String fieldBBox = "";
	private String outputBox = "";

	private Boolean fieldAChk = new Boolean ( false );
	private Boolean fieldBChk = new Boolean ( false );
	private Boolean override = new Boolean ( false );
	private Boolean case_sensitive = new Boolean ( false );

	private static final String CFG_NAME = "cfg.filter.name";
	private static final String CFG_TYPE = "cfg.filter.type";
	private static final String CFG_MATCHTYPE = "cfg.filter.matchtype";
	private static final String CFG_EXPRESSION = "cfg.filter.pattern";
	private static final String CFG_FIELD = "cfg.filter.field";
	private static final String CFG_SEARCH = "cfg.search.text";
	private static final String CFG_FIELD_A_TEXT = "cfg.field.a.text";
	private static final String CFG_FIELD_B_TEXT = "cfg.field.b.text";
	private static final String CFG_OUTPUT_TEXT = "cfg.output.text";
	private static final String CFG_REPLACE = "cfg.replace.text";
	private static final String CFG_FIELD_A_BOX = "cfg.field.a.box";
	private static final String CFG_FIELD_B_BOX = "cfg.field.b.box";
	private static final String CFG_OUTPUT_BOX = "cfg.output.box";
	private static final String CFG_AGREED = "cfg.case_sensitive";
	private static final String CFG_FIELDACHK = "cfg.fieldachk";
	private static final String CFG_FIELDBCHK = "cfg.fieldbchk";
	private static final String CFG_OVERRIDE = "cfg.override";
	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getFieldAText() {
		return fieldAText;
	}

	public void setFieldAText(String fieldAText) {
		this.fieldAText = fieldAText;
	}

	public String getFieldBText() {
		return fieldBText;
	}

	public void setFieldBText(String fieldBText) {
		this.fieldBText = fieldBText;
	}

	public String getOutputText() {
		return outputText;
	}

	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}

	public String getReplaceString() {
		return replaceString;
	}

	public void setReplaceString(String replaceString) {
		this.replaceString = replaceString;
	}

	public String getFieldABox() {
		return fieldABox;
	}

	public void setFieldABox(String fieldABox) {
		this.fieldABox = fieldABox;
	}

	public String getFieldBBox() {
		return fieldBBox;
	}

	public void setFieldBBox(String fieldBBox) {
		this.fieldBBox = fieldBBox;
	}

	public String getOutputBox() {
		return outputBox;
	}

	public void setOutputBox(String outputBox) {
		this.outputBox = outputBox;
	}

	public Boolean getFieldAChk() {
		return fieldAChk;
	}

	public void setFieldAChk(Boolean fieldAChk) {
		this.fieldAChk = fieldAChk;
	}

	public Boolean getFieldBChk() {
		return fieldBChk;
	}

	public void setFieldBChk(Boolean fieldBChk) {
		this.fieldBChk = fieldBChk;
	}

	public Boolean getOverride() {
		return override;
	}

	public void setOverride(Boolean override) {
		this.override = override;
	}

	public Boolean getCase_sensitive() {
		return case_sensitive;
	}

	public void setCase_sensitive(Boolean case_sensitive) {
		this.case_sensitive = case_sensitive;
	}

	public String getDetailMatchType() {
		return detailMatchType;
	}

	public void setDetailMatchType(String detailMatchType) {
		this.detailMatchType = detailMatchType;
	}

	public String getDetailExpressionValue() {
		return detailExpressionValue;
	}

	public void setDetailExpressionValue(String detailExpressionValue) {
		this.detailExpressionValue = detailExpressionValue;
	}

	public String getDetailField() {
		return detailField;
	}

	public void setDetailField(String detailField) {
		this.detailField = detailField;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	
	public String[] getPermissions() {
		
		List<String> permissions = new ArrayList<String>();
		if ( getCase_sensitive() ) permissions.add("CASE_SENSITIVE");
		if ( getFieldAChk() ) permissions.add("FIELD_A_REQUIRED");
		if ( getFieldBChk() ) permissions.add("FIELD_B_REQUIRED");
		if ( getOverride() ) permissions.add("OVERRIDE_OUTPUT_FIELD");
		
		return permissions.toArray(new String[] {} );
	}
	
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_NAME,  getFilterName() );
		settings.addString(CFG_TYPE,  getFilterType() );
		settings.addString(CFG_MATCHTYPE,  getDetailMatchType() );
		settings.addString(CFG_EXPRESSION,  getDetailExpressionValue() );
		settings.addString(CFG_FIELD,  getDetailField() );
		settings.addString(CFG_SEARCH,  getSearchString() ); 
		settings.addString(CFG_FIELD_A_TEXT, getFieldAText()); 
		settings.addString(CFG_FIELD_B_TEXT, getFieldBText() ); 
		settings.addString(CFG_OUTPUT_TEXT, getOutputText() ); 
		settings.addString(CFG_REPLACE, getReplaceString() ); 
		settings.addString(CFG_FIELD_A_BOX, getFieldABox() ); 
		settings.addString(CFG_FIELD_B_BOX, getFieldBBox() ); 
		settings.addString(CFG_OUTPUT_BOX, getOutputBox() ); 
		settings.addBoolean(CFG_AGREED, getCase_sensitive());
		settings.addBoolean(CFG_FIELDACHK, getFieldAChk());
		settings.addBoolean(CFG_FIELDBCHK, getFieldBChk());
		settings.addBoolean(CFG_OVERRIDE, getOverride() );
	
		
	}
	
	public void load ( NodeSettingsRO settings ) {
		setFilterName(settings.getString(CFG_NAME, ""));
		setFilterType(settings.getString(CFG_TYPE, ""));
		setDetailMatchType(settings.getString(CFG_MATCHTYPE, ""));
		setDetailExpressionValue(settings.getString(CFG_EXPRESSION, ""));
		setDetailField(settings.getString(CFG_FIELD, ""));
		setSearchString(settings.getString(CFG_SEARCH, ""));
		setFieldAText(settings.getString(CFG_FIELD_A_TEXT, ""));
		setFieldBText(settings.getString(CFG_FIELD_B_TEXT, ""));
		setOutputText(settings.getString(CFG_OUTPUT_TEXT, ""));
		setReplaceString(settings.getString(CFG_REPLACE, ""));
		setFieldABox(settings.getString(CFG_FIELD_A_BOX, ""));
		setFieldBBox(settings.getString(CFG_FIELD_B_BOX, ""));
		setOutputBox(settings.getString(CFG_OUTPUT_BOX, ""));
		setCase_sensitive(settings.getBoolean(CFG_AGREED, false));
		setFieldAChk(settings.getBoolean(CFG_FIELDACHK, false));
		setFieldBChk(settings.getBoolean(CFG_FIELDBCHK, false));
		setOverride(settings.getBoolean(CFG_OVERRIDE, false));			
	}

}

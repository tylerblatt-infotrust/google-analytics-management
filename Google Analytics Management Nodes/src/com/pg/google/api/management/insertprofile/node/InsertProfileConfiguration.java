package com.pg.google.api.management.insertprofile.node;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class InsertProfileConfiguration {

	private String profileName = "";
	private String timezone = "";
	private String websiteUrl = "";
	private String defaultPage = "";
	private String excludeQueryParameters = "";
	private String siteSearchQueryParameters = "";
	private String siteSearchCategoryParameters = "";
	private String type = ""; 
	private String currency = ""; 
	private String webPropertyId = ""; 
	
	private Boolean stripSiteSearchQueryParameters = false;
	private Boolean stripSiteSearchCategoryParameters = false;
	private Boolean enableEcom = false;
	private Boolean enableEnhancedEcom = false;
	
	
	
	private static final String CFG_PROFILE_NAME = "cfg.profilename";
	private static final String CFG_WEB_PROPERTY = "cfg.webproperty";
	private static final String CFG_ENHANCED_ECOM = "cfg.ehancedecom";
	private static final String CFG_ECOM = "cfg.ecom";
	private static final String CFG_TIME_ZONE = "cfg.time.zone";
	private static final String CFG_WEBSITE_URL = "cfg.website.url";
	private static final String CFG_DEFAULT_PAGE = "cfg.default.page";
	private static final String CFG_EXCLUDE_QUERY_PARAMETERS = "cfg.exclude.query.parameters";
	private static final String CFG_SITE_SEARCH_QUERY_PARAMETERS = "cfg.site.search.query.parameters";
	private static final String CFG_SITE_SEARCH_CATEGORY_PARAMETERS = "cfg.site.search.category.parameters";
	private static final String CFG_STRIP_SITE_SEARCH_QUERY_PARAMETERS = "cfg.strip.site.search.query.parameters";
	private static final String CFG_STRIP_SITE_SEARCH_CATEGORY_PARAMETERS = "cfg.strip.site.search.category.parameters";
	private static final String CFG_TYPE = "cfg.type";
	private static final String CFG_CURRENCY = "cfg.currency";

	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_PROFILE_NAME, getProfileName());
		settings.addString(CFG_WEB_PROPERTY, getWebPropertyId());
		settings.addBoolean(CFG_ECOM, getEnableEcom());
		settings.addBoolean(CFG_ENHANCED_ECOM, getEnableEnhancedEcom());
		settings.addBoolean(CFG_STRIP_SITE_SEARCH_CATEGORY_PARAMETERS, getStripSiteSearchCategoryParameters());
		settings.addBoolean(CFG_STRIP_SITE_SEARCH_QUERY_PARAMETERS, getStripSiteSearchQueryParameters());
		settings.addString(CFG_TIME_ZONE, getTimezone());
		settings.addString(CFG_WEBSITE_URL, getWebsiteUrl());
		settings.addString(CFG_DEFAULT_PAGE, getDefaultPage());
		settings.addString(CFG_EXCLUDE_QUERY_PARAMETERS, getExcludeQueryParameters());
		settings.addString(CFG_SITE_SEARCH_QUERY_PARAMETERS, getSiteSearchQueryParameters());
		settings.addString(CFG_SITE_SEARCH_CATEGORY_PARAMETERS, getSiteSearchCategoryParameters());
		settings.addString(CFG_TYPE, getType());
		settings.addString(CFG_CURRENCY, getCurrency());
	}
	
	public void load ( NodeSettingsRO settings ) {
		setProfileName(settings.getString(CFG_PROFILE_NAME, ""));
		setWebPropertyId(settings.getString(CFG_WEB_PROPERTY, ""));
		setEnableEcom(settings.getBoolean(CFG_ENHANCED_ECOM, false));
		setEnableEnhancedEcom(settings.getBoolean(CFG_ECOM, false));
		setStripSiteSearchCategoryParameters(settings.getBoolean(CFG_STRIP_SITE_SEARCH_CATEGORY_PARAMETERS, false));
		setStripSiteSearchQueryParameters(settings.getBoolean(CFG_STRIP_SITE_SEARCH_QUERY_PARAMETERS, false));
		setTimezone(settings.getString(CFG_TIME_ZONE, ""));
		setWebsiteUrl(settings.getString(CFG_WEBSITE_URL, ""));
		setDefaultPage(settings.getString(CFG_DEFAULT_PAGE, ""));
		setExcludeQueryParameters(settings.getString(CFG_EXCLUDE_QUERY_PARAMETERS, ""));
		setSiteSearchQueryParameters(settings.getString(CFG_SITE_SEARCH_QUERY_PARAMETERS, ""));
		setSiteSearchCategoryParameters(settings.getString(CFG_SITE_SEARCH_CATEGORY_PARAMETERS, ""));
		setType(settings.getString(CFG_TYPE, ""));
		setCurrency(settings.getString(CFG_CURRENCY, ""));
	}
	
	
	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getDefaultPage() {
		return defaultPage;
	}

	public void setDefaultPage(String defaultPage) {
		this.defaultPage = defaultPage;
	}

	public String getExcludeQueryParameters() {
		return excludeQueryParameters;
	}

	public void setExcludeQueryParameters(String excludeQueryParameters) {
		this.excludeQueryParameters = excludeQueryParameters;
	}

	public String getSiteSearchQueryParameters() {
		return siteSearchQueryParameters;
	}

	public void setSiteSearchQueryParameters(String siteSearchQueryParameters) {
		this.siteSearchQueryParameters = siteSearchQueryParameters;
	}

	public String getSiteSearchCategoryParameters() {
		return siteSearchCategoryParameters;
	}

	public void setSiteSearchCategoryParameters(String siteSearchCategoryParameters) {
		this.siteSearchCategoryParameters = siteSearchCategoryParameters;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Boolean getStripSiteSearchQueryParameters() {
		return stripSiteSearchQueryParameters;
	}

	public void setStripSiteSearchQueryParameters(
			Boolean stripSiteSearchQueryParameters) {
		this.stripSiteSearchQueryParameters = stripSiteSearchQueryParameters;
	}

	public Boolean getStripSiteSearchCategoryParameters() {
		return stripSiteSearchCategoryParameters;
	}

	public void setStripSiteSearchCategoryParameters(
			Boolean stripSiteSearchCategoryParameters) {
		this.stripSiteSearchCategoryParameters = stripSiteSearchCategoryParameters;
	}

	public Boolean getEnableEnhancedEcom() {
		return enableEnhancedEcom;
	}

	public void setEnableEnhancedEcom(Boolean enableEnhancedEcom) {
		this.enableEnhancedEcom = enableEnhancedEcom;
	}

	public Boolean getEnableEcom() {
		return enableEcom;
	}

	public void setEnableEcom(Boolean enableEcom) {
		this.enableEcom = enableEcom;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getWebPropertyId() {
		return webPropertyId;
	}

	public void setWebPropertyId(String webPropertyId) {
		this.webPropertyId = webPropertyId;
	}
}

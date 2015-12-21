package com.pg.google.api.management.updateprofile.node;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class GoogleAnalyticsUpdateProfileConfig {

	private String profile_id = "";
	private String profile_kind = "";
	private String profile_selfLink = "";
	private String profile_accountId = "";
	private String profile_webPropertyId = "";
	private String profile_internalWebPropertyId = "";
	private String profile_name = "";
	private String profile_currency = "";
	private String profile_timezone = "";
	private String profile_websiteUrl = "";
	private String profile_defaultPage = "";
	private String profile_excludeQueryParameters = "";
	private String profile_siteSearchQueryParameters = "";
	private String profile_type = "";
	private String profile_siteSearchCategoryParameters = "";
	private String profile_permissions_effective = "";
	private String parent_link_type = "";
	private String parent_link_href = "";
	private String child_link_type = "";
	private String child_link_href = "";
	
	
	private Boolean profile_stripSiteSearchQueryParameters = false;
	private Boolean profile_stripSiteSearchCategoryParameters = false;
	private Boolean profileEcommerceTracking = false;
	private Boolean profileEnhancedEcommerceTracking = false;
	
	private final static String  CFG_ID = "cfg.id.id";
	private final static String  CFG_KIND = "cfg.kind.id";
	private final static String  CFG_SELFLINK = "cfg.selflink.id";
	private final static String  CFG_ACCOUNTID = "cfg.accountid.id";
	private final static String  CFG_WEBPROPERTYID = "cfg.webpropertyid.id";
	private final static String  CFG_INTERNALWEBPROPERTYID = "cfg.internalwebpropertyid.id";
	private final static String  CFG_NAME = "cfg.name.id";
	private final static String  CFG_CURRENCY = "cfg.currency.id";
	private final static String  CFG_TIMEZONE = "cfg.timezone.id";
	private final static String  CFG_WEBSITEURL = "cfg.websiteurl.id";
	private final static String  CFG_DEFAULTPAGE = "cfg.defaultpage.id";
	private final static String  CFG_EXCLUDEQUERYPARAMETERS = "cfg.excludequeryparameters.id";
	private final static String  CFG_SITESEARCHQUERYPARAMETERS = "cfg.sitesearchqueryparameters.id";
	private final static String  CFG_TYPE = "cfg.type.id";
	private final static String  CFG_SITESEARCHCATEGORYPARAMETERS = "cfg.sitesearchcategoryparameters.id";
	private final static String  CFG_PERMISSIONS_EFFECTIVE = "cfg.permissions.effective.id";
	private final static String  CFG_PARENT_LINK_TYPE = "cfg.parent.link.type.id";
	private final static String  CFG_PARENT_LINK_HREF = "cfg.parent.link.href.id";
	private final static String  CFG_CHILD_LINK_TYPE = "cfg.child.link.type.id";
	private final static String  CFG_CHILD_LINK_HREF = "cfg.child.link.href.id";
	private final static String  CFG_PROFILE_STRIPSITESEARCHQUERYPARAMETERS = "cfg.profile.stripsitesearchqueryparameters.id";
	private final static String  CFG_PROFILE_STRIPSITESEARCHCATEGORYPARAMETERS = "cfg.profile.stripsitesearchcategoryparameters.id";
	private final static String  CFG_PROFILEECOMMERCETRACKING = "cfg.profileecommercetracking.id";
	private final static String  CFG_PROFILEENHANCEDECOMMERCETRACKING = "cfg.profileenhancedecommercetracking.id";
	

public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_ID, getProfile_id());
		settings.addString(CFG_KIND, getProfile_kind());
		settings.addString(CFG_SELFLINK, getProfile_selfLink());
		settings.addString(CFG_ACCOUNTID, getProfile_accountId());
		settings.addString(CFG_WEBPROPERTYID, getProfile_webPropertyId());
		settings.addString(CFG_INTERNALWEBPROPERTYID, getProfile_internalWebPropertyId());
		settings.addString(CFG_NAME, getProfile_name());
		settings.addString(CFG_CURRENCY, getProfile_currency());
		settings.addString(CFG_TIMEZONE, getProfile_timezone());
		settings.addString(CFG_WEBSITEURL, getProfile_websiteUrl());
		settings.addString(CFG_DEFAULTPAGE, getProfile_defaultPage());
		settings.addString(CFG_EXCLUDEQUERYPARAMETERS, getProfile_excludeQueryParameters());
		settings.addString(CFG_SITESEARCHQUERYPARAMETERS, getProfile_siteSearchQueryParameters());
		settings.addString(CFG_TYPE, getProfile_type());
		settings.addString(CFG_SITESEARCHCATEGORYPARAMETERS, getProfile_siteSearchCategoryParameters());
		settings.addString(CFG_PERMISSIONS_EFFECTIVE, getProfile_permissions_effective());
		settings.addString(CFG_PARENT_LINK_TYPE, getParent_link_type());
		settings.addString(CFG_PARENT_LINK_HREF, getParent_link_href());
		settings.addString(CFG_CHILD_LINK_TYPE, getChild_link_type());
		settings.addString(CFG_CHILD_LINK_HREF, getChild_link_href());
		settings.addBoolean(CFG_PROFILE_STRIPSITESEARCHQUERYPARAMETERS, getProfile_stripSiteSearchQueryParameters()); 
		settings.addBoolean(CFG_PROFILE_STRIPSITESEARCHCATEGORYPARAMETERS, getProfile_stripSiteSearchCategoryParameters()); 
		settings.addBoolean(CFG_PROFILEECOMMERCETRACKING, getProfileEcommerceTracking()); 
		settings.addBoolean(CFG_PROFILEENHANCEDECOMMERCETRACKING, getProfileEnhancedEcommerceTracking());
	}

public void load ( NodeSettingsRO settings ) {
	setProfile_id(settings.getString(CFG_ID, ""));
	setProfile_kind(settings.getString(CFG_KIND, ""));
	setProfile_selfLink(settings.getString(CFG_SELFLINK, ""));
	setProfile_accountId(settings.getString(CFG_ACCOUNTID, ""));
	setProfile_webPropertyId(settings.getString(CFG_WEBPROPERTYID, ""));
	setProfile_internalWebPropertyId(settings.getString(CFG_INTERNALWEBPROPERTYID, ""));
	setProfile_name(settings.getString(CFG_NAME, ""));
	setProfile_currency(settings.getString(CFG_CURRENCY, ""));
	setProfile_timezone(settings.getString(CFG_TIMEZONE, ""));
	setProfile_websiteUrl(settings.getString(CFG_WEBSITEURL, ""));
	setProfile_defaultPage(settings.getString(CFG_DEFAULTPAGE, ""));
	setProfile_excludeQueryParameters(settings.getString(CFG_EXCLUDEQUERYPARAMETERS, ""));
	setProfile_siteSearchQueryParameters(settings.getString(CFG_SITESEARCHQUERYPARAMETERS, ""));
	setProfile_type(settings.getString(CFG_TYPE, ""));
	setProfile_siteSearchCategoryParameters(settings.getString(CFG_SITESEARCHCATEGORYPARAMETERS, ""));
	setProfile_permissions_effective(settings.getString(CFG_PERMISSIONS_EFFECTIVE, ""));
	setParent_link_type(settings.getString(CFG_PARENT_LINK_TYPE, ""));
	setParent_link_href(settings.getString(CFG_PARENT_LINK_HREF, ""));
	setChild_link_type(settings.getString(CFG_CHILD_LINK_TYPE, ""));
	setChild_link_href(settings.getString(CFG_CHILD_LINK_HREF, ""));
	setProfile_stripSiteSearchQueryParameters(settings.getBoolean(CFG_PROFILE_STRIPSITESEARCHQUERYPARAMETERS , false));
	setProfile_stripSiteSearchCategoryParameters(settings.getBoolean(CFG_PROFILE_STRIPSITESEARCHCATEGORYPARAMETERS , false));
	setProfileEcommerceTracking(settings.getBoolean(CFG_PROFILEECOMMERCETRACKING , false));
	setProfileEnhancedEcommerceTracking(settings.getBoolean(CFG_PROFILEENHANCEDECOMMERCETRACKING , false));
}
	
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}
	public String getProfile_kind() {
		return profile_kind;
	}
	public void setProfile_kind(String profile_kind) {
		this.profile_kind = profile_kind;
	}
	public String getProfile_selfLink() {
		return profile_selfLink;
	}
	public void setProfile_selfLink(String profile_selfLink) {
		this.profile_selfLink = profile_selfLink;
	}
	public String getProfile_accountId() {
		return profile_accountId;
	}
	public void setProfile_accountId(String profile_accountId) {
		this.profile_accountId = profile_accountId;
	}
	public String getProfile_webPropertyId() {
		return profile_webPropertyId;
	}
	public void setProfile_webPropertyId(String profile_webPropertyId) {
		this.profile_webPropertyId = profile_webPropertyId;
	}
	public String getProfile_internalWebPropertyId() {
		return profile_internalWebPropertyId;
	}
	public void setProfile_internalWebPropertyId(
			String profile_internalWebPropertyId) {
		this.profile_internalWebPropertyId = profile_internalWebPropertyId;
	}
	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	public String getProfile_currency() {
		return profile_currency;
	}
	public void setProfile_currency(String profile_currency) {
		this.profile_currency = profile_currency;
	}
	public String getProfile_timezone() {
		return profile_timezone;
	}
	public void setProfile_timezone(String profile_timezone) {
		this.profile_timezone = profile_timezone;
	}
	public String getProfile_websiteUrl() {
		return profile_websiteUrl;
	}
	public void setProfile_websiteUrl(String profile_websiteUrl) {
		this.profile_websiteUrl = profile_websiteUrl;
	}
	public String getProfile_defaultPage() {
		return profile_defaultPage;
	}
	public void setProfile_defaultPage(String profile_defaultPage) {
		this.profile_defaultPage = profile_defaultPage;
	}
	public String getProfile_excludeQueryParameters() {
		return profile_excludeQueryParameters;
	}
	public void setProfile_excludeQueryParameters(
			String profile_excludeQueryParameters) {
		this.profile_excludeQueryParameters = profile_excludeQueryParameters;
	}
	public String getProfile_siteSearchQueryParameters() {
		return profile_siteSearchQueryParameters;
	}
	public void setProfile_siteSearchQueryParameters(
			String profile_siteSearchQueryParameters) {
		this.profile_siteSearchQueryParameters = profile_siteSearchQueryParameters;
	}
	public String getProfile_type() {
		return profile_type;
	}
	public void setProfile_type(String profile_type) {
		this.profile_type = profile_type;
	}
	public String getProfile_siteSearchCategoryParameters() {
		return profile_siteSearchCategoryParameters;
	}
	public void setProfile_siteSearchCategoryParameters(
			String profile_siteSearchCategoryParameters) {
		this.profile_siteSearchCategoryParameters = profile_siteSearchCategoryParameters;
	}
	public String getProfile_permissions_effective() {
		return profile_permissions_effective;
	}
	public void setProfile_permissions_effective(
			String profile_permissions_effective) {
		this.profile_permissions_effective = profile_permissions_effective;
	}
	public String getParent_link_type() {
		return parent_link_type;
	}
	public void setParent_link_type(String parent_link_type) {
		this.parent_link_type = parent_link_type;
	}
	public String getParent_link_href() {
		return parent_link_href;
	}
	public void setParent_link_href(String parent_link_href) {
		this.parent_link_href = parent_link_href;
	}
	public String getChild_link_type() {
		return child_link_type;
	}
	public void setChild_link_type(String child_link_type) {
		this.child_link_type = child_link_type;
	}
	public String getChild_link_href() {
		return child_link_href;
	}
	public void setChild_link_href(String child_link_href) {
		this.child_link_href = child_link_href;
	}
	public Boolean getProfile_stripSiteSearchQueryParameters() {
		return profile_stripSiteSearchQueryParameters;
	}
	public void setProfile_stripSiteSearchQueryParameters(
			Boolean profile_stripSiteSearchQueryParameters) {
		this.profile_stripSiteSearchQueryParameters = profile_stripSiteSearchQueryParameters;
	}
	public Boolean getProfile_stripSiteSearchCategoryParameters() {
		return profile_stripSiteSearchCategoryParameters;
	}
	public void setProfile_stripSiteSearchCategoryParameters(
			Boolean profile_stripSiteSearchCategoryParameters) {
		this.profile_stripSiteSearchCategoryParameters = profile_stripSiteSearchCategoryParameters;
	}
	public Boolean getProfileEcommerceTracking() {
		return profileEcommerceTracking;
	}
	public void setProfileEcommerceTracking(Boolean profileEcommerceTracking) {
		this.profileEcommerceTracking = profileEcommerceTracking;
	}
	public Boolean getProfileEnhancedEcommerceTracking() {
		return profileEnhancedEcommerceTracking;
	}
	public void setProfileEnhancedEcommerceTracking(
			Boolean profileEnhancedEcommerceTracking) {
		this.profileEnhancedEcommerceTracking = profileEnhancedEcommerceTracking;
	}
	
	
	
	
	
	
}

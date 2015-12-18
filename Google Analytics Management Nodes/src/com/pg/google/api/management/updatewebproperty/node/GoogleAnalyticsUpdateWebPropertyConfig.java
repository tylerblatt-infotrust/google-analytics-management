package com.pg.google.api.management.updatewebproperty.node;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class GoogleAnalyticsUpdateWebPropertyConfig {

	private String wpId = "";
	private String wpKind = "";
	private String wpSelfLink = "";
	private String wpAccountId = "";
	private String wpInternalWebPropertyId = "";
	private String wpName = "";
	private String wpWebsiteUrl = "";
	private String wpLevel = "";
	private int wpProfileCount = 0;
	private String wpIndustryVertical = "";
	private long wpDefaultProfileId = 0;
	private String wpPermissionsEffective = "";
	private String parentLinkType = "";
	private String parentLinkHref = "";
	private String childLinkType = "";
	private String childLinkHref = "";
	
	private final static String  CFG_WP_ID = "cfg.wp.id";
	private final static String  CFG_WP_KIND = "cfg.wp.kind";
	private final static String  CFG_WP_SELF_LINK = "cfg.wp.self.link";
	private final static String  CFG_WP_ACCOUNT_ID = "cfg.wp.account.id";
	private final static String  CFG_WP_INTERNAL_WEB_PROPERTY_ID = "cfg.wp.internal.web.property.id";
	private final static String  CFG_WP_NAME = "cfg.wp.name";
	private final static String  CFG_WP_WEBSITE_URL = "cfg.wp.website.url";
	private final static String  CFG_WP_LEVEL = "cfg.wp.level";
	private final static String  CFG_WP_PROFILE_COUNT = "cfg.wp.profile.count";
	private final static String  CFG_WP_INDUSTRY_VERTICAL = "cfg.wp.industry.vertical";
	private final static String  CFG_WP_DEFAULT_PROFILE_ID = "cfg.wp.default.profile.id";
	private final static String  CFG_WP_PERMISSIONS_EFFECTIVE = "cfg.wp.permissions.effective";
	private final static String  CFG_PARENT_LINK_TYPE = "cfg.parent.link.type";
	private final static String  CFG_PARENT_LINK_HREF = "cfg.parent.link.href";
	private final static String  CFG_CHILD_LINK_TYPE = "cfg.child.link.type";
	private final static String  CFG_CHILD_LINK_HREF = "cfg.child.link.href";
	
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_WP_ID, getWpId());
		settings.addString(CFG_WP_KIND, getWpKind());
		settings.addString(CFG_WP_SELF_LINK, getWpSelfLink());
		settings.addString(CFG_WP_ACCOUNT_ID, getWpAccountId());
		settings.addString(CFG_WP_INTERNAL_WEB_PROPERTY_ID, getWpInternalWebPropertyId());
		settings.addString(CFG_WP_NAME, getWpName());
		settings.addString(CFG_WP_WEBSITE_URL, getWpWebsiteUrl());
		settings.addString(CFG_WP_LEVEL, getWpLevel());
		settings.addInt(CFG_WP_PROFILE_COUNT, getWpProfileCount());
		settings.addString(CFG_WP_INDUSTRY_VERTICAL, getWpIndustryVertical());
		settings.addLong(CFG_WP_DEFAULT_PROFILE_ID, getWpDefaultProfileId());
		settings.addString(CFG_WP_PERMISSIONS_EFFECTIVE, getWpPermissionsEffective());
		settings.addString(CFG_PARENT_LINK_TYPE, getParentLinkType());
		settings.addString(CFG_PARENT_LINK_HREF, getParentLinkHref());
		settings.addString(CFG_CHILD_LINK_TYPE, getChildLinkType());
		settings.addString(CFG_CHILD_LINK_HREF, getChildLinkHref());
	

	}
	
	public void load ( NodeSettingsRO settings ) {
		setWpId(settings.getString(CFG_WP_ID, ""));
		setWpKind(settings.getString(CFG_WP_KIND, ""));
		setWpSelfLink(settings.getString(CFG_WP_SELF_LINK, ""));
		setWpAccountId(settings.getString(CFG_WP_ACCOUNT_ID, ""));
		setWpInternalWebPropertyId(settings.getString(CFG_WP_INTERNAL_WEB_PROPERTY_ID, ""));
		setWpName(settings.getString(CFG_WP_NAME, ""));
		setWpWebsiteUrl(settings.getString(CFG_WP_WEBSITE_URL, ""));
		setWpLevel(settings.getString(CFG_WP_LEVEL, ""));
		setWpProfileCount(settings.getInt(CFG_WP_PROFILE_COUNT, 0));
		setWpIndustryVertical(settings.getString(CFG_WP_INDUSTRY_VERTICAL, ""));
		setWpDefaultProfileId(settings.getLong(CFG_WP_DEFAULT_PROFILE_ID, 0));
		setWpPermissionsEffective(settings.getString(CFG_WP_PERMISSIONS_EFFECTIVE, ""));
		setParentLinkType(settings.getString(CFG_PARENT_LINK_TYPE, ""));
		setParentLinkHref(settings.getString(CFG_PARENT_LINK_HREF, ""));
		setChildLinkType(settings.getString(CFG_CHILD_LINK_TYPE, ""));
		setChildLinkHref(settings.getString(CFG_CHILD_LINK_HREF, ""));
	}

	
	public String getWpId() {
		return wpId;
	}
	public void setWpId(String wpId) {
		this.wpId = wpId;
	}
	public String getWpKind() {
		return wpKind;
	}
	public void setWpKind(String wpKind) {
		this.wpKind = wpKind;
	}
	public String getWpSelfLink() {
		return wpSelfLink;
	}
	public void setWpSelfLink(String wpSelfLink) {
		this.wpSelfLink = wpSelfLink;
	}
	public String getWpAccountId() {
		return wpAccountId;
	}
	public void setWpAccountId(String wpAccountId) {
		this.wpAccountId = wpAccountId;
	}
	public String getWpInternalWebPropertyId() {
		return wpInternalWebPropertyId;
	}
	public void setWpInternalWebPropertyId(String wpInternalWebPropertyId) {
		this.wpInternalWebPropertyId = wpInternalWebPropertyId;
	}
	public String getWpName() {
		return wpName;
	}
	public void setWpName(String wpName) {
		this.wpName = wpName;
	}
	public String getWpWebsiteUrl() {
		return wpWebsiteUrl;
	}
	public void setWpWebsiteUrl(String wpWebsiteUrl) {
		this.wpWebsiteUrl = wpWebsiteUrl;
	}
	public String getWpLevel() {
		return wpLevel;
	}
	public void setWpLevel(String wpLevel) {
		this.wpLevel = wpLevel;
	}
	public int getWpProfileCount() {
		return wpProfileCount;
	}
	public void setWpProfileCount(int wpProfileCount) {
		this.wpProfileCount = wpProfileCount;
	}
	public String getWpIndustryVertical() {
		return wpIndustryVertical;
	}
	public void setWpIndustryVertical(String wpIndustryVertical) {
		this.wpIndustryVertical = wpIndustryVertical;
	}
	public long getWpDefaultProfileId() {
		return wpDefaultProfileId;
	}
	public void setWpDefaultProfileId(long wpDefaultProfileId) {
		this.wpDefaultProfileId = wpDefaultProfileId;
	}
	public String getWpPermissionsEffective() {
		return wpPermissionsEffective;
	}
	public void setWpPermissionsEffective(String wpPermissionsEffective) {
		this.wpPermissionsEffective = wpPermissionsEffective;
	}
	public String getParentLinkType() {
		return parentLinkType;
	}
	public void setParentLinkType(String parentLinkType) {
		this.parentLinkType = parentLinkType;
	}
	public String getParentLinkHref() {
		return parentLinkHref;
	}
	public void setParentLinkHref(String parentLinkHref) {
		this.parentLinkHref = parentLinkHref;
	}
	public String getChildLinkType() {
		return childLinkType;
	}
	public void setChildLinkType(String childLinkType) {
		this.childLinkType = childLinkType;
	}
	public String getChildLinkHref() {
		return childLinkHref;
	}
	public void setChildLinkHref(String childLinkHref) {
		this.childLinkHref = childLinkHref;
	}

	
	
}

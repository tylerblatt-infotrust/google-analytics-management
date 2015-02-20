package com.pg.google.api.management.updatepermissions.node;

import java.util.ArrayList;
import java.util.List;

import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

public class GoogleAnalyticsUpdatePermissionsConfiguration {

	private String level = "";
	private String userId = "";
	private String operation = "";
	private Boolean manageUsers = false, edit = false, collaborate = false, read = false;
	
	private final static String CFG_LEVEL = "cfg.level";
	private final static String CFG_USER_ID = "cfg.user.id";
	private final static String CFG_OPERATION = "cfg.operation";
	private final static String CFG_MANAGE_USERS = "cfg.manage.users";
	private final static String CFG_EDIT = "cfg.edit";
	private final static String CFG_COLLABORATE = "cfg.collaborate";
	private final static String CFG_READ = "cfg.read";
	
	public void save ( NodeSettingsWO settings ) {
		settings.addString(CFG_LEVEL, getLevel());
		settings.addString(CFG_USER_ID, getUserId());
		settings.addString(CFG_OPERATION, getOperation());
		
		settings.addBoolean(CFG_MANAGE_USERS, getManageUsers() );
		settings.addBoolean(CFG_EDIT, getEdit());
		settings.addBoolean(CFG_COLLABORATE, getCollaborate());
		settings.addBoolean(CFG_READ, getRead());
	}
	
	public void load ( NodeSettingsRO settings ) {
		setLevel(settings.getString(CFG_LEVEL, ""));
		setUserId(settings.getString(CFG_USER_ID, ""));
		setOperation(settings.getString(CFG_OPERATION, ""));
		
		setManageUsers(settings.getBoolean(CFG_MANAGE_USERS, false));
		setEdit(settings.getBoolean(CFG_EDIT, false));
		setCollaborate(settings.getBoolean(CFG_COLLABORATE, false));
		setRead(settings.getBoolean(CFG_READ, false));
	}
	
	public String[] getPermissions() {
		
		List<String> permissions = new ArrayList<String>();
		if ( getManageUsers() ) permissions.add("MANAGE_USERS");
		if ( getEdit() ) permissions.add("EDIT");
		if ( getCollaborate() ) permissions.add("COLLABORATE");
		if ( getRead() ) permissions.add("READ_AND_ANALYZE");
		
		return permissions.toArray(new String[] {} );
	}
	
	public boolean isAddOperation() {
		return GoogleAnalyticsUpdatePermissionsNodeDialog.ADD_OP.equals(getOperation());
	}
	
	public boolean isRemoveOperation() {
		return GoogleAnalyticsUpdatePermissionsNodeDialog.REMOVE_OP.equals(getOperation());
	}
	
	public boolean isSetTo() {
		return GoogleAnalyticsUpdatePermissionsNodeDialog.SET_TO_OP.equals(getOperation());
	}
	
	public boolean isProfileLevel() {
		return "Profile Level".equals(getLevel());
	}
	public boolean isAccountLevel() {
		return "Account Level".equals(getLevel());
	}
	public boolean isPropertyLevel() {
		return "Property Level".equals(getLevel());
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Boolean getManageUsers() {
		return manageUsers;
	}
	public void setManageUsers(Boolean manageUsers) {
		this.manageUsers = manageUsers;
	}
	public Boolean getEdit() {
		return edit;
	}
	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	public Boolean getCollaborate() {
		return collaborate;
	}
	public void setCollaborate(Boolean collaborate) {
		this.collaborate = collaborate;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
}

package com.pg.google.api.management.updatepermissions.node;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.lang.StringUtils;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.pg.knime.node.StandardTrackedNodeDialogPane;

/**
 * <code>NodeDialog</code> for the "GoogleAnalyticsUpdatePermissions" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsUpdatePermissionsNodeDialog extends StandardTrackedNodeDialogPane {

	private DefaultComboBoxModel<String> cbmLevels = new DefaultComboBoxModel<String>(new String[] { "Account Level", "Property Level", "Profile Level"} );
	private JTextArea txtUserId = new JTextArea();
	private JComboBox<String> cbxPermissionOperations = new JComboBox<String>(new String[]{ADD_OP, REMOVE_OP, SET_TO_OP});
	private JCheckBox chkManageUsers = 	new JCheckBox("Manage Users");
	private JCheckBox chkEdit = 		new JCheckBox("Edit");
	private JCheckBox chkCollaborate = 	new JCheckBox("Collaborate");
	private JCheckBox chkRead = 		new JCheckBox("Read & Analyze");
	
	private GoogleAnalyticsUpdatePermissionsConfiguration configuration = new GoogleAnalyticsUpdatePermissionsConfiguration();
	
	public static final String ADD_OP = "Add", REMOVE_OP = "Remove", SET_TO_OP = "Set To";
	
	/*
     * New pane for configuring the GoogleAnalyticsUpdatePermissions node.
     */
    protected GoogleAnalyticsUpdatePermissionsNodeDialog() {
    	
    	JPanel pnlCheckboxes = new JPanel();
    	pnlCheckboxes.add(chkManageUsers);
    	pnlCheckboxes.add(chkEdit);
    	pnlCheckboxes.add(chkCollaborate);
    	pnlCheckboxes.add(chkRead);
    	
    	addTab ( 
    		"Settings",
    		buildStandardPanel(
    			new PanelBuilder()
    				.add("Level", new JComboBox<String>(cbmLevels) )
    				.add("User Id", txtUserId )
    				.add("Permissions Operation", cbxPermissionOperations )
    				.add("Permissions", pnlCheckboxes )
    				.build()
    		)
    	);
    	
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {

		configuration = new GoogleAnalyticsUpdatePermissionsConfiguration();
		configuration.setUserId(txtUserId.getText());
		configuration.setOperation((String)cbxPermissionOperations.getSelectedItem());
		
		configuration.setManageUsers(chkManageUsers.isSelected());
		configuration.setCollaborate(chkCollaborate.isSelected());
		configuration.setEdit(chkEdit.isSelected());
		configuration.setRead(chkRead.isSelected());
		
		configuration.save(settings);
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {

		configuration = new GoogleAnalyticsUpdatePermissionsConfiguration();
		configuration.load(settings);
		
		txtUserId.setText(configuration.getUserId());
		
		if ( !StringUtils.isEmpty(configuration.getOperation()) )
			cbxPermissionOperations.setSelectedItem(configuration.getOperation());
		
		chkManageUsers.setSelected(configuration.getManageUsers());
		chkEdit.setSelected(configuration.getEdit());
		chkCollaborate.setSelected(configuration.getCollaborate());
		chkRead.setSelected(configuration.getRead());
		
	}
}


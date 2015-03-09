package com.pg.google.api.management.addusertoprofile;

import javax.swing.JTextField;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.pg.knime.node.StandardTrackedNodeDialogPane;

/**
 * <code>NodeDialog</code> for the "GoogleAnalyticsAddUserToProfile" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsAddUserToProfileNodeDialog extends StandardTrackedNodeDialogPane {

	private GoogleAnalyticsAddUserToProfileConfiguration configuration = new GoogleAnalyticsAddUserToProfileConfiguration();
	private JTextField txtEmailAddress = new JTextField();
	
    /**
     * New pane for configuring the GoogleAnalyticsAddUserToProfile node.
     */
    protected GoogleAnalyticsAddUserToProfileNodeDialog() {

    	addTab(
    		"Settings",
    		buildStandardPanel(
    			new PanelBuilder()
    				.add("Email Address", txtEmailAddress)
    				.build()
    		)
    	);
    	
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {
		
		configuration = new GoogleAnalyticsAddUserToProfileConfiguration();
		configuration.setEmailAddress(txtEmailAddress.getText());
		configuration.save(settings);
		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {
		
		configuration = new GoogleAnalyticsAddUserToProfileConfiguration();
		configuration.load(settings);
		txtEmailAddress.setText(configuration.getEmailAddress());
		
	}
}


package com.pg.google.api.management.listusers.node;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.pg.knime.node.StandardTrackedNodeDialogPane;


/**
 * <code>NodeDialog</code> for the "GoogleAnalyticUsers" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author P&G, eBusiness
 */
public class GoogleAnalyticUsersNodeDialog extends StandardTrackedNodeDialogPane {

	private DefaultComboBoxModel<String> cbmLevels = new DefaultComboBoxModel<String>();
	private GoogleAnalyticsUsersConfiguration configuration = new GoogleAnalyticsUsersConfiguration();
	
    /**
     * New pane for configuring the GoogleAnalyticUsers node.
     */
    protected GoogleAnalyticUsersNodeDialog() {
    	
    	JComboBox<String> cbxLevels = new JComboBox<String>(cbmLevels);
    	cbmLevels.addElement("Account Level");
    	cbmLevels.addElement("Property Level");
    	cbmLevels.addElement("Profile Level");
    	
    	
    	addTab("Settings", 
    		buildStandardPanel(
    			new PanelBuilder()
    				.add("Level", cbxLevels )
    				.build()    				
    		)
    	);
    	
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {
		configuration = new GoogleAnalyticsUsersConfiguration();
		configuration.setLevel((String)cbmLevels.getSelectedItem());
		configuration.save(settings);
		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {
	
		configuration = new GoogleAnalyticsUsersConfiguration();
		configuration.load(settings);
		
		cbmLevels.setSelectedItem(configuration.getLevel());
	}

	
}


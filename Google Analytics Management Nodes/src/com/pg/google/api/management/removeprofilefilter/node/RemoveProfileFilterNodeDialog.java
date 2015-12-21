package com.pg.google.api.management.removeprofilefilter.node;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.pg.knime.node.StandardTrackedNodeDialogPane;
/**
 * <code>NodeDialog</code> for the "RemoveProfileFilter" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author 
 */
public class RemoveProfileFilterNodeDialog extends StandardTrackedNodeDialogPane {

	private JTextArea filterID = new JTextArea();
	private JTextArea profileID = new JTextArea();
	private JTextArea accountID = new JTextArea();
	private JTextArea propertyID = new JTextArea();
	
	private JCheckBox chkSure = 	new JCheckBox("Are you sure you want to remove these filters?");
	private RemoveProfileFilterNodeConfiguration config = new RemoveProfileFilterNodeConfiguration();
	
    protected RemoveProfileFilterNodeDialog() {
        super();
        
        JPanel pnlCheckboxes = new JPanel();
    	pnlCheckboxes.add(chkSure);
    	
    	addTab ( 
    		"Settings",
    		buildStandardPanel(
    			new PanelBuilder()
    				.add("Filter ID", filterID )
    				.add("Profile ID", profileID )
    				.add("Property ID", propertyID )
    				.add("Account ID", accountID )
    				.build()
    		)
    	);
                    
    }

    @Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {

    	config.setFilterID(filterID.getText());
    	config.setProfileID(profileID.getText());
    	config.setAccountID(accountID.getText());
    	config.setPropertyID(propertyID.getText());
    	config.save(settings);
	
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {

		config = new RemoveProfileFilterNodeConfiguration();
		config.load(settings);
		
		filterID.setText(config.getFilterID());
		accountID.setText(config.getAccountID());
		profileID.setText(config.getProfileID());
		propertyID.setText(config.getPropertyID());
	}
}


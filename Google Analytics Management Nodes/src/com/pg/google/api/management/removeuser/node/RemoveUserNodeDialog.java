package com.pg.google.api.management.removeuser.node;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.pg.knime.node.StandardTrackedNodeDialogPane;

/**
 * <code>NodeDialog</code> for the "RemoveUser" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author P&G, eBusiness
 */
public class RemoveUserNodeDialog extends StandardTrackedNodeDialogPane {

	private DefaultComboBoxModel<String> cbmLevels = new DefaultComboBoxModel<String>(new String[] { "Account Level", "Property Level", "Profile Level"} );
	private JTextField txtUserId = new JTextField();
	private RemoveUserConfiguration configuration = new RemoveUserConfiguration();
	
    /**
     * New pane for configuring the RemoveUser node.
     */
    protected RemoveUserNodeDialog() {

    	addTab("Settings", 
        		buildStandardPanel(
        			new PanelBuilder()
        				.add("Level", new JComboBox<String>(cbmLevels) )
        				.add("User ID", txtUserId)
        				.build()    				
        		)
        	);
    	
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {
		
		configuration = new RemoveUserConfiguration();
		configuration.setLevel((String)cbmLevels.getSelectedItem());
		configuration.setUserId(txtUserId.getText());
		
		configuration.save(settings);
		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {
		
		configuration = new RemoveUserConfiguration();
		configuration.load(settings);
		
		cbmLevels.setSelectedItem(configuration.getLevel());
		txtUserId.setText(configuration.getUserId());
		
	}
}


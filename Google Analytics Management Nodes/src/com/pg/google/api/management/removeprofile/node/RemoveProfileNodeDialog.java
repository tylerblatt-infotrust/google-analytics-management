package com.pg.google.api.management.removeprofile.node;

import javax.swing.JCheckBox;

import org.knime.core.data.DataTableSpec;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;

import com.pg.knime.node.StandardTrackedNodeDialogPane;


/**
 * <code>NodeDialog</code> for the "RemoveProfile" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author P&G, eBusiness
 */
public class RemoveProfileNodeDialog extends StandardTrackedNodeDialogPane {

	private JCheckBox chkAgree = new JCheckBox("I take responsibility for deleting this Profile");
	private RemoveProfileConfiguration configuration = new RemoveProfileConfiguration();
	
    /**
     * New pane for configuring the RemoveProfile node.
     */
    protected RemoveProfileNodeDialog() {

    	addTab("Settings", 
        		buildStandardPanel(
        			new PanelBuilder()
        				.add(null, chkAgree )
        				.build()    				
        		)
        	);
    	
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {
		
		configuration.setAgreed(chkAgree.isEnabled());
		configuration.save(settings);
		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			DataTableSpec[] specs) throws NotConfigurableException {
		
		// Not reload?
		
	}
}


package com.pg.google.api.management.insertprofilefilter;

import javax.swing.JTextField;

import org.knime.core.data.DataTableSpec;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.pg.knime.node.StandardTrackedNodeDialogPane;

/**
 * <code>NodeDialog</code> for the "InsertProfileFilterNode" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author P&G, eBusiness
 */
public class InsertProfileFilterNodeNodeDialog extends StandardTrackedNodeDialogPane {

	private ProfileFilterConfiguration configuration = new ProfileFilterConfiguration();
	private JTextField txtFilterName = new JTextField();
	
    /**
     * New pane for configuring the InsertProfileFilterNode node.
     */
    protected InsertProfileFilterNodeNodeDialog() {

    	addTab("Settings", 
    		buildStandardPanel(
    				new PanelBuilder()
    					.add("Filter ID", txtFilterName )
    					.build()
    		)
    			
    	);
    	
    	
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {
		
		configuration.setFilterName(txtFilterName.getText());
		configuration.save(settings);
		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {
	
		configuration.load(settings);
		
	}
}


package com.pg.google.api.management.updatecustomdimensions.node;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.pg.knime.node.StandardTrackedNodeDialogPane;


/**
 * <code>NodeDialog</code> for the "GoogleAnalyticsUpdateCustomDimensions" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author 
 */
public class GoogleAnalyticsUpdateCustomDimensionsNodeDialog extends StandardTrackedNodeDialogPane  {

    /**
     * New pane for configuring GoogleAnalyticsUpdateCustomDimensions node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
	private GoogleAnalyticsUpdateCustomDimensionConfig config = new GoogleAnalyticsUpdateCustomDimensionConfig(); 
	
	private JComboBox<String> SCOPES = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] { "HIT", "SESSION", "USER", "PRODUCT" } ));
	private JTextArea cd_id = new JTextArea();
    private JTextArea cd_name = new JTextArea();
	private JCheckBox cd_active = 	new JCheckBox("Active");
	
    protected GoogleAnalyticsUpdateCustomDimensionsNodeDialog() {
        super();
        
        addTab ( 
        		"Settings",
        		buildStandardPanel(
        			new PanelBuilder()
        				.add("Custom Dimension ID", cd_id )
        				.add("Custom Dimension Name", cd_name )
        				.add("Scope", SCOPES )
        				.add(null, cd_active )
        				.build()
        		)
        	);
                    
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {

		config.setCd_active(cd_active.isSelected());
    	config.setCd_id(cd_id.getText());
    	config.setCd_name(cd_name.getText());
    	config.setCd_scope((String)SCOPES.getSelectedItem());
    	config.save(settings);
		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {

		config = new GoogleAnalyticsUpdateCustomDimensionConfig();
		config.load(settings);
		
		cd_id.setText(config.getCd_id());
		cd_name.setText(config.getCd_name());
		SCOPES.setSelectedItem(config.getCd_scope());
		cd_active.setSelected(config.getCd_active());
	}
}


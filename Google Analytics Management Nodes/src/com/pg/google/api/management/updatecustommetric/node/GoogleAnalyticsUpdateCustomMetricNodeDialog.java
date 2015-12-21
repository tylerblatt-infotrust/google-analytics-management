package com.pg.google.api.management.updatecustommetric.node;

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
 * <code>NodeDialog</code> for the "GoogleAnalyticsUpdateCustomMetric" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author 
 */
public class GoogleAnalyticsUpdateCustomMetricNodeDialog extends StandardTrackedNodeDialogPane {

	GoogleAnalyticsUpdateCustomMetricConfig config = new GoogleAnalyticsUpdateCustomMetricConfig();
	
	private JComboBox<String> SCOPES = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] { "HIT", "SESSION", "USER", "PRODUCT" } ));
	private JComboBox<String> FORMAT = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] { "INTEGER", "CURRENCY(DECIMAL)", "TIME"} ));
	
	private JTextArea cm_id = new JTextArea();
    private JTextArea cm_name = new JTextArea();
    private JTextArea cm_min = new JTextArea();
    private JTextArea cm_max = new JTextArea();
	
    private JCheckBox cm_active = 	new JCheckBox("Active");
	
    protected GoogleAnalyticsUpdateCustomMetricNodeDialog() {
        super();
        
        addTab ( 
        		"Settings",
        		buildStandardPanel(
        			new PanelBuilder()
        				.add("Custom Dimension ID", cm_id )
        				.add("Custom Dimension Name", cm_name )
        				.add("Scope", SCOPES )
        				.add("Formatting Type", FORMAT)
        				.add("Min Value(Optional)", cm_min)
        				.add("Max Value(Optional)", cm_max)
        				.add(null, cm_active )
        				.build()
        		)
        	);
                    
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {

		config.setCm_active(cm_active.isSelected());
    	config.setCm_id(cm_id.getText());
    	config.setCm_name(cm_name.getText());
    	config.setCm_max(cm_max.getText());
    	config.setCm_min(cm_min.getText());
    	config.setCm_format((String)FORMAT.getSelectedItem());
    	config.setCm_scope((String)SCOPES.getSelectedItem());
    	config.save(settings);
		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {

		config = new GoogleAnalyticsUpdateCustomMetricConfig();
		config.load(settings);
		
		cm_id.setText(config.getCm_id());
		cm_name.setText(config.getCm_name());
		SCOPES.setSelectedItem(config.getCm_scope());
		cm_active.setSelected(config.getCm_active());
		FORMAT.setSelectedItem(config.getCm_format());
		cm_min.setText(config.getCm_min());
		cm_max.setText(config.getCm_max());
	}
	
}


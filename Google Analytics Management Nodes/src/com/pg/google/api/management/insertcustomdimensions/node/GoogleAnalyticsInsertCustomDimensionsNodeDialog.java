package com.pg.google.api.management.insertcustomdimensions.node;

import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.google.api.services.analytics.model.CustomDimension;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObjectSpec;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;
import com.pg.knime.node.StandardTrackedNodeDialogPane;

/**
 * <code>NodeDialog</code> for the "GoogleAnalyticsInsertCustomDimensions" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsInsertCustomDimensionsNodeDialog extends StandardTrackedNodeDialogPane {

	private GoogleAnalyticsInsertCustomDimensionsConfiguration configuration = new GoogleAnalyticsInsertCustomDimensionsConfiguration();
	
	private List<JLabel> lblIds = new ArrayList<JLabel>();
	private List<JTextField> txtNames = new ArrayList<JTextField>();
	private List<JComboBox<String>> cbxScopes = new ArrayList<JComboBox<String>>();
	
	private static final NodeLogger LOGGER = NodeLogger.getLogger(GoogleAnalyticsInsertCustomDimensionsNodeDialog.class);
	private static final String[] SCOPES = new String[] { "HIT", "SESSION", "USER" };
	
    /**
     * New pane for configuring the GoogleAnalyticsInsertCustomDimensions node.
     */
    protected GoogleAnalyticsInsertCustomDimensionsNodeDialog() {

    	
    	JPanel pnl = new JPanel(new GridBagLayout());
    	
    	for ( int i =1 ; i <= 50; i++ ) {
    		JLabel lblId = new JLabel("ga:dimension" + i );
    		lblIds.add(lblId);
    		pnl.add(lblId, getGBC(0, i-1, 1, 1));
    		
    		JTextField txtName = new JTextField("");
    		txtNames.add(txtName);
    		pnl.add(txtName, getGBC(1, i-1, 5, 1));
    		
    		JComboBox<String> cbxScope = new JComboBox<String>(SCOPES);
    		cbxScopes.add(cbxScope);
    		pnl.add(cbxScope, getGBC(2, i-1, 1, 1));
    		
    		
    	}
    	
    	addTab(
    		"Settings",
    		pnl
    	);
    	
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {
			
		configuration = new GoogleAnalyticsInsertCustomDimensionsConfiguration();
		
		String[] dimensionNames = new String[50];
		String[] dimensionScopes = new String[50];
		for ( int i = 1; i <= 50; i++ ) {
			dimensionNames[i-1] = txtNames.get(i-1).getText();
			dimensionScopes[i-1] = (String)cbxScopes.get(i-1).getSelectedItem();
		}
		
		configuration.setDimensionNames(Arrays.asList(dimensionNames));
		configuration.setDimensionScopes(Arrays.asList(dimensionScopes));
		configuration.save(settings);
		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {
	
		configuration = new GoogleAnalyticsInsertCustomDimensionsConfiguration();
		configuration.load(settings);
		
		// Try and pre-populate dimensions / scope
		try {
			if ( !configuration.isSetup() ) {
				GoogleAnalyticsManagementClient managementClient = new GoogleAnalyticsManagementClient(((GoogleAnalyticsConnectionPortObjectSpec)specs[0]).getGoogleAnalyticsConnection());
				List<CustomDimension> existingDimensions = managementClient.getCustomDimensions();
				configuration.load(existingDimensions);
			}
		} catch (IOException exc ) {
			LOGGER.warn("Unable to retrieve existing custom dimensions");
		}
		
		int i = 0;
		for ( JTextField txtName : txtNames ) {
			txtName.setText(configuration.getDimensionNames().get(i++));
		}
		
		i=0;
		for ( JComboBox<String> cbxScope : cbxScopes ) {
			cbxScope.setSelectedItem(configuration.getDimensionScopes().get(i++));
		}
		
	}
}


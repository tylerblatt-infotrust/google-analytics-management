package com.pg.google.api.management.patchprofile;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.google.api.services.analytics.Analytics.Data.Ga.Get;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.GaData.ProfileInfo;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnection;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObjectSpec;
import com.pg.knime.node.StandardTrackedNodeDialogPane;

/**
 * <code>NodeDialog</code> for the "PatchProfile" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author P&G, eBusiness
 */
public class PatchProfileNodeDialog extends StandardTrackedNodeDialogPane {

	private JTextField txtProfileName = new JTextField();
	private PatchProfileConfiguration configuration = new PatchProfileConfiguration();
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	
    /**
     * New pane for configuring the PatchProfile node.
     */
    protected PatchProfileNodeDialog() {

    	addTab("Settings", 
    		buildStandardPanel(
    				new PanelBuilder()
    					.add("Profile Name", txtProfileName)
    					.build()
    		)
    		
    	);
    	
    	
    }

	@Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {
		
		configuration = new PatchProfileConfiguration();
		configuration.setProfileName(txtProfileName.getText());
		
		configuration.save(settings);
		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {
		
		try {
			GoogleAnalyticsConnection analyticsConnection = ((GoogleAnalyticsConnectionPortObjectSpec)specs[0]).getGoogleAnalyticsConnection();
	    	String profileId = analyticsConnection.getProfileId();
	    	Get query = analyticsConnection.getAnalytics().data().ga().get("ga:"+profileId, SDF.format(new Date()), SDF.format(new Date()), "ga:pageviews");
	    	
	    	GaData response = query.execute();
	    	if ( response != null && response.getTotalResults() >= 0 ) {
	    		ProfileInfo profileInfo = response.getProfileInfo();
	    		
	    		txtProfileName.setText(profileInfo.getProfileName());
	    	}

		} catch ( Exception exc ) {
			
			
		}
		
	}
}


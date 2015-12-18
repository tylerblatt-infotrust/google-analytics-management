package com.pg.google.api.management.updateprofile.node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

import com.google.api.services.analytics.model.Profile;
import com.google.api.services.analytics.model.Profile.ChildLink;
import com.google.api.services.analytics.model.Profile.ParentLink;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;


/**
 * This is the model implementation of GoogleAnalyticsUpdateProfile.
 * 
 *
 * @author 
 */
public class GoogleAnalyticsUpdateProfileNodeModel extends NodeModel {
    
	private GoogleAnalyticsUpdateProfileConfig config = new GoogleAnalyticsUpdateProfileConfig();
	
    protected GoogleAnalyticsUpdateProfileNodeModel() {
    
        // TODO one incoming port and one outgoing port is assumed
    	super(
    			new PortType[] {GoogleAnalyticsConnectionPortObject.TYPE} , 
    			new PortType[] {BufferedDataTable.TYPE}
    	    	);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PortObject[] execute(PortObject[] inObjects, ExecutionContext exec)
    		throws Exception {
    	
    	GoogleAnalyticsConnectionPortObject analyticsConnection = (GoogleAnalyticsConnectionPortObject)inObjects[0];
    	GoogleAnalyticsManagementClient managementClient = new GoogleAnalyticsManagementClient(analyticsConnection.getGoogleAnalyticsConnection());
    	
    	DataTableSpec spec = createSpec();
    	BufferedDataContainer container = exec.createDataContainer(spec);
    	
    	List<DataCell> cells = new ArrayList<DataCell>(spec.getNumColumns());
    	
    	cells.add(new StringCell(config.getProfile_id()));
    	cells.add(new StringCell(config.getProfile_kind()));
    	cells.add(new StringCell(config.getProfile_name()));
    	cells.add(new StringCell(config.getProfile_currency()));
    	cells.add(new StringCell(config.getProfile_timezone()));
    	cells.add(new StringCell(config.getProfile_websiteUrl()));
    	cells.add(new StringCell(config.getProfile_defaultPage()));
    	cells.add(new StringCell(config.getProfile_excludeQueryParameters()));
    	cells.add(new StringCell(config.getProfile_siteSearchQueryParameters()));
    	cells.add(new StringCell(config.getProfile_type()));
    	cells.add(new StringCell(config.getProfile_siteSearchCategoryParameters()));
    	
    	
    	Profile body = new Profile();
    	body.setId(config.getProfile_id());
    	body.setKind(config.getProfile_kind());
    	body.setName(config.getProfile_name());
    	body.setCurrency(config.getProfile_currency());
    	body.setTimezone(config.getProfile_timezone());
    	body.setWebsiteUrl(config.getProfile_websiteUrl());
    	body.setDefaultPage(config.getProfile_defaultPage());
    	body.setExcludeQueryParameters(config.getProfile_excludeQueryParameters());
    	body.setSiteSearchQueryParameters(config.getProfile_siteSearchQueryParameters());
    	body.setSiteSearchCategoryParameters(config.getProfile_siteSearchCategoryParameters());
    	body.setStripSiteSearchCategoryParameters(config.getProfile_stripSiteSearchCategoryParameters());
    	body.setStripSiteSearchQueryParameters(config.getProfile_stripSiteSearchQueryParameters());
    	body.setType(config.getProfile_type());
    	body.setECommerceTracking(config.getProfileEcommerceTracking());
    	body.setEnhancedECommerceTracking(config.getProfileEnhancedEcommerceTracking());
    	
    	    	
    	
    	try {
        	
        	managementClient.updateProfile(config.getProfile_id(), body);
        	
        	cells.add(new StringCell("Success"));
        	cells.add(new StringCell(""));
        	
        	
    	} catch ( IOException exc ) {
    		cells.add(new StringCell("Failed"));
    		cells.add(new StringCell(exc.getMessage()));
    	}
		
		container.addRowToTable(new DefaultRow("Row 0", cells));
    	
    	container.close();
        return new PortObject[] { container.getTable() };

        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
        // TODO Code executed on reset.
        // Models build during execute are cleared here.
        // Also data handled in load/saveInternals will be erased here.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PortObjectSpec[] configure(PortObjectSpec[] inSpecs)
    		throws InvalidSettingsException {
    	
    	return new PortObjectSpec[] { createSpec() };
    }
    
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException {
        
        // TODO: check if user settings are available, fit to the incoming
        // table structure, and the incoming types are feasible for the node
        // to execute. If the node can execute in its current state return
        // the spec of its output data table(s) (if you can, otherwise an array
        // with null elements), or throw an exception with a useful user message

        return new DataTableSpec[]{null};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {

       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
            
    	config = new GoogleAnalyticsUpdateProfileConfig();
		config.load(settings);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException {
            
        // TODO check if the settings could be applied to our model
        // e.g. if the count is in a certain range (which is ensured by the
        // SettingsModel).
        // Do not actually set any values of any member variables.


    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
        
        // TODO load internal data. 
        // Everything handed to output ports is loaded automatically (data
        // returned by the execute method, models loaded in loadModelContent,
        // and user settings set through loadSettingsFrom - is all taken care 
        // of). Load here only the other internals that need to be restored
        // (e.g. data used by the views).

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
       
        // TODO save internal models. 
        // Everything written to output ports is saved automatically (data
        // returned by the execute method, models saved in the saveModelContent,
        // and user settings saved through saveSettingsTo - is all taken care 
        // of). Save here only the other internals that need to be preserved
        // (e.g. data used by the views).

    }
    
    private DataTableSpec createSpec() {
    	List<DataColumnSpec> colSpecs = new ArrayList<DataColumnSpec>();
    	
    	colSpecs.add(new DataColumnSpecCreator("ID", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Kind", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Self Link", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Account ID", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Web Property ID", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Internal Web Property ID", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Name", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Currency", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Timezone", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Website URL", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Default Page", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Exclude Query Params", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Site Search Query Params", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Type", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Site Search Category Params", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Parent Link Type", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Parent Link HREF", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Child Link Type", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Child Link HREF", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Active", StringCell.TYPE).createSpec());    	
    	colSpecs.add(new DataColumnSpecCreator("Status", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Error Message", StringCell.TYPE).createSpec());
    	
    	return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }

}


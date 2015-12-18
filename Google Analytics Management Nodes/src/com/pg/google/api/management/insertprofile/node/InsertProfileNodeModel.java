package com.pg.google.api.management.insertprofile.node;

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
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObjectSpec;
import com.pg.google.api.analytics.connector.node.GoogleAnalyticsConnectorConfiguration;
import com.pg.google.api.connector.data.GoogleApiConnectionPortObject;
import com.pg.google.api.connector.data.GoogleApiConnectionPortObjectSpec;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;


/**
 * This is the model implementation of InsertProfile.
 * 
 *
 * @author 
 */
public class InsertProfileNodeModel extends NodeModel {
    
	private InsertProfileConfiguration configuration = new InsertProfileConfiguration();    
	
	
    protected InsertProfileNodeModel()  {
    
        // TODO one incoming port and one outgoing port is assumed
     super(
		new PortType[] {GoogleApiConnectionPortObject.TYPE} , 
		new PortType[] {BufferedDataTable.TYPE}
    	);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PortObject[] execute(PortObject[] inObjects, ExecutionContext exec)
    		throws Exception {

    	GoogleApiConnectionPortObjectSpec inSpec = (GoogleApiConnectionPortObjectSpec)inObjects[0].getSpec();
    	GoogleAnalyticsConnectorConfiguration m_config = new GoogleAnalyticsConnectorConfiguration();
    	
    	
    	GoogleAnalyticsConnectionPortObjectSpec analyticsConnection = new GoogleAnalyticsConnectionPortObjectSpec(
                m_config.createGoogleAnalyticsConnection(((GoogleApiConnectionPortObjectSpec)inSpec)
                        .getGoogleApiConnection()));
    	
    	GoogleAnalyticsManagementClient managementClient = new GoogleAnalyticsManagementClient(analyticsConnection.getGoogleAnalyticsConnection());
    	
    	DataTableSpec spec = createSpec();
    	BufferedDataContainer container = exec.createDataContainer(spec);
    	
    	List<DataCell> cells = new ArrayList<DataCell>(spec.getNumColumns());
    	
    	cells.add(new StringCell(configuration.getProfileName()));
    	cells.add(new StringCell(configuration.getType()));
    	cells.add(new StringCell(configuration.getCurrency()));
    	cells.add(new StringCell(configuration.getTimezone()));
    	cells.add(new StringCell(configuration.getWebsiteUrl()));
    	cells.add(new StringCell(configuration.getDefaultPage()));
    	cells.add(new StringCell(configuration.getExcludeQueryParameters()));
    	cells.add(new StringCell(configuration.getSiteSearchQueryParameters()));
    	cells.add(new StringCell(configuration.getSiteSearchCategoryParameters()));
    	
    	Profile body = new Profile();
    	body.setName(configuration.getProfileName());
    	body.setECommerceTracking(configuration.getEnableEcom());
    	body.setEnhancedECommerceTracking(configuration.getEnableEnhancedEcom());
    	body.setType(configuration.getType());
    	body.setCurrency(configuration.getCurrency());
    	body.setTimezone(configuration.getTimezone());
    	if(!configuration.getWebsiteUrl().isEmpty()){body.setWebsiteUrl(configuration.getWebsiteUrl());};
    	if(!configuration.getDefaultPage().isEmpty()){body.setDefaultPage(configuration.getDefaultPage());}
    	if(!configuration.getExcludeQueryParameters().isEmpty()){body.setExcludeQueryParameters(configuration.getExcludeQueryParameters());}
    	if(!configuration.getSiteSearchCategoryParameters().isEmpty()){body.setSiteSearchCategoryParameters(configuration.getSiteSearchCategoryParameters());}
    	if(!configuration.getSiteSearchQueryParameters().isEmpty()){body.setSiteSearchQueryParameters(configuration.getSiteSearchQueryParameters());}
    	body.setStripSiteSearchCategoryParameters(configuration.getStripSiteSearchCategoryParameters());
    	body.setStripSiteSearchQueryParameters(configuration.getStripSiteSearchQueryParameters());
    	
	
    	try {
    	
        	String response = managementClient.insertProfile(body, configuration.getWebPropertyId());
        	cells.add(new StringCell (response));
        	cells.add(new StringCell("Success"));
        	cells.add(new StringCell(""));
        	
        	
    	} catch ( IOException exc ) {
    		cells.add(new StringCell (""));
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

        // TODO save user settings to the config object.
        
      

    }
    
    @Override
    protected PortObjectSpec[] configure(PortObjectSpec[] inSpecs)
    		throws InvalidSettingsException {
    	
    	return new PortObjectSpec[] { createSpec() };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
    	
    	configuration = new InsertProfileConfiguration();
    	configuration.load(settings);
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
    	
    	colSpecs.add(new DataColumnSpecCreator("Profile Name", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Type", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Currency", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Timezone", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Website Url", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Default Page", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Exclude Query Parameters", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Site Search Query Parameters", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Site Search Category Parameters", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Profile ID", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Status", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Error Message", StringCell.TYPE).createSpec());
    	
    	return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }

}


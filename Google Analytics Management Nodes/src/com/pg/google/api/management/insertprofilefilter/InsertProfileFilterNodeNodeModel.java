package com.pg.google.api.management.insertprofilefilter;

import java.io.File;
import java.io.IOException;

import org.knime.core.data.DataTableSpec;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortType;

import com.google.api.services.analytics.model.GaData.ProfileInfo;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;

/**
 * This is the model implementation of InsertProfileFilterNode.
 * 
 *
 * @author P&G, eBusiness
 */
public class InsertProfileFilterNodeNodeModel extends NodeModel {
    
	private ProfileFilterConfiguration configuration = new ProfileFilterConfiguration();
	
    /**
     * Constructor for the node model.
     */
    protected InsertProfileFilterNodeNodeModel() {
    
    	  super(
              	new PortType[] {GoogleAnalyticsConnectionPortObject.TYPE} , 
              	new PortType[] {BufferedDataTable.TYPE}
              );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(PortObject[] inObjects,
            final ExecutionContext exec) throws Exception {

       
    	GoogleAnalyticsConnectionPortObject analyticsConnection = (GoogleAnalyticsConnectionPortObject)inObjects[0];
    	GoogleAnalyticsManagementClient client = new GoogleAnalyticsManagementClient(analyticsConnection.getGoogleAnalyticsConnection());
    	ProfileInfo profileInfo = client.getProfileInfo();
    	
    	String status = "SUCCESS";
    	
    	try { 
    		client.insertProfileFilter(configuration.getFilterName());
    	} catch ( Exception exc ) {
    		
    		status = "FAILED";
    	}
    	
    	// TODO: Create Data Table as output
    	
    	return new BufferedDataTable[] {};
    	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
        // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException {

        // TODO: generated method stub
        return new DataTableSpec[]{null};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {
         // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
        // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException {
        // TODO: generated method stub
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
        // TODO: generated method stub
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
        // TODO: generated method stub
    }

}


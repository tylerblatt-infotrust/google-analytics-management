package com.pg.google.api.management.insertcustomdimensions.node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;

import com.google.api.services.analytics.model.CustomDimension;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;

/**
 * This is the model implementation of GoogleAnalyticsInsertCustomDimensions.
 * 
 *
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsInsertCustomDimensionsNodeModel extends NodeModel {
    
	private GoogleAnalyticsInsertCustomDimensionsConfiguration configuration = new GoogleAnalyticsInsertCustomDimensionsConfiguration();
	
    /**
     * Constructor for the node model.
     */
    protected GoogleAnalyticsInsertCustomDimensionsNodeModel() {
        super(
            	new PortType[] { GoogleAnalyticsConnectionPortObject.TYPE },
            	new PortType[] { BufferedDataTable.TYPE }
            );
    }

    @Override
    protected PortObject[] execute(PortObject[] inObjects, ExecutionContext exec)
    		throws Exception {

    	GoogleAnalyticsConnectionPortObject analyticsConnection = (GoogleAnalyticsConnectionPortObject)inObjects[0];
    	GoogleAnalyticsManagementClient managementClient = new GoogleAnalyticsManagementClient(analyticsConnection.getGoogleAnalyticsConnection());
    
    	List<CustomDimension> dimensions = managementClient.getCustomDimensions();
    	
    	for ( int i = 1; i <= configuration.getMaxDimensions(); i++ ) {
    		
    		String dimensionId = "ga:dimension" + i;
    		exec.setMessage("Updating Custom Dimension " + i);
    		
    		String dimensionName = configuration.getDimensionNames().get(i-1);
    		if ( StringUtils.isEmpty(dimensionName ) ) dimensionName = GoogleAnalyticsInsertCustomDimensionsConfiguration.DEFAULT_NAME;
    		
    		String dimensionScope = configuration.getDimensionScopes().get(i-1);
    		if ( StringUtils.isEmpty(dimensionScope) ) dimensionScope = GoogleAnalyticsInsertCustomDimensionsConfiguration.DEFAULT_SCOPE;
    		
    		// Update or Create
    		if ( configuration.getDimension(dimensionId, dimensions) != null ) 
    			managementClient.updateCustomDimension(dimensionId, dimensionName, dimensionScope);
    		else
    			managementClient.insertCustomDimension(dimensionId, dimensionName, dimensionScope);
    	}
    	
    	return new PortObject[] { null };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
        
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
    protected void saveSettingsTo(final NodeSettingsWO settings) {
         // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
       
    	configuration = new GoogleAnalyticsInsertCustomDimensionsConfiguration();
    	configuration.load(settings);
    	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException {
        
    	
    	
    	
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

    private DataTableSpec createSpec() {
List<DataColumnSpec> colSpecs = new ArrayList<DataColumnSpec>();
        
        colSpecs.add(new DataColumnSpecCreator("Account ID", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Property ID", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Custom Dimension ID", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Status", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Error Message", StringCell.TYPE).createSpec());
        
        return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }
    
}


package com.pg.google.api.management.updatecustomdimensions.node;

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

import com.google.api.services.analytics.model.CustomDimension;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;


/**
 * This is the model implementation of GoogleAnalyticsUpdateCustomDimensions.
 * 
 *
 * @author 
 */
public class GoogleAnalyticsUpdateCustomDimensionsNodeModel extends NodeModel {
    
	private GoogleAnalyticsUpdateCustomDimensionConfig config = new GoogleAnalyticsUpdateCustomDimensionConfig();
	
    /**
     * Constructor for the node model.
     */
    protected GoogleAnalyticsUpdateCustomDimensionsNodeModel() {
    
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
    	
    	cells.add(new StringCell(config.getCd_id()));
		cells.add(new StringCell(config.getCd_name()));
		cells.add(new StringCell(config.getCd_scope()));

		CustomDimension body = new CustomDimension();
		body.setName(config.getCd_name());
		body.setScope(config.getCd_scope());
		body.setActive(config.getCd_active());
		
		
				
		try {
    	
        	managementClient.updateCustomeDimension(config.getCd_id(), body);
        	
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
            
    	config = new GoogleAnalyticsUpdateCustomDimensionConfig();
		config.load(settings);

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
    	
    	colSpecs.add(new DataColumnSpecCreator("Custom Dimension Id", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Custom Dimension Name", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Custom Dimension Scope", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Active", StringCell.TYPE).createSpec());    	
    	colSpecs.add(new DataColumnSpecCreator("Status", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Error Message", StringCell.TYPE).createSpec());
    	
    	return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }

}


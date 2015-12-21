package com.pg.google.api.management.insertprofilefilter;
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
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;
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
    protected PortObject[] execute(PortObject[] inObjects,
            final ExecutionContext exec) throws Exception {
       
    	GoogleAnalyticsConnectionPortObject analyticsConnection = (GoogleAnalyticsConnectionPortObject)inObjects[0];
    	GoogleAnalyticsManagementClient client = new GoogleAnalyticsManagementClient(analyticsConnection.getGoogleAnalyticsConnection());
    	
    	
    	DataTableSpec spec = createSpec();
    	BufferedDataContainer container = exec.createDataContainer(spec);
    	List<DataCell> cells = new ArrayList<DataCell>(spec.getNumColumns());
    	
    	cells.add(new StringCell(configuration.getFilterName()));
        	
    
    	
    	try { 
    		client.insertProfileFilter(configuration.getFilterName());
    		cells.add(new StringCell("Success"));
        	cells.add(new StringCell(""));
    	} catch ( Exception exc ) {
    		cells.add(new StringCell("Failed"));
    		cells.add(new StringCell(exc.getMessage()));
    	
    	}
    	
    	// TODO: Create Data Table as output
    	
    	container.addRowToTable(new DefaultRow("Row 0", cells));
    	
    	container.close();
        return new PortObject[] { container.getTable() };
    	
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
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
    	
    	configuration = new ProfileFilterConfiguration();
    	configuration.load(settings);
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
    	
    	colSpecs.add(new DataColumnSpecCreator("Profile Filter Name", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Status", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Error Message", StringCell.TYPE).createSpec());
    	
    	return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }
}
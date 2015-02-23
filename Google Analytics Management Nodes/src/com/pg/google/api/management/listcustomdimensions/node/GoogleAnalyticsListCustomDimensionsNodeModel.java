package com.pg.google.api.management.listcustomdimensions.node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.date.DateAndTimeCell;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
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

import com.google.api.services.analytics.model.CustomDimension;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;

/**
 * This is the model implementation of GoogleAnalyticsListCustomDimensions.
 * 
 *
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsListCustomDimensionsNodeModel extends NodeModel {
    
    /**
     * Constructor for the node model.
     */
    protected GoogleAnalyticsListCustomDimensionsNodeModel() {
    
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
    	
    	List<CustomDimension> dimensions = managementClient.getCustomDimensions();
    	int row = 0;
    	for ( CustomDimension dimension : dimensions ) {
    		List<DataCell> cells = new ArrayList<DataCell>(spec.getNumColumns());
            
    		cells.add(new StringCell(dimension.getAccountId()));
    		cells.add(new StringCell(dimension.getWebPropertyId()));
    		cells.add(new StringCell(dimension.getKind()));
    		cells.add(new StringCell(dimension.getId()));
    		cells.add(new StringCell(dimension.getName()));
    		cells.add(new DoubleCell(dimension.getIndex()));
    		cells.add(new StringCell(dimension.getScope()));
    		cells.add(new StringCell(dimension.getActive().toString()));
    		cells.add(new DateAndTimeCell(dimension.getCreated().getValue(), true, true, false));
    		cells.add(new DateAndTimeCell(dimension.getUpdated().getValue(), true, true, false));
    		
    		container.addRowToTable(new DefaultRow("Row " + row++, cells));
    	}
    	
    	container.close();
        return new PortObject[] { container.getTable() };
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
    	
    	return new DataTableSpec[]{ createSpec() };
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
    
    private DataTableSpec createSpec() {
    	List<DataColumnSpec> colSpecs = new ArrayList<DataColumnSpec>();
    	
    	colSpecs.add(new DataColumnSpecCreator("Account Id", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Property Id", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Dimension Kind", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Dimension Id", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Dimension Name", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Dimension Index", DoubleCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Dimension Scope", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Dimension Active", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Dimension Created", DateAndTimeCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Dimension Updated", DateAndTimeCell.TYPE).createSpec());
        
    	return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }

}


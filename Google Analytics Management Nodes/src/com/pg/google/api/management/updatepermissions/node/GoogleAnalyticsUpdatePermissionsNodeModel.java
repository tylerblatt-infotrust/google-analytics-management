package com.pg.google.api.management.updatepermissions.node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;

import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;

/**
 * This is the model implementation of GoogleAnalyticsUpdatePermissions.
 * 
 *
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsUpdatePermissionsNodeModel extends NodeModel {
    
	private GoogleAnalyticsUpdatePermissionsConfiguration configuration = new GoogleAnalyticsUpdatePermissionsConfiguration();
	
	private static final NodeLogger LOGGER = NodeLogger.getLogger(GoogleAnalyticsUpdatePermissionsNodeModel.class);
	
    /**
     * Constructor for the node model.
     */
    protected GoogleAnalyticsUpdatePermissionsNodeModel() {
        super(
        	new PortType[] { GoogleAnalyticsConnectionPortObject.TYPE },
        	new PortType[] { BufferedDataTable.TYPE }
        );
    }

    @Override
    protected PortObject[] execute(PortObject[] inObjects, ExecutionContext exec)
    		throws Exception {

    	GoogleAnalyticsConnectionPortObject analyticsConnection = (GoogleAnalyticsConnectionPortObject)inObjects[0];
    	GoogleAnalyticsManagementClient client = new GoogleAnalyticsManagementClient(analyticsConnection.getGoogleAnalyticsConnection());
    	
    	DataTableSpec spec = createSpec();
    	BufferedDataContainer container = exec.createDataContainer(spec);
    	
    	List<DataCell> cells = new ArrayList<DataCell>(spec.getNumColumns());
        
		
		cells.add(new StringCell(configuration.getUserId()));
		cells.add(new StringCell(configuration.getOperation()));
		cells.add(new StringCell(StringUtils.join(configuration.getPermissions(), ",")));
        
		try {
			if ( configuration.isAccountLevel() )
				client.updateAccountPermission(configuration.getUserId(), configuration.getPermissions());
			else if ( configuration.isPropertyLevel() )
				client.updatePropertyPermission(configuration.getUserId(), configuration.getPermissions() );
			else
				client.updateProfilePermission(configuration.getUserId(), configuration.getPermissions());
			
			cells.add(new StringCell("SUCEEDED"));
			cells.add(new StringCell(""));
			
		} catch ( Exception exc ) {
			LOGGER.error(exc.getMessage());
			cells.add(new StringCell("FAILED"));
			cells.add(new StringCell(exc.getMessage()));
		}
    	
    	container.addRowToTable(new DefaultRow("Row 0", cells));
    	
    	container.close();
    	return new BufferedDataTable[]{container.getTable()};
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
        // TODO: generated method stub
    }

    @Override
    protected PortObjectSpec[] configure(PortObjectSpec[] inSpecs)
    		throws InvalidSettingsException {

    	return new DataTableSpec[]{createSpec()};
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {
         configuration.save(settings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
        
    	configuration = new GoogleAnalyticsUpdatePermissionsConfiguration();
        configuration.load(settings);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException {
     
    	configuration = new GoogleAnalyticsUpdatePermissionsConfiguration();
    	configuration.load(settings);
    	if ( !configuration.isSetTo() ) {
    		throw new InvalidSettingsException("Only 'Set To' operation currently supported");
    	}
    	
    	if ( !configuration.getManageUsers() && !configuration.getEdit() && !configuration.getCollaborate() && !configuration.getRead() ) {
    		throw new InvalidSettingsException("At least one permission level must be chosen");
    	}
    	
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
        
        colSpecs.add(new DataColumnSpecCreator("User Id", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Operation", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Permissions", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Status", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Error Message", StringCell.TYPE).createSpec());
        
        return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }

}


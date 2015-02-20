package com.pg.google.api.management.node;

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
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;

import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;
import com.pg.google.api.management.data.GoogleAnalyticsUser;

/**
 * This is the model implementation of GoogleAnalyticUsers.
 * 
 *
 * @author P&G, eBusiness
 */
public class GoogleAnalyticUsersNodeModel extends NodeModel {
    
	private GoogleAnalyticsUsersConfiguration configuration = new GoogleAnalyticsUsersConfiguration();
	
    /**
     * Constructor for the node model.
     */
    protected GoogleAnalyticUsersNodeModel() {
        super(
        		new PortType[] {GoogleAnalyticsConnectionPortObject.TYPE} , 
        		new PortType[] {BufferedDataTable.TYPE}
        );
    }

    @Override
    protected PortObject[] execute(PortObject[] inObjects, ExecutionContext exec)
    		throws Exception {
    	
    	
    	GoogleAnalyticsConnectionPortObject analyticsConnection = (GoogleAnalyticsConnectionPortObject)inObjects[0];
    	GoogleAnalyticsManagementClient managementClient = new GoogleAnalyticsManagementClient(analyticsConnection.getGoogleAnalyticsConnection());
    	
    	List<GoogleAnalyticsUser> users = null;
    	String level = configuration.getLevel();
    	
		if ( "Account Level".equals(level) ) {
			users = managementClient.getAccountUsers();
		} else if ("Property Level".equals(level) ) {
			users = managementClient.getPropertyUsers();
		} else {
			users = managementClient.getProfileUsers();
		}
    	    	
    	DataTableSpec spec = createSpec();
    	BufferedDataContainer container = exec.createDataContainer(createSpec());
    	
    	int row = 0;
    	for ( GoogleAnalyticsUser user : users ) {
    		List<DataCell> cells = new ArrayList<DataCell>(spec.getNumColumns());
            
    		cells.add(new StringCell(user.getAccountId()));
    		cells.add(new StringCell(user.getPropertyId()));
    		cells.add(new StringCell(user.getProfileId()));
    		cells.add(new StringCell(user.getProfileName()));
    		
    		cells.add(new StringCell(user.getUserId()));
    		cells.add(new StringCell(user.getEmail()));
    		
    		cells.add(new StringCell(StringUtils.join(user.getLocalPermissions(), ",")));
            cells.add(new StringCell(StringUtils.join(user.getEffectivePermissions(), ",")));
            
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

    	return new PortObjectSpec[] {createSpec()};
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {
        if ( configuration != null ) 
        	configuration.save(settings);
    	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
        configuration = new GoogleAnalyticsUsersConfiguration();
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
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
        
    }
    
    private DataTableSpec createSpec() {
        List<DataColumnSpec> colSpecs = new ArrayList<DataColumnSpec>();
        
        // Add additional columns to DataSpec
        colSpecs.add(new DataColumnSpecCreator("Account Id", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Property Id", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Profile Id", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Profile Name", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("User Id", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Email Address", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Local Permissions", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Effective Permissions", StringCell.TYPE).createSpec());
        
        return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }

}
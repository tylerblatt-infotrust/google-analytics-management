package com.pg.google.api.management.patchprofile;

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

import com.google.api.services.analytics.model.GaData.ProfileInfo;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;

/**
 * This is the model implementation of PatchProfile.
 * 
 *
 * @author P&G, eBusiness
 */
public class PatchProfileNodeModel extends NodeModel {
    
	private PatchProfileConfiguration configuration = new PatchProfileConfiguration();
	
    /**
     * Constructor for the node model.
     */
    protected PatchProfileNodeModel() {
    
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
    	
    	ProfileInfo profileInfo = client.getProfileInfo();
    	
    	cells.add(new StringCell(profileInfo.getAccountId()));
		cells.add(new StringCell(profileInfo.getWebPropertyId()));
		cells.add(new StringCell(profileInfo.getProfileId()));
		cells.add(new StringCell(configuration.getProfileName()));
		
    	try {
    		client.patchProfile(configuration.getProfileName());
    		cells.add(new StringCell("Success"));
    		cells.add(new StringCell(""));
    		
    	} catch ( Exception exc ) {
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
        // TODO: generated method stub
    }

    @Override
    protected PortObjectSpec[] configure(PortObjectSpec[] inSpecs)
    		throws InvalidSettingsException {
 
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
        
    	configuration = new PatchProfileConfiguration();
    	configuration.load(settings);
    	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException {

    	configuration = new PatchProfileConfiguration();
    	configuration.load(settings);
    	
    	if  (StringUtils.isEmpty(configuration.getProfileName())) {
    		throw new InvalidSettingsException("Unable to set Profile name to blank (just a bad idea!)");
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
    	
    	colSpecs.add(new DataColumnSpecCreator("Account Id", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Property Id", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Profile Id", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Updated Profile Name", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Status", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Error Message", StringCell.TYPE).createSpec());
    	
    	return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }

}


package com.pg.google.api.management.listprofilefilters;

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
import org.knime.core.data.def.IntCell;
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

import com.google.api.services.analytics.model.Filter;
import com.google.api.services.analytics.model.FilterExpression;
import com.google.api.services.analytics.model.GaData.ProfileInfo;
import com.google.api.services.analytics.model.ProfileFilterLink;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;

/**
 * This is the model implementation of ListProfileFilters.
 * 
 *
 * @author P&G, eBusiness
 */
public class ListProfileFiltersNodeModel extends NodeModel {
    
    /**
     * Constructor for the node model.
     */
    protected ListProfileFiltersNodeModel() {
    
        super(
            	new PortType[] {GoogleAnalyticsConnectionPortObject.TYPE} , 
            	new PortType[] {BufferedDataTable.TYPE}
            );
    }

    @Override
    protected PortObject[] execute(PortObject[] inObjects, ExecutionContext exec)
    		throws Exception {
    	
    	GoogleAnalyticsConnectionPortObject analyticsConnection = (GoogleAnalyticsConnectionPortObject)inObjects[0];
    	GoogleAnalyticsManagementClient client = new GoogleAnalyticsManagementClient(analyticsConnection.getGoogleAnalyticsConnection());
    	ProfileInfo profileInfo = client.getProfileInfo();
    	
    	DataTableSpec spec = createSpec();
    	BufferedDataContainer container = exec.createDataContainer(spec);
    	
    	exec.setMessage("Getting filters...");
    	List<ProfileFilterLink> filterLinks = client.getFilters();
    	
    	
    	int row = 0;
    	for ( ProfileFilterLink filterLink : filterLinks ) {
    		
    		exec.setMessage("Getting filter " + row++ + " of " + filterLink.size() );
    		Filter filter = client.getFilter(filterLink.getFilterRef().getId());
    		
    		List<DataCell> cells = new ArrayList<DataCell>(spec.getNumColumns());
    		
    		cells.add(new StringCell(profileInfo.getAccountId()));
    		cells.add(new StringCell(profileInfo.getWebPropertyId()));
    		cells.add(new StringCell(profileInfo.getProfileId()));
    		
    		cells.add(new StringCell(filterLink.getFilterRef().getId()));
    		cells.add(new IntCell(filterLink.getRank()));
    		
    		cells.add(new StringCell(filter.getName()));
    		cells.add(new StringCell(filter.getType()));
    		cells.add(new DateAndTimeCell(filter.getCreated().getValue(), true, true, false));
    		
    		// Exclude
    		FilterExpression expression = filter.getExcludeDetails();
    		if ( expression == null ) expression = new FilterExpression();
    		cells.add(new StringCell(IfNull(expression.getExpressionValue(),"")));
    		cells.add(new StringCell(IfNull(expression.getMatchType(),"")));
    		cells.add(new StringCell(IfNull(expression.getField(),"")));
    		cells.add(new StringCell(IfNull(expression.getCaseSensitive(), "")));
    		
    		// Include
    		expression = filter.getIncludeDetails();
    		if (expression == null ) expression = new FilterExpression();
    		cells.add(new StringCell(IfNull(expression.getExpressionValue(),"")));
    		cells.add(new StringCell(IfNull(expression.getMatchType(),"")));
    		cells.add(new StringCell(IfNull(expression.getField(),"")));
    		cells.add(new StringCell(IfNull(expression.getCaseSensitive(), "")));
    		
    		container.addRowToTable(new DefaultRow("Row " + row++, cells));
    	}
    	
    	container.close();
        return new PortObject[] { container.getTable() };
    }
    
    private String IfNull ( Object val, String alt ) {
    	if ( val != null ) return val.toString();
    	if ( alt != null ) return alt;
    	return "";
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
    	colSpecs.add(new DataColumnSpecCreator("Profile Id", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Filter Id", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Filter Rank", IntCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Filter Name", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Filter Type", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Created", DateAndTimeCell.TYPE).createSpec());
    	
    	colSpecs.add(new DataColumnSpecCreator("Exclude Filter Expression", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Exclude Filter Match Type", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Exclude Filter Field", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Exclude Filter Case Sensitive", StringCell.TYPE).createSpec());
    	
    	colSpecs.add(new DataColumnSpecCreator("Include Filter Expression", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Include Filter Match Type", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Include Filter Field", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Include Filter Case Sensitive", StringCell.TYPE).createSpec());
    	
    	return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }

}


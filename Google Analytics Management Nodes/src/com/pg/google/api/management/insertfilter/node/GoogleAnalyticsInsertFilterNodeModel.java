package com.pg.google.api.management.insertfilter.node;

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
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

import com.google.api.services.analytics.model.Filter;
import com.google.api.services.analytics.model.Filter.AdvancedDetails;
import com.google.api.services.analytics.model.Filter.LowercaseDetails;
import com.google.api.services.analytics.model.Filter.SearchAndReplaceDetails;
import com.google.api.services.analytics.model.Filter.UppercaseDetails;
import com.google.api.services.analytics.model.FilterExpression;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;


/**
 * This is the model implementation of GoogleAnalyticsInsertFilter.
 * 
 *
 * @author 
 */
public class GoogleAnalyticsInsertFilterNodeModel extends NodeModel {
    
private GoogleAnalyticsInsertFilterConfiguration config = new GoogleAnalyticsInsertFilterConfiguration();	
	
    /**
     * Constructor for the node model.
     */
	protected GoogleAnalyticsInsertFilterNodeModel() {
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
		
    	DataTableSpec spec = createSpec();
    	BufferedDataContainer container = exec.createDataContainer(spec);
    	
    	List<DataCell> cells = new ArrayList<DataCell>(spec.getNumColumns());
    	
    	cells.add(new StringCell(config.getFilterName() ));
    	cells.add(new StringCell(config.getFilterType()));
    	cells.add(new StringCell(config.getDetailMatchType()));
    	cells.add(new StringCell(config.getDetailExpressionValue()));
    	cells.add(new StringCell(config.getDetailField()));
    	cells.add(new StringCell(config.getSearchString()));
    	cells.add(new StringCell(config.getFieldAText()));
    	cells.add(new StringCell(config.getFieldBText()));
    	cells.add(new StringCell(config.getOutputText()));
    	cells.add(new StringCell(config.getReplaceString()));
    	cells.add(new StringCell(config.getFieldABox()));
    	cells.add(new StringCell(config.getFieldBBox()));
    	cells.add(new StringCell(config.getOutputBox()));
    	cells.add(new StringCell(StringUtils.join(config.getPermissions(), ",")));
		
		Filter filter = new Filter(); 
		filter = addFilterDetail(filter, config.getFilterType());
		filter.setType(config.getFilterType());
		filter.setName(config.getFilterName());		
								
		try {
    	
        	String response = managementClient.insertFilter(filter);
        	cells.add(new StringCell(response));
        	cells.add(new StringCell("Success"));
        	cells.add(new StringCell(""));
        	container.addRowToTable(new DefaultRow("Row 0", cells));
        	
        	
    	} catch ( IOException exc ) {
    		cells.add(new StringCell(""));
    		cells.add(new StringCell("Failed"));
    		cells.add(new StringCell(exc.getMessage()));
    		container.addRowToTable(new DefaultRow("Row 0", cells));
    	}
		
		    	
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
    protected PortObjectSpec[] configure(PortObjectSpec[] inSpecs)
    		throws InvalidSettingsException {
    	
    	return new PortObjectSpec[] { createSpec() };
    }
    
    
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException {
        
        // TODO: check if user settings are available, fit to the incoming
        // table structure, and the incoming types are feasible for the node
        // to execute. If the node can execute in its current state return
        // the spec of its output data table(s) (if you can, otherwise an array
        // with null elements), or throw an exception with a useful user message

        return new DataTableSpec[]{createSpec()};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {

        // TODO save user settings to the config object.
        
   

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
    	
    	config = new GoogleAnalyticsInsertFilterConfiguration();
    	config.load(settings);
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
    	
    	colSpecs.add(new DataColumnSpecCreator("Filter Name", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Filter Type", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Detail Field", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Detail Match Type", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Detail Expression Value", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Search String", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Extract A", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Extract B", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Output Constructor", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Replace String", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Field A", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Field B", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Output To", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Permissions", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Filter ID", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Status", StringCell.TYPE).createSpec());
    	colSpecs.add(new DataColumnSpecCreator("Error Message", StringCell.TYPE).createSpec());
    	
    	return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }
    
    private Filter addFilterDetail(Filter filter, String filter_type){
       
    	if(filter_type == "INCLUDE")
        {
        	FilterExpression include = new FilterExpression();
			include.setField(config.getDetailField());
			include.setExpressionValue(config.getDetailExpressionValue());
			include.setCaseSensitive(config.getCase_sensitive());
        	filter.setIncludeDetails(include);
        }
        else if (filter_type == "EXCLUDE")
    	{
    		FilterExpression exclude = new FilterExpression();
    		exclude.setField(config.getDetailField());
			exclude.setExpressionValue(config.getDetailExpressionValue());
			exclude.setCaseSensitive(config.getCase_sensitive());
			filter.setExcludeDetails(exclude);
       	}
    	else if(filter_type == "LOWERCASE"){
    		LowercaseDetails lower = new LowercaseDetails();
    		lower.setField(config.getDetailField());
			filter.setLowercaseDetails(lower);
        }
        else if (filter_type == "UPPERCASE"){
    		UppercaseDetails upper = new UppercaseDetails();
    		upper.setField(config.getDetailField());
			filter.setUppercaseDetails(upper);
    	}
    	else if(filter_type == "SEARCH_AND_REPLACE"){
    		SearchAndReplaceDetails search = new SearchAndReplaceDetails();
			search.setField(config.getDetailField());
			search.setSearchString(config.getSearchString());
			search.setReplaceString(config.getReplaceString());
			search.setCaseSensitive(config.getCase_sensitive());
    		filter.setSearchAndReplaceDetails(search);
    	}
    	else if(filter_type == "ADVANCED"){
    		AdvancedDetails advanced = new AdvancedDetails();
    		advanced.setFieldA(config.getFieldABox());
    		advanced.setExtractA(config.getFieldAText());
    		advanced.setFieldB(config.getFieldABox());
    		advanced.setExtractB(config.getFieldBText());
    		advanced.setOutputToField(config.getOutputBox());
    		advanced.setOutputConstructor(config.getOutputText());
    		advanced.setFieldARequired(config.getFieldAChk());
    		advanced.setFieldBRequired(config.getFieldBChk());
    		advanced.setOverrideOutputField(config.getOverride());
    		advanced.setCaseSensitive(config.getCase_sensitive());
			filter.setAdvancedDetails(advanced);
    	}
    	return filter; 
    }
}


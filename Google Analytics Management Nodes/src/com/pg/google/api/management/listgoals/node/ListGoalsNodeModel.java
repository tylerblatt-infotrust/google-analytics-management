package com.pg.google.api.management.listgoals.node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.collection.CollectionCellFactory;
import org.knime.core.data.collection.ListCell;
import org.knime.core.data.date.DateAndTimeCell;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;

import com.google.api.services.analytics.model.GaData.ProfileInfo;
import com.google.api.services.analytics.model.Goal;
import com.google.api.services.analytics.model.Goal.EventDetails;
import com.google.api.services.analytics.model.Goal.EventDetails.EventConditions;
import com.google.api.services.analytics.model.Goal.UrlDestinationDetails;
import com.google.api.services.analytics.model.Goal.VisitNumPagesDetails;
import com.google.api.services.analytics.model.Goal.UrlDestinationDetails.Steps;
import com.pg.google.api.analytics.connector.data.GoogleAnalyticsConnectionPortObject;
import com.pg.google.api.management.data.GoogleAnalyticsManagementClient;
import com.pg.knime.node.StandardTrackedNodeModel;

/**
 * This is the model implementation of ListGoals.
 * 
 *
 * @author P&G, eBusiness
 */
public class ListGoalsNodeModel extends StandardTrackedNodeModel {
    
    /**
     * Constructor for the node model.
     */
    protected ListGoalsNodeModel() {
        
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
    	
    	ProfileInfo profileInfo = managementClient.getProfileInfo();
    	List<Goal> goals = managementClient.getGoals();
    	
    	DataTableSpec spec = createSpec();
    	BufferedDataContainer container = exec.createDataContainer(createSpec());
    	
    	int row = 0;
    	for ( Goal goal : goals ) {
    		List<DataCell> cells = new ArrayList<DataCell>(spec.getNumColumns());
            
    		cells.add(new StringCell(goal.getAccountId()));
    		cells.add(new StringCell(goal.getWebPropertyId()));
    		cells.add(new StringCell(goal.getProfileId()));
    		cells.add(new StringCell(profileInfo.getProfileName()));
    		
    		cells.add(new StringCell(goal.getId()));
    		cells.add(new StringCell(goal.getName()));
    		cells.add(new DoubleCell(goal.getValue()));
    		cells.add(new StringCell(goal.getType()));
    		cells.add(BooleanCell.get(goal.getActive()));
    		
    		// Visit Time on Site
    		if ( goal.getVisitNumPagesDetails() != null ) {
    			VisitNumPagesDetails details = goal.getVisitNumPagesDetails();
    			
    			cells.add(new StringCell(details.getComparisonType()));
        		cells.add(new DoubleCell(details.getComparisonValue()));
        		
    		} else {
    			cells.add(new StringCell(""));
        		cells.add(new DoubleCell(0));
    		}
    		
    		
    		// Url Details Collection
    		if ( goal.getUrlDestinationDetails() != null ) {
    		
    			UrlDestinationDetails details = goal.getUrlDestinationDetails();
    			
    			cells.add(new StringCell(details.getUrl()));
    			cells.add(BooleanCell.get(details.getCaseSensitive()));
    			cells.add(new StringCell(details.getMatchType()));
    			cells.add(BooleanCell.get(details.getFirstStepRequired()));
    			
    			if ( details.getSteps() != null ) {
    				cells.add(new IntCell(details.getSteps().size()));
        			
    				Collection<StringCell> stepCollection = new ArrayList<StringCell>(details.getSteps().size());
                	for ( Steps step : details.getSteps() ) {
                		stepCollection.add(new StringCell(step.getUrl()));
                	}
                	
                	cells.add(CollectionCellFactory.createListCell(stepCollection));;
        			
    			} else {
    				cells.add(new IntCell(0));
        			cells.add(CollectionCellFactory.createListCell(new ArrayList<StringCell>()));
    			}
    			
    		} else {
    			cells.add(new StringCell(""));
    			cells.add(BooleanCell.get(false));
    			cells.add(new StringCell(""));
    			cells.add(BooleanCell.get(false));
    			cells.add(new IntCell(0));
    			cells.add(CollectionCellFactory.createListCell(new ArrayList<StringCell>()));
        	}
    		
    		// Event Details
    		if ( goal.getEventDetails() != null ) {
    			EventDetails details = goal.getEventDetails();
    			
    			cells.add(BooleanCell.get(details.getUseEventValue()));
    			
    			if ( details.getEventConditions() != null ) {
    				Collection<StringCell> conditionCollection = new ArrayList<StringCell>(details.getEventConditions().size());
        	        for ( EventConditions condition: details.getEventConditions() ) {
        	        	conditionCollection.add(new StringCell(condition.getType() + ":" + condition.getExpression()));
        	        }
        	        cells.add(CollectionCellFactory.createListCell(conditionCollection));
    			} else {
    				cells.add(CollectionCellFactory.createListCell(new ArrayList<StringCell>()));
    			}
    			
    		} else {
    			cells.add(BooleanCell.get(false));
    			cells.add(CollectionCellFactory.createListCell(new ArrayList<StringCell>()));
    		}
    		
    		
    		cells.add(new DateAndTimeCell(goal.getCreated().getValue(), true, true, false));
    		cells.add(new DateAndTimeCell(goal.getUpdated().getValue(), true, true, false));
    		
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
        
        // Add additional columns to DataSpec
        colSpecs.add(new DataColumnSpecCreator("Account Id", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Property Id", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Profile Id", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Profile Name", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Goal Id", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Goal Name", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Goal Value", DoubleCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Goal Type", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Goal Active", BooleanCell.TYPE).createSpec());
        
        // Visit Page Num
        colSpecs.add(new DataColumnSpecCreator("Page Number Comparison Type", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Page Number Value", DoubleCell.TYPE).createSpec());
        
        // Url Destination Details
        colSpecs.add(new DataColumnSpecCreator("Desination Url", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Url Case Sensitive", BooleanCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Url Match Type", StringCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Url First Step Required", BooleanCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Url Num of Steps", IntCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Url Steps", ListCell.getCollectionType(StringCell.TYPE)).createSpec());
        
        // Event Details
        colSpecs.add(new DataColumnSpecCreator("Event Use Value", BooleanCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Event Conditions", ListCell.getCollectionType(StringCell.TYPE)).createSpec());
        
        
        colSpecs.add(new DataColumnSpecCreator("Goal Created", DateAndTimeCell.TYPE).createSpec());
        colSpecs.add(new DataColumnSpecCreator("Goal Updated", DateAndTimeCell.TYPE).createSpec());
        
        return new DataTableSpec(colSpecs.toArray(new DataColumnSpec[colSpecs.size()]));
    }
    
}


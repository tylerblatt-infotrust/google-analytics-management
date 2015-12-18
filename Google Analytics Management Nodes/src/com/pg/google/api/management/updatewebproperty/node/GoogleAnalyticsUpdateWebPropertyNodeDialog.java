package com.pg.google.api.management.updatewebproperty.node;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.pg.knime.node.StandardTrackedNodeDialogPane;


/**
 * <code>NodeDialog</code> for the "GoogleAnalyticsUpdateWebProperty" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author 
 */
public class GoogleAnalyticsUpdateWebPropertyNodeDialog extends StandardTrackedNodeDialogPane {

	private GoogleAnalyticsUpdateWebPropertyConfig config = new GoogleAnalyticsUpdateWebPropertyConfig();
	
	private JTextArea wpId = new JTextArea();
	private JTextArea wpKind = new JTextArea();
	private JTextArea wpSelfLink = new JTextArea();
	private JTextArea wpAccountId = new JTextArea();
	private JTextArea wpInternalWebPropertyId = new JTextArea();
	private JTextArea wpName = new JTextArea();
	private JTextArea wpWebsiteUrl = new JTextArea();
	private JTextArea wpLevel = new JTextArea();
	private JTextArea wpProfileCount = new JTextArea();
	private JComboBox<String> wpIndustryVertical = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"UNSPECIFIED", "ARTS_AND_ENTERTAINMENT", "AUTOMOTIVE", "BEAUTY_AND_FITNESS", "BOOKS_AND_LITERATURE", "BUSINESS_AND_INDUSTRIAL_MARKETS", "COMPUTERS_AND_ELECTRONICS", "FINANCE", "FOOD_AND_DRINK", "GAMES", "HEALTHCARE", "HOBBIES_AND_LEISURE", "HOME_AND_GARDEN", "INTERNET_AND_TELECOM", "JOBS_AND_EDUCATION", "LAW_AND_GOVERNMENT", "NEWS", "ONLINE_COMMUNITIES", "OTHER", "PEOPLE_AND_SOCIETY", "PETS_AND_ANIMALS", "REAL_ESTATE", "REFERENCE", "SCIENCE", "SHOPPING", "SPORTS", "TRAVEL" }));
	private JTextArea wpDefaultProfileId = new JTextArea();
	private JTextArea wpPermissionsEffective = new JTextArea();
	private JTextArea parentLinkType = new JTextArea();
	private JTextArea parentLinkHref = new JTextArea();
	private JTextArea childLinkType = new JTextArea();
	private JTextArea childLinkHref = new JTextArea();

	
	
    protected GoogleAnalyticsUpdateWebPropertyNodeDialog() {
        super();
        
        addTab ( 
        		"Settings",
        		buildStandardPanel(
        			new PanelBuilder()
        			.add("Id (UA-XXXXX-YY)",wpId)
        			.add("Kind",wpKind)
        			.add("Name",wpName)
        			.add("Website Url",wpWebsiteUrl)
        			.add("Level",wpLevel)
        			.add("Profile Count",wpProfileCount)
        			.add("Industry Vertical",wpIndustryVertical)
        			.add("Default Profile Id",wpDefaultProfileId)
        			.add("Permissions Effective",wpPermissionsEffective)
        			.build()
        		)
        	);
    }


    @Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {

		
    	config.setWpId(wpId.getText());
		config.setWpKind(wpKind.getText());
		config.setWpSelfLink(wpSelfLink.getText());
		config.setWpAccountId(wpAccountId.getText());
		config.setWpInternalWebPropertyId(wpInternalWebPropertyId.getText());
		config.setWpName(wpName.getText());
		config.setWpWebsiteUrl(wpWebsiteUrl.getText());
		config.setWpLevel(wpLevel.getText());
		config.setWpProfileCount(Integer.parseInt(wpProfileCount.getText()));
		config.setWpIndustryVertical((String)wpIndustryVertical.getSelectedItem());
		config.setWpDefaultProfileId(Long.parseLong(wpDefaultProfileId.getText(), 10));
		config.setWpPermissionsEffective(wpPermissionsEffective.getText());
		config.setParentLinkType(parentLinkType.getText());
		config.setParentLinkHref(parentLinkHref.getText());
		config.setChildLinkType(childLinkType.getText());
		config.setChildLinkHref(childLinkHref.getText());
    	config.save(settings);



		
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {

		config = new GoogleAnalyticsUpdateWebPropertyConfig();
		config.load(settings);
		

		wpId.setText(config.getWpId());
		wpKind.setText(config.getWpKind());
		wpSelfLink.setText(config.getWpSelfLink());
		wpAccountId.setText(config.getWpAccountId());
		wpInternalWebPropertyId.setText(config.getWpInternalWebPropertyId());
		wpName.setText(config.getWpName());
		wpWebsiteUrl.setText(config.getWpWebsiteUrl());
		wpLevel.setText(config.getWpLevel());
		wpProfileCount.setText(Integer.toString(config.getWpProfileCount()));
		wpIndustryVertical.setSelectedItem(config.getWpIndustryVertical());
		wpDefaultProfileId.setText(Long.toString(config.getWpDefaultProfileId()));
		wpPermissionsEffective.setText(config.getWpPermissionsEffective());
		parentLinkType.setText(config.getParentLinkType());
		parentLinkHref.setText(config.getParentLinkHref());
		childLinkType.setText(config.getChildLinkType());
		childLinkHref.setText(config.getChildLinkHref());
	}
}


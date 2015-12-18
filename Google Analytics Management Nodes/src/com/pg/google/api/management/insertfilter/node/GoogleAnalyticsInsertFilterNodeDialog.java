package com.pg.google.api.management.insertfilter.node;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.NotConfigurableException;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.port.PortObjectSpec;

import com.pg.knime.node.StandardTrackedNodeDialogPane;


/**
 * <code>NodeDialog</code> for the "GoogleAnalyticsInsertFilter" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author 
 */
public class GoogleAnalyticsInsertFilterNodeDialog extends StandardTrackedNodeDialogPane {

	private JTextArea filterName = new JTextArea();
	private JTextArea detailExpressionValue = new JTextArea();
	private JTextArea searchString = new JTextArea();
	private JTextArea fieldAText = new JTextArea();
	private JTextArea fieldBText= new JTextArea();
	private JTextArea outputText= new JTextArea();	
	private JTextArea replaceString = new JTextArea();
	
	private JComboBox<String> filterType = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] { "INCLUDE", "EXCLUDE", "LOWERCASE",
		      "UPPERCASE", "SEARCH_AND_REPLACE", "ADVANCED"} ));
	private JComboBox<String>  detailMatchType = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] { "BEGINS_WITH", "EQUAL", "ENDS_WITH", "CONTAINS", "MATCHES"} ));
	private JComboBox<String>  detailField = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"PAGE_REQUEST_URI", "PAGE_HOSTNAME", "PAGE_TITLE", "REFERRAL", "COST_DATA_URI", "HIT_TYPE", "INTERNAL_SEARCH_TERM", "INTERNAL_SEARCH_TYPE", "SOURCE_PROPERTY_TRACKING_ID", "CAMPAIGN_SOURCE", "CAMPAIGN_MEDIUM", "CAMPAIGN_NAME", "CAMPAIGN_AD_GROUP", "CAMPAIGN_TERM", "CAMPAIGN_CONTENT", "CAMPAIGN_CODE", "CAMPAIGN_REFERRAL_PATH", "TRANSACTION_COUNTRY", "TRANSACTION_REGION", "TRANSACTION_CITY", "TRANSACTION_AFFILIATION", "ITEM_NAME", "ITEM_CODE", "ITEM_VARIATION", "TRANSACTION_ID", "TRANSACTION_CURRENCY_CODE", "PRODUCT_ACTION_TYPE", "BROWSER", "BROWSER_VERSION", "BROWSER_SIZE", "PLATFORM", "PLATFORM_VERSION", "LANGUAGE", "SCREEN_RESOLUTION", "SCREEN_COLORS", "JAVA_ENABLED", "FLASH_VERSION", "GEO_SPEED", "VISITOR_TYPE", "GEO_ORGANIZATION", "GEO_DOMAIN", "GEO_IP_ADDRESS", "GEO_IP_VERSION", "GEO_COUNTRY", "GEO_REGION", "GEO_CITY", "EVENT_CATEGORY", "EVENT_ACTION", "EVENT_LABEL", "CUSTOM_FIELD_1", "CUSTOM_FIELD_2", "USER_DEFINED_VALUE", "APP_ID", "APP_INSTALLER_ID", "APP_NAME", "APP_VERSION", "SCREEN", "IS_APP", "IS_FATAL_EXCEPTION", "EXCEPTION_DESCRIPTION", "IS_MOBILE", "IS_TABLET", "DEVICE_CATEGORY", "MOBILE_HAS_QWERTY_KEYBOARD", "MOBILE_HAS_NFC_SUPPORT", "MOBILE_HAS_CELLULAR_RADIO", "MOBILE_HAS_WIFI_SUPPORT", "MOBILE_BRAND_NAME", "MOBILE_MODEL_NAME", "MOBILE_MARKETING_NAME", "MOBILE_POINTING_METHOD", "SOCIAL_NETWORK", "SOCIAL_ACTION", "SOCIAL_ACTION_TARGET"} ));
	private JComboBox<String>  fieldABox = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"PAGE_REQUEST_URI", "PAGE_HOSTNAME", "PAGE_TITLE", "REFERRAL", "COST_DATA_URI", "HIT_TYPE", "INTERNAL_SEARCH_TERM", "INTERNAL_SEARCH_TYPE", "SOURCE_PROPERTY_TRACKING_ID", "CAMPAIGN_SOURCE", "CAMPAIGN_MEDIUM", "CAMPAIGN_NAME", "CAMPAIGN_AD_GROUP", "CAMPAIGN_TERM", "CAMPAIGN_CONTENT", "CAMPAIGN_CODE", "CAMPAIGN_REFERRAL_PATH", "TRANSACTION_COUNTRY", "TRANSACTION_REGION", "TRANSACTION_CITY", "TRANSACTION_AFFILIATION", "ITEM_NAME", "ITEM_CODE", "ITEM_VARIATION", "TRANSACTION_ID", "TRANSACTION_CURRENCY_CODE", "PRODUCT_ACTION_TYPE", "BROWSER", "BROWSER_VERSION", "BROWSER_SIZE", "PLATFORM", "PLATFORM_VERSION", "LANGUAGE", "SCREEN_RESOLUTION", "SCREEN_COLORS", "JAVA_ENABLED", "FLASH_VERSION", "GEO_SPEED", "VISITOR_TYPE", "GEO_ORGANIZATION", "GEO_DOMAIN", "GEO_IP_ADDRESS", "GEO_IP_VERSION", "GEO_COUNTRY", "GEO_REGION", "GEO_CITY", "EVENT_CATEGORY", "EVENT_ACTION", "EVENT_LABEL", "CUSTOM_FIELD_1", "CUSTOM_FIELD_2", "USER_DEFINED_VALUE", "APP_ID", "APP_INSTALLER_ID", "APP_NAME", "APP_VERSION", "SCREEN", "IS_APP", "IS_FATAL_EXCEPTION", "EXCEPTION_DESCRIPTION", "IS_MOBILE", "IS_TABLET", "DEVICE_CATEGORY", "MOBILE_HAS_QWERTY_KEYBOARD", "MOBILE_HAS_NFC_SUPPORT", "MOBILE_HAS_CELLULAR_RADIO", "MOBILE_HAS_WIFI_SUPPORT", "MOBILE_BRAND_NAME", "MOBILE_MODEL_NAME", "MOBILE_MARKETING_NAME", "MOBILE_POINTING_METHOD", "SOCIAL_NETWORK", "SOCIAL_ACTION", "SOCIAL_ACTION_TARGET"} ));
	private JComboBox<String>  fieldBBox = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"PAGE_REQUEST_URI", "PAGE_HOSTNAME", "PAGE_TITLE", "REFERRAL", "COST_DATA_URI", "HIT_TYPE", "INTERNAL_SEARCH_TERM", "INTERNAL_SEARCH_TYPE", "SOURCE_PROPERTY_TRACKING_ID", "CAMPAIGN_SOURCE", "CAMPAIGN_MEDIUM", "CAMPAIGN_NAME", "CAMPAIGN_AD_GROUP", "CAMPAIGN_TERM", "CAMPAIGN_CONTENT", "CAMPAIGN_CODE", "CAMPAIGN_REFERRAL_PATH", "TRANSACTION_COUNTRY", "TRANSACTION_REGION", "TRANSACTION_CITY", "TRANSACTION_AFFILIATION", "ITEM_NAME", "ITEM_CODE", "ITEM_VARIATION", "TRANSACTION_ID", "TRANSACTION_CURRENCY_CODE", "PRODUCT_ACTION_TYPE", "BROWSER", "BROWSER_VERSION", "BROWSER_SIZE", "PLATFORM", "PLATFORM_VERSION", "LANGUAGE", "SCREEN_RESOLUTION", "SCREEN_COLORS", "JAVA_ENABLED", "FLASH_VERSION", "GEO_SPEED", "VISITOR_TYPE", "GEO_ORGANIZATION", "GEO_DOMAIN", "GEO_IP_ADDRESS", "GEO_IP_VERSION", "GEO_COUNTRY", "GEO_REGION", "GEO_CITY", "EVENT_CATEGORY", "EVENT_ACTION", "EVENT_LABEL", "CUSTOM_FIELD_1", "CUSTOM_FIELD_2", "USER_DEFINED_VALUE", "APP_ID", "APP_INSTALLER_ID", "APP_NAME", "APP_VERSION", "SCREEN", "IS_APP", "IS_FATAL_EXCEPTION", "EXCEPTION_DESCRIPTION", "IS_MOBILE", "IS_TABLET", "DEVICE_CATEGORY", "MOBILE_HAS_QWERTY_KEYBOARD", "MOBILE_HAS_NFC_SUPPORT", "MOBILE_HAS_CELLULAR_RADIO", "MOBILE_HAS_WIFI_SUPPORT", "MOBILE_BRAND_NAME", "MOBILE_MODEL_NAME", "MOBILE_MARKETING_NAME", "MOBILE_POINTING_METHOD", "SOCIAL_NETWORK", "SOCIAL_ACTION", "SOCIAL_ACTION_TARGET"} ));
	private JComboBox<String>  outputBox = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"PAGE_REQUEST_URI", "PAGE_HOSTNAME", "PAGE_TITLE", "REFERRAL", "COST_DATA_URI", "HIT_TYPE", "INTERNAL_SEARCH_TERM", "INTERNAL_SEARCH_TYPE", "SOURCE_PROPERTY_TRACKING_ID", "CAMPAIGN_SOURCE", "CAMPAIGN_MEDIUM", "CAMPAIGN_NAME", "CAMPAIGN_AD_GROUP", "CAMPAIGN_TERM", "CAMPAIGN_CONTENT", "CAMPAIGN_CODE", "CAMPAIGN_REFERRAL_PATH", "TRANSACTION_COUNTRY", "TRANSACTION_REGION", "TRANSACTION_CITY", "TRANSACTION_AFFILIATION", "ITEM_NAME", "ITEM_CODE", "ITEM_VARIATION", "TRANSACTION_ID", "TRANSACTION_CURRENCY_CODE", "PRODUCT_ACTION_TYPE", "BROWSER", "BROWSER_VERSION", "BROWSER_SIZE", "PLATFORM", "PLATFORM_VERSION", "LANGUAGE", "SCREEN_RESOLUTION", "SCREEN_COLORS", "JAVA_ENABLED", "FLASH_VERSION", "GEO_SPEED", "VISITOR_TYPE", "GEO_ORGANIZATION", "GEO_DOMAIN", "GEO_IP_ADDRESS", "GEO_IP_VERSION", "GEO_COUNTRY", "GEO_REGION", "GEO_CITY", "EVENT_CATEGORY", "EVENT_ACTION", "EVENT_LABEL", "CUSTOM_FIELD_1", "CUSTOM_FIELD_2", "USER_DEFINED_VALUE", "APP_ID", "APP_INSTALLER_ID", "APP_NAME", "APP_VERSION", "SCREEN", "IS_APP", "IS_FATAL_EXCEPTION", "EXCEPTION_DESCRIPTION", "IS_MOBILE", "IS_TABLET", "DEVICE_CATEGORY", "MOBILE_HAS_QWERTY_KEYBOARD", "MOBILE_HAS_NFC_SUPPORT", "MOBILE_HAS_CELLULAR_RADIO", "MOBILE_HAS_WIFI_SUPPORT", "MOBILE_BRAND_NAME", "MOBILE_MODEL_NAME", "MOBILE_MARKETING_NAME", "MOBILE_POINTING_METHOD", "SOCIAL_NETWORK", "SOCIAL_ACTION", "SOCIAL_ACTION_TARGET"} ));
	
	
	
	private JCheckBox fieldAChk = new JCheckBox("Field A Required");
	private JCheckBox fieldBChk = new JCheckBox("Field B Required");
	private JCheckBox override = new JCheckBox("Override Output Field");
	private JCheckBox chkAgree = new JCheckBox("Make my filter case sensitive");
	
	private PanelMapPair PMP;
	private GoogleAnalyticsInsertFilterConfiguration config = new GoogleAnalyticsInsertFilterConfiguration();
	
    protected GoogleAnalyticsInsertFilterNodeDialog() {
    	
    	PMP = searchableStandardPanel(
    		new PanelBuilder()
    			.add("Detail Expression value", detailExpressionValue)
    			.add("Filter Name", filterName)
    			.add("Filter Type", filterType)
    			.add("Detail Match Type", detailMatchType)
    			.add("Detail Field", detailField)
    			.add("Search String", searchString)
    			.add("Replace String", replaceString)
    			.add("Field A", fieldABox)
    			.add("Extract A", fieldAText)
    			.add("Field B", fieldBBox)
    			.add("Extract B", fieldBText)
    			.add("Output To", outputBox)
    			.add("ConstructoOutputTo", outputText)
    			.add("Field A Checkbox", fieldAChk)
    			.add("Field B Checkbox", fieldBChk)
    			.add("Override Output Field", override)
    			.add("Case Sensitive", chkAgree)
    			.build()
        );
    	
    	JPanel panel = PMP.getPanel();
    	final HashMap<String,Component> componentMap = PMP.getComponentMap();
    	
    	hideComponents(componentMap);
    	showSelectedComponents(componentMap, "INCLUDE");
    	
    	filterType.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	hideComponents(componentMap);
            	showSelectedComponents(componentMap, (String)filterType.getSelectedItem());
            }
        });
   	
    	addTab ("Settings", panel);
    }

    @Override
	protected void saveSettingsTo(NodeSettingsWO settings)
			throws InvalidSettingsException {
        config.setFilterName(filterName.getText());
    	config.setFilterType((String) filterType.getSelectedItem());
    	config.setDetailMatchType((String) detailMatchType.getSelectedItem());
    	config.setDetailExpressionValue(detailExpressionValue.getText());
    	config.setDetailField((String) detailField.getSelectedItem());    	
		config.setSearchString(searchString.getText());
		config.setFieldAText(fieldAText.getText());
		config.setFieldBText(fieldBText.getText());
		config.setOutputText(outputText.getText());
		config.setReplaceString(replaceString.getText());
		config.setFieldABox((String)fieldABox.getSelectedItem());
		config.setFieldBBox((String)fieldBBox.getSelectedItem());
		config.setOutputBox((String)outputBox.getSelectedItem());
		config.setCase_sensitive(chkAgree.isSelected());
		config.setFieldAChk(fieldAChk.isSelected());
		config.setFieldBChk(fieldBChk.isSelected());
		config.setOverride(override.isSelected());
       	config.save(settings);
	
	}
	
	@Override
	protected void loadSettingsFrom(NodeSettingsRO settings,
			PortObjectSpec[] specs) throws NotConfigurableException {

		config = new GoogleAnalyticsInsertFilterConfiguration();
		config.load(settings);
		
		filterName.setText(config.getFilterName());
		filterType.setSelectedItem(config.getFilterType());
		detailMatchType.setSelectedItem(config.getDetailMatchType());
		detailExpressionValue.setText(config.getDetailExpressionValue());
		detailField.setSelectedItem(config.getDetailField());
		searchString.setText(config.getSearchString());
		fieldAText.setText(config.getFieldAText());
		fieldBText.setText(config.getFieldBText());
		outputText.setText(config.getOutputText());
		replaceString.setText(config.getReplaceString());
		fieldABox.setSelectedItem(config.getFieldABox());
		fieldBBox.setSelectedItem(config.getFieldBBox());
		outputBox.setSelectedItem(config.getOutputBox());
		chkAgree.setSelected(config.getCase_sensitive());
		fieldAChk.setSelected(config.getFieldAChk());
		fieldBChk.setSelected(config.getFieldBChk());
		override.setSelected(config.getOverride());
	}
	
	private void showSelectedComponents(HashMap<String,Component> componentMap, String filter_type)
	{
		if(filter_type == "INCLUDE" || filter_type == "EXCLUDE")
    	{
    		detailField.setVisible(true);
    		componentMap.get("Detail Field").setVisible(true);
    		detailExpressionValue.setVisible(true);
    		componentMap.get("Detail Expression value").setVisible(true);
    		chkAgree.setVisible(true);
    		componentMap.get("Case Sensitive").setVisible(true);
       	}
    	else if(filter_type == "LOWERCASE" || filter_type == "UPPERCASE"){
    		detailField.setVisible(true);
    		componentMap.get("Detail Field").setVisible(true);
    	}
    	else if(filter_type == "SEARCH_AND_REPLACE"){
    		detailField.setVisible(true);
    		componentMap.get("Detail Field").setVisible(true);
    		searchString.setVisible(true);
    		componentMap.get("Search String").setVisible(true);
    		replaceString.setVisible(true);
    		componentMap.get("Replace String").setVisible(true);
    		chkAgree.setVisible(true);
    		componentMap.get("Case Sensitive").setVisible(true);
    	}
    	else if(filter_type == "ADVANCED"){
    		fieldAText.setVisible(true);
    		fieldBText.setVisible(true);
    		outputText.setVisible(true);
    		fieldABox.setVisible(true);
    		fieldBBox.setVisible(true);
    		outputBox.setVisible(true);
    		fieldAChk.setVisible(true);
    		componentMap.get("Override Output Field").setVisible(true);
    		componentMap.get("Extract A").setVisible(true);
    		componentMap.get("Extract B").setVisible(true);
    		componentMap.get("ConstructoOutputTo").setVisible(true);
    		componentMap.get("Field A Checkbox").setVisible(true);
    		componentMap.get("Field B Checkbox").setVisible(true);
    		componentMap.get("Output To").setVisible(true);
    		componentMap.get("Field A").setVisible(true);
    		fieldBChk.setVisible(true);
    		componentMap.get("Field B").setVisible(true);
    		componentMap.get("Override Output Field").setVisible(true);       		
    		override.setVisible(true);
    		chkAgree.setVisible(true);
    		componentMap.get("Case Sensitive").setVisible(true);
    	}
	}
	
	private void hideComponents(HashMap<String,Component> componentMap)
	{
		componentMap.get("Detail Expression value").setVisible(false);
		componentMap.get("Detail Match Type").setVisible(false);
		componentMap.get("Detail Field").setVisible(false);
		componentMap.get("Case Sensitive").setVisible(false);
		componentMap.get("Search String").setVisible(false);
		componentMap.get("Replace String").setVisible(false);
		componentMap.get("Field A Checkbox").setVisible(false);
		componentMap.get("Field B Checkbox").setVisible(false);
		componentMap.get("Override Output Field").setVisible(false);
		componentMap.get("Override Output Field").setVisible(false);
		componentMap.get("Extract A").setVisible(false);
		componentMap.get("Extract B").setVisible(false);
		componentMap.get("ConstructoOutputTo").setVisible(false);
		componentMap.get("Field A").setVisible(false);
		componentMap.get("Field B").setVisible(false);
		componentMap.get("Output To").setVisible(false);
		detailExpressionValue.setVisible(false);
		detailMatchType.setVisible(false);
		detailField.setVisible(false);
		chkAgree.setVisible(false);
		searchString.setVisible(false);
		replaceString.setVisible(false);
		fieldAChk.setVisible(false);
		fieldBChk.setVisible(false);
		override.setVisible(false);
		fieldAText.setVisible(false);
		fieldBText.setVisible(false);
		outputText.setVisible(false);
		fieldABox.setVisible(false);
		fieldBBox.setVisible(false);
		outputBox.setVisible(false);
	}
}


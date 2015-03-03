package com.pg.google.api.management.insertcustomdimensions.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticsInsertCustomDimensions" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsInsertCustomDimensionsNodeFactory 
        extends NodeFactory<GoogleAnalyticsInsertCustomDimensionsNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticsInsertCustomDimensionsNodeModel createNodeModel() {
        return new GoogleAnalyticsInsertCustomDimensionsNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<GoogleAnalyticsInsertCustomDimensionsNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticsInsertCustomDimensionsNodeModel nodeModel) {
        return new GoogleAnalyticsInsertCustomDimensionsNodeView(nodeModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new GoogleAnalyticsInsertCustomDimensionsNodeDialog();
    }

}


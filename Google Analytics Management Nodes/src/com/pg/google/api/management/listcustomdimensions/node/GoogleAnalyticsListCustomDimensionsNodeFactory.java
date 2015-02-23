package com.pg.google.api.management.listcustomdimensions.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticsListCustomDimensions" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsListCustomDimensionsNodeFactory 
        extends NodeFactory<GoogleAnalyticsListCustomDimensionsNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticsListCustomDimensionsNodeModel createNodeModel() {
        return new GoogleAnalyticsListCustomDimensionsNodeModel();
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
    public NodeView<GoogleAnalyticsListCustomDimensionsNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticsListCustomDimensionsNodeModel nodeModel) {
        return new GoogleAnalyticsListCustomDimensionsNodeView(nodeModel);
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
        return new GoogleAnalyticsListCustomDimensionsNodeDialog();
    }

}


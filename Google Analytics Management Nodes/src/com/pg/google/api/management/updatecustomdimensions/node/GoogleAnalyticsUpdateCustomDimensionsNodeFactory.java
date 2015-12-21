package com.pg.google.api.management.updatecustomdimensions.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticsUpdateCustomDimensions" Node.
 * 
 *
 * @author 
 */
public class GoogleAnalyticsUpdateCustomDimensionsNodeFactory 
        extends NodeFactory<GoogleAnalyticsUpdateCustomDimensionsNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticsUpdateCustomDimensionsNodeModel createNodeModel() {
        return new GoogleAnalyticsUpdateCustomDimensionsNodeModel();
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
    public NodeView<GoogleAnalyticsUpdateCustomDimensionsNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticsUpdateCustomDimensionsNodeModel nodeModel) {
        return new GoogleAnalyticsUpdateCustomDimensionsNodeView(nodeModel);
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
        return new GoogleAnalyticsUpdateCustomDimensionsNodeDialog();
    }

}


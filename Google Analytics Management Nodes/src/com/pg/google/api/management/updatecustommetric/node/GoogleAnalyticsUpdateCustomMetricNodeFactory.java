package com.pg.google.api.management.updatecustommetric.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticsUpdateCustomMetric" Node.
 * 
 *
 * @author 
 */
public class GoogleAnalyticsUpdateCustomMetricNodeFactory 
        extends NodeFactory<GoogleAnalyticsUpdateCustomMetricNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticsUpdateCustomMetricNodeModel createNodeModel() {
        return new GoogleAnalyticsUpdateCustomMetricNodeModel();
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
    public NodeView<GoogleAnalyticsUpdateCustomMetricNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticsUpdateCustomMetricNodeModel nodeModel) {
        return new GoogleAnalyticsUpdateCustomMetricNodeView(nodeModel);
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
        return new GoogleAnalyticsUpdateCustomMetricNodeDialog();
    }

}


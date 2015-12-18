package com.pg.google.api.management.insertfilter.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticsInsertFilter" Node.
 * 
 *
 * @author 
 */
public class GoogleAnalyticsInsertFilterNodeFactory 
        extends NodeFactory<GoogleAnalyticsInsertFilterNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticsInsertFilterNodeModel createNodeModel() {
        return new GoogleAnalyticsInsertFilterNodeModel();
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
    public NodeView<GoogleAnalyticsInsertFilterNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticsInsertFilterNodeModel nodeModel) {
        return new GoogleAnalyticsInsertFilterNodeView(nodeModel);
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
        return new GoogleAnalyticsInsertFilterNodeDialog();
    }

}


package com.pg.google.api.management.updatewebproperty.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticsUpdateWebProperty" Node.
 * 
 *
 * @author 
 */
public class GoogleAnalyticsUpdateWebPropertyNodeFactory 
        extends NodeFactory<GoogleAnalyticsUpdateWebPropertyNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticsUpdateWebPropertyNodeModel createNodeModel() {
        return new GoogleAnalyticsUpdateWebPropertyNodeModel();
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
    public NodeView<GoogleAnalyticsUpdateWebPropertyNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticsUpdateWebPropertyNodeModel nodeModel) {
        return new GoogleAnalyticsUpdateWebPropertyNodeView(nodeModel);
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
        return new GoogleAnalyticsUpdateWebPropertyNodeDialog();
    }

}


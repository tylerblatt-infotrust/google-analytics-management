package com.pg.google.api.management.updateprofile.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticsUpdateProfile" Node.
 * 
 *
 * @author 
 */
public class GoogleAnalyticsUpdateProfileNodeFactory 
        extends NodeFactory<GoogleAnalyticsUpdateProfileNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticsUpdateProfileNodeModel createNodeModel() {
        return new GoogleAnalyticsUpdateProfileNodeModel();
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
    public NodeView<GoogleAnalyticsUpdateProfileNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticsUpdateProfileNodeModel nodeModel) {
        return new GoogleAnalyticsUpdateProfileNodeView(nodeModel);
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
        return new GoogleAnalyticsUpdateProfileNodeDialog();
    }

}


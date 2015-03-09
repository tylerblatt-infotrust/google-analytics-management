package com.pg.google.api.management.addusertoprofile;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticsAddUserToProfile" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsAddUserToProfileNodeFactory 
        extends NodeFactory<GoogleAnalyticsAddUserToProfileNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticsAddUserToProfileNodeModel createNodeModel() {
        return new GoogleAnalyticsAddUserToProfileNodeModel();
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
    public NodeView<GoogleAnalyticsAddUserToProfileNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticsAddUserToProfileNodeModel nodeModel) {
        return new GoogleAnalyticsAddUserToProfileNodeView(nodeModel);
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
        return new GoogleAnalyticsAddUserToProfileNodeDialog();
    }

}


package com.pg.google.api.management.listusers.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticUsers" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class GoogleAnalyticUsersNodeFactory 
        extends NodeFactory<GoogleAnalyticUsersNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticUsersNodeModel createNodeModel() {
        return new GoogleAnalyticUsersNodeModel();
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
    public NodeView<GoogleAnalyticUsersNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticUsersNodeModel nodeModel) {
        return new GoogleAnalyticUsersNodeView(nodeModel);
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
        return new GoogleAnalyticUsersNodeDialog();
    }

}


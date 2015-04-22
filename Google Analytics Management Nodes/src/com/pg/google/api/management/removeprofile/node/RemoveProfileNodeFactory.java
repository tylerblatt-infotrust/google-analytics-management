package com.pg.google.api.management.removeprofile.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "RemoveProfile" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class RemoveProfileNodeFactory 
        extends NodeFactory<RemoveProfileNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RemoveProfileNodeModel createNodeModel() {
        return new RemoveProfileNodeModel();
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
    public NodeView<RemoveProfileNodeModel> createNodeView(final int viewIndex,
            final RemoveProfileNodeModel nodeModel) {
        return new RemoveProfileNodeView(nodeModel);
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
        return new RemoveProfileNodeDialog();
    }

}


package com.pg.google.api.management.removeuser.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "RemoveUser" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class RemoveUserNodeFactory 
        extends NodeFactory<RemoveUserNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RemoveUserNodeModel createNodeModel() {
        return new RemoveUserNodeModel();
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
    public NodeView<RemoveUserNodeModel> createNodeView(final int viewIndex,
            final RemoveUserNodeModel nodeModel) {
        return new RemoveUserNodeView(nodeModel);
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
        return new RemoveUserNodeDialog();
    }

}


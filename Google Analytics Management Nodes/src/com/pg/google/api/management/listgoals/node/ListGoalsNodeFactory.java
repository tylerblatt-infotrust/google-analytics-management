package com.pg.google.api.management.listgoals.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "ListGoals" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class ListGoalsNodeFactory 
        extends NodeFactory<ListGoalsNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ListGoalsNodeModel createNodeModel() {
        return new ListGoalsNodeModel();
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
    public NodeView<ListGoalsNodeModel> createNodeView(final int viewIndex,
            final ListGoalsNodeModel nodeModel) {
        return new ListGoalsNodeView(nodeModel);
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
        return new ListGoalsNodeDialog();
    }

}


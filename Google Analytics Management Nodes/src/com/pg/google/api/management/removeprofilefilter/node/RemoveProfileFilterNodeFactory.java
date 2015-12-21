package com.pg.google.api.management.removeprofilefilter.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "RemoveProfileFilter" Node.
 * 
 *
 * @author 
 */
public class RemoveProfileFilterNodeFactory 
        extends NodeFactory<RemoveProfileFilterNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RemoveProfileFilterNodeModel createNodeModel() {
        return new RemoveProfileFilterNodeModel();
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
    public NodeView<RemoveProfileFilterNodeModel> createNodeView(final int viewIndex,
            final RemoveProfileFilterNodeModel nodeModel) {
        return new RemoveProfileFilterNodeView(nodeModel);
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
        return new RemoveProfileFilterNodeDialog();
    }

}


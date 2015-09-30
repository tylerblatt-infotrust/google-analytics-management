package com.pg.google.api.management.insertprofilefilter;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "InsertProfileFilterNode" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class InsertProfileFilterNodeNodeFactory 
        extends NodeFactory<InsertProfileFilterNodeNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InsertProfileFilterNodeNodeModel createNodeModel() {
        return new InsertProfileFilterNodeNodeModel();
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
    public NodeView<InsertProfileFilterNodeNodeModel> createNodeView(final int viewIndex,
            final InsertProfileFilterNodeNodeModel nodeModel) {
        return new InsertProfileFilterNodeNodeView(nodeModel);
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
        return new InsertProfileFilterNodeNodeDialog();
    }

}


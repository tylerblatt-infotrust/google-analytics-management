package com.pg.google.api.management.insertprofile.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "InsertProfile" Node.
 * 
 *
 * @author 
 */
public class InsertProfileNodeFactory 
        extends NodeFactory<InsertProfileNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InsertProfileNodeModel createNodeModel() {
        return new InsertProfileNodeModel();
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
    public NodeView<InsertProfileNodeModel> createNodeView(final int viewIndex,
            final InsertProfileNodeModel nodeModel) {
        return new InsertProfileNodeView(nodeModel);
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
        return new InsertProfileNodeDialog();
    }

}


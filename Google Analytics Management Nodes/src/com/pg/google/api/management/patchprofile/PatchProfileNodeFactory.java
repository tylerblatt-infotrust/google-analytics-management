package com.pg.google.api.management.patchprofile;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "PatchProfile" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class PatchProfileNodeFactory 
        extends NodeFactory<PatchProfileNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public PatchProfileNodeModel createNodeModel() {
        return new PatchProfileNodeModel();
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
    public NodeView<PatchProfileNodeModel> createNodeView(final int viewIndex,
            final PatchProfileNodeModel nodeModel) {
        return new PatchProfileNodeView(nodeModel);
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
        return new PatchProfileNodeDialog();
    }

}


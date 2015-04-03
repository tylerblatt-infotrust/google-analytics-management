package com.pg.google.api.management.listprofilefilters;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "ListProfileFilters" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class ListProfileFiltersNodeFactory 
        extends NodeFactory<ListProfileFiltersNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ListProfileFiltersNodeModel createNodeModel() {
        return new ListProfileFiltersNodeModel();
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
    public NodeView<ListProfileFiltersNodeModel> createNodeView(final int viewIndex,
            final ListProfileFiltersNodeModel nodeModel) {
        return new ListProfileFiltersNodeView(nodeModel);
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
        return new ListProfileFiltersNodeDialog();
    }

}


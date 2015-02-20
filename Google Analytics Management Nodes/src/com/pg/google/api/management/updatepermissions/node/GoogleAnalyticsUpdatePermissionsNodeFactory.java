package com.pg.google.api.management.updatepermissions.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "GoogleAnalyticsUpdatePermissions" Node.
 * 
 *
 * @author P&G, eBusiness
 */
public class GoogleAnalyticsUpdatePermissionsNodeFactory 
        extends NodeFactory<GoogleAnalyticsUpdatePermissionsNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoogleAnalyticsUpdatePermissionsNodeModel createNodeModel() {
        return new GoogleAnalyticsUpdatePermissionsNodeModel();
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
    public NodeView<GoogleAnalyticsUpdatePermissionsNodeModel> createNodeView(final int viewIndex,
            final GoogleAnalyticsUpdatePermissionsNodeModel nodeModel) {
        return new GoogleAnalyticsUpdatePermissionsNodeView(nodeModel);
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
        return new GoogleAnalyticsUpdatePermissionsNodeDialog();
    }

}


package amerifrance.emt.modules.base.tile;

import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.common.tiles.TileNode;

public class TileEntityPortableNode extends TileNode {

    public TileEntityPortableNode() {
        super();
    }

    @Override
    public NodeModifier getNodeModifier() {
        return NodeModifier.FADING;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.UNSTABLE;
    }
}
package amerifrance.emt.modules.base.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BaseBlockRegistry {

    // Blocks
    public static Block shieldBlock;
    public static Block portableNode;

    public static void registerBlocks() {

        shieldBlock = new BlockShield();
        GameRegistry.registerBlock(shieldBlock, "BlockShield");
        portableNode = new BlockPortableNode();
        GameRegistry.registerBlock(portableNode, "BlockPortableNode");
    }
}

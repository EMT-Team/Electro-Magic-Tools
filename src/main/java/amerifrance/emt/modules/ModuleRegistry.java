package amerifrance.emt.modules;

import amerifrance.emt.ConfigHandler;
import amerifrance.emt.modules.base.BaseRecipeRegistry;
import amerifrance.emt.modules.base.blocks.BaseBlockRegistry;
import amerifrance.emt.modules.base.items.BaseItemRegistry;
import amerifrance.emt.modules.base.tile.BaseTileRegistry;
import amerifrance.emt.modules.ic2.IC2RecipeRegistry;
import amerifrance.emt.modules.ic2.blocks.IC2BlockRegistry;
import amerifrance.emt.modules.ic2.entity.IC2EntityRegistry;
import amerifrance.emt.modules.ic2.items.IC2ItemRegistry;
import amerifrance.emt.modules.ic2.tile.IC2TileRegistry;
import amerifrance.emt.modules.tools.ToolRecipeRegistry;
import amerifrance.emt.modules.tools.items.ToolItemRegistry;
import amerifrance.emt.util.EssentiaOutputs;

import static amerifrance.emt.ElectroMagicTools.logger;

public class ModuleRegistry {

    public static void registerEarlyModules() {

        BaseBlockRegistry.registerBlocks();
        BaseItemRegistry.registerItems();
        BaseTileRegistry.registerBaseTiles();
        BaseRecipeRegistry.init();

        if (ConfigHandler.enableIC2Module) {
            logger.info("Enabling the IC2 module.");
            IC2BlockRegistry.registerBlocks();
            IC2ItemRegistry.registerItems();
            IC2TileRegistry.registerTiles();
            IC2EntityRegistry.registerEntities();
            IC2RecipeRegistry.init();
            EssentiaOutputs.addPrimalOutputs();
            EssentiaOutputs.addOutputs();
        }

        if (ConfigHandler.enableTEModule) {
            logger.info("Enabling the TE module.");
            // Do stuff
        }

        if (ConfigHandler.enableToolModule) {
            logger.info("Enabling the Tool module.");
            ToolItemRegistry.registerItems();
            ToolRecipeRegistry.init();
        }
    }

    public static void registerMidModules() {

        if (ConfigHandler.enableIC2Module) {
            // Do stuff
        }

        if (ConfigHandler.enableTEModule) {
            // Do stuff
        }

        if (ConfigHandler.enableToolModule) {
            // Do stuff
        }
    }

    public static void registerLateModules() {

        if (ConfigHandler.enableIC2Module) {
            // Do stuff
        }

        if (ConfigHandler.enableTEModule) {
            // Do stuff
        }

        if (ConfigHandler.enableToolModule) {
            // Do stuff
        }
    }
}

package amerifrance.emt.proxies;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import amerifrance.emt.model.RenderProcessor;
import amerifrance.emt.modules.ic2.blocks.IC2BlockRegistry;
import amerifrance.emt.modules.ic2.tile.TileProcessor;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileProcessor.class, new RenderProcessor());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(IC2BlockRegistry.processor), new RenderProcessor());
    }
}

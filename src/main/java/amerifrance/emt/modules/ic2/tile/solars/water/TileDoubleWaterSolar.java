package amerifrance.emt.modules.ic2.tile.solars.water;

import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.modules.ic2.blocks.IC2BlockRegistry;

public class TileDoubleWaterSolar extends TileWaterSolar {

    public TileDoubleWaterSolar() {
        output = ConfigHandler.doubleCompressedSolarOutput;
        energySource = new BasicSource(this, 10000, 3);
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(IC2BlockRegistry.solars1, 1, 4);
    }
}

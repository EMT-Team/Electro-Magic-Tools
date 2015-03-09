package amerifrance.emt.modules.ic2.tile.solars.fire;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.modules.ic2.blocks.IC2BlockRegistry;
import amerifrance.emt.modules.ic2.tile.solars.TileEntitySolarBase;

public class TileFireSolar extends TileEntitySolarBase {

    public TileFireSolar() {
        output = ConfigHandler.compressedSolarOutput;
    }

    @Override
    public void checkConditions() {
        if (!initialized && worldObj != null) {
            canRain = worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0;
            initialized = true;
        }

        if (worldObj.provider.dimensionId == -1) {
            energySource.addEnergy(output * 2);
        } else {
            if (tick-- == 0) {
                updateSunState();
                tick = 64;
            }
        }
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(IC2BlockRegistry.solars1, 1, 12);
    }
}

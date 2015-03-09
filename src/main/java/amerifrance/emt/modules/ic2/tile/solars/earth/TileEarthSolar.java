package amerifrance.emt.modules.ic2.tile.solars.earth;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.modules.ic2.blocks.IC2BlockRegistry;
import amerifrance.emt.modules.ic2.tile.solars.TileEntitySolarBase;

public class TileEarthSolar extends TileEntitySolarBase {

    public TileEarthSolar() {
        output = ConfigHandler.compressedSolarOutput;
    }

    @Override
    public void createEnergy() {
        if (theSunIsVisible && this.yCoord <= 10) {
            energySource.addEnergy(output * 2.5);
        } else if (theSunIsVisible) {
            energySource.addEnergy(output);
        }
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(IC2BlockRegistry.solars2, 1, 2);
    }
}

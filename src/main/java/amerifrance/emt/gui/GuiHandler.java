package amerifrance.emt.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import amerifrance.emt.gui.container.ContainerEtherealMacerator;
import amerifrance.emt.gui.container.ContainerWandRecharger;
import amerifrance.emt.modules.ic2.tile.TileEtherealMacerator;
import amerifrance.emt.modules.ic2.tile.TileWandRecharger;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

        switch (ID) {
            case 0:
                if (entity != null && entity instanceof TileWandRecharger) {
                    return new ContainerWandRecharger(player.inventory, (TileWandRecharger) entity);
                }
            case 1:
                if (entity != null && entity instanceof TileEtherealMacerator) {
                    return new ContainerEtherealMacerator(player.inventory, (TileEtherealMacerator) entity);
                }
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

        switch (ID) {
            case 0:
                if (entity != null && entity instanceof TileWandRecharger) {
                    return new ContainerWandRecharger(player.inventory, (TileWandRecharger) entity);
                }
            case 1:
                if (entity != null && entity instanceof TileEtherealMacerator) {
                    return new ContainerEtherealMacerator(player.inventory, (TileEtherealMacerator) entity);
                }
            default:
                return null;
        }
    }
}

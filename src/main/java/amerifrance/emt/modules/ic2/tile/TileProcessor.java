package amerifrance.emt.modules.ic2.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import amerifrance.emt.modules.base.tile.TileMultiblock;

public class TileProcessor extends TileMultiblock {

    @Override
    public boolean checkMultiblock() {
        int i = 0;
        for (int x = xCoord - 1; x < xCoord + 2; x++) {
            for (int y = yCoord; y < yCoord + 3; y++) {
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    if (tile != null && (tile instanceof TileProcessor)) {
                        if (this.isMaster()) {
                            if (((TileProcessor) tile).hasMaster()) i++;
                        } else if (!((TileProcessor) tile).hasMaster()) i++;
                    }
                }
            }
        }
        return i > 25 && worldObj.isAirBlock(xCoord, yCoord + 1, zCoord);
    }

    @Override
    public void setupStructure() {
        for (int x = xCoord - 1; x < xCoord + 2; x++) {
            for (int y = yCoord; y < yCoord + 3; y++) {
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    boolean master = (x == xCoord && y == yCoord && z == zCoord);
                    if (tile != null && (tile instanceof TileProcessor)) {
                        ((TileProcessor) tile).setMasterCoords(xCoord, yCoord, zCoord);
                        ((TileProcessor) tile).setHasMaster(true);
                        ((TileProcessor) tile).setIsMaster(master);
                    }
                }
            }
        }
    }

    @Override
    public void resetStructure() {
        for (int x = xCoord - 1; x < xCoord + 2; x++) {
            for (int y = yCoord; y < yCoord + 3; y++) {
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    if (tile != null && (tile instanceof TileProcessor)) ((TileProcessor) tile).reset();
                }
            }
        }
    }

    @Override
    public void multiblockTick() {
        if (worldObj.isAirBlock(xCoord, yCoord + 6, zCoord)) {
            worldObj.setBlock(xCoord, yCoord + 6, zCoord, Blocks.diamond_block);
        }
    }

    @Override
    public void masterWriteToNBT(NBTTagCompound tagCompound) {
    }

    @Override
    public void masterReadFromNBT(NBTTagCompound tagCompound) {
    }

    @Override
    public void slaveWriteToNBT(NBTTagCompound tagCompound) {
    }

    @Override
    public void slaveReadFromNBT(NBTTagCompound tagCompound) {
    }

    @Override
    public boolean onBlockRightClicked(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return true;
    }
}

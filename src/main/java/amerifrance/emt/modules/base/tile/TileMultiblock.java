package amerifrance.emt.modules.base.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class TileMultiblock extends TileEntityBase {

    private boolean hasMaster, isMaster;
    private int masterX, masterY, masterZ;
    private int masterTicks;

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (hasMaster()) {
            if (isMaster()) {
                masterTicks++;
                if (masterTicks % 400 == 0 && !checkMultiblock()) {
                    resetStructure();
                    return;
                }
                multiblockTick();
            }
        } else {
            if (checkMultiblock()) setupStructure();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        masterX = tagCompound.getInteger("masterX");
        masterY = tagCompound.getInteger("masterY");
        masterZ = tagCompound.getInteger("masterZ");
        hasMaster = tagCompound.getBoolean("hasMaster");
        isMaster = tagCompound.getBoolean("isMaster");

        if (hasMaster) {
            if (isMaster) masterReadFromNBT(tagCompound);
            else slaveReadFromNBT(tagCompound);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        tagCompound.setInteger("masterX", masterX);
        tagCompound.setInteger("masterY", masterY);
        tagCompound.setInteger("masterZ", masterZ);
        tagCompound.setBoolean("hasMaster", hasMaster);
        tagCompound.setBoolean("isMaster", isMaster);

        if (hasMaster) {
            if (isMaster) masterWriteToNBT(tagCompound);
            else slaveWriteToNBT(tagCompound);
        }
    }

    public boolean hasMaster() {
        return hasMaster;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public int getMasterX() {
        return masterX;
    }

    public int getMasterY() {
        return masterY;
    }

    public int getMasterZ() {
        return masterZ;
    }

    public void setHasMaster(boolean hasMaster) {
        this.hasMaster = hasMaster;
    }

    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    public void setMasterCoords(int x, int y, int z) {
        masterX = x;
        masterY = y;
        masterZ = z;
    }

    public void reset() {
        masterX = 0;
        masterY = 0;
        masterZ = 0;
        hasMaster = false;
        isMaster = false;
    }

    public TileMultiblock getMaster() {
        TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
        if (tile != null && tile instanceof TileMultiblock) {
            return (TileMultiblock) tile;
        }
        return null;
    }

    public abstract boolean checkMultiblock();

    public abstract void setupStructure();

    public abstract void resetStructure();

    public abstract void multiblockTick();

    public abstract void masterWriteToNBT(NBTTagCompound tagCompound);

    public abstract void masterReadFromNBT(NBTTagCompound tagCompound);

    public abstract void slaveWriteToNBT(NBTTagCompound tagCompound);

    public abstract void slaveReadFromNBT(NBTTagCompound tagCompound);

    public abstract boolean onBlockRightClicked(EntityPlayer player, int side, float hitX, float hitY, float hitZ);
}

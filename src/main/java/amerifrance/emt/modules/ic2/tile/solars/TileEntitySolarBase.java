package amerifrance.emt.modules.ic2.tile.solars;

import ic2.api.energy.prefab.BasicSource;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import amerifrance.emt.modules.ic2.tile.TileIC2;

import java.util.Random;

public class TileEntitySolarBase extends TileIC2 implements IWrenchable {

    public static Random random = new Random();
    public BasicSource energySource;
    public boolean initialized;
    public boolean theSunIsVisible;
    public boolean canRain;
    public boolean noSunlight;
    public int tick;
    public double output = 0;

    public TileEntitySolarBase() {
        this.tick = random.nextInt(64);
        this.energySource = new BasicSource(this, 10000, 2);
    }

    @Override
    public void updateEntity() {
        energySource.updateEntity();
        checkConditions();
    }

    public void checkConditions() {
        if (!initialized && worldObj != null) {
            canRain = worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0;
            noSunlight = worldObj.provider.hasNoSky;
            initialized = true;
        }
        if (noSunlight) {
            return;
        }
        if (tick-- == 0) {
            updateSunState();
            tick = 64;
        }
        createEnergy();
    }

    public void createEnergy() {
        if (theSunIsVisible) {
            energySource.addEnergy(output);
        }
    }

    public void updateSunState() {
        boolean isRaining = canRain && (worldObj.isRaining() || worldObj.isThundering());
        theSunIsVisible = worldObj.isDaytime() && !isRaining && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord);
    }

    @Override
    public void onChunkUnload() {
        energySource.onChunkUnload();
    }

    @Override
    public void invalidate() {
        energySource.invalidate();
        super.invalidate();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        energySource.writeToNBT(nbttagcompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        energySource.readFromNBT(tagCompound);
    }

    @Override
    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
        return false;
    }

    @Override
    public short getFacing() {
        return 0;
    }

    @Override
    public void setFacing(short facing) {
    }

    @Override
    public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public float getWrenchDropRate() {
        return 1;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return null;
    }
}
package amerifrance.emt.modules.ic2.tile;

import ic2.api.energy.prefab.BasicSource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.util.EssentiaOutputs;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.lib.events.EssentiaHandler;

public class TileEssentiaGenerator extends TileIC2 {
    public String[] aspects = new String[4];
    public boolean[] isFree = new boolean[4];
    public static int waitTime;
    public BasicSource energySource = new BasicSource(this, 1000000000, 3);

    public TileEssentiaGenerator() {
        waitTime = 30;
        for (int i = 0; i < aspects.length; i++) {
            aspects[i] = String.valueOf(i);
            isFree[i] = true;
        }
    }

    public void updateEntity() {
        energySource.updateEntity();
        if (waitTime > 0) {
            waitTime--;
        }
        if (waitTime <= 0 && !worldObj.isRemote) {
            for (int i = 0; i < aspects.length; i++) {
                if (EssentiaHandler.drainEssentia(this, Aspect.getAspect(aspects[i]), ForgeDirection.UNKNOWN, 8)) {
                    energySource.addEnergy(EssentiaOutputs.outputs.get(aspects[i]));
                }
            }
            waitTime = ConfigHandler.essentiaGeneratorsDelay;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energySource.readFromNBT(tag);
        for (int i = 0; i < aspects.length; i++) {
            this.aspects[i] = tag.getString("aspect" + String.valueOf(i));
            this.isFree[i] = tag.getBoolean("isFree" + String.valueOf(i));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        energySource.writeToNBT(tag);
        for (int i = 0; i < aspects.length; i++) {
            tag.setString("aspect" + String.valueOf(i), this.aspects[i]);
            tag.setBoolean("isFree" + String.valueOf(i), this.isFree[i]);
        }
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
}
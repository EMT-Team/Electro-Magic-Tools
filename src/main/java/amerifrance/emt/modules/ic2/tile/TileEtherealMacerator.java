package amerifrance.emt.modules.ic2.tile;

import ic2.api.item.IC2Items;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.oredict.OreDictionary;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.modules.ic2.blocks.IC2BlockRegistry;
import thaumcraft.common.config.ConfigItems;

public class TileEtherealMacerator extends TileIC2 implements ISidedInventory, IWrenchable {

    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{2, 1};
    private static final int[] slots_sides = new int[]{1};

    private ItemStack[] slots = new ItemStack[3];
    public int maceratingSpeed = ConfigHandler.etherealProcessorBaseSpeed;
    public int cookTime;

    public int getSizeInventory() {
        return this.slots.length;
    }

    public ItemStack getStackInSlot(int par1) {
        return this.slots[par1];
    }

    public ItemStack decrStackSize(int par1, int par2) {
        if (this.slots[par1] != null) {
            ItemStack itemstack;

            if (this.slots[par1].stackSize <= par2) {
                itemstack = this.slots[par1];
                this.slots[par1] = null;
                return itemstack;
            } else {
                itemstack = this.slots[par1].splitStack(par2);

                if (this.slots[par1].stackSize == 0) {
                    this.slots[par1] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.slots[par1] != null) {
            ItemStack itemstack = this.slots[par1];
            this.slots[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.slots[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInvName() {
        return "Ethereal Processor";
    }

    public boolean isInvNameLocalized() {
        return false;
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.slots.length) {
                this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.cookTime = tagCompound.getShort("CookTime");
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setShort("CookTime", (short) this.cookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.slots.length; ++i) {
            if (this.slots[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.slots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        tagCompound.setTag("Items", nbttaglist);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public void updateEntity() {
        if (this.canSmelt() && !isOverLimit(1) && !isOverLimit(2) && !isOP()) {
            ++this.cookTime;
            this.isOn = true;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

            if (this.cookTime == this.maceratingSpeed) {
                this.cookTime = 0;
                this.smeltItem();
            }
        } else {
            this.cookTime = 0;
            this.isOn = false;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    private boolean canSmelt() {
        if (this.slots[0] == null) {
            return false;
        } else {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
            if (itemstack == null)
                return false;
            if (this.slots[2] == null)
                return true;
            if (!this.slots[2].isItemEqual(itemstack))
                return false;
            int result = slots[2].stackSize + (itemstack.stackSize * 2);
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);

            if (this.slots[2] == null) {
                this.slots[2] = itemstack.copy();
                this.slots[2].stackSize *= 2;
            } else if (this.slots[2].isItemEqual(itemstack)) {
                slots[2].stackSize += (itemstack.stackSize * 2);
            }

            --this.slots[0].stackSize;

            if (this.slots[0].stackSize <= 0) {
                this.slots[0] = null;
            }
            this.addBonus();
        }
    }

    public void addBonus() {
        if (this.canSmelt()) {
            if (this.worldObj.rand.nextInt(ConfigHandler.etherealProcessorBonus) == 0) {
                if (this.slots[1] == null) {
                    this.setInventorySlotContents(1, new ItemStack(ConfigItems.itemNugget, 1, 6));
                } else if (this.slots[1].isItemEqual(new ItemStack(ConfigItems.itemNugget, 1, 6)) && !isOverLimit(1) && !isOverLimit(2)) {
                    slots[1] = new ItemStack(getStackInSlot(1).getItem(), slots[1].stackSize + 1, 2);
                }
            }
        }
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (slot != 0)
            return false;
        if (FurnaceRecipes.smelting().getSmeltingResult(stack) != null)
            return true;

        return false;
    }

    public int[] getAccessibleSlotsFromSide(int par1) {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }

    public boolean isOverLimit(int i) {
        if (slots[i] != null) {
            if (slots[i].stackSize >= 63)
                return true;
            else if (slots[i].stackSize < 63)
                return false;
            else
                return false;
        } else {
            return false;
        }
    }

    public boolean isOP() {
        final int cobblestoneId = OreDictionary.getOreID("blockCobble");

        if (OreDictionary.getOreID(this.slots[0]) == cobblestoneId || this.slots[0].getItem() == Item.getItemFromBlock(Blocks.sand) || this.slots[0].getItem() == Items.potato || this.slots[0].getItem() == Items.porkchop || this.slots[0].getItem() == Items.beef || this.slots[0].getItem() == Items.chicken || this.slots[0].getItem() == Items.fish || this.slots[0].getItem() == IC2Items.getItem("coalDust").getItem() || (OreDictionary.getOreName(OreDictionary.getOreID(this.slots[0])).toLowerCase().contains("dust") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("armour") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("armor") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("helm") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("legging") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("boot") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("chestp") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("pick") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("shovel") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("axe") || this.slots[0].getItem().getUnlocalizedName(this.slots[0]).toLowerCase().contains("pick"))) {
            return true;
        } else {
            return false;
        }
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
        return new ItemStack(IC2BlockRegistry.machines, 1, 1);
    }

    @Override
    public String getInventoryName() {
        return "Ethereal Macerator";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }
}

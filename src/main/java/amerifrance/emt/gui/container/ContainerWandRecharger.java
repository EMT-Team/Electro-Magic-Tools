package amerifrance.emt.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import amerifrance.emt.modules.ic2.tile.TileWandRecharger;

public class ContainerWandRecharger extends Container {

    TileWandRecharger charger;

    public ContainerWandRecharger(InventoryPlayer invPlayer, TileWandRecharger entity) {
        this.charger = entity;

        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(invPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
            }
        }

        this.addSlotToContainer(new Slot(charger, 0, 80, 35));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return charger.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
        ItemStack stack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (par2 == 1) {
                if (!this.mergeItemStack(stack1, 2, 38, true)) {
                    return null;
                }

                slot.onSlotChange(stack1, stack);
            } else if (par2 != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(stack1) != null) {
                    if (!this.mergeItemStack(stack1, 0, 1, false)) {
                        return null;
                    }
                } else if (par2 >= 2 && par2 < 29) {
                    if (!this.mergeItemStack(stack1, 29, 38, false)) {
                        return null;
                    }
                } else if (par2 >= 29 && par2 < 38 && !this.mergeItemStack(stack1, 2, 29, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(stack1, 2, 38, false)) {
                return null;
            }

            if (stack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (stack1.stackSize == stack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, stack1);
        }

        return stack;
    }
}

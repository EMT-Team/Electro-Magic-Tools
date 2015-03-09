package amerifrance.emt.modules.ic2.items.foci;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import amerifrance.emt.ConfigHandler;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusCharge extends ItemFocusIC2 {

    private static final AspectList visCost = new AspectList().add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.AIR, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10);

    public ItemFocusCharge() {
        super("charge", "focus_charge");
    }

    @Override
    public AspectList getVisCost(ItemStack stack) {
        return visCost.copy();
    }

    @Override
    public String getSortingHelper(ItemStack stack) {
        return "INVCHARGE";
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition) {
        ItemWandCasting wand = (ItemWandCasting) stack.getItem();
        if (wand.consumeAllVis(stack, player, getVisCost(stack), true, true)) {
            if (!world.isRemote) {

                int energyLeft = ConfigHandler.chargeFocusProduction;
                for (int i = 0; i < player.inventory.armorInventory.length; i++) {
                    if (energyLeft > 0) {
                        if ((player.inventory.armorInventory[i] != null) && (player.inventory.armorInventory[i].getItem() instanceof IElectricItem)) {
                            double sentPacket = ElectricItem.manager.charge(player.inventory.armorInventory[i], energyLeft, 4, false, false);
                            energyLeft -= sentPacket;
                        }
                    } else {
                        return stack;
                    }
                }
                for (int j = 0; j < player.inventory.mainInventory.length; j++) {
                    if (energyLeft > 0) {
                        if ((player.inventory.mainInventory[j] != null) && (player.inventory.mainInventory[j].getItem() instanceof IElectricItem)) {
                            double sentPacket = ElectricItem.manager.charge(player.inventory.mainInventory[j], energyLeft, 4, false, false);
                            energyLeft -= sentPacket;
                        }
                    } else {
                        return stack;
                    }
                }
            }
        }
        return stack;
    }

    @Override
    public WandFocusAnimation getAnimation(ItemStack stack) {
        return WandFocusAnimation.CHARGE;
    }
}

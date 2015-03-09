package amerifrance.emt.modules.ic2.items.foci;

import ic2.api.item.ElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import amerifrance.emt.ConfigHandler;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusWandCharge extends ItemFocusIC2 {

    AspectList visCost = new AspectList().add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.AIR, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10);

    public ItemFocusWandCharge() {
        super("charge.wand", "focus_wandCharge");
    }

    @Override
    public AspectList getVisCost(ItemStack stack) {
        return visCost.copy();
    }

    @Override
    public String getSortingHelper(ItemStack stack) {
        return "WANDCHARGE";
    }

    @Override
    public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player, int integer) {

        if (!player.worldObj.isRemote) {
            ItemWandCasting wandItem = (ItemWandCasting) itemstack.getItem();

            ItemStack armor = player.inventory.armorInventory[1];
            if (armor != null) {
                if ((ElectricItem.manager.use(armor, ConfigHandler.wandChargeFocusCost / 4, player) && (ElectricItem.manager.use(armor, ConfigHandler.wandChargeFocusCost / 4, player)) && (ElectricItem.manager.use(armor, ConfigHandler.wandChargeFocusCost / 4, player)) && (ElectricItem.manager.use(armor, ConfigHandler.wandChargeFocusCost / 4, player)))) {
                    wandItem.addVis(itemstack, Aspect.ORDER, 1, true);
                    wandItem.addVis(itemstack, Aspect.FIRE, 1, true);
                    wandItem.addVis(itemstack, Aspect.ENTROPY, 1, true);
                    wandItem.addVis(itemstack, Aspect.WATER, 1, true);
                    wandItem.addVis(itemstack, Aspect.EARTH, 1, true);
                    wandItem.addVis(itemstack, Aspect.AIR, 1, true);
                }
            }
        }
    }
}

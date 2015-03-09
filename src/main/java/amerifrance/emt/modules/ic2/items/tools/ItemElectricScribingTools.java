package amerifrance.emt.modules.ic2.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import thaumcraft.api.IScribeTools;
import thaumcraft.common.items.ItemInkwell;

import java.util.List;

public class ItemElectricScribingTools extends ItemInkwell implements IElectricItem, IScribeTools {

    public static int maxCharge = 400;

    public ItemElectricScribingTools() {
        setUnlocalizedName(ModInformation.ID + ".module.ic2.scribingtools.electric");
        setCreativeTab(ElectroMagicTools.tabEMT);
        setMaxDamage(maxCharge);
        setMaxStackSize(1);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        ItemStack itemStack = new ItemStack(this, 1);
        if (getChargedItem(itemStack) == this) {
            ItemStack charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, 2147483647, 2147483647, true, false);
            list.add(charged);
        }
        if (getEmptyItem(itemStack) == this) {
            list.add(new ItemStack(this, 1, getMaxDamage()));
        }
    }

    // Begin IElectricItem

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    @Override
    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 1;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return 5;
    }
}

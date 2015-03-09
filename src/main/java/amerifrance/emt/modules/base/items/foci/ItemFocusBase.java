package amerifrance.emt.modules.base.items.foci;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

import java.util.List;

public class ItemFocusBase extends ItemFocusBasic {

    private static AspectList visCost = new AspectList();

    public ItemFocusBase(String unlocName, String textureName) {
        super();

        setUnlocalizedName(ModInformation.ID + ".module.base.focus." + unlocName);
        setTextureName(ModInformation.TEXLOC + textureName);
        setCreativeTab(ElectroMagicTools.tabEMT);
        setMaxDamage(1);
        setNoRepair();
        setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        return this.itemIcon;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.rare;
    }

    @Override
    public int getFocusColor(ItemStack focusStack) {
        return 0xFFFF00;
    }

    @Override
    public AspectList getVisCost(ItemStack stack) {
        return visCost.copy();
    }

    @Override
    public String getSortingHelper(ItemStack stack) {
        return "EMTBASE";
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition mop) {
        return stack;
    }

    @Override
    public IIcon getFocusDepthLayerIcon(ItemStack stack) {
        return null;
    }

    @Override
    public IIcon getOrnament(ItemStack stack) {
        return null;
    }

    @Override
    public WandFocusAnimation getAnimation(ItemStack stack) {
        return WandFocusAnimation.WAVE;
    }

    @Override
    public boolean isVisCostPerTick(ItemStack stack) {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        return 5;
    }

    @Override
    public boolean isItemTool(ItemStack stack) {
        return true;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack stack, int rank) {
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        AspectList cost = getVisCost(stack);
        if (cost != null && cost.size() > 0) {
            list.add(TextHelper.localize(isVisCostPerTick(stack) ? "item.Focus.cost2" : "item.Focus.cost1"));
            for (Aspect aspect : cost.getAspectsSorted()) {
                float amount = cost.getAmount(aspect) / 100.0F;
                list.add(" " + '\u00a7' + aspect.getChatcolor() + aspect.getName() + '\u00a7' + "r x " + amount);
            }
        }
    }
}

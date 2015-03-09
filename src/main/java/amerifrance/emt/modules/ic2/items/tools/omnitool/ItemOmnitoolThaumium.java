package amerifrance.emt.modules.ic2.items.tools.omnitool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;

public class ItemOmnitoolThaumium extends ItemOmnitoolIron {

    public ItemOmnitoolThaumium() {
        super();
        setUnlocalizedName(ModInformation.ID + ".module.ic2.omnitool.thaumium");
        setCreativeTab(ElectroMagicTools.tabEMT);
        if (!ConfigHandler.toolsInBore) {
            this.setMaxDamage(27);
        } else {
            this.setMaxDamage(467);
        }

        efficiencyOnProperMaterial = 21F;
        maxCharge = 140000;
        hitCost = 650;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "tools/omnitool_thaumium");
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int par4, int par5, int par6, EntityLivingBase entityLiving) {
        if (!ConfigHandler.toolsInBore) {
            cost = 300;
        } else {
            cost = 1;
        }
        ElectricItem.manager.use(stack, cost, entityLiving);
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityliving, EntityLivingBase attacker) {
        if (ElectricItem.manager.use(stack, hitCost, attacker)) {
            entityliving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 10F);
        }
        return false;
    }

    /* IC2 API METHODS */
    @Override
    public double getTransferLimit(ItemStack stack) {
        return 800;
    }
}

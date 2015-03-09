package amerifrance.emt.modules.ic2.items.armor.boots;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;

public class ItemQuantumBootsTraveller extends ItemElectricBootsTraveller {

    public ItemQuantumBootsTraveller() {
        super();
        setUnlocalizedName(ModInformation.ID + ".module.ic2.boots.traveller.quantum");
        maxCharge = 10000000;
        speedBonus = (float) ConfigHandler.quantumBootsSpeed;
        jumpBonus = (float) ConfigHandler.quantumBootsJump;
        visDiscount = 5;
        transferLimit = 10000;
        energyPerDamage = 1000;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "armor/boots_quantum");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ModInformation.TEXLOC + "textures/models/quantumbootstravel.png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(stack);
    }
}
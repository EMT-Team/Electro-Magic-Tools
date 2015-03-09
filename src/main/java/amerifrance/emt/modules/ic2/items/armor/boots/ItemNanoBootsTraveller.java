package amerifrance.emt.modules.ic2.items.armor.boots;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;

public class ItemNanoBootsTraveller extends ItemElectricBootsTraveller {

    public ItemNanoBootsTraveller() {
        super();
        setUnlocalizedName(ModInformation.ID + ".module.ic2.boots.traveller.nano");
        maxCharge = 1000000;
        speedBonus = (float) ConfigHandler.nanoBootsSpeed;
        jumpBonus = (float) ConfigHandler.nanoBootsJump;
        visDiscount = 4;
        transferLimit = 1000;
        energyPerDamage = 500;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "armor/boots_nano");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ModInformation.TEXLOC + "textures/models/nanobootstravel.png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextHelper.YELLOW + super.getItemStackDisplayName(stack);
    }
}

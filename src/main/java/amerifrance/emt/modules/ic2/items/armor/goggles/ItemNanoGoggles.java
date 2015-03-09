package amerifrance.emt.modules.ic2.items.armor.goggles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;

public class ItemNanoGoggles extends ItemElectricGoggles {

    public ItemNanoGoggles() {
        super(ArmorMaterial.DIAMOND);
        setUnlocalizedName(ModInformation.ID + ".module.ic2.goggles.nano");
        setCreativeTab(ElectroMagicTools.tabEMT);
        visDiscount = 6;
        tier = 3;
        maxCharge = 100000;
        energyPerDamage = 1000;
        transferLimit = 100;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "armor/goggles_nano");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ModInformation.TEXLOC + "textures/models/thaumicnanohelmet.png";
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (ConfigHandler.enableNightVision) {
            if (ElectricItem.manager.canUse(itemStack, 1 / 1000)) {

                int x = MathHelper.floor_double(player.posX);
                int z = MathHelper.floor_double(player.posZ);
                int y = MathHelper.floor_double(player.posY);

                int lightlevel = player.worldObj.getBlockLightValue(x, y, z);
                if (lightlevel >= 0)
                    player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, -3));
                ElectricItem.manager.use(itemStack, 1 / 1000, player);
            } else {
                player.addPotionEffect(new PotionEffect(Potion.blindness.id, 300, 0, true));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextHelper.YELLOW + super.getItemStackDisplayName(stack);
    }
}
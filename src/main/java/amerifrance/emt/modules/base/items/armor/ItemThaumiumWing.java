package amerifrance.emt.modules.base.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;

import java.util.List;

public class ItemThaumiumWing extends ItemFeatherWing implements IVisDiscountGear, IRepairable {

    public ItemThaumiumWing(ArmorMaterial material) {
        super(material);
        setUnlocalizedName(ModInformation.ID + ".module.base.wing.thaumium");
        setTextureName(ModInformation.TEXLOC + "armor/wing_thaumium");
        setCreativeTab(ElectroMagicTools.tabEMT);
        setMaxStackSize(1);
        setMaxDamage(250);
        isDamageable();
        visDiscount = 4;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return visDiscount;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ModInformation.TEXLOC + "textures/models/thaumiumwing.png";
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(TextHelper.localize("tooltip.EMT.visDiscount") + ": " + String.valueOf(visDiscount) + "%");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (world.isRemote) {
            boolean isJumping = Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed();
            boolean isHoldingJump = Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed();
            boolean isSneaking = Minecraft.getMinecraft().gameSettings.keyBindSneak.getIsKeyPressed();

            if (isJumping) player.motionY = 0.5;

            if (isHoldingJump && !player.onGround && player.motionY < 0 && !player.capabilities.isCreativeMode)
                player.motionY *= 0.5;

            if (player.isInWater() && !player.capabilities.isCreativeMode) player.motionY = -0.6;

            if ((player.worldObj.isRaining() || player.worldObj.isThundering()) && !player.capabilities.isCreativeMode)
                player.motionY = -0.3;

            if (isSneaking && !player.onGround) player.motionY = -0.6;

        }
        if (player.fallDistance > 0.0F) player.fallDistance = 0;
    }
}

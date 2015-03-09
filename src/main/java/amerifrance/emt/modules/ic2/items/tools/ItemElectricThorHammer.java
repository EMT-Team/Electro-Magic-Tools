package amerifrance.emt.modules.ic2.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.modules.ic2.entity.EntityArcher;
import amerifrance.emt.util.TextHelper;

import java.util.List;

public class ItemElectricThorHammer extends ItemSword implements IElectricItem {

    public int maxCharge = 1000000;

    public ItemElectricThorHammer() {
        super(ToolMaterial.EMERALD);
        setUnlocalizedName(ModInformation.ID + ".module.ic2.hammer.electric");
        setCreativeTab(ElectroMagicTools.tabEMT);
        setMaxDamage(27);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "hammer/electricthorhammer");
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

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityliving, EntityLivingBase attacker) {
        int hitCost = 5000;

        if (ElectricItem.manager.use(stack, hitCost, attacker)) {
            entityliving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 19F);
        }
        return false;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(String.format(TextHelper.localize("tooltip.EMT.hammer.owned"), player.getCommandSenderName()));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        int lightningCost = 75000;

        if (player.capabilities.isCreativeMode) {
            return stack;
        } else if (ElectricItem.manager.canUse(stack, lightningCost)) {
            player.swingItem();
            // Corners
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ - 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ + 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ - 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ + 8));

            // Fronts
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY, player.posZ - 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY, player.posZ + 8));

            // Others
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ + 1));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ + 2));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ + 3));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ + 4));

            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ - 1));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ - 2));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ - 3));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ - 4));

            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 1, player.posY, player.posZ + 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 2, player.posY, player.posZ - 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 3, player.posY, player.posZ + 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX + 4, player.posY, player.posZ - 8));

            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 1, player.posY, player.posZ + 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 2, player.posY, player.posZ - 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 3, player.posY, player.posZ + 8));
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX - 4, player.posY, player.posZ - 8));

            EntityArcher archer;
            archer = new EntityArcher(world);
            archer.setPosition(player.posX + 8, player.posY + 2, player.posZ - 8);
            world.spawnEntityInWorld(archer);

            EntityArcher archer1;
            archer1 = new EntityArcher(world);
            archer1.setPosition(player.posX - 8, player.posY + 2, player.posZ + 8);
            world.spawnEntityInWorld(archer1);

            EntityArcher archer2;
            archer2 = new EntityArcher(world);
            archer2.setPosition(player.posX - 8, player.posY + 2, player.posZ - 8);
            world.spawnEntityInWorld(archer2);

            EntityArcher archer3;
            archer3 = new EntityArcher(world);
            archer3.setPosition(player.posX + 8, player.posY + 2, player.posZ + 8);
            world.spawnEntityInWorld(archer3);

            ElectricItem.manager.use(stack, lightningCost, player);
            return stack;
        } else {
            world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY, player.posZ));
            player.addPotionEffect(new PotionEffect(Potion.harm.getId(), 1, 1));
            return stack;
        }
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        if (ConfigHandler.allowEnchanting) {
            return 0;
        } else {
            return 4;
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        return ConfigHandler.allowEnchanting;
    }

    /* IC2 API METHODS */

    @Override
    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 2;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return 100;
    }
}

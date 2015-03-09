package amerifrance.emt.modules.ic2.items.tools.omnitool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;

import java.util.List;

public class ItemOmnitoolIron extends ItemPickaxe implements IElectricItem {

    public int maxCharge = 20000;
    public int cost;
    public int hitCost = 125;

    public ItemOmnitoolIron() {
        super(ToolMaterial.EMERALD);
        setUnlocalizedName(ModInformation.ID + ".module.ic2.omnitool.iron");
        setCreativeTab(ElectroMagicTools.tabEMT);
        if (!ConfigHandler.toolsInBore) {
            this.setMaxDamage(27);
        } else {
            this.setMaxDamage(200);
        }

        efficiencyOnProperMaterial = 13F;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "tools/omnitool_iron");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        ItemStack stack = new ItemStack(this, 1);
        if (getChargedItem(stack) == this) {
            ItemStack charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, 2147483647, 2147483647, true, false);
            list.add(charged);
        }
        if (getEmptyItem(stack) == this) {
            list.add(new ItemStack(this, 1, getMaxDamage()));
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int par4, int par5, int par6, EntityLivingBase entityLiving)

    {
        if (!ConfigHandler.toolsInBore) {
            cost = 100;
        } else {
            cost = 1;
        }
        ElectricItem.manager.use(stack, cost, entityLiving);
        return true;
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return Items.iron_axe.canHarvestBlock(block, stack) || Items.iron_sword.canHarvestBlock(block, stack) || Items.iron_pickaxe.canHarvestBlock(block, stack) || Items.iron_shovel.canHarvestBlock(block, stack) || Items.shears.canHarvestBlock(block, stack);
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (!ElectricItem.manager.canUse(stack, cost)) {
            return 1.0F;
        }

        if (Items.wooden_axe.getDigSpeed(stack, block, meta) > 1.0F || Items.wooden_sword.getDigSpeed(stack, block, meta) > 1.0F || Items.wooden_pickaxe.getDigSpeed(stack, block, meta) > 1.0F || Items.wooden_shovel.getDigSpeed(stack, block, meta) > 1.0F || Items.shears.getDigSpeed(stack, block, meta) > 1.0F) {
            return efficiencyOnProperMaterial;
        } else {
            return super.getDigSpeed(stack, block, meta);
        }
    }

    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase attacker) {
        if (ElectricItem.manager.use(itemstack, hitCost, attacker)) {
            entityliving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 8F);
        }
        return false;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        for (int i = 0; i < player.inventory.mainInventory.length; i++) {
            ItemStack torchStack = player.inventory.mainInventory[i];
            if (torchStack == null || !torchStack.getUnlocalizedName().toLowerCase().contains("torch")) {
                continue;
            }
            Item item = torchStack.getItem();
            if (!(item instanceof ItemBlock)) {
                continue;
            }
            int oldMeta = torchStack.getItemDamage();
            int oldSize = torchStack.stackSize;
            boolean result = torchStack.tryPlaceItemIntoWorld(player, world, x, y, z, side, xOffset, yOffset, zOffset);
            if (player.capabilities.isCreativeMode) {
                torchStack.setItemDamage(oldMeta);
                torchStack.stackSize = oldSize;
            } else if (torchStack.stackSize <= 0) {
                ForgeEventFactory.onPlayerDestroyItem(player, torchStack);
                player.inventory.mainInventory[i] = null;
            }
            if (result) {
                return true;
            }
        }

        return super.onItemUse(stack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        if (!ConfigHandler.allowEnchanting) {
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
    public Item getChargedItem(ItemStack stack) {
        return this;
    }

    @Override
    public Item getEmptyItem(ItemStack stack) {
        return this;
    }

    @Override
    public boolean canProvideEnergy(ItemStack stack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack stack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack stack) {
        return 2;
    }

    @Override
    public double getTransferLimit(ItemStack stack) {
        return 200;
    }
}

package amerifrance.emt.modules.base.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TorchPlacer;

import java.util.ArrayList;
import java.util.Random;

public class ItemBaseChainsaw extends ItemAxe {

    public String textureName;

    public ItemBaseChainsaw(ToolMaterial material, int maxDamage, String textureName) {
        super(material);
        this.setCreativeTab(ElectroMagicTools.tabEMT);
        this.setMaxStackSize(1);
        this.setMaxDamage(maxDamage);
        this.textureName = textureName;
        this.efficiencyOnProperMaterial = this.toolMaterial.getEfficiencyOnProperMaterial();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "tools/chainsaw" + textureName);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {
        if ((double) block.getBlockHardness(world, x, y, z) != 0.0D) stack.damageItem(1, entityLiving);
        return true;
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return false;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (block.getHarvestTool(meta) == null) {
            return efficiencyOnProperMaterial;
        } else {
            if ((canHarvestBlock(block, stack) && (block.getHarvestTool(meta).equals("axe")) || Items.shears.canHarvestBlock(block, stack))) {
                return efficiencyOnProperMaterial;
            }
        }
        return super.getDigSpeed(stack, block, meta);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        return TorchPlacer.placeTorch(stack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        if (ConfigHandler.allowEnchanting) return 0;
        else return toolMaterial.getEnchantability();
    }

    @Override
    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        return ConfigHandler.allowEnchanting;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityliving, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        if (player.worldObj.isRemote) {
            return false;
        }
        Block block = player.worldObj.getBlock(x, y, z);
        if (block instanceof IShearable) {
            IShearable target = (IShearable) block;
            if (target.isShearable(stack, player.worldObj, x, y, z)) {
                ArrayList<ItemStack> drops = target.onSheared(stack, player.worldObj, x, y, z,
                        EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
                Random rand = new Random();
                for (ItemStack drop : drops) {
                    float f = 0.7F;
                    double d = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d1 = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d2 = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(player.worldObj, (double) x + d, (double) y + d1, (double) z + d2, drop);
                    entityitem.delayBeforeCanPickup = 10;
                    player.worldObj.spawnEntityInWorld(entityitem);
                }
                stack.damageItem(1, player);
                player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(block)], 1);
            }
        }
        return false;
    }
}

package amerifrance.emt.modules.ic2.items.tools.drill;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.EntityFollowingItem;
import thaumcraft.common.lib.utils.BlockUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemDrillRockbreaker extends ItemDrillThaumium {

    private static final Block[] isEffective = {Blocks.nether_brick, Blocks.netherrack, Blocks.glowstone, Blocks.iron_block, Blocks.gold_block, Blocks.diamond_block, Blocks.lapis_block, Blocks.redstone_block, Blocks.redstone_ore, Blocks.emerald_ore, Blocks.emerald_block, Blocks.stonebrick, Blocks.glass, Blocks.stone, Blocks.gold_ore, Blocks.iron_ore, Blocks.coal_ore, Blocks.cobblestone, Blocks.diamond_ore, Blocks.lapis_ore, Blocks.dirt, Blocks.gravel, Blocks.sand, Blocks.sandstone, Blocks.soul_sand, Blocks.clay, Blocks.grass, Blocks.snow_layer, Blocks.snow, Blocks.farmland, Blocks.hardened_clay, Blocks.stained_hardened_clay, Blocks.mossy_cobblestone};
    public Icon icon;
    int side;
    public int searchCost = 1000;
    public int hitCost = 400;

    public ItemDrillRockbreaker() {
        setCreativeTab(ElectroMagicTools.tabEMT);
        setUnlocalizedName(ModInformation.ID + ".module.ic2.drill.rockbreaker");
        if (!ConfigHandler.toolsInBore) {
            this.setMaxDamage(27);
        } else {
            this.setMaxDamage(2571);
        }

        efficiencyOnProperMaterial = 25F;
        side = 0;
        maxCharge = 900000;
        transferLimit = 900;
        tier = 3;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "tools/drill_rockbreaker");
    }

    private boolean isEffectiveAgainst(Block block) {
        for (int i = 0; i < isEffective.length; i++) {
            if (isEffective[i] == block) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        MovingObjectPosition movingobjectposition = BlockUtils.getTargetBlock(player.worldObj, player, true);
        if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            side = movingobjectposition.sideHit;
        }
        return super.onBlockStartBreak(stack, x, y, z, player);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {
        if (!ConfigHandler.toolsInBore) {
            cost = 350;
        } else {
            cost = 1;
        }
        if (entityLiving.isSneaking()) {
            if (ElectricItem.manager.canUse(stack, cost)) {
                ElectricItem.manager.use(stack, cost, entityLiving);
            }
            return true;
        }
        int md = world.getBlockMetadata(x, y, z);
        if (ForgeHooks.isToolEffective(stack, block, md) || isEffectiveAgainst(block)) {
            for (int aa = -1; aa <= 1; aa++) {
                for (int bb = -1; bb <= 1; bb++) {
                    int xx = 0;
                    int yy = 0;
                    int zz = 0;
                    if (side <= 1) {
                        xx = aa;
                        zz = bb;
                    } else if (side <= 3) {
                        xx = aa;
                        yy = bb;
                    } else {
                        zz = aa;
                        yy = bb;
                    }
                    Block bl = world.getBlock(x + xx, y + yy, z + zz);
                    md = world.getBlockMetadata(x + xx, y + yy, z + zz);
                    if (!ForgeHooks.isToolEffective(stack, bl, md) && !isEffectiveAgainst(bl)) {
                        continue;
                    }
                    if (ElectricItem.manager.canUse(stack, cost)) {
                        ElectricItem.manager.use(stack, cost, entityLiving);
                    }
                    int fortune = EnchantmentHelper.getFortuneModifier(entityLiving);
                    world.setBlockToAir(x + xx, y + yy, z + zz);
                    ArrayList ret = bl.getDrops(world, x + xx, y + yy, z + zz, md, fortune);
                    boolean creative = false;
                    if ((entityLiving instanceof EntityPlayer) && ((EntityPlayer) entityLiving).capabilities.isCreativeMode) {
                        creative = true;
                    }
                    if (ret.size() <= 0 || creative || entityLiving.worldObj.isRemote) {
                        continue;
                    }
                    ItemStack is;
                    for (Iterator iterator = ret.iterator(); iterator.hasNext(); world.spawnEntityInWorld(new EntityFollowingItem(world, (double) x + (double) xx + 0.5D, (double) y + (double) yy + 0.5D, (double) z + (double) zz + 0.5D, is, entityLiving, 3))) {
                        is = (ItemStack) iterator.next();
                    }
                }
            }
        }
        return true;
    }

    @SuppressWarnings("unused")
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        if (!player.isSneaking()) {
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
        } else {
            ElectricItem.manager.use(stack, searchCost, player);
            if (!world.isRemote) {
                world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wandfail", 0.2F, 0.2F + world.rand.nextFloat() * 0.2F);
                return super.onItemUse(stack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
            }
            Minecraft mc = Minecraft.getMinecraft();
            Thaumcraft.instance.renderEventHandler.startScan(player, x, y, z, System.currentTimeMillis() + 5000, 10);
            player.swingItem();
            return super.onItemUse(stack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
        }

        return super.onItemUse(stack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!player.worldObj.isRemote && (!(entity instanceof EntityPlayer) || MinecraftServer.getServer().isPVPEnabled())) {
            entity.setFire(2);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase attacker) {
        if (ElectricItem.manager.use(itemstack, hitCost, attacker)) {
            entityliving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 12F);
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(stack);
    }
}

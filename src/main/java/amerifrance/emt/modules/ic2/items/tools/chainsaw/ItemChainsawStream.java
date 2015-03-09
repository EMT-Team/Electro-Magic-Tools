package amerifrance.emt.modules.ic2.items.tools.chainsaw;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockBubble;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.Utils;

import java.awt.*;

public class ItemChainsawStream extends ItemChainsawThaumium {

    boolean alternateServer;
    boolean alternateClient;

    public ItemChainsawStream() {
        setUnlocalizedName(ModInformation.ID + ".module.ic2.chainsaw.stream");
        setCreativeTab(ElectroMagicTools.tabEMT);

        efficiencyOnProperMaterial = 25F;
        alternateServer = false;
        alternateClient = false;
        maxCharge = 900000;
        cost = 400;
        hitCost = 500;
        tier = 3;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "tools/chainsaw_stream");
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        Block bi = world.getBlock(x, y, z);
        if ((!player.isSneaking()) && (Utils.isWoodLog(world, x, y, z))) {
            if (!world.isRemote) {
                if (BlockUtils.breakFurthestBlock(world, x, y, z, bi, player)) {
                    world.playSoundEffect(x, y, z, "thaumcraft:bubble", 0.15F, 1.0F);
                    ElectricItem.manager.use(stack, cost, player);
                    this.alternateServer = (!this.alternateServer);
                }
            } else {
                player.swingItem();
                ElectricItem.manager.use(stack, cost, player);
                this.alternateClient = (!this.alternateClient);
            }
        }
        return super.onItemUse(stack, player, world, x, y, z, par7, par8, par9, par10);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
        World world = player.worldObj;
        Block bi = world.getBlock(x, y, z);
        if ((!player.isSneaking()) && (Utils.isWoodLog(world, x, y, z))) {
            if (!world.isRemote) {
                BlockUtils.breakFurthestBlock(world, x, y, z, bi, player);
                PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBubble(x, y, z, new Color(0.33F, 0.33F, 1.0F).getRGB()), new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, y, z, 32.0D));

                world.playSoundEffect(x, y, z, "thaumcraft:bubble", 0.15F, 1.0F);
            }
            ElectricItem.manager.use(itemstack, cost, player);
            return true;
        }
        return super.onBlockStartBreak(itemstack, x, y, z, player);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(stack);
    }

    /* IC2 API METHODS */
    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return 900;
    }
}

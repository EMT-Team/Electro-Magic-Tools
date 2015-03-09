package amerifrance.emt.modules.ic2.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.modules.ic2.tile.TileEtherealMacerator;
import amerifrance.emt.modules.ic2.tile.TileWandRecharger;

import java.util.List;
import java.util.Random;

public class BlockMachines extends BlockIC2 {

    public BlockMachines() {
        super("machine", Material.iron);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        this.blockIcon = ir.registerIcon(ModInformation.TEXLOC + "machines/top");

        this.top[0] = ir.registerIcon(ModInformation.TEXLOC + "machines/top");
        this.top[1] = ir.registerIcon(ModInformation.TEXLOC + "machines/etherealmaceratortop");

        this.bottom[0] = ir.registerIcon(ModInformation.TEXLOC + "machines/side");
        this.bottom[1] = ir.registerIcon(ModInformation.TEXLOC + "machines/etherealmacerator");

        this.side[0] = ir.registerIcon(ModInformation.TEXLOC + "machines/side");
        this.side[1] = ir.registerIcon(ModInformation.TEXLOC + "machines/etherealmacerator");

        this.frontOff[0] = ir.registerIcon(ModInformation.TEXLOC + "machines/wandcharger");
        this.frontOff[1] = ir.registerIcon(ModInformation.TEXLOC + "machines/etherealmaceratorfront");

        this.frontOn[0] = ir.registerIcon(ModInformation.TEXLOC + "machines/wandcharger");
        this.frontOn[1] = ir.registerIcon(ModInformation.TEXLOC + "machines/etherealmaceratorfrontactive");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List list) {
        list.add(new ItemStack(id, 1, 0));
        list.add(new ItemStack(id, 1, 1));
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 0:
                return new TileWandRecharger();
            case 1:
                return new TileEtherealMacerator();
        }

        return super.createTileEntity(world, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileWandRecharger)
            player.openGui(ElectroMagicTools.instance, 0, world, x, y, z);
        else if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEtherealMacerator)
            player.openGui(ElectroMagicTools.instance, 1, world, x, y, z);

        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        dropItems(world, x, y, z);
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    private void dropItems(World world, int x, int y, int z) {
        Random rand = new Random();

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                if (item.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }
}

package amerifrance.emt.modules.base.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.modules.base.tile.TileEntityPortableNode;
import thaumcraft.common.blocks.BlockAiry;

import java.util.List;
import java.util.Random;

public class BlockPortableNode extends BlockAiry {

    public IIcon[] texture = new IIcon[1];

    public BlockPortableNode() {
        setBlockName(ModInformation.ID + ".module.base.node.portable");
        setCreativeTab(ElectroMagicTools.tabEMT);
        setBlockUnbreakable();
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        return new TileEntityPortableNode();
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }

    public boolean canEntityDestroy(World world, int x, int y, int z, Entity entity) {
        return false;
    }

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister ri) {
        this.texture[0] = ri.registerIcon(ModInformation.TEXLOC + "portablenode");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.texture[0];
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List list) {
        list.add(new ItemStack(id, 1, 0));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote && player.isSneaking() && player.getHeldItem() == null) {
            world.setBlockToAir(x, y, z);
            world.removeTileEntity(x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}

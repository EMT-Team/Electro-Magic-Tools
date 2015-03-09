package amerifrance.emt.modules.ic2.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import amerifrance.emt.ModInformation;
import amerifrance.emt.modules.ic2.tile.solars.air.TileAirSolar;
import amerifrance.emt.modules.ic2.tile.solars.compressed.TileCompressedSolar;
import amerifrance.emt.modules.ic2.tile.solars.compressed.TileDoubleCompressedSolar;
import amerifrance.emt.modules.ic2.tile.solars.compressed.TileTripleCompressedSolar;
import amerifrance.emt.modules.ic2.tile.solars.dark.TileDarkSolar;
import amerifrance.emt.modules.ic2.tile.solars.dark.TileDoubleDarkSolar;
import amerifrance.emt.modules.ic2.tile.solars.dark.TileTripleDarkSolar;
import amerifrance.emt.modules.ic2.tile.solars.fire.TileDoubleFireSolar;
import amerifrance.emt.modules.ic2.tile.solars.fire.TileFireSolar;
import amerifrance.emt.modules.ic2.tile.solars.fire.TileTripleFireSolar;
import amerifrance.emt.modules.ic2.tile.solars.order.TileDoubleOrderSolar;
import amerifrance.emt.modules.ic2.tile.solars.order.TileOrderSolar;
import amerifrance.emt.modules.ic2.tile.solars.order.TileTripleOrderSolar;
import amerifrance.emt.modules.ic2.tile.solars.water.TileDoubleWaterSolar;
import amerifrance.emt.modules.ic2.tile.solars.water.TileTripleWaterSolar;
import amerifrance.emt.modules.ic2.tile.solars.water.TileWaterSolar;

import java.util.List;

public class BlockSolars1 extends BlockIC2Container {

    public BlockSolars1() {
        super("solars", Material.iron, soundTypeMetal, 4.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.top[0] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/solartop");
        this.top[1] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/doublesolartop");
        this.top[2] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/triplesolartop");
        this.top[3] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/solartop");
        this.top[4] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/doublesolartop");
        this.top[5] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/triplesolartop");
        this.top[6] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/solartop");
        this.top[7] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/doublesolartop");
        this.top[8] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/triplesolartop");
        this.top[9] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/solartop");
        this.top[10] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/doublesolartop");
        this.top[11] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/triplesolartop");
        this.top[12] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/solartop");
        this.top[13] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/doublesolartop");
        this.top[14] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/triplesolartop");
        this.top[15] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/solartop");

        this.bottom[0] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/bottom");
        this.bottom[1] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/bottom");
        this.bottom[2] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/bottom");
        this.bottom[3] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/waterbottom");
        this.bottom[4] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/waterbottom");
        this.bottom[5] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/waterbottom");
        this.bottom[6] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/darkbottom");
        this.bottom[7] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/darkbottom");
        this.bottom[8] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/darkbottom");
        this.bottom[9] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/orderbottom");
        this.bottom[10] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/orderbottom");
        this.bottom[11] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/orderbottom");
        this.bottom[12] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/firebottom");
        this.bottom[13] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/firebottom");
        this.bottom[14] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/firebottom");
        this.bottom[15] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/airbottom");

        this.frontOff[0] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/side");
        this.frontOff[1] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/side");
        this.frontOff[2] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/side");
        this.frontOff[3] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/waterside");
        this.frontOff[4] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/waterside");
        this.frontOff[5] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/waterside");
        this.frontOff[6] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/darkside");
        this.frontOff[7] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/darkside");
        this.frontOff[8] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/darkside");
        this.frontOff[9] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/orderside");
        this.frontOff[10] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/orderside");
        this.frontOff[11] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/orderside");
        this.frontOff[12] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/fireside");
        this.frontOff[13] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/fireside");
        this.frontOff[14] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/fireside");
        this.frontOff[15] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/airside");

        this.side[0] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/side");
        this.side[1] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/side");
        this.side[2] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/side");
        this.side[3] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/waterside");
        this.side[4] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/waterside");
        this.side[5] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/water/waterside");
        this.side[6] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/darkside");
        this.side[7] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/darkside");
        this.side[8] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/dark/darkside");
        this.side[9] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/orderside");
        this.side[10] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/orderside");
        this.side[11] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/order/orderside");
        this.side[12] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/fireside");
        this.side[13] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/fireside");
        this.side[14] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/fire/fireside");
        this.side[15] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/airside");
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void getSubBlocks(Item id, CreativeTabs tab, List list) {
        for (int i = 0; i <= 15; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 0:
                return new TileCompressedSolar();
            case 1:
                return new TileDoubleCompressedSolar();
            case 2:
                return new TileTripleCompressedSolar();
            case 3:
                return new TileWaterSolar();
            case 4:
                return new TileDoubleWaterSolar();
            case 5:
                return new TileTripleWaterSolar();
            case 6:
                return new TileDarkSolar();
            case 7:
                return new TileDoubleDarkSolar();
            case 8:
                return new TileTripleDarkSolar();
            case 9:
                return new TileOrderSolar();
            case 10:
                return new TileDoubleOrderSolar();
            case 11:
                return new TileTripleOrderSolar();
            case 12:
                return new TileFireSolar();
            case 13:
                return new TileDoubleFireSolar();
            case 14:
                return new TileTripleFireSolar();
            case 15:
                return new TileAirSolar();
        }

        return super.createTileEntity(world, meta);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }
}
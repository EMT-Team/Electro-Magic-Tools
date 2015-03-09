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
import amerifrance.emt.modules.ic2.tile.solars.air.TileDoubleAirSolar;
import amerifrance.emt.modules.ic2.tile.solars.air.TileTripleAirSolar;
import amerifrance.emt.modules.ic2.tile.solars.earth.TileDoubleEarthSolar;
import amerifrance.emt.modules.ic2.tile.solars.earth.TileEarthSolar;
import amerifrance.emt.modules.ic2.tile.solars.earth.TileTripleEarthSolar;

import java.util.List;

public class BlockSolars2 extends BlockIC2Container {

    public BlockSolars2() {
        super("solars", Material.iron, soundTypeMetal, 4.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.top[0] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/doublesolartop");
        this.top[1] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/triplesolartop");
        this.top[2] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/solartop");
        this.top[3] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/doublesolartop");
        this.top[4] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/triplesolartop");

        this.bottom[0] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/airbottom");
        this.bottom[1] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/airbottom");
        this.bottom[2] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/earthbottom");
        this.bottom[3] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/earthbottom");
        this.bottom[4] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/earthbottom");

        this.frontOff[0] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/airside");
        this.frontOff[1] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/airside");
        this.frontOff[2] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/earthside");
        this.frontOff[3] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/earthside");
        this.frontOff[4] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/earthside");

        this.side[0] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/airside");
        this.side[1] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/air/airside");
        this.side[2] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/earthside");
        this.side[3] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/earthside");
        this.side[4] = iconRegister.registerIcon(ModInformation.TEXLOC + "solars/earth/earthside");
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void getSubBlocks(Item id, CreativeTabs tab, List list) {
        for (int i = 0; i <= 4; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        if (meta == 0) {
            return new TileDoubleAirSolar();
        }
        if (meta == 1) {
            return new TileTripleAirSolar();
        }
        if (meta == 2) {
            return new TileEarthSolar();
        }
        if (meta == 3) {
            return new TileDoubleEarthSolar();
        }
        if (meta == 4) {
            return new TileTripleEarthSolar();
        }
        return super.createTileEntity(world, meta);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }
}
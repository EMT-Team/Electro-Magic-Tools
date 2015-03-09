package amerifrance.emt.modules.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import amerifrance.emt.modules.base.tile.TileMultiblock;

public class BlockMultiblock extends BlockBaseContainer {

    public BlockMultiblock(String unlocName, Material material, SoundType soundType, float hardness) {
        super(unlocName, material, soundType, hardness);
    }

    public BlockMultiblock(String unlocName, String textureName, Material material, SoundType soundType, float hardness) {
        super(unlocName, textureName, material, soundType, hardness);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileMultiblock) {
            TileMultiblock multiblock = (TileMultiblock) tile;
            if (multiblock.getMaster() != null) {
                return multiblock.getMaster().onBlockRightClicked(player, side, hitX, hitY, hitZ);
            }
        }
        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        TileMultiblock multiblock = (TileMultiblock) world.getTileEntity(x, y, z);
        if (multiblock.getMaster() != null) {
            multiblock.getMaster().resetStructure();
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        TileMultiblock multiblock = (TileMultiblock) world.getTileEntity(x, y, z);
        if (multiblock.getMaster() != null & !multiblock.checkMultiblock()) {
            multiblock.getMaster().resetStructure();
        }
        super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
    }
}

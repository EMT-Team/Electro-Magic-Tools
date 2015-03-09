package amerifrance.emt.modules.ic2.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import amerifrance.emt.modules.base.blocks.BlockMultiblock;
import amerifrance.emt.modules.ic2.tile.TileProcessor;

public class BlockProcessor extends BlockMultiblock {

    public BlockProcessor() {
        super("processor", Material.iron, soundTypeMetal, 4.0F);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileProcessor();
    }
}

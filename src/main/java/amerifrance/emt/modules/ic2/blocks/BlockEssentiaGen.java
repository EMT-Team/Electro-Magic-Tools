package amerifrance.emt.modules.ic2.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import amerifrance.emt.ModInformation;
import amerifrance.emt.modules.ic2.tile.TileEssentiaGenerator;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaContainerItem;

public class BlockEssentiaGen extends BlockIC2Container {

    public BlockEssentiaGen() {
        super("generator.essentia", Material.iron, soundTypeMetal, 4.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ri) {
        this.blockIcon = ri.registerIcon(ModInformation.TEXLOC + "machines/top");
        this.top[0] = ri.registerIcon(ModInformation.TEXLOC + "essentiagenerator/top");
        this.bottom[0] = ri.registerIcon(ModInformation.TEXLOC + "essentiagenerator/top");
        this.side[0] = ri.registerIcon(ModInformation.TEXLOC + "essentiagenerator/side");
        this.frontOff[0] = ri.registerIcon(ModInformation.TEXLOC + "essentiagenerator/saxumfront");
        this.frontOn[0] = ri.registerIcon(ModInformation.TEXLOC + "essentiagenerator/saxumfront");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEssentiaGenerator();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        TileEssentiaGenerator tile = (TileEssentiaGenerator) world.getTileEntity(x, y, z);
        if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IEssentiaContainerItem) {
            IEssentiaContainerItem item = (IEssentiaContainerItem) player.getHeldItem().getItem();
            Aspect aspect = item.getAspects(player.getHeldItem()).getAspects()[0];
            if (tile.isFree[0]) {
                tile.aspects[0] = aspect.getTag();
                tile.isFree[0] = false;
            } else if (tile.isFree[1]) {
                tile.aspects[1] = aspect.getTag();
                tile.isFree[1] = false;
            } else if (tile.isFree[2]) {
                tile.aspects[2] = aspect.getTag();
                tile.isFree[2] = false;
            } else if (tile.isFree[3]) {
                tile.aspects[3] = aspect.getTag();
                tile.isFree[3] = false;
            }
            return true;
        } else if (player.getHeldItem() == null && player.isSneaking()) {
            for (int i = 0; i < tile.aspects.length; i++) {
                if (!tile.isFree[i]) {
                    tile.isFree[i] = true;
                }
            }
            return true;
        }
        return false;
    }
}

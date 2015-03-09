package amerifrance.emt.modules.base.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.modules.base.tile.TileEntityBase;

public class BlockBaseContainer extends BlockContainer {

    public IIcon[] top = new IIcon[16];
    public IIcon[] bottom = new IIcon[16];
    public IIcon[] side = new IIcon[16];
    public IIcon[] frontOff = new IIcon[16];
    public IIcon[] frontOn = new IIcon[16];

    public BlockBaseContainer(String unlocName, Material material, SoundType soundType, float hardness) {
        super(material);

        setBlockName(ModInformation.ID + ".module.base." + unlocName);
        setCreativeTab(ElectroMagicTools.tabEMT);
        setStepSound(soundType);
        setHardness(hardness);
    }

    public BlockBaseContainer(String unlocName, String textureName, Material material, SoundType soundType, float hardness) {
        super(material);

        setBlockName(ModInformation.ID + ".module.base." + unlocName);
        setBlockTextureName(ModInformation.TEXLOC + textureName);
        setCreativeTab(ElectroMagicTools.tabEMT);
        setStepSound(soundType);
        setHardness(hardness);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side) {
        TileEntityBase tile = (TileEntityBase) access.getTileEntity(x, y, z);
        int i = access.getBlockMetadata(x, y, z);

        if (side == 0) {
            return this.bottom[i];
        } else if (side == 1) {
            return this.top[i];
        } else if (side != tile.facing) {
            return this.side[i];
        } else if (tile.isOn) {
            return this.frontOn[i];
        } else {
            return this.frontOff[i];
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side < 1) {
            return this.bottom[meta];
        }
        if (side == 1) {
            return this.top[meta];
        }
        if (side == 3) {
            return this.frontOff[meta];
        }
        return this.side[meta];
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        int facing = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileEntityBase tile = (TileEntityBase) world.getTileEntity(x, y, z);

        if (facing == 0)
            tile.facing = 2;
        else if (facing == 1)
            tile.facing = 5;
        else if (facing == 2)
            tile.facing = 3;
        else if (facing == 3)
            tile.facing = 4;
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }
}

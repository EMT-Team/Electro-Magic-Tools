package amerifrance.emt.modules.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;

public class BlockBase extends Block {

    public BlockBase(String unlocName, Material material, String textureName, SoundType soundType, float hardness) {
        super(material);

        setBlockName(ModInformation.ID + ".module.base." + unlocName);
        setBlockTextureName(ModInformation.TEXLOC + textureName);
        setCreativeTab(ElectroMagicTools.tabEMT);
        setStepSound(soundType);
        setHardness(hardness);
    }

    public BlockBase(String unlocName, Material material, SoundType soundType, float hardness) {
        super(material);

        setBlockName(ModInformation.ID + ".module.base." + unlocName);
        setCreativeTab(ElectroMagicTools.tabEMT);
        setStepSound(soundType);
        setHardness(hardness);
    }
}

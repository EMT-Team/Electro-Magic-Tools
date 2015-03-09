package amerifrance.emt.modules.base.items;

import net.minecraft.item.Item;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;

public class ItemBase extends Item {

    public ItemBase(String unlocName, String textureName) {
        super();

        setUnlocalizedName(ModInformation.ID + ".module.base." + unlocName);
        setTextureName(ModInformation.ID + ":" + textureName);
        setCreativeTab(ElectroMagicTools.tabEMT);
    }

    public ItemBase(String unlocName) {
        super();

        setUnlocalizedName(ModInformation.ID + ".module.base." + unlocName);
        setCreativeTab(ElectroMagicTools.tabEMT);
    }
}

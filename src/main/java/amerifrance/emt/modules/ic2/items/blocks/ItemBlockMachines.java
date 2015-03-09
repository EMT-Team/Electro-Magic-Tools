package amerifrance.emt.modules.ic2.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMachines extends ItemBlock {

    private static final String[] names = {"wandrecharge", "etherealprocessor"};

    public ItemBlockMachines(Block id) {
        super(id);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "." + names[stack.getItemDamage() % names.length];
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }
}

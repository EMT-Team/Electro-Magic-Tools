package amerifrance.emt.modules.ic2.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSolars1 extends ItemBlock {

    private static final String[] names = {"compressed", "doublecompressed", "triplecompressed", "water", "doublewater", "triplewater", "dark", "doubledark", "tripledark", "order", "doubleorder", "tripleorder", "fire", "doublefire", "triplefire", "air"};

    public ItemBlockSolars1(Block id) {
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

package amerifrance.emt.modules.tools.items.types;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import amerifrance.emt.modules.base.items.ItemBaseDrill;

public class ItemVanillaDrill extends ItemBaseDrill {

    public ItemStack repairMaterial;

    public ItemVanillaDrill(ToolMaterial material, int maxDamage, String textureName, ItemStack repairMaterial) {
        super(material, maxDamage, textureName);
        this.repairMaterial = repairMaterial;
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return (block.getHarvestLevel(new ItemStack(Item.getItemFromBlock(block)).getItemDamage()) <= this.toolMaterial.getHarvestLevel());
    }

    @Override
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        return stack2.isItemEqual(repairMaterial) || super.getIsRepairable(stack1, stack2);
    }

    @Override
    public boolean isRepairable() {
        return true;
    }
}

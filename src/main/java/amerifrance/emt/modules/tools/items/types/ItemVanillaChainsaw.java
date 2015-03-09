package amerifrance.emt.modules.tools.items.types;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import amerifrance.emt.modules.base.items.ItemBaseChainsaw;

public class ItemVanillaChainsaw extends ItemBaseChainsaw {

    public Item repairMaterial;
    public int repairMeta;
    public float damageAdded;

    public ItemVanillaChainsaw(ToolMaterial material, int maxDamage, String textureName, Item repairMaterial) {
        super(material, maxDamage, textureName);
        this.repairMaterial = repairMaterial;
        this.repairMeta = 0;
        damageAdded = 2;
    }

    public ItemVanillaChainsaw(ToolMaterial material, int maxDamage, String textureName, Item repairMaterial, float damageAdded) {
        super(material, maxDamage, textureName);
        this.repairMaterial = repairMaterial;
        this.repairMeta = 0;
        this.damageAdded = damageAdded;
    }

    public ItemVanillaChainsaw(ToolMaterial material, int maxDamage, String textureName, Item repairMaterial, int repairMeta) {
        super(material, maxDamage, textureName);
        this.repairMaterial = repairMaterial;
        this.repairMeta = repairMeta;
        damageAdded = 2;
    }

    public ItemVanillaChainsaw(ToolMaterial material, int maxDamage, String textureName, Item repairMaterial, int repairMeta, float damageAdded) {
        super(material, maxDamage, textureName);
        this.repairMaterial = repairMaterial;
        this.repairMeta = repairMeta;
        this.damageAdded = damageAdded;
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return (block.getHarvestLevel(new ItemStack(Item.getItemFromBlock(block)).getItemDamage()) <= this.toolMaterial.getHarvestLevel());
    }

    @Override
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        return (stack2.getItem() == repairMaterial && stack2.getItemDamage() == repairMeta) || super.getIsRepairable(stack1, stack2);
    }

    @Override
    public boolean isRepairable() {
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityliving, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        entityliving.attackEntityFrom(DamageSource.generic, damageAdded);
        return true;
    }
}

package amerifrance.emt.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import amerifrance.emt.modules.base.items.BaseItemRegistry;

import static amerifrance.emt.ConfigHandler.*;

public class LootHandler {

    public static void addLootToChests() {
        if (thorHammerSpawn && thorHammerResearch) {
            ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(BaseItemRegistry.thorHammerBroken), 0, 1, chanceTaintedMjolnir));
        }
        if (oneRingSpawn) {
            ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(BaseItemRegistry.baseBaubles, 1, 0), 0, 1, chanceOneRing));
        }
    }
}

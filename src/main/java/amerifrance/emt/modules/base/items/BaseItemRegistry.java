package amerifrance.emt.modules.base.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import amerifrance.emt.modules.base.items.armor.ItemFeatherWing;
import amerifrance.emt.modules.base.items.armor.ItemThaumiumWing;
import amerifrance.emt.modules.base.items.foci.ItemFocusChristmas;
import amerifrance.emt.modules.base.items.foci.ItemFocusShield;
import amerifrance.emt.modules.base.items.tools.hammer.ItemThorHammer;
import amerifrance.emt.modules.base.items.tools.hammer.ItemThorHammerBroken;
import thaumcraft.api.ThaumcraftApi;

public class BaseItemRegistry {

    // Materials
    // Armor
    public static ItemArmor.ArmorMaterial featherWingMaterial = EnumHelper.addArmorMaterial("FEATEHRWiNG", 1, new int[]{2, 2, 2, 2}, 6);

    public static Item baseBaubles;

    public static Item materials;

    public static Item focusChristmas;
    public static Item focusShield;

    public static Item thorHammer;
    public static Item thorHammerBroken;

    public static Item wingFeather;
    public static Item wingThaumium;

    public static void registerItems() {

        baseBaubles = new ItemBaseBaubles();
        GameRegistry.registerItem(baseBaubles, "ItemBaseBaubles");

        materials = new ItemBaseMaterials();
        GameRegistry.registerItem(materials, "ItemBaseMaterials");


        focusChristmas = new ItemFocusChristmas();
        GameRegistry.registerItem(focusChristmas, "ItemFocusChristmas");
        focusShield = new ItemFocusShield();
        GameRegistry.registerItem(focusShield, "ItemFocusShield");

        thorHammer = new ItemThorHammer();
        GameRegistry.registerItem(thorHammer, "ItemThorHammer");
        thorHammerBroken = new ItemThorHammerBroken();
        GameRegistry.registerItem(thorHammerBroken, "ItemThorHammerBroken");

        wingFeather = new ItemFeatherWing(featherWingMaterial);
        GameRegistry.registerItem(wingFeather, "ItemFeatherWing");
        wingThaumium = new ItemThaumiumWing(ThaumcraftApi.armorMatThaumium);
        GameRegistry.registerItem(wingThaumium, "ItemThaumiumWing");
    }
}

package amerifrance.emt.modules.ic2.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import amerifrance.emt.modules.ic2.items.armor.boots.ItemElectricBootsTraveller;
import amerifrance.emt.modules.ic2.items.armor.boots.ItemNanoBootsTraveller;
import amerifrance.emt.modules.ic2.items.armor.boots.ItemQuantumBootsTraveller;
import amerifrance.emt.modules.ic2.items.armor.goggles.ItemElectricGoggles;
import amerifrance.emt.modules.ic2.items.armor.goggles.ItemNanoGoggles;
import amerifrance.emt.modules.ic2.items.armor.goggles.ItemQuantumGoggles;
import amerifrance.emt.modules.ic2.items.armor.goggles.ItemSolarGoggles;
import amerifrance.emt.modules.ic2.items.armor.wings.ItemNanoWing;
import amerifrance.emt.modules.ic2.items.armor.wings.ItemQuantumWing;
import amerifrance.emt.modules.ic2.items.foci.ItemFocusCharge;
import amerifrance.emt.modules.ic2.items.foci.ItemFocusExplosion;
import amerifrance.emt.modules.ic2.items.foci.ItemFocusWandCharge;
import amerifrance.emt.modules.ic2.items.tools.ItemElectricScribingTools;
import amerifrance.emt.modules.ic2.items.tools.ItemElectricThorHammer;
import amerifrance.emt.modules.ic2.items.tools.ItemHoeGrowthElectric;
import amerifrance.emt.modules.ic2.items.tools.chainsaw.ItemChainsawDiamond;
import amerifrance.emt.modules.ic2.items.tools.chainsaw.ItemChainsawStream;
import amerifrance.emt.modules.ic2.items.tools.chainsaw.ItemChainsawThaumium;
import amerifrance.emt.modules.ic2.items.tools.drill.ItemDrillRockbreaker;
import amerifrance.emt.modules.ic2.items.tools.drill.ItemDrillThaumium;
import amerifrance.emt.modules.ic2.items.tools.omnitool.ItemOmnitoolDiamond;
import amerifrance.emt.modules.ic2.items.tools.omnitool.ItemOmnitoolIron;
import amerifrance.emt.modules.ic2.items.tools.omnitool.ItemOmnitoolThaumium;

public class IC2ItemRegistry {

    public static Item baubleIC2;

    public static Item focusCharge;
    public static Item focusWandCharge;
    public static Item focusExplosion;

    public static Item toolScribingElectric;

    public static Item toolHoeGrowthElectric;

    public static Item toolChainsawThaumium;
    public static Item toolChainsawDiamond;
    public static Item toolChainsawStream;

    public static Item toolDrillThaumium;
    public static Item toolDrillRockbreaker;

    public static Item toolOmniIron;
    public static Item toolOmniThaumium;
    public static Item toolOmniDiamond;

    public static Item weaponHammerElectric;

    public static Item armorElectricGoggles;
    public static Item armorNanoGoggles;
    public static Item armorQuantumGoggles;
    public static Item armorSolarGoggles;

    public static Item armorNanoWing;
    public static Item armorQuantumWing;

    public static Item armorElectricBoots;
    public static Item armorNanoBoots;
    public static Item armorQuantumBoots;

    public static void registerItems() {

        baubleIC2 = new ItemIC2Baubles();
        GameRegistry.registerItem(baubleIC2, "ItemIC2Baubles");

        focusCharge = new ItemFocusCharge();
        GameRegistry.registerItem(focusCharge, "ItemFocusCharge");
        focusWandCharge = new ItemFocusWandCharge();
        GameRegistry.registerItem(focusWandCharge, "ItemFocusWandCharge");
        focusExplosion = new ItemFocusExplosion();
        GameRegistry.registerItem(focusExplosion, "ItemFocusExplosion");

        toolScribingElectric = new ItemElectricScribingTools();
        GameRegistry.registerItem(toolScribingElectric, "ItemElectricScribingTools");

        toolHoeGrowthElectric = new ItemHoeGrowthElectric();
        GameRegistry.registerItem(toolHoeGrowthElectric, "ItemHoeGrowthElectric");

        toolChainsawThaumium = new ItemChainsawThaumium();
        GameRegistry.registerItem(toolChainsawThaumium, "ItemChainsawThaumium");
        toolChainsawDiamond = new ItemChainsawDiamond();
        GameRegistry.registerItem(toolChainsawDiamond, "ItemChainsawDiamond");
        toolChainsawStream = new ItemChainsawStream();
        GameRegistry.registerItem(toolChainsawStream, "ItemChainsawStream");

        toolDrillThaumium = new ItemDrillThaumium();
        GameRegistry.registerItem(toolDrillThaumium, "ItemDrillThaumium");
        toolDrillRockbreaker = new ItemDrillRockbreaker();
        GameRegistry.registerItem(toolDrillRockbreaker, "ItemDrillRockbreaker");

        toolOmniIron = new ItemOmnitoolIron();
        GameRegistry.registerItem(toolOmniIron, "ItemOmnitoolIron");

        toolOmniThaumium = new ItemOmnitoolThaumium();
        GameRegistry.registerItem(toolOmniThaumium, "ItemOmnitoolThaumium");

        toolOmniDiamond = new ItemOmnitoolDiamond();
        GameRegistry.registerItem(toolOmniDiamond, "ItemOmnitoolDiamond");

        weaponHammerElectric = new ItemElectricThorHammer();
        GameRegistry.registerItem(weaponHammerElectric, "ItemElectricThorHammer");

        armorElectricGoggles = new ItemElectricGoggles(ItemArmor.ArmorMaterial.IRON);
        GameRegistry.registerItem(armorElectricGoggles, "ItemElectricGoggles");
        armorNanoGoggles = new ItemNanoGoggles();
        GameRegistry.registerItem(armorNanoGoggles, "ItemNanoGoggles");
        armorQuantumGoggles = new ItemQuantumGoggles();
        GameRegistry.registerItem(armorQuantumGoggles, "ItemQuantumGoggles");
        armorSolarGoggles = new ItemSolarGoggles();
        GameRegistry.registerItem(armorSolarGoggles, "ItemSolarGoggles");

        armorNanoWing = new ItemNanoWing();
        GameRegistry.registerItem(armorNanoWing, "ItemNanoWing");
        armorQuantumWing = new ItemQuantumWing();
        GameRegistry.registerItem(armorQuantumWing, "ItemQuantumWing");

        armorElectricBoots = new ItemElectricBootsTraveller();
        GameRegistry.registerItem(armorElectricBoots, "ItemElectricBootsTraveller");
        armorNanoBoots = new ItemNanoBootsTraveller();
        GameRegistry.registerItem(armorNanoBoots, "ItemNanoBootsTraveller");
        armorQuantumBoots = new ItemQuantumBootsTraveller();
        GameRegistry.registerItem(armorQuantumBoots, "ItemQuantumBootsTraveller");
    }
}

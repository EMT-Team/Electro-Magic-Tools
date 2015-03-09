package amerifrance.emt.modules.tools.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import amerifrance.emt.ModInformation;
import amerifrance.emt.modules.tools.items.types.ItemShardCarver;
import amerifrance.emt.modules.tools.items.types.ItemVanillaChainsaw;
import amerifrance.emt.modules.tools.items.types.ItemVanillaDrill;
import amerifrance.emt.modules.tools.items.types.ItemVanillaOmnitool;

public class ToolItemRegistry {

    public static Item toolComponents;

    // Tools
    public static Item.ToolMaterial copperMaterial = EnumHelper.addToolMaterial("copperToolMaterial", 2, 300, 6.0F, 2.0F, 14);
    public static Item.ToolMaterial tinMaterial = EnumHelper.addToolMaterial("tinToolMatierial", 2, 200, 8.0F, 4F, 18);
    public static Item.ToolMaterial leadMaterial = EnumHelper.addToolMaterial("leadToolMaterial", 2, 250, 7.0F, 3F, 15);
    public static Item.ToolMaterial bronzeMaterial = EnumHelper.addToolMaterial("bronzeToolMaterial", 2, 350, 7.0F, 3.0F, 16);

    // Items
    public static Item woodenDrill, stoneDrill, ironDrill, goldenDrill, diamondDrill, copperDrill, tinDrill, leadDrill, bronzeDrill;
    public static Item woodenChainsaw, stoneChainsaw, ironChainsaw, goldenChainsaw, diamondChainsaw, copperChainsaw, tinChainsaw, leadChainsaw, bronzeChainsaw;
    public static Item ironOmnitool, goldenOmnitool, diamondOmnitool, copperOmnitool, tinOmnitool, leadOmnitool, bronzeOmnitool;
    public static Item woodCarver, stoneCarver, ironCarver, obsidianCarver, diamondCarver;

    public static void registerItems() {
        toolComponents = new ItemToolComponents();
        GameRegistry.registerItem(toolComponents, "ItemToolComponents");

        woodCarver = new ItemShardCarver(8).setUnlocalizedName(ModInformation.ID + ".module.tools.carver.wood");
        GameRegistry.registerItem(woodCarver, "woodCarver");
        stoneCarver = new ItemShardCarver(32).setUnlocalizedName(ModInformation.ID + ".module.tools.carver.stone");
        GameRegistry.registerItem(stoneCarver, "stoneCarver");
        ironCarver = new ItemShardCarver(128).setUnlocalizedName(ModInformation.ID + ".module.tools.carver.iron");
        GameRegistry.registerItem(ironCarver, "ironCarver");
        obsidianCarver = new ItemShardCarver(256).setUnlocalizedName(ModInformation.ID + ".module.tools.carver.obsidian");
        GameRegistry.registerItem(obsidianCarver, "obsidianCarver");
        diamondCarver = new ItemShardCarver(1204).setUnlocalizedName(ModInformation.ID + ".module.tools.carver.diamond");
        GameRegistry.registerItem(diamondCarver, "diamondCarver");

        woodenDrill = new ItemVanillaDrill(Item.ToolMaterial.WOOD, 75, "drill_wood", new ItemStack(Item.getItemFromBlock(Blocks.planks))).setUnlocalizedName(ModInformation.ID + ".module.tools.drill.wood");
        GameRegistry.registerItem(woodenDrill, "woodenDrill");
        stoneDrill = new ItemVanillaDrill(Item.ToolMaterial.STONE, 165, "drill_stone", new ItemStack(Item.getItemFromBlock(Blocks.cobblestone))).setUnlocalizedName(ModInformation.ID + ".module.tools.drill.stone");
        GameRegistry.registerItem(stoneDrill, "stoneDrill");
        ironDrill = new ItemVanillaDrill(Item.ToolMaterial.IRON, 400, "drill_iron", new ItemStack(Items.iron_ingot)).setUnlocalizedName(ModInformation.ID + ".module.tools.drill.iron");
        GameRegistry.registerItem(ironDrill, "ironDrill");
        goldenDrill = new ItemVanillaDrill(Item.ToolMaterial.GOLD, 55, "drill_gold", new ItemStack(Items.gold_ingot)).setUnlocalizedName(ModInformation.ID + ".module.tools.drill.gold");
        GameRegistry.registerItem(goldenDrill, "goldenDrill");
        diamondDrill = new ItemVanillaDrill(Item.ToolMaterial.EMERALD, 2015, "drill_diamond", new ItemStack(Items.diamond)).setUnlocalizedName(ModInformation.ID + ".module.tools.drill.diamond");
        GameRegistry.registerItem(diamondDrill, "diamondDrill");
        copperDrill = new ItemVanillaDrill(copperMaterial, 450, "drill_copper", OreDictionary.getOres("ingotCopper").get(0)).setUnlocalizedName(ModInformation.ID + ".module.tools.drill.copper");
        GameRegistry.registerItem(copperDrill, "copperDrill");
        tinDrill = new ItemVanillaDrill(tinMaterial, 350, "drill_tin", OreDictionary.getOres("ingotTin").get(0)).setUnlocalizedName(ModInformation.ID + ".module.tools.drill.tin");
        GameRegistry.registerItem(tinDrill, "tinDrill");
        leadDrill = new ItemVanillaDrill(leadMaterial, 400, "drill_lead", OreDictionary.getOres("ingotLead").get(0)).setUnlocalizedName(ModInformation.ID + ".module.tools.drill.lead");
        GameRegistry.registerItem(leadDrill, "leadDrill");
        bronzeDrill = new ItemVanillaDrill(bronzeMaterial, 650, "drill_bronze", OreDictionary.getOres("ingotBronze").get(0)).setUnlocalizedName(ModInformation.ID + ".module.tools.drill.bronze");
        GameRegistry.registerItem(bronzeDrill, "bronzeDrill");

        woodenChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.WOOD, 75, "chainsaw_wooden", Item.getItemFromBlock(Blocks.planks)).setUnlocalizedName(ModInformation.ID + ".module.tools.chainsaw.wood");
        GameRegistry.registerItem(woodenChainsaw, "woodenChainsaw");
        stoneChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.STONE, 165, "chainsaw_stone", Item.getItemFromBlock(Blocks.cobblestone)).setUnlocalizedName(ModInformation.ID + ".module.tools.chainsaw.stone");
        GameRegistry.registerItem(stoneChainsaw, "stoneChainsaw");
        ironChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.IRON, 315, "chainsaw_iron", Items.iron_ingot, 3F).setUnlocalizedName(ModInformation.ID + ".module.tools.chainsaw.iron");
        GameRegistry.registerItem(ironChainsaw, "ironChainsaw");
        goldenChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.GOLD, 55, "chainsaw_gold", Items.gold_ingot).setUnlocalizedName(ModInformation.ID + ".module.tools.chainsaw.gold");
        GameRegistry.registerItem(goldenChainsaw, "goldenChainsaw");
        diamondChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.EMERALD, 2015, "chainsaw_diamond", Items.diamond, 6F).setUnlocalizedName(ModInformation.ID + ".module.tools.chainsaw.diamond");
        GameRegistry.registerItem(diamondChainsaw, "diamondChainsaw");
        copperChainsaw = new ItemVanillaChainsaw(copperMaterial, 450, "chainsaw_copper", OreDictionary.getOres("ingotCopper").get(0).getItem(), 3F).setUnlocalizedName(ModInformation.ID + ".module.tools.chainsaw.copper");
        GameRegistry.registerItem(copperChainsaw, "copperChainsaw");
        tinChainsaw = new ItemVanillaChainsaw(tinMaterial, 350, "chainsaw_tin", OreDictionary.getOres("ingotTin").get(0).getItem(), 5F).setUnlocalizedName(ModInformation.ID + ".module.tools.chainsaw.tin");
        GameRegistry.registerItem(tinChainsaw, "tinChainsaw");
        leadChainsaw = new ItemVanillaChainsaw(leadMaterial, 400, "chainsaw_lead", OreDictionary.getOres("ingotLead").get(0).getItem(), 3.5F).setUnlocalizedName(ModInformation.ID + ".module.tools.chainsaw.lead");
        GameRegistry.registerItem(leadChainsaw, "leadChainsaw");
        bronzeChainsaw = new ItemVanillaChainsaw(bronzeMaterial, 650, "chainsaw_bronze", OreDictionary.getOres("ingotBronze").get(0).getItem(), 4F).setUnlocalizedName(ModInformation.ID + ".module.tools.chainsaw.bronze");
        GameRegistry.registerItem(bronzeChainsaw, "bronzeChainsaw");

        ironOmnitool = new ItemVanillaOmnitool(Item.ToolMaterial.IRON, 500, "omnitool_iron", Items.iron_ingot).setUnlocalizedName(ModInformation.ID + ".module.tools.omnitool.iron");
        GameRegistry.registerItem(ironOmnitool, "ironOmnitool");
        goldenOmnitool = new ItemVanillaOmnitool(Item.ToolMaterial.GOLD, 100, "omnitool_gold", Items.gold_ingot).setUnlocalizedName(ModInformation.ID + ".module.tools.omnitool.gold");
        GameRegistry.registerItem(goldenOmnitool, "goldenOmnitool");
        diamondOmnitool = new ItemVanillaOmnitool(Item.ToolMaterial.EMERALD, 3900, "omnitool_diamond", Items.diamond).setUnlocalizedName(ModInformation.ID + ".module.tools.omnitool.diamond");
        GameRegistry.registerItem(diamondOmnitool, "diamondOmnitool");
        copperOmnitool = new ItemVanillaOmnitool(copperMaterial, 850, "omnitool_copper", OreDictionary.getOres("ingotCopper").get(0).getItem()).setUnlocalizedName(ModInformation.ID + ".module.tools.omnitool.copper");
        GameRegistry.registerItem(copperOmnitool, "copperOmnitool");
        tinOmnitool = new ItemVanillaOmnitool(tinMaterial, 640, "omnitool_tin", OreDictionary.getOres("ingotTin").get(0).getItem()).setUnlocalizedName(ModInformation.ID + ".module.tools.omnitool.tin");
        GameRegistry.registerItem(tinOmnitool, "tinOmnitool");
        leadOmnitool = new ItemVanillaOmnitool(leadMaterial, 700, "omnitool_lead", OreDictionary.getOres("ingotLead").get(0).getItem()).setUnlocalizedName(ModInformation.ID + ".module.tools.omnitool.lead");
        GameRegistry.registerItem(leadOmnitool, "leadOmnitool");
        bronzeOmnitool = new ItemVanillaOmnitool(bronzeMaterial, 950, "omnitool_bronze", OreDictionary.getOres("ingotBronze").get(0).getItem()).setUnlocalizedName(ModInformation.ID + ".module.tools.omnitool.bronze");
        GameRegistry.registerItem(bronzeOmnitool, "bronzeOmnitool");
    }
}

package amerifrance.emt.modules.tools;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import amerifrance.emt.modules.tools.items.ToolItemRegistry;
import amerifrance.emt.modules.tools.items.ToolItemStacks;

public class ToolRecipeRegistry {

    public static IRecipe woodenDrill, stoneDrill, ironDrill, goldenDrill, diamondDrill, copperDrill, tinDrill, leadDrill, bronzeDrill;
    public static IRecipe woodenChainsaw, stoneChainsaw, ironChainsaw, goldenChainsaw, diamondChainsaw, copperChainsaw, tinChainsaw, leadChainsaw, bronzeChainsaw;
    public static IRecipe ironOmnitool, goldenOmnitool, diamondOmnitool, copperOmnitool, tinOmnitool, leadOmnitool, bronzeOmnitool;
    public static IRecipe woodenDrillTop, stoneDrillTop, ironDrillTop, goldenDrillTop, diamondDrillTop, copperDrillTop, tinDrillTop, leadDrillTop, bronzeDrillTop;
    public static IRecipe woodenChainsawTop, stoneChainsawTop, ironChainsawTop, goldenChainsawTop, diamondChainsawTop, copperChainsawTop, tinChainsawTop, leadChainsawTop, bronzeChainsawTop;
    public static IRecipe woodenCarver, stoneCarver, ironCarver, obsidianCarver, diamondCarver;

    public static void init() {
        addChainsawRecipes();
        addChainsawTopRecipes();
        addDrillRecipes();
        addDrillTopRecipes();
        addCarversRecipes();
        addShardsRecipes();
    }

    private static void addDrillTopRecipes() {
        woodenDrillTop = drillTopRecipe(ToolItemStacks.woodenDrillTop, "plankWood", ToolItemStacks.woodenShard);
        stoneDrillTop = drillTopRecipe(ToolItemStacks.stoneDrillTop, "stone", ToolItemStacks.stoneShard);
        ironDrillTop = drillTopRecipe(ToolItemStacks.ironDrillTop, "ingotIron", ToolItemStacks.ironShard);
        goldenDrillTop = drillTopRecipe(ToolItemStacks.goldenDrillTop, "ingotGold", ToolItemStacks.goldenShard);
        diamondDrillTop = drillTopRecipe(ToolItemStacks.diamondDrillTop, "gemDiamond", ToolItemStacks.obsidianShard);

        if (isOreRegistered("ingotCopper"))
            copperDrillTop = drillTopRecipe(ToolItemStacks.copperDrillTop, "ingotCopper", ToolItemStacks.copperShard);
        if (isOreRegistered("ingotTin"))
            tinDrillTop = drillTopRecipe(ToolItemStacks.tinDrillTop, "ingotTin", ToolItemStacks.tinShard);
        if (isOreRegistered("ingotLead"))
            leadDrillTop = drillTopRecipe(ToolItemStacks.leadDrillTop, "ingotLead", ToolItemStacks.leadShard);
        if (isOreRegistered("ingotBronze"))
            bronzeDrillTop = drillTopRecipe(ToolItemStacks.bronzeDrillTop, "ingotBronze", ToolItemStacks.bronzeShard);
    }

    private static void addChainsawTopRecipes() {
        woodenChainsawTop = chainsawTopRecipe(ToolItemStacks.woodenChainsawTop, "plankWood", ToolItemStacks.woodenShard);
        stoneChainsawTop = chainsawTopRecipe(ToolItemStacks.stoneChainsawTop, "stone", ToolItemStacks.stoneShard);
        ironChainsawTop = chainsawTopRecipe(ToolItemStacks.ironChainsawTop, "ingotIron", ToolItemStacks.ironShard);
        goldenChainsawTop = chainsawTopRecipe(ToolItemStacks.goldenChainsawTop, "ingotGold", ToolItemStacks.goldenShard);
        diamondChainsawTop = chainsawTopRecipe(ToolItemStacks.diamondChainsawTop, "gemDiamond", ToolItemStacks.obsidianShard);

        if (isOreRegistered("ingotCopper"))
            copperChainsawTop = chainsawTopRecipe(ToolItemStacks.copperChainsawTop, "ingotCopper", ToolItemStacks.copperShard);
        if (isOreRegistered("ingotTin"))
            tinChainsawTop = chainsawTopRecipe(ToolItemStacks.tinChainsawTop, "ingotTin", ToolItemStacks.tinShard);
        if (isOreRegistered("ingotLead"))
            leadChainsawTop = chainsawTopRecipe(ToolItemStacks.leadChainsawTop, "ingotLead", ToolItemStacks.leadShard);
        if (isOreRegistered("ingotBronze"))
            bronzeChainsawTop = chainsawTopRecipe(ToolItemStacks.bronzeChainsawTop, "ingotBronze", ToolItemStacks.bronzeShard);
    }

    private static void addDrillRecipes() {
        woodenDrill = toolRecipe(ToolItemStacks.woodenDrill, ToolItemStacks.woodenDrillTop, "plankWood", "stickWood");
        stoneDrill = toolRecipe(ToolItemStacks.stoneDrill, ToolItemStacks.stoneDrillTop, "stone", "cobblestone");
        ironDrill = toolRecipe(ToolItemStacks.ironDrill, ToolItemStacks.ironDrillTop, "ingotIron", new ItemStack(Items.leather));
        goldenDrill = toolRecipe(ToolItemStacks.goldenDrill, ToolItemStacks.goldenDrillTop, "ingotGold", new ItemStack(Blocks.obsidian));
        diamondDrill = toolRecipe(ToolItemStacks.diamondDrill, ToolItemStacks.diamondDrillTop, "gemDiamond", "ingotGold");

        if (isOreRegistered("ingotCopper") && isOreRegistered("ingotTin"))
            copperDrill = toolRecipe(ToolItemStacks.copperDrill, ToolItemStacks.copperDrillTop, "ingotCopper", "ingotTin");
        if (isOreRegistered("ingotTin"))
            tinDrill = toolRecipe(ToolItemStacks.tinDrill, ToolItemStacks.tinDrillTop, "ingotTin", "ingotGold");
        if (isOreRegistered("ingotLead"))
            leadDrill = toolRecipe(ToolItemStacks.leadDrill, ToolItemStacks.leadDrillTop, "ingotLead", "ingotIron");
        if (isOreRegistered("ingotBronze"))
            bronzeDrill = toolRecipe(ToolItemStacks.bronzeDrill, ToolItemStacks.bronzeDrillTop, "ingotBronze", new ItemStack(Blocks.obsidian));
    }

    private static void addChainsawRecipes() {
        woodenChainsaw = toolRecipe(ToolItemStacks.woodenChainsaw, ToolItemStacks.woodenChainsawTop, "plankWood", "stickWood");
        stoneChainsaw = toolRecipe(ToolItemStacks.stoneChainsaw, ToolItemStacks.stoneChainsawTop, "stone", "cobblestone");
        ironChainsaw = toolRecipe(ToolItemStacks.ironChainsaw, ToolItemStacks.ironChainsawTop, "ingotIron", new ItemStack(Items.leather));
        goldenChainsaw = toolRecipe(ToolItemStacks.goldenChainsaw, ToolItemStacks.goldenChainsawTop, "ingotGold", new ItemStack(Blocks.obsidian));
        diamondChainsaw = toolRecipe(ToolItemStacks.diamondChainsaw, ToolItemStacks.diamondChainsawTop, "gemDiamond", "ingotGold");

        if (isOreRegistered("ingotCopper") && isOreRegistered("ingotTin"))
            copperChainsaw = toolRecipe(ToolItemStacks.copperChainsaw, ToolItemStacks.copperChainsawTop, "ingotCopper", "ingotTin");
        if (isOreRegistered("ingotTin"))
            tinChainsaw = toolRecipe(ToolItemStacks.tinChainsaw, ToolItemStacks.tinChainsawTop, "ingotTin", "ingotGold");
        if (isOreRegistered("ingotLead"))
            leadChainsaw = toolRecipe(ToolItemStacks.leadChainsaw, ToolItemStacks.leadChainsawTop, "ingotLead", "ingotIron");
        if (isOreRegistered("ingotBronze"))
            bronzeChainsaw = toolRecipe(ToolItemStacks.bronzeChainsaw, ToolItemStacks.bronzeChainsawTop, "ingotBronze", new ItemStack(Blocks.obsidian));
    }

    private static void addCarversRecipes() {
        for (int i = 0; i < OreDictionary.getOres("logWood").size(); i++) {
            woodenCarver = GameRegistry.addShapedRecipe(ToolItemStacks.woodenCarver, " X ", "YYY", 'X', Items.flint, 'Y', OreDictionary.getOres("logWood").get(i));
        }
        stoneCarver = GameRegistry.addShapedRecipe(ToolItemStacks.stoneCarver, " X ", "YYY", 'X', Items.flint, 'Y', Blocks.stone);
        ironCarver = GameRegistry.addShapedRecipe(ToolItemStacks.ironCarver, " X ", "YYY", 'X', ToolItemStacks.ironShard, 'Y', Blocks.stone);
        obsidianCarver = GameRegistry.addShapedRecipe(ToolItemStacks.obsidianCarver, " X ", "YYY", 'X', ToolItemStacks.ironShard, 'Y', Blocks.obsidian);
        diamondCarver = GameRegistry.addShapedRecipe(ToolItemStacks.diamondCarver, " X ", "YYY", "ZZZ", 'X', ToolItemStacks.obsidianShard, 'Y', Items.diamond, 'Z', Blocks.obsidian);
    }

    private static void addShardsRecipes() {
        addCarverRecipe(ToolItemStacks.woodenShard, "logWood");
        addCarverRecipe(ToolItemStacks.stoneShard, "stone");
        addCarverRecipe(ToolItemStacks.ironShard, "ingotIron");
        addCarverRecipe(ToolItemStacks.goldenShard, "ingotGold");
        addCarverRecipe(ToolItemStacks.obsidianShard, new ItemStack(Blocks.obsidian));

        if (isOreRegistered("ingotCopper"))
            addCarverRecipe(ToolItemStacks.copperShard, "ingotCopper");
        if (isOreRegistered("ingotTin"))
            addCarverRecipe(ToolItemStacks.tinShard, "ingotTin");
        if (isOreRegistered("ingotLead"))
            addCarverRecipe(ToolItemStacks.leadShard, "ingotLead");
        if (isOreRegistered("ingotBronze"))
            addCarverRecipe(ToolItemStacks.bronzeShard, "ingotBronze");
    }

    public static IRecipe toolRecipe(ItemStack output, ItemStack X, String Y, ItemStack Z) {
        if (isOreRegistered(Y)) {
            for (int i = 0; i < OreDictionary.getOres(Y).size(); i++) {
                return GameRegistry.addShapedRecipe(output, " X ", " Y ", "YZY", 'X', X, 'Y', OreDictionary.getOres(Y).get(i), 'Z', Z);
            }
        }
        return null;
    }

    public static IRecipe toolRecipe(ItemStack output, ItemStack X, String Y, String Z) {
        if (isOreRegistered(Y) && isOreRegistered(Z)) {
            for (int i = 0; i < OreDictionary.getOres(Y).size(); i++) {
                for (int j = 0; j < OreDictionary.getOres(Z).size(); j++) {
                    return GameRegistry.addShapedRecipe(output, " X ", " Y ", "YZY", 'X', X, 'Y', OreDictionary.getOres(Y).get(j), 'Z', OreDictionary.getOres(Z).get(i));
                }
            }
        }
        return null;
    }

    public static IRecipe drillTopRecipe(ItemStack output, String X, ItemStack Y) {
        if (isOreRegistered(X)) {
            for (int i = 0; i < OreDictionary.getOres(X).size(); i++) {
                return GameRegistry.addShapedRecipe(output, " Y ", "XXX", 'X', OreDictionary.getOres(X).get(i), 'Y', Y);
            }
        }
        return null;
    }

    public static IRecipe chainsawTopRecipe(ItemStack output, String X, ItemStack Y) {
        if (isOreRegistered(X)) {
            for (int i = 0; i < OreDictionary.getOres(X).size(); i++) {
                return GameRegistry.addShapedRecipe(output, " Y ", "XYX", "XYX", 'X', OreDictionary.getOres(X).get(i), 'Y', Y);
            }
        }
        return null;
    }

    public static void addCarverRecipe(ItemStack output, ItemStack input) {
        GameRegistry.addShapelessRecipe(output, ToolItemStacks.woodenCarver, input);
        GameRegistry.addShapelessRecipe(output, ToolItemStacks.stoneCarver, input);
        GameRegistry.addShapelessRecipe(output, ToolItemStacks.ironCarver, input);
        GameRegistry.addShapelessRecipe(output, ToolItemStacks.obsidianCarver, input);
        GameRegistry.addShapelessRecipe(output, ToolItemStacks.diamondCarver, input);
    }

    public static void addCarverRecipe(ItemStack output, String input) {
        if (isOreRegistered(input)) {
            for (int i = 0; i < OreDictionary.getOres(input).size(); i++) {
                GameRegistry.addShapelessRecipe(output, new ItemStack(ToolItemRegistry.woodCarver), OreDictionary.getOres(input).get(i));
                GameRegistry.addShapelessRecipe(output, ToolItemStacks.stoneCarver, OreDictionary.getOres(input).get(i));
                GameRegistry.addShapelessRecipe(output, ToolItemStacks.ironCarver, OreDictionary.getOres(input).get(i));
                GameRegistry.addShapelessRecipe(output, ToolItemStacks.obsidianCarver, OreDictionary.getOres(input).get(i));
                GameRegistry.addShapelessRecipe(output, ToolItemStacks.diamondCarver, OreDictionary.getOres(input).get(i));
            }
        }
    }

    private static boolean isOreRegistered(String ore) {
        return !OreDictionary.getOres(ore).isEmpty();
    }
}

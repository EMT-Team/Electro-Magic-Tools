package amerifrance.emt;

import tterrag.core.common.config.AbstractConfigHandler;

public class ConfigHandler extends AbstractConfigHandler {

    public static final ConfigHandler INSTANCE = new ConfigHandler();

    private ConfigHandler() {
        super(ModInformation.ID);
    }

    // Config categories
    public static String modules = "Modules";
    public static String research = "Research";
    public static String values = "Numeric Values";
    public static String misc = "Misc Options";
    public static String generators = "Essentia Generator Outputs";

    // Config Options
    public static boolean thaumiumDrillResearch = true;
    public static boolean thaumiumChainsawResearch = true;
    public static boolean thaumiumOmnitoolResearch = true;
    public static boolean rockbreakerDrillResearch = true;
    public static boolean streamChainsawResearch = true;
    public static boolean electricGogglesResearch = true;
    public static boolean nanoGogglesResearch = true;
    public static boolean quantumGogglesResearch = true;
    public static boolean ironOmnitoolResearch = true;
    public static boolean diamondOmnitoolResearch = true;
    public static boolean explosionFocusResearch = true;
    public static boolean christmasFocusResearch = true;
    public static boolean shieldFocusResearch = true;
    public static boolean shieldBlockResearch = true;
    public static boolean potentiaGeneratorResearch = true;
    public static boolean ignisGeneratorResearch = true;
    public static boolean auramGeneratorResearch = true;
    public static boolean arborGeneratorResearch = true;
    public static boolean wandChargingStationResearch = true;
    public static boolean tinyUraniumResearch = true;
    public static boolean thorHammerResearch = true;
    public static boolean superchargedThorHammerResearch = true;
    public static boolean compressedSolarsResearch = true;
    public static boolean solarHelmetResearch = true;
    public static boolean electricBootsResearch = true;
    public static boolean nanoBootsResearch = true;
    public static boolean quantumBootsResearch = true;
    public static boolean electricScribingToolsResearch = true;
    public static boolean etherealProcessorResearch = true;
    public static boolean waterSolarsResearch = true;
    public static boolean darkSolarsResearch = true;
    public static boolean orderSolarsResearch = true;
    public static boolean fireSolarsResearch = true;
    public static boolean airSolarsResearch = true;
    public static boolean earthSolarsResearch = true;
    public static boolean saxumGenResearch = true;
    public static boolean uuMInfusionResearch = true;
    public static boolean portableNodeResarch = true;
    public static boolean electricHoeGrowthResearch = true;
    public static boolean chargeFocusResearch = true;
    public static boolean wandChargeFocusResearch = true;
    public static boolean inventoryChargingRingResearch = true;
    public static boolean armorChargingRingResearch = true;
    public static boolean thaumiumWingResearch = true;
    public static boolean nanoWingResearch = true;
    public static boolean quantumWingResearch = true;
    public static boolean aerGeneratorResearch = true;

    public static int armorBaubleProduction = 32;
    public static int inventoryBaubleProduction = 32;
    public static double nanoBootsSpeed = 0.25;
    public static double nanoBootsJump = 0.6;
    public static double quantumBootsSpeed = 0.5;
    public static double quantumBootsJump = 0.9;
    public static int chargeFocusProduction = 64;
    public static int wandChargeFocusCost = 40000;
    public static int etherealProcessorBonus = 10;
    public static int etherealProcessorBaseSpeed = 400;
    public static int wandChargerConsumption = 50000;
    public static double compressedSolarOutput = 10;
    public static double doubleCompressedSolarOutput = 100;
    public static double tripleCompressedSolarOutput = 1000;

    // DEFAULTS - Aer - 15,000 Aqua - 5,000 Ignis - 20,000 Ordo - 16,000 Perditio - 10,000 Terra - 2,000
    public static double fireOutput = 20000;
    public static double waterOutput = 5000;
    public static double airOutput = 15000;
    public static double earthOutput = 2000;
    public static double orderOutput = 16000;
    public static double entropyOutput = 10000;
    public static double outputCap = -1;
    public static int essentiaGeneratorsDelay = 120;

    public static boolean oneRingSpawn = true;
    public static boolean thorHammerSpawn = true;
    public static int chanceOneRing = 25;
    public static int chanceTaintedMjolnir = 15;
    public static boolean allowEnchanting = false;
    public static boolean enableNightVision = true;
    public static boolean toolsInBore = false;

    public static boolean enableIC2Module = true;
    public static boolean enableTEModule = false;
    public static boolean enableToolModule = false;

    @Override
    protected void init() {
        addSection(values, "values", "The only way to change some numbers that my machines uses.");
        addSection(research, "research", "The only way to disable some researches. Be careful, if you disable some researches, all researches linked to it will be removed too. Really, don't touch that unless you know exactly what you are doing. If a research is part of a disabled module, don't worry about it.");
        addSection(generators, "generators", "Change outputs linked to different Essentias here");
        addSection(misc, "misc", "Options that don't fit into the other categories.");
        addSection(modules, "modules", "Select the modules to play with.");
    }

    @Override
    protected void reloadNonIngameConfigs() {
        activateSection(research);
        thaumiumDrillResearch = getValue("thaumiumDrillResearch", thaumiumDrillResearch, RestartReqs.REQUIRES_MC_RESTART);
        thaumiumChainsawResearch = getValue("thaumiumChainsawResearch", thaumiumChainsawResearch, RestartReqs.REQUIRES_MC_RESTART);
        thaumiumOmnitoolResearch = getValue("thaumiumOmnitoolResearch", thaumiumOmnitoolResearch, RestartReqs.REQUIRES_MC_RESTART);
        rockbreakerDrillResearch = getValue("rockbreakerDrillResearch", rockbreakerDrillResearch, RestartReqs.REQUIRES_MC_RESTART);
        streamChainsawResearch = getValue("streamChainsawResearch", streamChainsawResearch, RestartReqs.REQUIRES_MC_RESTART);
        electricGogglesResearch = getValue("electricGogglesResearch", electricGogglesResearch, RestartReqs.REQUIRES_MC_RESTART);
        nanoGogglesResearch = getValue("nanoGogglesResearch", nanoGogglesResearch, RestartReqs.REQUIRES_MC_RESTART);
        quantumGogglesResearch = getValue("quantumGogglesResearch", quantumGogglesResearch, RestartReqs.REQUIRES_MC_RESTART);
        ironOmnitoolResearch = getValue("ironOmnitoolResearch", ironOmnitoolResearch, RestartReqs.REQUIRES_MC_RESTART);
        diamondOmnitoolResearch = getValue("diamondOmnitoolResearch", diamondOmnitoolResearch, RestartReqs.REQUIRES_MC_RESTART);
        explosionFocusResearch = getValue("explosionFocusResearch", explosionFocusResearch, RestartReqs.REQUIRES_MC_RESTART);
        christmasFocusResearch = getValue("christmasFocusResearch", christmasFocusResearch, RestartReqs.REQUIRES_MC_RESTART);
        shieldFocusResearch = getValue("shieldFocusResearch", shieldFocusResearch, RestartReqs.REQUIRES_MC_RESTART);
        shieldBlockResearch = getValue("shieldBlockResearch", shieldBlockResearch, RestartReqs.REQUIRES_MC_RESTART);
        potentiaGeneratorResearch = getValue("potentiaGeneratorResearch", potentiaGeneratorResearch, RestartReqs.REQUIRES_MC_RESTART);
        ignisGeneratorResearch = getValue("ignisGeneratorResearch", ignisGeneratorResearch, RestartReqs.REQUIRES_MC_RESTART);
        auramGeneratorResearch = getValue("auramGeneratorResearch", auramGeneratorResearch, RestartReqs.REQUIRES_MC_RESTART);
        arborGeneratorResearch = getValue("arborGeneratorResearch", arborGeneratorResearch, RestartReqs.REQUIRES_MC_RESTART);
        wandChargingStationResearch = getValue("wandChargingStationResearch", wandChargingStationResearch, RestartReqs.REQUIRES_MC_RESTART);
        tinyUraniumResearch = getValue("tinyUraniumResearch", tinyUraniumResearch, RestartReqs.REQUIRES_MC_RESTART);
        thorHammerResearch = getValue("thorHammerResearch", thorHammerResearch, RestartReqs.REQUIRES_MC_RESTART);
        superchargedThorHammerResearch = getValue("superchargedThorHammerResearch", superchargedThorHammerResearch, RestartReqs.REQUIRES_MC_RESTART);
        compressedSolarsResearch = getValue("compressedSolarsResearch", compressedSolarsResearch, RestartReqs.REQUIRES_MC_RESTART);
        solarHelmetResearch = getValue("solarHelmetResearch", solarHelmetResearch, RestartReqs.REQUIRES_MC_RESTART);
        electricBootsResearch = getValue("electricBootsResearch", electricBootsResearch, RestartReqs.REQUIRES_MC_RESTART);
        nanoBootsResearch = getValue("nanoBootsResearch", nanoBootsResearch, RestartReqs.REQUIRES_MC_RESTART);
        quantumBootsResearch = getValue("quantumBootsResearch", quantumBootsResearch, RestartReqs.REQUIRES_MC_RESTART);
        electricScribingToolsResearch = getValue("electricScribingToolsResearch", electricScribingToolsResearch, RestartReqs.REQUIRES_MC_RESTART);
        etherealProcessorResearch = getValue("etherealProcessorResearch", etherealProcessorResearch, RestartReqs.REQUIRES_MC_RESTART);
        waterSolarsResearch = getValue("waterSolarsResearch", waterSolarsResearch, RestartReqs.REQUIRES_MC_RESTART);
        darkSolarsResearch = getValue("darkSolarsResearch", darkSolarsResearch, RestartReqs.REQUIRES_MC_RESTART);
        orderSolarsResearch = getValue("orderSolarsResearch", orderSolarsResearch, RestartReqs.REQUIRES_MC_RESTART);
        fireSolarsResearch = getValue("fireSolarsResearch", fireSolarsResearch, RestartReqs.REQUIRES_MC_RESTART);
        airSolarsResearch = getValue("airSolarsResearch", airSolarsResearch, RestartReqs.REQUIRES_MC_RESTART);
        earthSolarsResearch = getValue("earthSolarsResearch", earthSolarsResearch, RestartReqs.REQUIRES_MC_RESTART);
        saxumGenResearch = getValue("saxumGenResearch", saxumGenResearch, RestartReqs.REQUIRES_MC_RESTART);
        uuMInfusionResearch = getValue("uuMInfusionResearch", uuMInfusionResearch, RestartReqs.REQUIRES_MC_RESTART);
        portableNodeResarch = getValue("portableNodeResarch", portableNodeResarch, RestartReqs.REQUIRES_MC_RESTART);
        electricHoeGrowthResearch = getValue("electricHoeGrowthResearch", electricHoeGrowthResearch, RestartReqs.REQUIRES_MC_RESTART);
        chargeFocusResearch = getValue("chargeFocusResearch", chargeFocusResearch, RestartReqs.REQUIRES_MC_RESTART);
        wandChargeFocusResearch = getValue("wandChargeFocusResearch", wandChargeFocusResearch, RestartReqs.REQUIRES_MC_RESTART);
        inventoryChargingRingResearch = getValue("inventoryChargingRingResearch", inventoryChargingRingResearch, RestartReqs.REQUIRES_MC_RESTART);
        armorChargingRingResearch = getValue("armorChargingRingResearch", armorChargingRingResearch, RestartReqs.REQUIRES_MC_RESTART);
        thaumiumWingResearch = getValue("thaumiumWingResearch", thaumiumWingResearch, RestartReqs.REQUIRES_MC_RESTART);
        nanoWingResearch = getValue("nanoWingResearch", nanoWingResearch, RestartReqs.REQUIRES_MC_RESTART);
        quantumWingResearch = getValue("quantumWingResearch", quantumWingResearch, RestartReqs.REQUIRES_MC_RESTART);
        aerGeneratorResearch = getValue("aerGeneratorResearch", aerGeneratorResearch, RestartReqs.REQUIRES_MC_RESTART);

        activateSection(values);
        // Do stuff
        airOutput = getValue("aerOutput", airOutput, RestartReqs.REQUIRES_MC_RESTART);
        waterOutput = getValue("aquaOutput", waterOutput, RestartReqs.REQUIRES_MC_RESTART);
        fireOutput = getValue("ignisOutput", fireOutput, RestartReqs.REQUIRES_MC_RESTART);
        orderOutput = getValue("ordoOutput", orderOutput, RestartReqs.REQUIRES_MC_RESTART);
        entropyOutput = getValue("perditoOutput", entropyOutput, RestartReqs.REQUIRES_MC_RESTART);
        earthOutput = getValue("terraOutput", earthOutput, RestartReqs.REQUIRES_MC_RESTART);
        outputCap = getValue("outputCap", outputCap, RestartReqs.REQUIRES_MC_RESTART);
        essentiaGeneratorsDelay = getValue("essentiaGeneratorsDelay", essentiaGeneratorsDelay);

        activateSection(misc);
        // Do stuff

        activateSection(generators);
        // Do stuff

        activateSection(modules);
        enableIC2Module = getValue("enableIC2Module", "Enables the module that includes all EU compatible items and blocks.", enableIC2Module, RestartReqs.REQUIRES_MC_RESTART);
        enableTEModule = getValue("enableTEModule", "Enables the module that includes all RF compatible items and blocks.", enableTEModule, RestartReqs.REQUIRES_MC_RESTART);
        enableToolModule = getValue("enableToolModule", "Enables the module that adds lots of tools.", enableToolModule, RestartReqs.REQUIRES_MC_RESTART);
    }

    @Override
    protected void reloadIngameConfigs() {
        activateSection(values);
        armorBaubleProduction = getValue("armorBaubleProduction", "EU/t the ring will charge at. Default is 32.", armorBaubleProduction, RestartReqs.REQUIRES_WORLD_RESTART);
        inventoryBaubleProduction = getValue("inventoryBaubleProduction", "EU/t the ring will charge at. Default is 32.", inventoryBaubleProduction, RestartReqs.REQUIRES_WORLD_RESTART);
        nanoBootsSpeed = getValue("nanoBootsSpeed", "Watch out, that goes up REALLY quickly. Default is 0.25", nanoBootsSpeed, RestartReqs.REQUIRES_WORLD_RESTART);
        nanoBootsJump = getValue("nanoBootsJump", "Watch out, that goes up REALLY quickly. Default is 0.6", nanoBootsJump, RestartReqs.REQUIRES_WORLD_RESTART);
        quantumBootsSpeed = getValue("quantumBootsSpeed", "Watch out, that goes up REALLY quickly. Default is 0.5", quantumBootsSpeed, RestartReqs.REQUIRES_WORLD_RESTART);
        quantumBootsJump = getValue("quantumBootsJump", "Watch out, that goes up REALLY quickly. Default is 0.9", quantumBootsJump, RestartReqs.REQUIRES_WORLD_RESTART);
        chargeFocusProduction = getValue("chargeFocusProduction", "EU/t that the Wand Focus: Charging will produce. Default is 64.", chargeFocusProduction, RestartReqs.REQUIRES_WORLD_RESTART);
        wandChargeFocusCost = getValue("wandChargeFocusCost", "EU/vis restored. Default is 40000.", wandChargeFocusCost, RestartReqs.REQUIRES_WORLD_RESTART);
        etherealProcessorBonus = getValue("etherealProcessorBonus", "Default is 10.", etherealProcessorBonus, RestartReqs.NONE);
        etherealProcessorBaseSpeed = getValue("etherealProcessorBaseSpeed", "Base speed for the Ethereal Reprocessor. Default is 400.", etherealProcessorBaseSpeed, RestartReqs.NONE);
        wandChargerConsumption = getValue("wandChargerConsumption", "EU/vis that is used to charge a wand.", wandChargerConsumption, RestartReqs.NONE);
        compressedSolarOutput = getValue("compressedSolarOutput", "Default is 10.", compressedSolarOutput, RestartReqs.NONE);
        doubleCompressedSolarOutput = getValue("doubleCompressedSolarOutput", "Default is 100.", doubleCompressedSolarOutput, RestartReqs.NONE);
        tripleCompressedSolarOutput = getValue("tripleCompressedSolarOutput", "Default is 1000.", tripleCompressedSolarOutput, RestartReqs.NONE);

        activateSection(misc);
        oneRingSpawn = getValue("oneRingSpawn", "There is no other way to get this item.", oneRingSpawn, RestartReqs.NONE);
        thorHammerSpawn = getValue("thorHammerSpawn", "There is no other way to get this item.", thorHammerSpawn, RestartReqs.NONE);
        chanceOneRing = getValue("chanceOnceRing", "If you have a lot of mods adding dungeon loot, you should definitely increase this", chanceOneRing, RestartReqs.NONE);
        chanceTaintedMjolnir = getValue("chanceTaintedMjolnir", "If you have a lot of mods adding dungeon loot, you should definitely increase this", chanceTaintedMjolnir, RestartReqs.NONE);
        allowEnchanting = getValue("allowEnchanting", "Very low enchantability for tools.", allowEnchanting, RestartReqs.NONE);
        enableNightVision = getValue("enableNightVision", "This was added because of mods making you totally blind if using night-vision. For example, enable that when in the Deep Dark.", enableNightVision, RestartReqs.NONE);
        toolsInBore = getValue("toolsInBore", "Allow placing EMT tools into the Arcane Bore.", toolsInBore, RestartReqs.NONE);
    }
}

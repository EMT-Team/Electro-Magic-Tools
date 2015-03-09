package amerifrance.emt.modules.ic2.tile;

import cpw.mods.fml.common.registry.GameRegistry;
import amerifrance.emt.modules.ic2.tile.solars.air.TileAirSolar;
import amerifrance.emt.modules.ic2.tile.solars.air.TileDoubleAirSolar;
import amerifrance.emt.modules.ic2.tile.solars.air.TileTripleAirSolar;
import amerifrance.emt.modules.ic2.tile.solars.compressed.TileCompressedSolar;
import amerifrance.emt.modules.ic2.tile.solars.compressed.TileDoubleCompressedSolar;
import amerifrance.emt.modules.ic2.tile.solars.compressed.TileTripleCompressedSolar;
import amerifrance.emt.modules.ic2.tile.solars.dark.TileDarkSolar;
import amerifrance.emt.modules.ic2.tile.solars.dark.TileDoubleDarkSolar;
import amerifrance.emt.modules.ic2.tile.solars.dark.TileTripleDarkSolar;
import amerifrance.emt.modules.ic2.tile.solars.earth.TileDoubleEarthSolar;
import amerifrance.emt.modules.ic2.tile.solars.earth.TileEarthSolar;
import amerifrance.emt.modules.ic2.tile.solars.earth.TileTripleEarthSolar;
import amerifrance.emt.modules.ic2.tile.solars.fire.TileDoubleFireSolar;
import amerifrance.emt.modules.ic2.tile.solars.fire.TileFireSolar;
import amerifrance.emt.modules.ic2.tile.solars.fire.TileTripleFireSolar;
import amerifrance.emt.modules.ic2.tile.solars.order.TileDoubleOrderSolar;
import amerifrance.emt.modules.ic2.tile.solars.order.TileOrderSolar;
import amerifrance.emt.modules.ic2.tile.solars.order.TileTripleOrderSolar;
import amerifrance.emt.modules.ic2.tile.solars.water.TileDoubleWaterSolar;
import amerifrance.emt.modules.ic2.tile.solars.water.TileTripleWaterSolar;
import amerifrance.emt.modules.ic2.tile.solars.water.TileWaterSolar;

public class IC2TileRegistry {

    public static void registerTiles() {
        // Machines
        GameRegistry.registerTileEntity(TileEtherealMacerator.class, "TileEtherealMacerator");
        GameRegistry.registerTileEntity(TileWandRecharger.class, "TileWandRecharger");
        GameRegistry.registerTileEntity(TileEssentiaGenerator.class, "TileEssentiaGenerator");
        GameRegistry.registerTileEntity(TileProcessor.class, "TileProcessor");

        //Solars
        GameRegistry.registerTileEntity(TileCompressedSolar.class, "TileCompressedSolar");
        GameRegistry.registerTileEntity(TileDoubleCompressedSolar.class, "TileDoubleCompressedSolar");
        GameRegistry.registerTileEntity(TileTripleCompressedSolar.class, "TileTripleCompressedSolar");
        GameRegistry.registerTileEntity(TileWaterSolar.class, "TileWaterSolar");
        GameRegistry.registerTileEntity(TileDoubleWaterSolar.class, "TileDoubleWaterSolar");
        GameRegistry.registerTileEntity(TileTripleWaterSolar.class, "TileTripleWaterSolar");
        GameRegistry.registerTileEntity(TileDarkSolar.class, "TileDarkSolar");
        GameRegistry.registerTileEntity(TileDoubleDarkSolar.class, "TileDoubleDarkSolar");
        GameRegistry.registerTileEntity(TileTripleDarkSolar.class, "TileTripleDarkSolar");
        GameRegistry.registerTileEntity(TileOrderSolar.class, "TileOrderSolar");
        GameRegistry.registerTileEntity(TileDoubleOrderSolar.class, "TileDoubleOrderSolar");
        GameRegistry.registerTileEntity(TileTripleOrderSolar.class, "TileTripleOrderSolar");
        GameRegistry.registerTileEntity(TileFireSolar.class, "TileFireSolar");
        GameRegistry.registerTileEntity(TileDoubleFireSolar.class, "TileDoubleFireSolar");
        GameRegistry.registerTileEntity(TileTripleFireSolar.class, "TileTripleFireSolar");
        GameRegistry.registerTileEntity(TileAirSolar.class, "TileAirSolar");
        GameRegistry.registerTileEntity(TileDoubleAirSolar.class, "TileDoubleAirSolar");
        GameRegistry.registerTileEntity(TileTripleAirSolar.class, "TileTripleAirSolar");
        GameRegistry.registerTileEntity(TileEarthSolar.class, "TileEarthSolar");
        GameRegistry.registerTileEntity(TileDoubleEarthSolar.class, "TileDoubleEarthSolar");
        GameRegistry.registerTileEntity(TileTripleEarthSolar.class, "TileTripleEarthSolar");
    }
}

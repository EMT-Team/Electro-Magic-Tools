package amerifrance.emt.util;

import amerifrance.emt.ConfigHandler;
import thaumcraft.api.aspects.Aspect;

import java.util.Collection;
import java.util.HashMap;

public class EssentiaOutputs {
    public static HashMap<String, Double> outputs = new HashMap<String, Double>();

    public static void addOutput(Aspect aspect) {
        if (!aspect.isPrimal()) {
            Aspect[] parents = aspect.getComponents();
            if (ConfigHandler.outputCap == (-1)) {
                outputs.put(aspect.getTag(), (outputs.get(parents[0].getTag()) + outputs.get(parents[1].getTag())));
            } else {
                double originalOutput = (outputs.get(parents[0].getTag()) + outputs.get(parents[1].getTag()));
                if (originalOutput > ConfigHandler.outputCap) outputs.put(aspect.getTag(), ConfigHandler.outputCap);
            }
        }
    }

    public static void addOutputs() {
        Collection<Aspect> aspectCollection = Aspect.aspects.values();
        for (Aspect aspect : aspectCollection) {
            if (!aspect.isPrimal())
                addOutput(aspect);
        }
    }

    public static void addPrimalOutputs() {
        outputs.put(Aspect.FIRE.getTag(), ConfigHandler.fireOutput);
        outputs.put(Aspect.WATER.getTag(), ConfigHandler.waterOutput);
        outputs.put(Aspect.ORDER.getTag(), ConfigHandler.orderOutput);
        outputs.put(Aspect.ENTROPY.getTag(), ConfigHandler.entropyOutput);
        outputs.put(Aspect.EARTH.getTag(), ConfigHandler.earthOutput);
        outputs.put(Aspect.AIR.getTag(), ConfigHandler.airOutput);
    }
}

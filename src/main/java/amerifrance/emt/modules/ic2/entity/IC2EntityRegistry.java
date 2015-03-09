package amerifrance.emt.modules.ic2.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import amerifrance.emt.ElectroMagicTools;

public class IC2EntityRegistry {

    private static int startEID = 300;
    private static int entityIDs = 0;
    private static final Class<? extends EntityLiving> ARCHER_CLASS = EntityArcher.class;

    private static int getUniqueEntityID() {

        do {
            startEID++;
        } while (EntityList.getStringFromID(startEID) != null);
        return startEID;
    }

    @SuppressWarnings("unchecked")
    public static void registerEntityEgg(Class<? extends Entity> entity, int colPrim, int colSec) {
        int id = getUniqueEntityID();
        EntityList.IDtoClassMapping.put(id, entity);
        EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, colPrim, colSec));
    }

    public static void registerEntities() {

        EntityRegistry.registerModEntity(EntityArcher.class, "entityArcher", entityIDs++, ElectroMagicTools.instance, 80, 3, true);
        registerEntityEgg(ARCHER_CLASS, 0x99111F, 0xE5685);
    }
}

package amerifrance.emt.modules.base.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import amerifrance.emt.ModInformation;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.utils.InventoryUtils;

import java.util.List;

public class ItemBaseMaterials extends ItemBase {

    private static String[] names = {"oreClusterUranium", "crushedOreAmber", "crushedOreCinnabar", "purifiedOreAmber", "purifiedOreCinnabar", "thaumiumPlate", "lightningSummoner", "featherMesh", "glue", "ductTape", "rubberBall", "cardboard", "featherGluedCardboardWing", "denseIronSheet", "taintedFeather", "thaumiumWing", "uumatterDrop"};
    public IIcon[] icon = new IIcon[32];

    public ItemBaseMaterials() {
        super("materials");
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "." + names[stack.getItemDamage() % names.length];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ri) {
        icon[0] = ri.registerIcon(ModInformation.TEXLOC + "materials/clusteruranium");
        icon[1] = ri.registerIcon(ModInformation.TEXLOC + "materials/crushedamber");
        icon[2] = ri.registerIcon(ModInformation.TEXLOC + "materials/purifiedamber");
        icon[3] = ri.registerIcon(ModInformation.TEXLOC + "materials/crushedcinnabar");
        icon[4] = ri.registerIcon(ModInformation.TEXLOC + "materials/purifiedcinnabar");
        icon[5] = ri.registerIcon(ModInformation.TEXLOC + "materials/thaumiumplate");
        icon[6] = ri.registerIcon(ModInformation.TEXLOC + "materials/lightningsummoner");
        icon[7] = ri.registerIcon(ModInformation.TEXLOC + "materials/feathermesh");
        icon[8] = ri.registerIcon(ModInformation.TEXLOC + "materials/glue");
        icon[9] = ri.registerIcon(ModInformation.TEXLOC + "materials/ducttape");
        icon[10] = ri.registerIcon(ModInformation.TEXLOC + "materials/rubberball");
        icon[11] = ri.registerIcon(ModInformation.TEXLOC + "materials/cardboard");
        icon[12] = ri.registerIcon(ModInformation.TEXLOC + "materials/cardboardsheet");
        icon[13] = ri.registerIcon(ModInformation.TEXLOC + "materials/denseironsheet");
        icon[14] = ri.registerIcon(ModInformation.TEXLOC + "materials/taintedfeather");
        icon[15] = ri.registerIcon(ModInformation.TEXLOC + "materials/thaumiumWing");
        icon[16] = ri.registerIcon(ModInformation.TEXLOC + "materials/uumatterdrop");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon[meta];
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (int i = 0; i <= names.length - 1; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (stack != null && stack.getItemDamage() == 6) {
            float f = 1.0F;
            float f1 = player.prevRotationPitch + ((player.rotationPitch - player.prevRotationPitch) * f);
            float f2 = player.prevRotationYaw + ((player.rotationYaw - player.prevRotationYaw) * f);
            double d = player.prevPosX + ((player.posX - player.prevPosX) * f);
            double d1 = (player.prevPosY + ((player.posY - player.prevPosY) * f) + 1.6200000000000001D) - player.yOffset;
            double d2 = player.prevPosZ + ((player.posZ - player.prevPosZ) * f);
            Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
            float f3 = MathHelper.cos((-f2 * 0.01745329F) - 3.141593F);
            float f4 = MathHelper.sin((-f2 * 0.01745329F) - 3.141593F);
            float f5 = -MathHelper.cos(-f1 * 0.01745329F);
            float f6 = MathHelper.sin(-f1 * 0.01745329F);
            float f7 = f4 * f5;
            float f8 = f6;
            float f9 = f3 * f5;
            double d3 = 5000D;
            Vec3 vec3d1 = vec3d.addVector(f7 * d3, f8 * d3, f9 * d3);
            MovingObjectPosition movingobjectposition = player.worldObj.rayTraceBlocks(vec3d, vec3d1, true);

            if (movingobjectposition == null) {
                return stack;
            }
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;
                world.spawnEntityInWorld(new EntityLightningBolt(world, i, j, k));
            } else if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                Entity entityhit = movingobjectposition.entityHit;
                double x = entityhit.posX;
                double y = entityhit.posY;
                double z = entityhit.posZ;
                world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
            }

            player.swingItem();

            if (player.capabilities.isCreativeMode) {
                return stack;
            } else {
                player.inventory.consumeInventoryItem(this);
                return stack;
            }
        }
        return stack;
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        super.onUpdate(stack, world, entity, par4, par5);
        if ((!entity.worldObj.isRemote) && ((stack.getItemDamage() == 14)) && ((entity instanceof EntityLivingBase)) && (!((EntityLivingBase) entity).isEntityUndead()) && (!((EntityLivingBase) entity).isPotionActive(Config.potionTaintPoisonID)) && (world.rand.nextInt(4321) <= stack.stackSize)) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Config.potionTaintPoisonID, 120, 0, false));
            if ((entity instanceof EntityPlayer)) {
                InventoryUtils.consumeInventoryItem((EntityPlayer) entity, stack.getItem(), stack.getItemDamage());
            }
        }
    }
}

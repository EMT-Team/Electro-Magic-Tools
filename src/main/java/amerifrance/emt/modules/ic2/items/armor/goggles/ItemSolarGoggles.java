package amerifrance.emt.modules.ic2.items.armor.goggles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ElectroMagicTools;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class ItemSolarGoggles extends ItemQuantumGoggles {

    private int ticker;
    private int generating;
    private int genDay;
    private int genNight;
    private boolean sunIsUp;
    private boolean skyIsVisible;

    private static final Map potionCost = new HashMap();

    @SuppressWarnings("unchecked")
    public ItemSolarGoggles() {
        super();
        setUnlocalizedName(ModInformation.ID + ".module.ic2.goggles.solar");
        setCreativeTab(ElectroMagicTools.tabEMT);
        maxCharge = 1000000;
        genDay = 256;
        genNight = 128;
        maxCharge = 10000000;
        visDiscount = 7;
        tier = 3;
        transferLimit = 10000;
        energyPerDamage = 10000;

        potionCost.put(Potion.poison.id, 10000);
        potionCost.put(Potion.wither.id, 15000);
        potionCost.put(Potion.confusion.id, 5000);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "armor/goggles_solar");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layerType) {
        return ModInformation.TEXLOC + "textures/models/solarrevealinghelmet.png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(stack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onArmorTick(World worldObj, EntityPlayer player, ItemStack stack) {
        if (ConfigHandler.enableNightVision) {
            if (ElectricItem.manager.canUse(stack, 1 / 1000)) {


                int x = MathHelper.floor_double(player.posX);
                int z = MathHelper.floor_double(player.posZ);
                int y = MathHelper.floor_double(player.posY);

                int lightlevel = player.worldObj.getBlockLightValue(x, y, z);
                if (lightlevel >= 0)
                    player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, -3));
                ElectricItem.manager.use(stack, 1 / 1000, player);
            } else {
                player.addPotionEffect(new PotionEffect(Potion.blindness.id, 300, 0, true));
            }
        }

        int refill = player.getAir();
        if (ElectricItem.manager.canUse(stack, 1000) && refill < 100) {
            player.setAir(refill + 200);
            ElectricItem.manager.use(stack, 1000, null);
        }

        Iterator iterator = (new LinkedList(player.getActivePotionEffects())).iterator();
        do {
            if (!iterator.hasNext()) {
                break;
            } else {
                PotionEffect effect = (PotionEffect) iterator.next();
                int id = effect.getPotionID();
                Integer cost = (Integer) potionCost.get(id);
                if (cost != null) {
                    cost = cost * (effect.getAmplifier() + 1);
                    if (ElectricItem.manager.canUse(stack, cost)) {
                        ElectricItem.manager.use(stack, cost, null);
                        ItemStack milk = (new ItemStack(Items.milk_bucket));
                        player.curePotionEffects(milk);
                    }
                }
            }
        } while (true);

        if (worldObj.isRemote) {
            return;
        }
        checkForSun(player);
        if (this.generating > 0) {
            int energyLeft = this.generating;
            for (int i = 0; i < player.inventory.armorInventory.length; i++) {
                if (energyLeft > 0) {
                    if ((player.inventory.armorInventory[i] != null) && (player.inventory.armorInventory[i].getItem() instanceof IElectricItem)) {
                        double sentPacket = ElectricItem.manager.charge(player.inventory.armorInventory[i], energyLeft, 4, false, false);
                        energyLeft -= sentPacket;
                    }
                } else {
                    return;
                }
            }
            for (int j = 0; j < player.inventory.mainInventory.length; j++) {
                if (energyLeft > 0) {
                    if ((player.inventory.mainInventory[j] != null) && (player.inventory.mainInventory[j].getItem() instanceof IElectricItem)) {
                        double sentPacket = ElectricItem.manager.charge(player.inventory.mainInventory[j], energyLeft, 4, false, false);
                        energyLeft -= sentPacket;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public int checkForSun(EntityPlayer player) {
        if (this.ticker++ % tickRate() == 0) {
            updateVisibility(player);
        }
        if ((this.sunIsUp) && (this.skyIsVisible)) {
            this.generating = (genDay);
            return this.generating;
        }
        if (this.skyIsVisible) {
            this.generating = (genNight);
            return this.generating;
        }
        this.generating = 0;
        return this.generating;
    }

    public void updateVisibility(EntityPlayer player) {
        Boolean dampPlace = (player.worldObj.getWorldChunkManager().getBiomeGenAt((int) player.posX, (int) player.posZ).getIntRainfall() > 0);
        Boolean noSunWorld = player.worldObj.provider.hasNoSky;

        Boolean rainWeather = (dampPlace) && ((player.worldObj.isRaining()) || (player.worldObj.isThundering()));

        sunIsUp = player.worldObj.isDaytime() || !rainWeather;
        skyIsVisible = player.worldObj.canBlockSeeTheSky((int) player.posX, (int) player.posY + 1, (int) player.posZ) || !noSunWorld;
    }

    public static int tickRate() {
        return 128;
    }
}
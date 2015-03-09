package amerifrance.emt.modules.ic2.items.armor.goggles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
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

public class ItemQuantumGoggles extends ItemNanoGoggles {

    @SuppressWarnings("unchecked")
    private static final Map<Integer, Integer> potionCost = new HashMap();

    public ItemQuantumGoggles() {
        super();
        setUnlocalizedName(ModInformation.ID + ".module.ic2.goggles.quantum");
        setCreativeTab(ElectroMagicTools.tabEMT);
        maxCharge = 10000000;
        tier = 3;
        visDiscount = 8;
        transferLimit = 10000;
        energyPerDamage = 1000;

        potionCost.put(Potion.poison.id, 10000);
        potionCost.put(Potion.wither.id, 15000);
        potionCost.put(Potion.confusion.id, 5000);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.TEXLOC + "armor/goggles_quantum");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ModInformation.TEXLOC + "textures/models/thaumicquantumhelmet.png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextHelper.BRIGHT_BLUE + super.getItemStackDisplayName(stack).replace(TextHelper.YELLOW, "");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
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
                Integer cost = potionCost.get(id);
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
    }
}
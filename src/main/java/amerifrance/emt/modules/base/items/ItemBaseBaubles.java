package amerifrance.emt.modules.base.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;

import java.util.List;
import java.util.Random;

public class ItemBaseBaubles extends ItemBase implements IBauble {

    private static final String[] names = {"oneRing", "nothRing"};
    public IIcon[] icon = new IIcon[16];
    public static int wornTick;
    public Random random = new Random();

    public ItemBaseBaubles() {
        super("bauble");
        setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "." + names[stack.getItemDamage() % names.length];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ri) {
        this.icon[0] = ri.registerIcon(ModInformation.TEXLOC + "onering");
        this.icon[0] = ri.registerIcon(ModInformation.TEXLOC + "nothring");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon[meta];
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (int i = 0; i < names.length - 1; i++)
            list.add(new ItemStack(this, 1, i));
    }

    // Begin Baubles
    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        if (stack.getItemDamage() <= 0) {
            return BaubleType.RING;
        } else {
            return null;
        }
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        if (!player.worldObj.isRemote) {
            if (stack != null && stack.getItemDamage() == 0) {
                if (player instanceof EntityPlayer) {
                    ((EntityPlayer) player).capabilities.disableDamage = true;

                    wornTick++;

                    NBTTagCompound tag = new NBTTagCompound();
                    tag.setInteger("MindCorruption", wornTick);
                    ((EntityPlayer) player).writeToNBT(tag);
                    int corruption = tag.getInteger("MindCorruption");
                    if (tag != null && corruption != wornTick) {
                        wornTick = corruption;
                    }

                    if (corruption <= 0)
                        ((EntityPlayer) player).addChatMessage(new ChatComponentText(TextHelper.PURPLE + "You have worn the Ring. Your soul has now been forever " + TextHelper.PURPLE + "tainted. " + TextHelper.RED + TextHelper.ITALIC + "Beware of wearing the ring. The tainting will only " + TextHelper.RED + TextHelper.ITALIC + "increase, and strange things will start happening."));

                    if (corruption > 6000 && corruption < 24000 && random.nextInt(2000) == 0)
                        player.addPotionEffect(new PotionEffect(Potion.blindness.id, 500, 2, false));

                    if (corruption >= 6000 && corruption < 24000 && random.nextInt(2000) == 0)
                        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 500, 2, false));

                    if (corruption >= 24000 && corruption < 72000 && random.nextInt(2000) == 0) {
                        for (int i = 0; i <= 5; i++)
                            ((EntityPlayer) player).capabilities.disableDamage = false;

                        player.attackEntityFrom(DamageSource.magic, 5);
                    }

                    if (corruption >= 72000 && corruption < 120000 && random.nextInt(4000) == 0) {
                        for (int i = 0; i <= 100; i++)
                            ((EntityPlayer) player).capabilities.disableDamage = false;

                        player.motionY = 2;
                    }

                    if (corruption >= 120000 && random.nextInt(10000) == 0) {
                        for (int i = 0; i <= 510; i++)
                            ((EntityPlayer) player).capabilities.disableDamage = false;

                        player.addPotionEffect(new PotionEffect(Potion.wither.id, 5000, 4, false));
                    }
                }
            }
        }
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {
        if (stack != null && stack.getItemDamage() == 0) {
            player.setInvisible(true);
        }
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        if (stack != null && stack.getItemDamage() == 0) {
            player.setInvisible(false);
            if (player instanceof EntityPlayer && !((EntityPlayer) player).capabilities.isCreativeMode)
                ((EntityPlayer) player).capabilities.disableDamage = false;
        }
    }

    @Override
    public boolean canEquip(ItemStack stack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack stack, EntityLivingBase player) {
        return true;
    }
}
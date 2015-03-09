package amerifrance.emt.modules.ic2.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ModInformation;

import java.util.List;
import java.util.Random;

public class ItemIC2Baubles extends ItemIC2 implements IBauble {

    public IIcon[] icon = new IIcon[16];
    public Random random = new Random();
    private static final String[] names = {"euMaker.armor", "euMaker.inventory"};

    public ItemIC2Baubles() {
        super("bauble");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "." + names[stack.getItemDamage() % names.length];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ri) {
        this.icon[0] = ri.registerIcon(ModInformation.TEXLOC + "armoreumaker");
        this.icon[1] = ri.registerIcon(ModInformation.TEXLOC + "inventoryeumaker");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon[meta];
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        list.add(new ItemStack(this, 1, 0));
        list.add(new ItemStack(this, 1, 1));
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        if (stack.getItemDamage() <= 1) {
            return BaubleType.RING;
        } else {
            return null;
        }
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        // Armor charger
        if (!player.worldObj.isRemote) {
            if (stack != null && stack.getItemDamage() == 0 && player instanceof EntityPlayer) {
                int energyLeft = ConfigHandler.armorBaubleProduction;
                for (int i = 0; i < ((EntityPlayer) player).inventory.armorInventory.length; i++) {
                    if (energyLeft > 0) {
                        if ((((EntityPlayer) player).inventory.armorInventory[i] != null) && (((EntityPlayer) player).inventory.armorInventory[i].getItem() instanceof IElectricItem)) {
                            double sentPacket = ElectricItem.manager.charge(((EntityPlayer) player).inventory.armorInventory[i], energyLeft, 4, false, false);
                            energyLeft -= sentPacket;
                        }
                    }
                }
            }

            // Inventory charger
            if (stack != null && stack.getItemDamage() == 1 && player instanceof EntityPlayer) {
                int energyLeft = ConfigHandler.inventoryBaubleProduction;
                for (int i = 0; i < ((EntityPlayer) player).inventory.mainInventory.length; i++) {
                    if (energyLeft > 0) {
                        if ((((EntityPlayer) player).inventory.mainInventory[i] != null) && (((EntityPlayer) player).inventory.mainInventory[i].getItem() instanceof IElectricItem)) {
                            double sentPacket = ElectricItem.manager.charge(((EntityPlayer) player).inventory.mainInventory[i], energyLeft, 4, false, false);
                            energyLeft -= sentPacket;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
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
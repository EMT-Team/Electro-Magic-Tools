package amerifrance.emt.modules.ic2.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class EntityArcher extends EntitySnowman implements IExtendedEntityProperties {

    public EntityArcher(World world) {
        super(world);
        isImmuneToFire = true;
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));
        tasks.addTask(4, new EntityAIWander(this, 0.30D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
    }

    @Override
    public void onDeath(DamageSource par1DamageSource) {
        super.onDeath(par1DamageSource);
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1, true);
        this.setDead();
    }

    @Override
    protected Item getDropItem() {
        return Items.arrow;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        int j;
        int k;

        j = this.rand.nextInt(3 + par2);

        for (k = 0; k < j; ++k) {
            this.dropItem(Items.arrow, 1);
        }
        j = this.rand.nextInt(3 + par2);

        for (k = 0; k < j; ++k) {
            this.dropItem(Items.bone, 1);
        }
    }

    @Override
    protected void dropRareDrop(int par1) {
        this.entityDropItem(new ItemStack(Blocks.web, 1, 1), 0.0F);
    }

    protected void addRandomArmor() {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
        EntityArrow entityarrow = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float) (14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double) (par2 * 2.0F) + this.rand.nextGaussian() * 0.25D + (double) ((float) this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

        if (i > 0) {
            entityarrow.setDamage(entityarrow.getDamage() + (double) i * 0.5D + 0.5D);
        }

        if (j > 0) {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0) {
            entityarrow.setFire(100);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
    }

    @Override
    public void init(Entity entity, World world) {
    }
}

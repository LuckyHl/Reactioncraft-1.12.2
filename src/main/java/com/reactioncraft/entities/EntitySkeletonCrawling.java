package com.reactioncraft.entities;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import com.reactioncraft.core.Logger;

public class EntitySkeletonCrawling extends EntityMob
{
    static final DataParameter<Byte> skeletonVariation= EntityDataManager.createKey(EntitySkeletonCrawling.class,DataSerializers.BYTE);
    static final String TYPE="Variation";
    static final String TYPE1="Variation";
    public EntitySkeletonCrawling(World world)
    {
        super(world);
        this.tasks.addTask(1, new EntityAIWanderAvoidWater(this, 0.8D));
        this.tasks.addTask(2, new EntityAIBreakDoor(this));
        this.tasks.addTask(3,new EntityAIAttackMelee(this,1,false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2,new EntityAINearestAttackableTarget<EntityPlayer>(this,EntityPlayer.class,true));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(skeletonVariation,(byte) 0);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        int type=rand.nextInt(3);
        dataManager.set(skeletonVariation,(byte) type);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setByte(TYPE,getSkeletonVariation());
        //compound.setString(TYPE1, "TEST");

        //Logger.info("TEST1: i have the tag", TYPE1);
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        dataManager.set(skeletonVariation,compound.getByte(TYPE));
        //Logger.info("TEST2: i have the tag", TYPE1);
    }

    public byte getSkeletonVariation()
    {
        return dataManager.get(skeletonVariation);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    public int getTotalArmorValue()
    {
        int var1 = super.getTotalArmorValue() + 2;

        if (var1 > 20)
        {
            var1 = 20;
        }

        return var1;
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        super.dropFewItems(wasRecentlyHit, lootingModifier);
    }

    //    public void dropRareDrop(int par1)
//    {
//        switch (this.rand.nextInt(3))
//        {
//            case 0:
//                this.entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
//                break;
//
//            case 1:
//                this.entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
//                break;
//
//            case 2:
//                this.entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
//        }
//    }
}

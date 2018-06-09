package com.reactioncraft.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityZombieCrawling extends EntityMob
{
	public EntityZombieCrawling(World world)
	{
        super(world);

//        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWanderAvoidWater(this, 8.0F));
        this.tasks.addTask(2, new EntityAIBreakDoor(this));
        this.tasks.addTask(3, new EntityAIAttackMelee(this,1,  false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false, false));
    }

	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
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
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		if (this.world.isDaytime() && !this.world.isRemote && !this.isChild())
		{
			float var1 = this.getBrightness();

			if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.world.canSeeSky(getPosition()))
			{
				boolean var2 = true;
				ItemStack itemStack = this.getHeldItemMainhand();

				if (!itemStack .isEmpty())
				{
					if (itemStack.isItemStackDamageable())
					{
						itemStack.setItemDamage(itemStack.getItemDamage() + this.rand.nextInt(2));

						if (itemStack.getItemDamage()>= itemStack.getMaxDamage())
						{
							this.renderBrokenItemStack(itemStack);
							itemStack.shrink(1);
						}
					}

					var2 = false;
				}

				if (var2)
				{
					this.setFire(8);
				}
			}
		}

		super.onLivingUpdate();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	public SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		world.playSound(null,getPosition(),SoundEvents.ENTITY_ZOMBIE_STEP, SoundCategory.HOSTILE,0.15f,1);
	}

	@Nullable
	@Override
	protected Item getDropItem() {
		return Items.ROTTEN_FLESH;
	}


	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}

//	public void dropRareDrop(int par1)
//	{
//		switch (this.rand.nextInt(3))
//		{
//		case 0:
//			this.entityDropItem(new ItemStack(Items.SKULL, 1, 2), 0.0F);
//			break;
//		case 1:
//			this.entityDropItem(new ItemStack(Items.SKULL, 1, 2), 0.0F);
//			break;
//		case 2:
//			this.entityDropItem(new ItemStack(Items.SKULL, 1, 2), 0.0F);
//		}
//	}
}

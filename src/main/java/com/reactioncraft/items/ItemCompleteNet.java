package com.reactioncraft.items;

import com.google.common.collect.ImmutableSet;
import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class ItemCompleteNet extends ItemSword implements ItemModelProvider
{
	public int returnedAmt;
	protected final String name;

	public ItemCompleteNet(String name, ToolMaterial mat)
	{
		super(mat);
		//damageVsEntity = 0.0F;
		//attackSpeed = 0.0F;
		this.name = name;
		this.setRegistryName(new ResourceLocation(Reactioncraft.MODID, name));
		this.setUnlocalizedName(Reactioncraft.MODID + "." + name);
		this.setMaxStackSize(1);
		this.setCreativeTab(Reactioncraft.ReactioncraftItems);
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return ImmutableSet.of("pickaxe", "axe", "shovel", "sword");
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (!player.world.isRemote && entity instanceof EntityLiving && !Reactioncraft.exclusionList.isExcluded(entity))
		{
				String entityId = entity.getName().toString();

				NBTTagCompound entityTag = new NBTTagCompound();
				entity.writeToNBTOptional(entityTag);
				entityTag.removeTag("Pos");
				entityTag.removeTag("Motion");
				entityTag.removeTag("Rotation");
				entityTag.removeTag("Dimension");
				entityTag.removeTag("PortalCooldown");
				entityTag.removeTag("InLove");
				entityTag.removeTag("HurtTime");
				entityTag.removeTag("DeathTime");
				entityTag.removeTag("AttackTime");
				entityTag.setString("name", entityId);
				
				ItemStack is = new ItemStack(ItemIndex.caught);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setTag("EntityData", entityTag);
				nbt.setString("name", entityId);

				
				is.setTagCompound(nbt);
				 player.dropItem(is, false);
				if(!this.name.equals("creative_net")) stack.attemptDamageItem(1,itemRand, (EntityPlayerMP) player);
				entity.setDead();

				if (stack.getItemDamage() >= stack.getMaxDamage() - 1)
				{
					stack.shrink(1);
				}

				return true;

		}
		else
		{
			return false;
		}
	}

	/**
     * Returns True is the item is renderer in full 3D when held.
     */
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
	
	public int getMaxDamage(ItemStack stack)
	{
		NBTTagCompound compound = stack.getTagCompound();
		try
		{
			int hiltLevel = compound.getInteger("hilt");
			int netLevel = compound.getInteger("net");
			returnedAmt = (10 * (hiltLevel + netLevel));
			this.setMaxDamage(returnedAmt);
			Logger.info("returnedAmt=", returnedAmt);
			return returnedAmt;
		}
		catch (NullPointerException var5)
		{
			int exception = 1;
			this.setMaxDamage(exception);
			return exception;
		}
	}
	
	/**
     * Set the damage for this itemstack. Note, this method is responsible for zero checking.
     * @param stack the stack
     * @param damage the new damage value
     */
    public void setDamage(ItemStack stack, int damage)
    {
    	NBTTagCompound compound = stack.getTagCompound();
		try
		{
			int hiltLevel = compound.getInteger("hilt");
			int netLevel = compound.getInteger("net");
			damage = (10 * (hiltLevel + netLevel));
			this.setMaxDamage(damage);
			return;
		}
		catch (NullPointerException var5)
		{
			this.setMaxDamage(1);
			return;
		}
    }

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		if (stack.getTagCompound() != null)
		{
			tooltip.add("Hilt Level: " + stack.getTagCompound().getInteger("hilt"));
			tooltip.add("Net Level: "  + stack.getTagCompound().getInteger("net"));
			tooltip.add("Uses: " + returnedAmt);
		}
		else
		{
			if(stack.getTagCompound() != null)
			{
				tooltip.add("Hilt Level: " + stack.getTagCompound().getInteger("hilt"));
				tooltip.add("Net Level: "  + stack.getTagCompound().getInteger("net"));
				tooltip.add("Uses: " + returnedAmt);
			}
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
    @Override
    public void registerItemModel()
    {
        Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
    }
}
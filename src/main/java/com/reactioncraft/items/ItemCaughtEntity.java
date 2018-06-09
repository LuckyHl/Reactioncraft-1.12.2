package com.reactioncraft.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCaughtEntity extends ItemBase
{
	public ItemCaughtEntity(String name)
    {
        super(name);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        String name = this.getEntityName(stack);

        if (name != null)
        {
            return super.getItemStackDisplayName(stack) + ": " + name;
        }

        return super.getItemStackDisplayName(stack);
    }

    private String getEntityName(ItemStack stack)
    {
        NBTTagCompound tag = this.getEntityTag(stack); 
       
        if (tag != null)
        {
        	String name = tag.getString("Name");

            if (tag.hasKey("id", Constants.NBT.TAG_STRING))
            {
                 
               String id = tag.getString("name");
               String translated = I18n.format(id);

                if (!id.equals(translated))
                {
                    id = translated;
                }

                name = name.length() > 0 ? name : id;
            }

            return name;
        }

        return null;
    }

	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack=player.getHeldItem(hand);
        if (this.spawnCreature(worldIn, stack, pos.getX() + hitX, pos.getY() + hitY, pos.getZ() + hitZ) != null)
        {
            stack.shrink(1);
            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.PASS;
    }



    private @Nullable NBTTagCompound getEntityTag(ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound();
        return tag != null && tag.hasKey("EntityData", Constants.NBT.TAG_COMPOUND) ? tag.getCompoundTag("EntityData") : null;
    }

    @Nullable
    private Entity spawnCreature(World worldIn, ItemStack stack, double x, double y, double z)
    {
        if (worldIn.isRemote)
        {
            return null;
        }

        NBTTagCompound tag = this.getEntityTag(stack);

        if (tag != null)
        {
            Entity entity = EntityList.createEntityFromNBT(tag, worldIn);

            if (entity instanceof EntityLiving)
            {
                EntityLiving entityliving = (EntityLiving)entity;
                entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
                entityliving.rotationYawHead = entityliving.rotationYaw;
                entityliving.renderYawOffset = entityliving.rotationYaw;
                entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), null);
                worldIn.spawnEntity(entity);
                entityliving.playLivingSound();
            }

            return entity;
        }
        else
        {
            return null;
        }
    }

    public static NBTTagList newDoubleNBTList(double ... par1ArrayOfDouble)
    {
        NBTTagList var2 = new NBTTagList();
        double[] var3 = par1ArrayOfDouble;
        int var4 = par1ArrayOfDouble.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            double var6 = var3[var5];
            var2.appendTag(new NBTTagDouble(var6));
        }

        return var2;
    }

    public static NBTTagList newFloatNBTList(float ... par1ArrayOfFloat)
    {
        NBTTagList var2 = new NBTTagList();
        float[] var3 = par1ArrayOfFloat;
        int var4 = par1ArrayOfFloat.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            float var6 = var3[var5];
            var2.appendTag(new NBTTagFloat(var6));
        }

        return var2;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        String name = this.getEntityName(stack);

        if (name != null)
        {
            list.add("Caught: " + name);
        }
        else
        {
            list.add("Please craft to see results");
        }
    }
}

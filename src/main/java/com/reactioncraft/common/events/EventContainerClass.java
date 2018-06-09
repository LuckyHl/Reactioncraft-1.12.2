package com.reactioncraft.common.events;

import javax.annotation.Nullable;

import com.reactioncraft.blocks.BlockBookshelf;
import com.reactioncraft.common.EnumBookshelf;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventContainerClass
{
	public static double rand;
	
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onPlayerInteractEvent(PlayerInteractEvent event)
	{
		ItemStack book = new ItemStack(Items.BOOK);
		EntityPlayer playerIn = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		World worldIn = event.getWorld();
		IBlockState state = worldIn.getBlockState(pos);
		
		
		if(state.getBlock().getDefaultState() == Blocks.BOOKSHELF.getDefaultState())
		{
			if(event.getEntityPlayer().getHeldItemMainhand().isEmpty())
			{
				//worldIn.setBlockState(pos, Blocks.BONE_BLOCK.getDefaultState());
				//worldIn.setBlockState(pos, BlockIndex.bookcases.getDefaultState().withProperty(TYPE));
				worldIn.setBlockState(pos, BlockIndex.bookcases.getDefaultState().withProperty(BlockBookshelf.TYPE, EnumBookshelf.TWO_THIRDS));
				playerIn.inventory.addItemStackToInventory(book);
			}
		}
	}
	
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event)
	{
		rand = Math.random();

		if (event.getSource().getDamageType().equals("player"))
		{
			if (event.getEntityLiving() instanceof EntityPig && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityChicken && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityBat && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityCow && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityHorse && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityMooshroom && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityOcelot && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntitySheep && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityRabbit && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityPolarBear && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityVillager && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityWolf && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityPlayer && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityCreeper && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityPigZombie && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityWitch && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}

			if (event.getEntityLiving() instanceof EntityZombie && rand < 1.0D)
			{
				event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			}
		}
	}
}
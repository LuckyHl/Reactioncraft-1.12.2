package com.reactioncraft.items;

import javax.annotation.Nullable;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMiningHelmUL extends ItemExtraCrowns
{
	protected int enchantability;
	protected String name;

	/** The EnumArmorMaterial used for this ItemArmor */
	private final ArmorMaterial material;

	public ItemMiningHelmUL(String name, ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4)
	{
		super(name, par2EnumArmorMaterial, par3, par4);
		this.material = par2EnumArmorMaterial;
		this.enchantability = 30;
		this.name = name;
		this.setUnlocalizedName(Reactioncraft.MODID + "." + this.name);
		this.setMaxStackSize(1);
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);
		IBlockState blockState = worldIn.getBlockState(pos);

		if(stack.getItem() == ItemIndex.miningHelmUnlit)
		{
			if(blockState.getBlock() == Blocks.TORCH)
			{
				ItemStack lit = new ItemStack(ItemIndex.miningHelmLit);
				player.addItemStackToInventory(lit);
				player.inventory.deleteStack(player.getHeldItem(hand));
				return EnumActionResult.PASS;
			}
		}
		return EnumActionResult.PASS;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getItemEnchantability()
	{
		return this.enchantability;
	}
}
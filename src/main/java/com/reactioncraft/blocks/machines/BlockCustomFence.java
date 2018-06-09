package com.reactioncraft.blocks.machines;

import com.reactioncraft.Reactioncraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCustomFence extends BlockFence
{

	public BlockCustomFence(Material materialIn, MapColor mapColorIn) 
	{
		super(materialIn, mapColorIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
		BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, facing);
		Block block = iblockstate.getBlock();
		boolean flag = blockfaceshape == BlockFaceShape.MIDDLE_POLE && (iblockstate.getMaterial() == this.blockMaterial || block instanceof BlockCustomFence);
		return blockfaceshape == BlockFaceShape.MIDDLE_POLE || flag;
	}

	public void registerItemModel(ItemBlock itemBlock)
	{
		Reactioncraft.proxy.registerItemRenderer(itemBlock, 0, getRegistryName().getResourcePath());
	}
}
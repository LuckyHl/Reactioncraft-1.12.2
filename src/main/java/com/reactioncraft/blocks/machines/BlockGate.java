package com.reactioncraft.blocks.machines;

import net.minecraft.block.Block;

import net.minecraft.block.SoundType;

import net.minecraft.block.material.Material;

import net.minecraft.block.properties.PropertyBool;

import net.minecraft.block.state.BlockStateContainer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

import net.minecraft.world.IBlockAccess;

import net.minecraft.world.World;



import javax.annotation.Nullable;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.blocks.BlockBase;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.BlockIndex;



public final class BlockGate extends BlockBase
{
	private static final PropertyBool POWERED = PropertyBool.create("powered");


	public BlockGate() 
	{
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		setCreativeTab(Reactioncraft.Reactioncraft);
	}


	public void registerItemModel(ItemBlock itemBlock)
	{
		Reactioncraft.proxy.registerItemRenderer(itemBlock, 0, getRegistryName().getResourcePath());
	}
	
	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) 
	{
		boolean powered = meta != 0;
		return getDefaultState().withProperty(POWERED, powered);
	}



	@Override

	public int getMetaFromState(IBlockState state) 
	{
		return state.getValue(POWERED) ? 1 : 0;
	}



	@Override
	@Deprecated
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block block, BlockPos fromPos)
	{
		boolean isPowered = worldIn.isBlockPowered(pos);

		if(!worldIn.isRemote && isPowered != state.getValue(POWERED))
		{
			updatePoweredStateON(state, worldIn, pos, isPowered);
		}

		else if(!worldIn.isRemote && isPowered == state.getValue(POWERED))
		{
			updatePoweredStateOFF(state, worldIn, pos, isPowered);
		} 
	}



	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		boolean isPowered = worldIn.isBlockPowered(pos);
		if(!worldIn.isRemote && isPowered != state.getValue(POWERED))
		{
			updatePoweredStateON(state, worldIn, pos, isPowered);
		}
	}

	/**
	 * Determine if this block can make a redstone connection on the side provided,
	 * Useful to control which sides are inputs and outputs for redstone wires.
	 *
	 * @param state The current state
	 * @param world The current world
	 * @param pos Block position in world
	 * @param side The side that is trying to make the connection, CAN BE NULL
	 * @return True to make the connection
	 */
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side)
	{
		return true;
	}

	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, POWERED);
	}


	@Override

	public boolean hasTileEntity(IBlockState state) 
	{
		return state.getValue(POWERED);
	}



	@Override
	@Nullable
	public TileEntity createTileEntity(World world, IBlockState state) 
	{
		return hasTileEntity(state) ? null : null;
	}

	protected void updatePoweredStateON(IBlockState state, World worldIn, BlockPos pos, boolean isPowered) 
	{
		if (!worldIn.isRemote) {
			if (isPowered != state.getValue(POWERED)) 
			{
				//Logger.info("on");
				for(int x = 1; x < 90 && worldIn.getBlockState(pos.down(x)).getBlock() != Blocks.COBBLESTONE && worldIn.getBlockState(pos.down(x)).getBlock() == Blocks.AIR; x = x++) 
				{
					worldIn.setBlockState(pos.down(x++), BlockIndex.fence.getDefaultState());
					worldIn.playSound((EntityPlayer)null, pos.down(x), SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.25F + 0.6F);
					//System.out.print("value of x : " + x );  //For Debugging Height
					//System.out.print("\n");
				}
			}
		}
	}

	protected void updatePoweredStateOFF(IBlockState state, World worldIn, BlockPos pos, boolean isPowered) 
	{
		if (!worldIn.isRemote) {        	
			if (isPowered == state.getValue(POWERED)) 
			{
				//Logger.info("off");
				for(int x = 1; x < 90 && worldIn.getBlockState(pos.down(x)).getBlock() == BlockIndex.fence; x = x++) 
				{
					worldIn.setBlockState(pos.down(x), Blocks.AIR.getDefaultState());
					worldIn.playSound((EntityPlayer)null, pos.down(x++), SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.25F + 0.6F);
					//System.out.print("value of x : " + x ); //For Debugging Height
					//System.out.print("\n");
				}
			}
		}
	}
}
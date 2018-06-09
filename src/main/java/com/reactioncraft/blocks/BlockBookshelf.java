package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.EnumBookshelf;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//Minecraft

public class BlockBookshelf extends BlockBase implements MetadataArray
{
	public static final PropertyEnum<EnumBookshelf> TYPE = PropertyEnum.<EnumBookshelf>create("type", EnumBookshelf.class);

	public BlockBookshelf(Material materialIn)
	{
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumBookshelf.EMPTY));
		this.setCreativeTab(Reactioncraft.Reactioncraft);
	}

	/**
	 * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
	 * returns the metadata of the dropped item based on the old metadata of the block.
	 */
	public int damageDropped(IBlockState state)
	{
		return (state.getValue(TYPE)).getMetadata();
	}

	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this,1,EnumBookshelf.EMPTY.getMetadata()));
		items.add(new ItemStack(this,1,EnumBookshelf.SCROLLCASE_EMPTY.getMetadata()));
//		for (EnumBookshelf types : EnumBookshelf.values())
//		{
//			items.add(new ItemStack(this, 1, types.getMetadata()));
//		}
	}

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
	    IBlockState blockState=world.getBlockState(pos);
	    EnumBookshelf bookshelf=blockState.getValue(TYPE);
        return (bookshelf==EnumBookshelf.FULL || bookshelf==EnumBookshelf.SCROLLCASE_FULL || bookshelf==EnumBookshelf.WEBBED_FULL || bookshelf==EnumBookshelf.SCROLLCASE_WEBBED_FULL) ? 1 : 0;
    }

    /**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return (state.getValue(TYPE)).getMetadata();
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, EnumBookshelf.byMetadata(meta));
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		ItemStack book = new ItemStack(Items.BOOK),scroll=new ItemStack(ItemIndex.scroll);

		EnumBookshelf bookshelfType=state.getValue(TYPE);

		if(stack.getItem() == Items.BOOK)
		{
			if (bookshelfType == EnumBookshelf.EMPTY)
			{
				worldIn.setBlockState(pos, state.withProperty(TYPE,EnumBookshelf.ONE_THIRD));
				stack.shrink(1);
				return true;
			}
			else if (bookshelfType == EnumBookshelf.ONE_THIRD)
			{
				worldIn.setBlockState(pos, state.withProperty(TYPE,EnumBookshelf.TWO_THIRDS));
				stack.shrink(1);
				return true;
			}
			else if (bookshelfType== EnumBookshelf.TWO_THIRDS)
			{
				worldIn.setBlockState(pos, state.withProperty(TYPE,EnumBookshelf.FULL));
				stack.shrink(1);
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(stack.getItem()==ItemIndex.scroll)
		{
			if(bookshelfType==EnumBookshelf.SCROLLCASE_EMPTY)
			{
				worldIn.setBlockState(pos,state.withProperty(TYPE,EnumBookshelf.SCROLLCASE_ONE_THIRD));
				stack.shrink(1);
				return true;
			}
			else if(bookshelfType==EnumBookshelf.SCROLLCASE_ONE_THIRD)
			{
				worldIn.setBlockState(pos,state.withProperty(TYPE,EnumBookshelf.SCROLLCASE_TWO_THIRDS));
				stack.shrink(1);
				return true;
			}
			else if(bookshelfType==EnumBookshelf.SCROLLCASE_TWO_THIRDS)
			{
				worldIn.setBlockState(pos,state.withProperty(TYPE,EnumBookshelf.SCROLLCASE_FULL));
				stack.shrink(1);
				return true;
			}
			else return false;

		}
		else
		{
			if (bookshelfType == EnumBookshelf.FULL) {
				worldIn.setBlockState(pos, state.withProperty(TYPE,EnumBookshelf.TWO_THIRDS));
				playerIn.inventory.addItemStackToInventory(book);
				return true;
			}

			else
			if (bookshelfType == EnumBookshelf.TWO_THIRDS)
			{
				worldIn.setBlockState(pos, state.withProperty(TYPE,EnumBookshelf.ONE_THIRD));
				playerIn.inventory.addItemStackToInventory(book);
				return true;
			}
			else
			if (bookshelfType == EnumBookshelf.ONE_THIRD)
			{
				worldIn.setBlockState(pos, state.withProperty(TYPE,EnumBookshelf.EMPTY));
				playerIn.inventory.addItemStackToInventory(book);
				return true;
			}

			else if(bookshelfType==EnumBookshelf.SCROLLCASE_FULL)
			{
				worldIn.setBlockState(pos,state.withProperty(TYPE,EnumBookshelf.SCROLLCASE_TWO_THIRDS));
				playerIn.inventory.addItemStackToInventory(scroll);
				return true;
			}
			else if(bookshelfType==EnumBookshelf.SCROLLCASE_TWO_THIRDS)
			{
				worldIn.setBlockState(pos,state.withProperty(TYPE,EnumBookshelf.SCROLLCASE_ONE_THIRD));
				playerIn.inventory.addItemStackToInventory(scroll);
				return true;
			}
			else if(bookshelfType==EnumBookshelf.SCROLLCASE_ONE_THIRD)
			{
				worldIn.setBlockState(pos,state.withProperty(TYPE,EnumBookshelf.SCROLLCASE_EMPTY));
				playerIn.inventory.addItemStackToInventory(scroll);
				return true;
			}

		}
		return false;
	}	
}
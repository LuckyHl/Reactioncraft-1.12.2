package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.EnumGlass;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockGlowingGlassMulti extends BlockGlass implements MetadataArray
{
    public static final PropertyEnum<EnumGlass> TYPE = PropertyEnum.<EnumGlass>create("type", EnumGlass.class);
    protected String name;
    
    public BlockGlowingGlassMulti(Material materialIn, Boolean ignoreSimilar, String name)
    {
        super(materialIn, ignoreSimilar);
        this.name = name;
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumGlass.WHITE));
        this.setCreativeTab(Reactioncraft.Reactioncraft);
    }
    
    public void registerItemModel(ItemBlock itemBlock) 
	{
		Reactioncraft.proxy.registerItemRenderer(itemBlock, 0, name);
	}
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
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
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumGlass types : EnumGlass.values())
        {
            items.add(new ItemStack(this, 1, types.getMetadata()));
        }
    }

    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 0;
    }

    protected boolean canSilkHarvest()
    {
        return true;
    }
    
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, EnumGlass.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(TYPE)).getMetadata();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, TYPE);
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }
}
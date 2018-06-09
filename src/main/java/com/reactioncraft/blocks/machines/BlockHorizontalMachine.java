package com.reactioncraft.blocks.machines;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * Created on 1/5/18.
 */
public abstract class BlockHorizontalMachine extends BlockContainer implements ITileEntityProvider {
    public static final PropertyBool STATE=PropertyBool.create("active");
    public static final PropertyDirection FACING=PropertyDirection.create("facing", Arrays.asList(EnumFacing.HORIZONTALS));
    public BlockHorizontalMachine(Material materialIn) {
        super(materialIn);
        setDefaultState(blockState.getBaseState().withProperty(STATE,false).withProperty(FACING,EnumFacing.WEST));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,STATE,FACING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        if(meta<4)
        {
            return getDefaultState().withProperty(STATE,false).withProperty(FACING,EnumFacing.getHorizontal(meta));
        }
        else{
            return getDefaultState().withProperty(STATE,true).withProperty(FACING,EnumFacing.getHorizontal(meta-4));
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int facing=state.getValue(FACING).getHorizontalIndex();
        boolean active=state.getValue(STATE);
        if(active)
        {
            return facing;
        }
        else return facing+4;
    }


    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }


    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }


    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public abstract TileEntity createNewTileEntity(World worldIn, int meta);
}

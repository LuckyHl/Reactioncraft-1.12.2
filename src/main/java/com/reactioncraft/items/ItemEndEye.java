package com.reactioncraft.items;

import com.reactioncraft.registration.instances.BlockIndex;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

import static net.minecraft.block.BlockEndPortalFrame.EYE;
import static net.minecraft.block.BlockEndPortalFrame.FACING;

/**
 * Created on 1/7/18.
 */
public class ItemEndEye extends ItemEnderEye {
    static BlockPattern portalShape;
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote)
        {
            ItemStack eyestack=player.getHeldItem(hand);
            IBlockState blockState=worldIn.getBlockState(pos);
            if(blockState.getBlock()== BlockIndex.enderportalframe && !blockState.getValue(EYE))
            {
                worldIn.setBlockState(pos,blockState.withProperty(EYE,true));
                worldIn.updateComparatorOutputLevel(pos,BlockIndex.enderportalframe);
                eyestack.shrink(1);
                for (int i = 0; i < 16; ++i)
                {
                    double d0 = (double)((float)pos.getX() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
                    double d1 = (double)((float)pos.getY() + 0.8125F);
                    double d2 = (double)((float)pos.getZ() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
                worldIn.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                BlockPattern.PatternHelper patternHelper=getOrCreatePortalShape().match(worldIn, pos);
                if(patternHelper!=null)
                {
                    BlockPos blockpos = patternHelper.getFrontTopLeft().add(-3, 0, -3);

                    for (int j = 0; j < 3; ++j)
                    {
                        for (int k = 0; k < 3; ++k)
                        {
                            worldIn.setBlockState(blockpos.add(j, 0, k), Blocks.END_PORTAL.getDefaultState());
                        }
                    }

                    worldIn.playBroadcastSound(1038, blockpos.add(1, 0, 1), 0);
                }
                return EnumActionResult.SUCCESS;
            }
        }


        return EnumActionResult.FAIL;
    }

    BlockPattern getOrCreatePortalShape()
    {
        if (portalShape == null)
        {
            portalShape = FactoryBlockPattern.start().aisle("?vvv?", ">???<", ">???<", ">???<", "?^^^?").where('?',
                    BlockWorldState.hasState(BlockStateMatcher.ANY)).where('^',
                    BlockWorldState.hasState(BlockStateMatcher.forBlock(BlockIndex.enderportalframe)
                            .where(EYE, aBoolean -> Objects.equals(aBoolean, Boolean.TRUE)).where(FACING, enumFacing -> Objects.equals(enumFacing, EnumFacing.SOUTH))))
                    .where('>', BlockWorldState.hasState(BlockStateMatcher.forBlock(BlockIndex.enderportalframe)
                            .where(EYE, aBoolean -> Objects.equals(aBoolean, Boolean.TRUE)).where(FACING, enumFacing -> Objects.equals(enumFacing, EnumFacing.WEST))))
                    .where('v', BlockWorldState.hasState(BlockStateMatcher.forBlock(BlockIndex.enderportalframe).
                            where(EYE, aBoolean -> Objects.equals(aBoolean, Boolean.TRUE)).where(FACING, enumFacing -> Objects.equals(enumFacing, EnumFacing.NORTH)))).
                            where('<', BlockWorldState.hasState(BlockStateMatcher.forBlock(BlockIndex.enderportalframe)
                                    .where(EYE, aBoolean -> Objects.equals(aBoolean, Boolean.TRUE))
                                    .where(FACING, enumFacing -> Objects.equals(enumFacing, EnumFacing.EAST)))).build();
        }

        return portalShape;
    }
}

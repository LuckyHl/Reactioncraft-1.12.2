package com.reactioncraft.blocks;

import java.util.Random;

import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created on 12/31/17.
 */
public class BlockCornPlant extends BlockReactionPlant 
{

	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		int i = this.getAge(state);
		super.updateTick(worldIn, pos, state, rand);

        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            if (i < this.getMaxAge())
            {
                float f = getGrowthChance(this, worldIn, pos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        }
        if(i == this.getMaxAge())
        {
        	BlockPos pos1 = pos.down();
        	worldIn.setBlockState(pos, BlockIndex.cornTall.getDefaultState());
        	worldIn.setBlockState(pos1, Blocks.DIRT.getDefaultState());
        }
    }
	
    @Override
    protected Item getSeed() {
        return Items.AIR;
    }

    @Override
    protected Item getCrop() 
    {
        return Items.AIR;
    }
}

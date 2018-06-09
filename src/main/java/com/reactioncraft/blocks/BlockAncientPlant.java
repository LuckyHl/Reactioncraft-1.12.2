package com.reactioncraft.blocks;

import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockAncientPlant extends BlockReactionPlant
{

    public BlockAncientPlant()
    {
        super();
//        float f = 0.5F;
//        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }
    
    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        super.getDrops(drops, world, pos, state, fortune);
        int i = ((Integer)state.getValue(AGE)).intValue();
        int j = 1;

        if (i >= 6)
        {
            j = 1;
        }

        for (int k = 0; k < j; ++k)
        {
            drops.add(new ItemStack(ItemIndex.ancientFlower, 1));
        }
    }

    @Override
    protected Item getSeed() {
        return ItemIndex.ancientSeeds;
    }

    @Override
    protected Item getCrop() {
        return ItemIndex.ancientFruit;
    }
}

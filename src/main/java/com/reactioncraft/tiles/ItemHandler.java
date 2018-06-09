package com.reactioncraft.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.reactioncraft.items.tools.Tools;

/**
 * This item handler implementation checks whether a stack can be merged with already present stacks first
 */
public class ItemHandler extends ItemStackHandler {
    TileEntity owner;
    public ItemHandler(int size, @Nullable TileEntity owningTileEntity)
    {
        super(size);
        owner=owningTileEntity;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        for (int i = 0; i < getSlots(); i++) {
            ItemStack present=getStackInSlot(i);
            if(present.getCount()!=present.getMaxStackSize() && Tools.areItemTypesEqual(stack,present))
            {
                return super.insertItem(i,stack,simulate);
            }
        }
        return super.insertItem(slot, stack, simulate);
    }

    @Override
    protected void onContentsChanged(int slot) {
        if(owner!=null) owner.markDirty();
    }
}

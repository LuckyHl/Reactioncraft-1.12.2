package com.reactioncraft.itemhandlers;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.reactioncraft.tiles.ItemHandler;

/**
 * Created on 12/31/17.
 */
public class BrickOvenItemHandler extends ItemHandler {

    public BrickOvenItemHandler(int size, @Nullable TileEntity owningTileEntity) {
        super(size, owningTileEntity);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return super.insertItem(slot, stack, simulate);
    }
}

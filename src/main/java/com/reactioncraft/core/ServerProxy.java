package com.reactioncraft.core;

import com.reactioncraft.containers.ContainerBrickOven;
import com.reactioncraft.containers.ContainerClayalizer;
import com.reactioncraft.containers.ContainerFreezer;
import com.reactioncraft.tiles.TileEntityBrickOven;
import com.reactioncraft.tiles.TileEntityClayalizer;
import com.reactioncraft.tiles.TileEntityFreezer;
import com.reactioncraft.ui.GuiBrickoven;
import com.reactioncraft.ui.GuiClayalizer;
import com.reactioncraft.ui.GuiFreezer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class ServerProxy implements IGuiHandler
{

	public void registerItemRenderer(Item item, int meta, String id) 
	{
	}

	public void setItemBlockWithMetadataInventoryModel(ItemBlock itemBlock, String... variants)
	{

	}

	public void registerRenderInformation() 
	{
	}

	/**
	 *
	 * @param item block item
	 * @param metadataRange 1-16
	 */
	public void registerBlockItemRenderer(ItemBlock item, int metadataRange){}

    public enum GuiIDs
    {
        BRICK_OVEN,
		CLAYLISER,
		FREEZER
    }

    @Override
	@Nullable
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
    	final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

    	if(tileEntity!=null) {
			switch (GuiIDs.values()[ID]) {
				case BRICK_OVEN:
					return new ContainerBrickOven(player.inventory, (TileEntityBrickOven) tileEntity);
				case FREEZER:
					return new ContainerFreezer(player.inventory, (TileEntityFreezer) tileEntity);
				case CLAYLISER:
					return new ContainerClayalizer(player.inventory, (TileEntityClayalizer) tileEntity);
			}
			throw new IllegalArgumentException("No gui with id " + ID);
		}
		return null;
    }

	@Override
	@Nullable
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

		if (tileEntity != null) {
			switch (GuiIDs.values()[ID]) {
				case BRICK_OVEN: {
					return new GuiBrickoven(player.inventory, (TileEntityBrickOven) tileEntity);
				}
				case CLAYLISER:return new GuiClayalizer(player.inventory, (TileEntityClayalizer) tileEntity);
				case FREEZER:return new GuiFreezer(player.inventory, (TileEntityFreezer) tileEntity);
			}
		}
		return null;
	}
}
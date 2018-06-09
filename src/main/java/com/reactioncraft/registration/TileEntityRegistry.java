package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.tiles.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry 
{

	public static void registerTileEntities()
	{
		//GameRegistry.registerTileEntity(TileEntityBookcaseChest.class, "Bookcasechest");
        GameRegistry.registerTileEntity(TileEntityFreezer.class, Reactioncraft.MODID+":freezer");
        GameRegistry.registerTileEntity(TileEntityBrickOven.class,  Reactioncraft.MODID+":brickoven");
        GameRegistry.registerTileEntity(TileEntityClayalizer.class, Reactioncraft.MODID+":clayalizer");
        //GameRegistry.registerTileEntity(TileEntityExtendedPiston.class, Reactioncraft.MODID+":piston");
	}
}
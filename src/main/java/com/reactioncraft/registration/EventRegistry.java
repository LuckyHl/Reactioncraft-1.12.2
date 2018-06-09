package com.reactioncraft.registration;

import com.reactioncraft.common.events.ButcherEventClass;
import com.reactioncraft.common.events.EntityAIEventClass;
import com.reactioncraft.common.events.EventContainerClass;
import com.reactioncraft.craftinghandlers.CraftablesCraftingHandler;
import com.reactioncraft.craftinghandlers.HammerCraftingHandler;
import com.reactioncraft.craftinghandlers.MachinesCraftingHandler;
import com.reactioncraft.craftinghandlers.NetCraftingHandler;
import com.reactioncraft.world.BiomeHandler;
import net.minecraftforge.common.MinecraftForge;

public class EventRegistry 
{
	//All Events Registered Here
	public static void eventInit() 
	{
		MinecraftForge.EVENT_BUS.register(new ButcherEventClass());
		MinecraftForge.EVENT_BUS.register(new EntityAIEventClass());
		MinecraftForge.EVENT_BUS.register(new EventContainerClass());
		MinecraftForge.EVENT_BUS.register(new NetCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new MachinesCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new CraftablesCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new HammerCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new BiomeHandler());
	}
}
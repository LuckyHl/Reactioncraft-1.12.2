package com.reactioncraft.utils;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.PropertyIndex;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class constants 
{
	//Mod Info
	public static final String BaseID    = "Reactioncraft 3: Rebirth";
	public static final String MODID     = "reactioncraft";
	public static final String VERSION   = "0.6.3.1";
	public static final String MCVersion = "1.12.2";

	//Booleans for checking mods
	//Loaded Mods
	public static boolean millenaire() throws ClassNotFoundException 
	{
		try{
			Class.forName("org.millenaire.common.forge.Mill");
		}
		catch (NoClassDefFoundError ex) 
		{
			return false ;
		}
		return true ;
	}

	//Loaded Mods
	public static  boolean forestry() throws ClassNotFoundException 
	{
		try{
			Class.forName("forestry.Forestry");
		}
		catch (NoClassDefFoundError ex) 
		{
			return false ;
		}
		return true ;
	}

	public static void config() 
	{
		// Text for Config File
		
	}

	public static void configmillenaire() 
	{
		// Millenaire Villager Trades
		//name, selling price, buying price, reserved quantity, target quantity, foreign merchant price, auto-generated, needed equipment(deprecated), minimum reputation
		//PropertyIndex.cornTrade = Reactioncraft.millenaire.get("", "rawcorn,2,1,0,20", "");
	}
}
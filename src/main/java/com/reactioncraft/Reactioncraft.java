package com.reactioncraft;


import com.reactioncraft.api.ExclusionList;
import com.reactioncraft.api.OreDictionaryRegistry;
import com.reactioncraft.common.events.LootTableHandler;
import com.reactioncraft.core.Logger;
import com.reactioncraft.core.ServerProxy;
import com.reactioncraft.creativetabs.RCBlockTab;
import com.reactioncraft.creativetabs.RCFoodTab;
import com.reactioncraft.creativetabs.RCItemTab;
import com.reactioncraft.entities.EntityBee;
import com.reactioncraft.entities.EntityHydrolisc;
import com.reactioncraft.entities.EntitySkeletonCrawling;
import com.reactioncraft.entities.EntityZombieCrawling;
import com.reactioncraft.registration.*;
import com.reactioncraft.registration.instances.ItemIndex;
import com.reactioncraft.utils.ReactioncraftConfiguration;
import com.reactioncraft.utils.constants;
import com.reactioncraft.world.BiomeHandler;
import com.reactioncraft.world.Worldgen;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.awt.*;
import java.io.File;
import java.util.List;

@Mod(modid = Reactioncraft.MODID, name = Reactioncraft.NAME, version = Reactioncraft.VERSION, acceptedMinecraftVersions = Reactioncraft.MCVersion)
@SuppressWarnings("unused")
public class Reactioncraft
{
<<<<<<< HEAD
	public static final String NAME = constants.BaseID;
	public static final String MODID = constants.MODID;
	public static final String VERSION = constants.VERSION;
	public static final String MCVersion = constants.MCVersion;


	//Proxies
	@SidedProxy(serverSide = "com.reactioncraft.core.ServerProxy", clientSide = "com.reactioncraft.core.ClientProxy")
	public static ServerProxy proxy;

	//Instance
	@Mod.Instance(MODID)
	public static com.reactioncraft.Reactioncraft instance;

	//Creative Tabs
	public static CreativeTabs Reactioncraft      = new RCBlockTab(MODID);
	public static CreativeTabs ReactioncraftItems = new RCItemTab(MODID+" items");
	public static CreativeTabs Reactioncraftfood  = new RCFoodTab(MODID+" food");

	//Exclusion List of Entities
	public static ExclusionList exclusionList=new ExclusionList();

	//For Wild_Card Values (Replace as it pops up)
	public static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;

	//Config
	public static ReactioncraftConfiguration config;
	public static ReactioncraftConfiguration millenaire;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent evt)
	{
		Logger.setLogger(evt.getModLog());
		Logger.info("Reactioncraft Pre-initialization started");

		config = new ReactioncraftConfiguration(new File(evt.getModConfigurationDirectory(), "Reactioncraft/Basemod.wizim"));
		//millenaire = new ReactioncraftConfiguration(new File(Minecraft.getMinecraft().mcDataDir, "/mods/millenaire-custom/cultures/mayan/reactioncraft_traded_goods.txt"));

		try {
			config.load();
			constants.config();
		}
		finally {
			if (config.hasChanged()) { config.save(); }
		}

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ServerProxy());
		proxy.registerRenderInformation();
		MaterialIndex.initMaterials();

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new BlockRegistry());
		MinecraftForge.EVENT_BUS.register(new ItemRegistry());
		MinecraftForge.EVENT_BUS.register(new BiomeHandler());
		MinecraftForge.EVENT_BUS.register(new LootTableHandler());

		TileEntityRegistry.registerTileEntities();

		int eid=0;
		//NOTICE the colors can be changed as needed. First is shell color, second is spot color
		//FIXME some entities can hang the game
		EntityRegistry.registerModEntity(new ResourceLocation(MODID,"hydrolisc"), EntityHydrolisc.class,"hydrolisc",eid++,instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntityHydrolisc.class, EntityLiving.SpawnPlacementType.ON_GROUND);
		//
		//        EntityRegistry.registerModEntity(new ResourceLocation(MODID,"sea_creeper"), EntitySeaCreeper.class,"sea_creeper",2,instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		//        EntitySpawnPlacementRegistry.setPlacementType(EntitySeaCreeper.class, EntityLiving.SpawnPlacementType.IN_WATER);
		EntityRegistry.registerModEntity(new ResourceLocation(MODID,"crawling_skeleton"), EntitySkeletonCrawling.class,"crawling_skeleton",eid++,instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletonCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);

		EntityRegistry.registerModEntity(new ResourceLocation(MODID,"crawling_zombie"),EntityZombieCrawling.class,"crawling_zombie",eid++,instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,150,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntityZombieCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);



		//TODO biomes to spawn in
		ForgeRegistries.BIOMES.forEach(biome -> {
			if(!(biome instanceof BiomeOcean))
			{
				List<Biome.SpawnListEntry> listEntries= biome.getSpawnableList(EnumCreatureType.MONSTER);
				//100 is the max weight
				listEntries.add(new Biome.SpawnListEntry(EntitySkeletonCrawling.class,25,1,2));
				listEntries.add(new Biome.SpawnListEntry(EntityZombieCrawling.class,  25,1,2));
				//and so on
			}
		});
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		OreDictionaryRegistry.registerOres();
		EventRegistry.eventInit();

		//NOTICE
		GameRegistry.registerWorldGenerator(new Worldgen(), 3);

		RecipesManager.registerRecipes();
	}

	@Mod.EventHandler
	public void modsLoaded(FMLPostInitializationEvent evt)
	{		
		//millenaire integration
		try
		{
			if(constants.millenaire())
			{
				try {
					//millenaire.load();
					//constants.configmillenaire();
				}
				finally {
					//if (millenaire.hasChanged()) { millenaire.save(); }
				}
			}
			System.out.println("Millenaire integration loaded !");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Reactioncraft did not find millenaire, added recipes disabled!");
		}

		//Forestry integration
		try
		{
			if(constants.forestry())
			{
				System.out.println("Forestry integration loaded !");
			}
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Reactioncraft did not find Forestry, added recipes disabled!");
		}

		Logger.info("Reactioncraft has fully Loaded!");
	}

	@SubscribeEvent
	public void registerVillagers(RegistryEvent.Register<VillagerRegistry.VillagerProfession> registryEvent)
	{
		IForgeRegistry<VillagerRegistry.VillagerProfession> registry=registryEvent.getRegistry();
		Villagers.register(registry);
	}

	@SubscribeEvent
	public void onMissingMappings(RegistryEvent.MissingMappings missingMappings)
	{

	}
=======
    public static final String NAME = "Reactioncraft 3: Rebirth";
    public static final String MODID = "reactioncraft";
    public static final String VERSION = "0.2";

    //Proxies
    @SidedProxy(serverSide = "com.reactioncraft.core.ServerProxy", clientSide = "com.reactioncraft.core.ClientProxy")
    public static ServerProxy proxy;

    //Instance
    @Mod.Instance(MODID)
    public static com.reactioncraft.Reactioncraft instance;

    //Creative Tabs
    public static CreativeTabs Reactioncraft      = new RCBlockTab(MODID);
    public static CreativeTabs ReactioncraftItems = new RCItemTab(MODID+" items");
    public static CreativeTabs Reactioncraftfood  = new RCFoodTab(MODID+" food");

    //Exclusion List of Entities
    public static ExclusionList exclusionList=new ExclusionList();

    //For Wild_Card Values (Replace as it pops up)
    public static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt)
    {

        Logger.setLogger(evt.getModLog());
        Logger.info("Pre-initialization started");
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ServerProxy());
        proxy.registerRenderInformation();
        MaterialIndex.initMaterials();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new BlockRegistry());
        MinecraftForge.EVENT_BUS.register(new ItemRegistry());
        MinecraftForge.EVENT_BUS.register(new BiomeHandler());
        MinecraftForge.EVENT_BUS.register(new LootTableHandler());

        TileEntityRegistry.registerTileEntities();

        int eid=0;
        //NOTICE the colors can be changed as needed. First is shell color, second is spot color
        //FIXME some entities can hang the game
        EntityRegistry.registerModEntity(new ResourceLocation(MODID,"bee"), EntityBee.class,"bee",eid++,instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
        EntitySpawnPlacementRegistry.setPlacementType(EntityBee.class, EntityLiving.SpawnPlacementType.ON_GROUND);
//
        EntityRegistry.registerModEntity(new ResourceLocation(MODID,"hydrolisc"), EntityHydrolisc.class,"hydrolisc",eid++,instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
        EntitySpawnPlacementRegistry.setPlacementType(EntityHydrolisc.class, EntityLiving.SpawnPlacementType.ON_GROUND);
//
//        EntityRegistry.registerModEntity(new ResourceLocation(MODID,"sea_creeper"), EntitySeaCreeper.class,"sea_creeper",2,instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
//        EntitySpawnPlacementRegistry.setPlacementType(EntitySeaCreeper.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntityRegistry.registerModEntity(new ResourceLocation(MODID,"crawling_skeleton"), EntitySkeletonCrawling.class,"crawling_skeleton",eid++,instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
        EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletonCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);

        EntityRegistry.registerModEntity(new ResourceLocation(MODID,"crawling_zombie"),EntityZombieCrawling.class,"crawling_zombie",eid++,instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,150,1).getRGB());
        EntitySpawnPlacementRegistry.setPlacementType(EntityZombieCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);



        //TODO biomes to spawn in
        ForgeRegistries.BIOMES.forEach(biome -> {
            if(!(biome instanceof BiomeOcean))
            {
                List<Biome.SpawnListEntry> listEntries= biome.getSpawnableList(EnumCreatureType.MONSTER);
                //100 is the max weight
                listEntries.add(new Biome.SpawnListEntry(EntitySkeletonCrawling.class,90,1,4));
                listEntries.add(new Biome.SpawnListEntry(EntityZombieCrawling.class,90,1,4));
                //and so on
            }
        });
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        OreDictionaryRegistry.registerOres();
        IntegratedEventRegistry.eventInit();

        //NOTICE
        GameRegistry.registerWorldGenerator(new Worldgen(), 3);

        RecipeRegistry.netrecipyInit();
        RecipeRegistry.oreSmelting();
        RecipeRegistry.glassRecipesInit();
        RecipeRegistry.loadRecipesforVanilla();
        RecipeRegistry.foodRecipesInit();
        RecipeRegistry.loadORES();
        RecipeRegistry.currencyRecipesInit();
        RecipeRegistry.loadCore();
        RecipeRegistry.loadDesertRecipes();
        RecipeRegistry.miscRecipesInit();
    }

    @Mod.EventHandler
    public void modsLoaded(FMLPostInitializationEvent evt)
    {
        Logger.info("Fully Loaded!");
    }

    @SubscribeEvent
    public void registerVillagers(RegistryEvent.Register<VillagerRegistry.VillagerProfession> registryEvent)
    {
        IForgeRegistry<VillagerRegistry.VillagerProfession> registry=registryEvent.getRegistry();
        Villagers.register(registry);
    }

    @SubscribeEvent
    public void onMissingMappings(RegistryEvent.MissingMappings missingMappings)
    {

    }
//    @EventHandler
//	public void missingMappings(FMLMissingMappingsEvent event)
//    {
//		//Remapper.remap(event.get());
//	}
>>>>>>> f0aef18053091300e96805a3fdf8b31fad47382e
}
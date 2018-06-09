package com.reactioncraft.common.recipes;

import javax.annotation.Nullable;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;

import forestry.api.recipes.RecipeManagers;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistry
{
	@Nullable
	public static ItemStack rdustGold;
	@Nullable
	public static ItemStack rdustSilver;
	
	@SubscribeEvent
	public static void netrecipyInit() 
	{
		//Below is the crafting Recipe's for making the multi-Level / extra use Nets.
		Object[] levels = new Object[] {Blocks.PLANKS, Items.LEATHER, Items.IRON_INGOT, Items.GOLD_INGOT, Items.DIAMOND};
		for (int i = 0; i < levels.length; ++i)
		{
			Object[] hiltRec = new Object[] {" XI", "XIX", "IX ", 'X', Items.STICK, 'I', levels[i]};
			Object[] netRec = new Object[] {"IXI", "XIX", "IXI", 'X', Items.STRING, 'I', levels[i]};
			ItemStack hiltIs = new ItemStack(ItemIndex.hilt);
			ItemStack netIs = new ItemStack(ItemIndex.net);

			hiltIs.setTagCompound(new NBTTagCompound());
			netIs.setTagCompound(new NBTTagCompound());

			hiltIs.getTagCompound().setInteger("str1", i + 1);
			netIs.getTagCompound() .setInteger("str2", i + 1);
			GameRegistry.addShapedRecipe(new ResourceLocation(Reactioncraft.MODID,"net"+i),null,hiltIs,hiltRec);
			GameRegistry.addShapedRecipe(new ResourceLocation(Reactioncraft.MODID,"hilt"+i),null,netIs,netRec);
		}
		
		//GameRegistry.addShapelessRecipe(new ItemStack(ItemIndex.complete_net), new Object[] {ItemIndex.hilt, ItemIndex.net});
		//Object[] netREC = new Object[] {" X", "I ", 'I', ItemIndex.hilt, 'X', ItemIndex.net};
		//GameRegistry.addShapedRecipe(new ResourceLocation(Reactioncraft.MODID,"complete_net"),null,null, netREC);
	}
	
	public static void loadRecipesforVanilla()
	{
		GameRegistry.addSmelting(ItemIndex.iceBucket, new ItemStack(Items.WATER_BUCKET, 1), 0.5F);
	}
	
	public static void glassRecipesInit()
	{
		GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(ItemIndex.moltenglass, 1, 0), 0.5F);
	}
	
	public static void foodRecipesInit()
	{
		GameRegistry.addSmelting(ItemIndex.salmonRaw, new ItemStack(ItemIndex.salmon), 0.1F);
		GameRegistry.addSmelting(ItemIndex.yellowTailRaw, new ItemStack(ItemIndex.yellowTailCooked), 0.1F);
		GameRegistry.addSmelting(ItemIndex.raw_bacon, new ItemStack(ItemIndex.bacon), 0.1F);
		GameRegistry.addSmelting(ItemIndex.rawNuggets, new ItemStack(ItemIndex.chickenNuggets), 0.1F);
		GameRegistry.addSmelting(ItemIndex.bagofpopcorn, new ItemStack(ItemIndex.poppedbagofpopcorn), 0.1F);
		GameRegistry.addSmelting(ItemIndex.rawcorn, new ItemStack(ItemIndex.cookedCorn), 0.1F);
		GameRegistry.addSmelting(ItemIndex.raw_human, new ItemStack(ItemIndex.cooked_human), 0.1F);
		GameRegistry.addSmelting(ItemIndex.raw_horse, new ItemStack(ItemIndex.cooked_horse), 0.1F);
		GameRegistry.addSmelting(ItemIndex.cornSeed, new ItemStack(ItemIndex.popcornseeds), 0.1F);
	}
	
	/** Recipes that usually create vanilla items or blocks **/
	public static void miscRecipesInit() 
	{	
		GameRegistry.addSmelting(Blocks.TALLGRASS, new ItemStack(ItemIndex.straw), 0.5F);
		GameRegistry.addSmelting(BlockIndex.dark_sand, new ItemStack(Blocks.GLASS), 0.5F);
		GameRegistry.addSmelting(ItemIndex.goldrod, new ItemStack(Items.GOLD_INGOT, 2), 0.5F);
		GameRegistry.addSmelting(Blocks.OBSIDIAN, new ItemStack(ItemIndex.obsidianingot), 0.5F);
	}
	
	public static void oreSmelting()
	{
		GameRegistry.addSmelting(new ItemStack(BlockIndex.surfaceOres, 1, 2), new ItemStack(Items.GOLD_INGOT), 0.5F);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.surfaceOres, 1, 3), new ItemStack(Items.GOLD_INGOT), 0.5F);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.surfaceOres, 1, 4), new ItemStack(ItemIndex.ingotSilver), 0.5F);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.surfaceOres, 1, 5),new ItemStack(ItemIndex.ingotCopper),0.5F);
	}
	
	public static void currencyRecipesInit()
	{
		GameRegistry.addSmelting(ItemIndex.crown,        new ItemStack(ItemIndex.ingotRefinedgold, 3, 0), 0.5F);
		GameRegistry.addSmelting(ItemIndex.goldbucket,   new ItemStack(ItemIndex.moltengold), 0.5F);
		GameRegistry.addSmelting(ItemIndex.silverbucket, new ItemStack(ItemIndex.moltensilver), 0.5F);
		GameRegistry.addSmelting(ItemIndex.bronzebucket, new ItemStack(ItemIndex.moltenbronze), 0.5F);
		GameRegistry.addSmelting(ItemIndex.copperbucket, new ItemStack(ItemIndex.moltencopper), 0.5F);
	}
	
	public static void loadORES()
	{
		GameRegistry.addSmelting(new ItemStack(BlockIndex.endOres,1,1),new ItemStack(ItemIndex.meltedventinite),0.5F);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.endOres,1,0),new ItemStack(ItemIndex.meltedwizimite),0.5F);
        GameRegistry.addSmelting(new ItemStack(BlockIndex.netherOres),new ItemStack(ItemIndex.bloodstoneclump),0.5F);
		GameRegistry.addSmelting(ItemIndex.bloodstonedust, new ItemStack(ItemIndex.ingotbloodstone), 0.5F);
		GameRegistry.addSmelting(ItemIndex.bloodstoneclump, new ItemStack(ItemIndex.ingotbloodstone), 0.5F);
		GameRegistry.addSmelting(ItemIndex.irondust, new ItemStack(ItemIndex.superheatedironingot, 2, 0), 0.5F);
		GameRegistry.addSmelting(ItemIndex.goldDust, new ItemStack(Items.GOLD_INGOT), 0.1F);

	}

	public static void loadDesertRecipes()
	{
		GameRegistry.addSmelting(new ItemStack(BlockIndex.desertBlocks,1,3),new ItemStack(Items.GOLD_INGOT),0.5F);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.desertBlocks,1,1), new ItemStack(BlockIndex.desertBlocks, 1, 7), 0.1F);

		GameRegistry.addSmelting(BlockIndex.cherrywood,  new ItemStack(Items.COAL, 1, 1), 0.5F);
		GameRegistry.addSmelting(BlockIndex.redCactus,   new ItemStack(Items.DYE, 1, 1), 0.15F);
		GameRegistry.addSmelting(BlockIndex.greenCactus, new ItemStack(Items.DYE, 1, 2), 0.15F);
	}

	public static void loadCurrency()
	{
//	done	GameRegistry.addRecipe(new ItemStack(IntegratedItems.Bag, 1), new Object[] {"TTT", "T T", "TTT", 'T', IntegratedItems.Straw});
//	done	GameRegistry.addRecipe(new ItemStack(IntegratedItems.Sack, 1), new Object[] {"TTT", "TTT", 'T', IntegratedItems.Straw});
//	done	GameRegistry.addRecipe(new ItemStack(IntegratedItems.KinglyChest, 1), new Object[] {"T T", "TTT", "TTT", 'T', Blocks.WOOL});
//	done	GameRegistry.addRecipe(new ItemStack(IntegratedItems.KinglyLegs, 1), new Object[] {"TTT", "T T", "T T", 'T',  Blocks.WOOL});
//	done	GameRegistry.addRecipe(new ItemStack(IntegratedItems.KinglyBoots, 1), new Object[] {"T T", "T T", 'T',        Blocks.WOOL});
//
//		GameRegistry.addShapelessRecipe(new ItemStack(IntegratedItems.Metadatacoin, 2, 0), new Object[] {new ItemStack(IntegratedItems.moltencopper), new ItemStack(IntegratedItems.CoinMould, 1, Reactioncraft.WILDCARD_VALUE)});
//		GameRegistry.addShapelessRecipe(new ItemStack(IntegratedItems.Metadatacoin, 2, 1), new Object[] {new ItemStack(IntegratedItems.moltenbronze), new ItemStack(IntegratedItems.CoinMould, 1, Reactioncraft.WILDCARD_VALUE)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 2), new Object[] {"###", "###", "###", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 1)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 3), new Object[] {"DDD", 'D', new ItemStack(IntegratedItems.Metadatacoin, 1, 2)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 4), new Object[] {"###", "###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 3), 'D', IntegratedItems.Sack});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 5), new Object[] {"###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 4), 'D', IntegratedItems.Bag});
//		GameRegistry.addShapelessRecipe(new ItemStack(IntegratedItems.Metadatacoin, 2, 6), new Object[] {new ItemStack(IntegratedItems.moltensilver), new ItemStack(IntegratedItems.CoinMould, 1, Reactioncraft.WILDCARD_VALUE)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 7), new Object[] {"###", "###", "###", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 6)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 8), new Object[] {"DDD", 'D', new ItemStack(IntegratedItems.Metadatacoin, 1, 7)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 9), new Object[] {"###", "###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 8), 'D', IntegratedItems.Sack});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 10), new Object[] {"###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 9), 'D', IntegratedItems.Bag});
//		GameRegistry.addShapelessRecipe(new ItemStack(IntegratedItems.Metadatacoin, 2, 11), new Object[] {new ItemStack(IntegratedItems.moltengold), new ItemStack(IntegratedItems.CoinMould, 1, Reactioncraft.WILDCARD_VALUE)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 12), new Object[] {"###", "###", "###", '#', new ItemStack(IntegratedItems.Metadatacoin, 2, 11)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 13), new Object[] {"DDD", 'D', new ItemStack(IntegratedItems.Metadatacoin, 2, 12)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 14), new Object[] {"###", "###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 2, 13), 'D', IntegratedItems.Sack});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 15), new Object[] {"###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 2, 14), 'D', IntegratedItems.Bag});
	}

	@SuppressWarnings("unchecked")
	@Nullable
	public static void forestry()
	{
		//Corn 
		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack(ItemIndex.rawcorn),  FluidRegistry.getFluidStack("seed.oil", 25));
		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack(ItemIndex.cornSeed), FluidRegistry.getFluidStack("seed.oil", 10));
		
		//Ancient Fruit
		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack(ItemIndex.ancientFruit), FluidRegistry.getFluidStack("seed.oil", 15));
		
		//Cacti
		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack(BlockIndex.greenCactus), new FluidStack(FluidRegistry.WATER, 250));
		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack(BlockIndex.redCactus)  , new FluidStack(FluidRegistry.WATER, 250));
		
		//Desert Bricks to Mossy Version
		RecipeManagers.moistenerManager.addRecipe(new ItemStack(BlockIndex.desertBlocks, 1, 0), new ItemStack(BlockIndex.desertBlocks, 1, 7), 20000); //Change Meta
		
		//Darksand into glass fluid
		//RecipeManagers.fabricatorSmeltingManager.addSmelting(new ItemStack(Blocks.SAND), FluidRegistry.getFluidStack(1000, 3000);
	}

	@SuppressWarnings("unchecked")
	@Nullable
	public static void ic2() 
	{
		//Bloodstone block to bloodstone dust
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("oreBloodstone", 1), null, false, new ItemStack[] { new ItemStack(ItemIndex.bloodstonedust, 3)});

		
		//Black Diamond Ore to Black Diamond
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("oreNetherBlackDiamond", 1), null, false, new ItemStack[] { new ItemStack(ItemIndex.blackdiamond, 2)});
		
		//Dragonstone to Dragonstone Shard
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("oreNetherDragonstone", 1), null, false, new ItemStack[] { new ItemStack(ItemIndex.dragonstoneshard, 3)});
		
		//Nether Diamond to Diamond
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("oreNetherDiamondOre", 1), null, false, new ItemStack[] { new ItemStack(Items.DIAMOND, 2)});
		
		//Nether Gold to Crushed Gold
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("oreNetherGoldOre", 1), null, false, new ItemStack[] { new ItemStack(ItemIndex.goldDust, 2)});

		//Desert Gold to Crushed Gold
		rdustGold = IC2Items.getItem("crushed", "gold");
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("oreDesertGold", 1), null, false, new ItemStack[] { rdustGold });
		//Recipes.macerator.addRecipe(new RecipeInputOreDict("oreDesertGold", 1), null, new ItemStack[] { new ItemStack(dustGold.itemID, 2, 2)}); 

		//Silver Ore to Crushed Silver
		rdustSilver = IC2Items.getItem("crushed", "silver");
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("oreSilver", 1), null, false, new ItemStack[] { rdustSilver });
		
		//Desert Coal to Coal Item
		//Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("oreDesertCoal", 1), null, false, new ItemStack[] { new ItemStack(Items.COAL, 2) });

		//DarkCobble /DarkStone to DarkSand
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("DarkStone", 1)    , null, false, new ItemStack[] { new ItemStack(BlockIndex.dark_sand, 1)});
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("DarkCobble", 1)   , null, false, new ItemStack[] { new ItemStack(BlockIndex.dark_sand, 1)});
	}
}
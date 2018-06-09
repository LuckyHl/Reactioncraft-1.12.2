package com.reactioncraft.world;

import com.reactioncraft.blocks.BlockDesertMulti;
import com.reactioncraft.blocks.ores.BlockNetherOre;
import com.reactioncraft.blocks.ores.BlockSurfaceOre;
import com.reactioncraft.common.EnumNetherOres;
import com.reactioncraft.common.EnumSurfaceOres;
import com.reactioncraft.common.EnumDesertBlocks;
import com.reactioncraft.registration.instances.BlockIndex;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class Worldgen implements IWorldGenerator
{
	private  final IBlockState silver = BlockIndex.surfaceOres.getDefaultState().withProperty(BlockSurfaceOre.TYPE,  EnumSurfaceOres.SILVER);
	private  final IBlockState copper = BlockIndex.surfaceOres.getDefaultState().withProperty(BlockSurfaceOre.TYPE, EnumSurfaceOres.COPPER);

	//Desert Blocks
	private  final IBlockState desertCoal = BlockIndex.surfaceOres.getDefaultState().withProperty(BlockSurfaceOre.TYPE, EnumSurfaceOres.DESERT_COAL);
	private  final IBlockState darkBlueGem = BlockIndex.surfaceOres.getDefaultState().withProperty(BlockSurfaceOre.TYPE, EnumSurfaceOres.DARK_BLUE_GEM);
	private  final IBlockState lightBlueGem = BlockIndex.surfaceOres.getDefaultState().withProperty(BlockSurfaceOre.TYPE, EnumSurfaceOres.LIGHT_BLUE_GEM);
	private  final IBlockState desertGold = BlockIndex.surfaceOres.getDefaultState().withProperty(BlockSurfaceOre.TYPE, EnumSurfaceOres.DESERT_GOLD);

	private  final IBlockState granite = BlockIndex.desertBlocks.getDefaultState().withProperty(BlockDesertMulti.TYPE, EnumDesertBlocks.GRANITE);//Granite

	//Nether Blocks
	private  final IBlockState bloodstone = BlockIndex.netherOres.getDefaultState() .withProperty(BlockNetherOre.TYPE, EnumNetherOres.BLOODSTONE);
	private  final IBlockState blackdiamond = BlockIndex.netherOres.getDefaultState() .withProperty(BlockNetherOre.TYPE, EnumNetherOres.BLACKDIAMOND);
	private  final IBlockState diaomond = BlockIndex.netherOres.getDefaultState() .withProperty(BlockNetherOre.TYPE, EnumNetherOres.DIAMOND);
	private  final IBlockState dragonStone = BlockIndex.netherOres.getDefaultState().withProperty(BlockNetherOre.TYPE, EnumNetherOres.DRAGONSTONE);
	private  final IBlockState goldOre = BlockIndex.netherOres.getDefaultState().withProperty(BlockNetherOre.TYPE, EnumNetherOres.GOLD);

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.getDimensionType()==DimensionType.OVERWORLD)
		{
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
		else
		if (world.provider.getDimensionType()==DimensionType.NETHER)
		{
			generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
		else if(world.provider.getDimensionType()== DimensionType.THE_END)
		{

		}
	}

	private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		//Regular Stone Ores
		generateCopper(copper,  world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
		generateSilver   (silver,  world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);

		//Desert Biome Generation
		generateDesertCoal   (desertCoal, world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(7),  6);
		generateDesertGem1   (darkBlueGem, world, random, chunkX * 16, chunkZ * 16, 16, 64, 1 + random.nextInt(6),  6);
		generateDesertGem2   (lightBlueGem, world, random, chunkX * 16, chunkZ * 16, 16, 64, 1 + random.nextInt(6),  6);
		generateDesertGranite(granite, world, random, chunkX * 16, chunkZ * 16, 16, 64, 1 + random.nextInt(10), 6);
		generateDesertGold   (desertGold, world, random, chunkX * 16, chunkZ * 16, 16, 64, 1 + random.nextInt(7),  6);
	}

	private void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		generateBloodstone  (bloodstone,  world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(125), 6);
		generateBlackdiamond(blackdiamond,  world, random, chunkX * 16, chunkZ * 16, 16, 64, 1 + random.nextInt(4), 6);
		generateDiamond     (diaomond,  world, random, chunkX * 16, chunkZ * 16, 16, 64, 1 + random.nextInt(3), 6);
		generateDragonstone (dragonStone, world, random, chunkX * 16, chunkZ * 16, 16, 64, 2 + random.nextInt(10), 6);
		generateGold        (goldOre, world, random, chunkX * 16, chunkZ * 16, 16, 64, 1 + random.nextInt(5), 6);
	}
	
	private boolean generateGold(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances)
	{
		int deltaY = maxY - minY;
		boolean generated=false;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			//Generate
			ReactionNetherGenMinable generator = new ReactionNetherGenMinable(ore, size);
			generated=generator.generate(world, random, pos);
		}
		return generated;
	}
	
	private void generateDragonstone(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			//Generate 
			ReactionNetherGenMinable generator = new ReactionNetherGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}
	
	private void generateBlackdiamond(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			//Generate
			ReactionNetherGenMinable generator = new ReactionNetherGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}
	
	private void generateDiamond(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			ReactionNetherGenMinable generator = new ReactionNetherGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}
	
	private void generateBloodstone(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			BloodstoneGenMinable  generator = new BloodstoneGenMinable (ore, size);
			generator.generate(world, random, pos);
		}
	}



	private void generateDesertGold(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			ReactionSurfaceGenMinable generator = new ReactionSurfaceGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}

	private void generateDesertGranite(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			ReactionSurfaceGenMinable generator = new ReactionSurfaceGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}

	private void generateDesertGem2(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			ReactionSurfaceGenMinable generator = new ReactionSurfaceGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}

	private void generateDesertGem1(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			ReactionSurfaceGenMinable generator = new ReactionSurfaceGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}

	private void generateDesertCoal(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
			int deltaY = maxY - minY;
			for (int i = 0; i < chances; i++) 
			{
				BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

				//Generate Desert Coal
				ReactionSurfaceGenMinable generator = new ReactionSurfaceGenMinable(ore, size);
				generator.generate(world, random, pos);
				//System.out.println("Spawned " + Ore2 + " at " + pos);
			}
	}

	private void generateCopper(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances)
	{
			int deltaY = maxY - minY;
			for (int i = 0; i < chances; i++) 
			{
				BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

				//Generate Silver
				WorldGenMinable generator = new WorldGenMinable(ore, size);
				generator.generate(world, random, pos);
				//System.out.println("Spawned " + Ore + " at " + pos);
			}
	}

	private void generateSilver(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) 
		{
			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			//Generate Silver
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, random, pos);
			//System.out.println("Spawned " + Ore + " at " + pos);
		}
	}
}
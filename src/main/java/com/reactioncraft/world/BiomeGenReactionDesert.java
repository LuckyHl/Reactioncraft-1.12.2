package com.reactioncraft.world;

import com.reactioncraft.blocks.BlockDesertMulti;
import com.reactioncraft.common.EnumDesertBlocks;
import com.reactioncraft.entities.EntityHydrolisc;
import com.reactioncraft.registration.instances.BlockIndex;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;


public class BiomeGenReactionDesert extends BiomeDesert
{		
	public static WorldGenTrees cherryTrees=new WorldGenTrees(true,5,BlockIndex.cherrywood.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y),BlockIndex.cherryTreeLeaves.getDefaultState(),false);
    private CactusGenerator cactusGenerator =new CactusGenerator();

	public BiomeGenReactionDesert(BiomeProperties par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(EntityHydrolisc.class,8,1,3));
        this.topBlock    = BlockIndex.dark_sand.getDefaultState();
        this.fillerBlock = BlockIndex.dark_sand.getDefaultState();
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noiseVal) 
    {
        this.generateBiomeTerrain(world, rand, primer, x, z, noiseVal);
        int localX = Math.floorMod(x, 16);
        int localZ = Math.floorMod(z, 16);
        for (int y = 0; y < 255; ++y) 
        {
            if (primer.getBlockState(localZ, y, localX).getBlock() == Blocks.STONE) 
            {
            	primer.setBlockState(localZ, y, localX , BlockIndex.desertBlocks.getDefaultState().withProperty(BlockDesertMulti.TYPE, EnumDesertBlocks.three2));
            }
        }
    }
    
    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        BlockPos offset=pos.east(7).south(7).up(80);
        IBlockState blockState=worldIn.getBlockState(offset);
        while(blockState.getMaterial().isReplaceable())
        {
            blockState=worldIn.getBlockState(offset=offset.down());
        }

            if (rand.nextInt(64)==1 && !cherryTrees.generate(worldIn, rand, offset.up())) {

//                    System.out.println(offset);

            }
            else{
                if(!cactusGenerator.generate(worldIn, rand, offset.up()))
                {
//                    System.out.println(offset);
                    super.decorate(worldIn, rand, pos);
                };
            }

    }
}
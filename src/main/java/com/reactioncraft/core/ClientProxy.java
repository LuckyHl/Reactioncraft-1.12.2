package com.reactioncraft.core;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.entities.*;
import com.reactioncraft.items.ItemMulti;
import com.reactioncraft.mobs.common.models.ModelBee;
import com.reactioncraft.mobs.common.models.ModelHydrolisc;
import com.reactioncraft.mobs.common.models.ModelSeaCreeper;
import com.reactioncraft.mobs.common.models.ModelZombieCrawling;
import com.reactioncraft.mobs.common.renders.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy
{
    public static final String BORE_TEXTURE = "/mods/reactioncraft/textures/railcraft/";

    @Override
    public void registerItemRenderer(Item item, int meta, String id) 
    {
    	if (!(item instanceof ItemMulti))
    	{
    		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reactioncraft.MODID + ":" + id, "inventory"));
    	}
    }

	@Override
	public void setItemBlockWithMetadataInventoryModel(ItemBlock itemBlock, String... variants) {
		int m=0;
		for (String variant : variants) {
			ModelLoader.setCustomModelResourceLocation(itemBlock,m++,new ModelResourceLocation(Reactioncraft.MODID+":"+itemBlock.getRegistryName().getResourcePath()+variant,"inventory"));
		}
	}


	public void registerBlockItemRenderer(ItemBlock item, int metadataRange)
    {
		for (int i = 0; i < metadataRange; i++) {
			ModelLoader.setCustomModelResourceLocation(item,i,new ModelResourceLocation(item.getRegistryName(),"type="+i));
		}
    }


	@Override
    public void registerRenderInformation()
    {

		RenderingRegistry.registerEntityRenderingHandler(EntityBee.class,manager -> new RenderBee(new ModelBee(1),manager,0.5f));
//		RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class,manager -> new RenderStalker(new Model));

		RenderingRegistry.registerEntityRenderingHandler(EntityHydrolisc.class,manager -> new RenderHydrolisc(new ModelHydrolisc(0.2f),manager,1));
		RenderingRegistry.registerEntityRenderingHandler(EntitySeaCreeper.class,manager -> new RenderSeaCreeper(new ModelSeaCreeper(),manager,0));
//        RenderingRegistry.registerEntityRenderingHandler(EntitySeaCreeper.class, new RenderSeaCreeper(new ModelSeaCreeper(), 0.5F));
//        RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class, new RenderStalker(new ModelCreeper(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieCrawling.class,manager -> new RenderZombieCrawling(manager,new ModelZombieCrawling(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonCrawling.class,manager -> new RenderSkeletonCrawling(manager,new ModelZombieCrawling(),0.5f));
    }


}
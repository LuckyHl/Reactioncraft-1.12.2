package com.reactioncraft.api;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryRegistry
{
    public static void registerOres()
    {
        OreDictionary.registerOre("darkclay", new ItemStack(ItemIndex.sandStonePaste));
        OreDictionary.registerOre("hammer", new ItemStack(ItemIndex.hammer, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("hammer", new ItemStack(ItemIndex.bloodstoneHammer, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("hammer1", new ItemStack(ItemIndex.bloodstoneHammer, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("ingotRefinedgold", new ItemStack(ItemIndex.ingotRefinedgold, 1, 0));
        OreDictionary.registerOre("ingotBronze", new ItemStack(ItemIndex.ingotBronze, 1, 0));
        OreDictionary.registerOre("ingotCopper", new ItemStack(ItemIndex.ingotCopper, 1, 0));
        OreDictionary.registerOre("gemRuby", ItemIndex.ruby);
        OreDictionary.registerOre("oreDesertCoal", new ItemStack(BlockIndex.desertBlocks, 1, 0));
        OreDictionary.registerOre("DarkCobble", new ItemStack(BlockIndex.desertBlocks, 1, 1));
        OreDictionary.registerOre("DarkStone",  new ItemStack(BlockIndex.desertBlocks, 1, 7));
        OreDictionary.registerOre("oreDesertGold", new ItemStack(BlockIndex.desertBlocks, 1, 10));
        OreDictionary.registerOre("BloodstoneBrick", new ItemStack(BlockIndex.bloodstonebricks));
        OreDictionary.registerOre("darkStoneCarved", new ItemStack(BlockIndex.desertBlocks, 1, 7));
        OreDictionary.registerOre("darkstonebrick", new ItemStack(BlockIndex.desertBlocks, 1, 3));
        OreDictionary.registerOre("plankWood", new ItemStack(BlockIndex.cherryPlanks));
        OreDictionary.registerOre("cherryWood", new ItemStack(BlockIndex.cherrywood));
        OreDictionary.registerOre("scroll", new ItemStack(ItemIndex.scroll));
        OreDictionary.registerOre("chisel", new ItemStack(ItemIndex.flintChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel", new ItemStack(ItemIndex.copperChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel", new ItemStack(ItemIndex.goldChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel", new ItemStack(ItemIndex.diamondChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel", new ItemStack(ItemIndex.bloodstoneChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel1", new ItemStack(ItemIndex.goldChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel1", new ItemStack(ItemIndex.diamondChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel1", new ItemStack(ItemIndex.bloodstoneChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel2", new ItemStack(ItemIndex.diamondChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel2", new ItemStack(ItemIndex.bloodstoneChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("rawCorn", new ItemStack(ItemIndex.rawcorn));
        OreDictionary.registerOre("cheese", new ItemStack(ItemIndex.cheese));
        OreDictionary.registerOre("goldRod", new ItemStack(ItemIndex.goldrod));
        OreDictionary.registerOre("ingotObsidian", new ItemStack(ItemIndex.obsidianingot));
        OreDictionary.registerOre("diamondBlack", new ItemStack(ItemIndex.blackdiamond));
        OreDictionary.registerOre("ingotBloodstone", new ItemStack(ItemIndex.ingotbloodstone));
        OreDictionary.registerOre("ingotSilver", new ItemStack(ItemIndex.ingotSilver));
        OreDictionary.registerOre("ingotSuperheatediron", new ItemStack(ItemIndex.superheatedironingot));
        OreDictionary.registerOre("ironDust", new ItemStack(ItemIndex.irondust));
        OreDictionary.registerOre("shardDragonstone", new ItemStack(ItemIndex.dragonstoneshard));
        OreDictionary.registerOre("gemDragonstone", new ItemStack(ItemIndex.gemdragonstone));
        OreDictionary.registerOre("dyePurple", new ItemStack(ItemIndex.dragonstoneshard));
        //OreDictionary.registerOre("oreSilver", new ItemStack(BlockIndex.surfaceOres, 1, 4));
        OreDictionary.registerOre("bones", new ItemStack(ItemIndex.bones));
        OreDictionary.registerOre("wrappedCorn", new ItemStack(ItemIndex.wrappedcorn));
        
        //fixed Below
        OreDictionary.registerOre("oreBloodstone", new ItemStack(BlockIndex.netherOres, 1, 4));
        OreDictionary.registerOre("oreNetherBlackDiamond", new ItemStack(BlockIndex.netherOres, 1, 0));
        OreDictionary.registerOre("oreNetherDragonstone", new ItemStack(BlockIndex.netherOres, 1, 2));
        OreDictionary.registerOre("oreNetherDiamondOre", new ItemStack(BlockIndex.netherOres, 1, 1));
        OreDictionary.registerOre("oreNetherGoldOre", new ItemStack(BlockIndex.netherOres, 1, 3));
        
        //For IC2 Macerator
        //OreDictionary.registerOre("oreGold", new ItemStack(BlockIndex.netherOres, 1, 3));
        //OreDictionary.registerOre("oreGold", new ItemStack(BlockIndex.surfaceOres, 1, 3));
    }
}

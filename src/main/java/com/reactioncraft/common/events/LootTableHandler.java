package com.reactioncraft.common.events;


import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableHandler
{
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void addLoot(LootTableLoadEvent lootTableLoadEvent)
    {
		//ItemStacks
    	LootEntry coin_mould=createDefaultLootEntryMeta(ItemIndex.coinMould);
    	LootEntry ingot_mould_entry=createDefaultLootEntryMeta(ItemIndex.ingotmould);
    	
    	//Items
        LootEntry bronze_ingot=createDefaultLootEntry(ItemIndex.ingotBronze);
        LootEntry corn=createDefaultLootEntry(ItemIndex.rawcorn);
        LootEntry ruby=createDefaultLootEntry(ItemIndex.ruby);
        LootEntry ancient_fruit=createDefaultLootEntry(ItemIndex.ancientFruit);
        LootEntry ancient_flower=createDefaultLootEntry(ItemIndex.ancientFlower);
        LootEntry corn_seeds=createDefaultLootEntry(ItemIndex.cornSeed);
        LootEntry sugar_cane=createDefaultLootEntry(ItemIndex.sugarcaneItemBase);
         ResourceLocation tableName=lootTableLoadEvent.getName();
        LootTable lootTable=lootTableLoadEvent.getTable();
    	if(tableName== LootTableList.CHESTS_SPAWN_BONUS_CHEST)
        {
            LootPool lootPool=createDefaultLootPool(new LootEntry[]{ingot_mould_entry,coin_mould,ancient_fruit,ancient_flower},"bonus chest");
            lootTable.addPool(lootPool);
        }
        else if(tableName==LootTableList.CHESTS_ABANDONED_MINESHAFT)
        {
            LootPool lootPool=createDefaultLootPool(new LootEntry[]{ingot_mould_entry, bronze_ingot,coin_mould,ancient_flower,ancient_fruit,corn_seeds,sugar_cane},"mineshaft");
            lootTable.addPool(lootPool);
        }
        else if(tableName==LootTableList.CHESTS_DESERT_PYRAMID)
        {
            LootPool lootPool=createDefaultLootPool(new LootEntry[]{coin_mould,ruby},"pyramid");
            lootTable.addPool(lootPool);
        }
        else if(tableName==LootTableList.CHESTS_JUNGLE_TEMPLE)
        {
            LootPool lootPool=createDefaultLootPool(new LootEntry[]{coin_mould,ruby,ancient_flower,ancient_fruit,corn_seeds,sugar_cane},"jungle temple");
            lootTable.addPool(lootPool);
        }
        else if(tableName==LootTableList.CHESTS_VILLAGE_BLACKSMITH)
        {
            LootPool pool=createDefaultLootPool(new LootEntry[]{ingot_mould_entry, bronze_ingot,coin_mould},"blacksmith");
            lootTable.addPool(pool);
        }
        else if(tableName==LootTableList.CHESTS_SIMPLE_DUNGEON)
        {
 //           LootPool pool=createDefaultLootPool(new LootEntry[]{ingot_mould_entry})
        }
        else if(tableName==LootTableList.GAMEPLAY_FISHING)
        {
            LootEntry salmon=createDefaultLootEntry(ItemIndex.salmonRaw);
            LootEntry yellowtail=createDefaultLootEntry(ItemIndex.yellowTailRaw);
            LootPool pool=createDefaultLootPool(new LootEntry[]{salmon,yellowtail},"fishing");
            lootTable.addPool(pool);
        }
    }

    private LootPool createDefaultLootPool(LootEntry[] lootEntries,String id)
    {
        LootPool lootPool=new LootPool(lootEntries,new LootCondition[]{},new RandomValueRange(1),new RandomValueRange(0),id);
        return lootPool;
    }


    private LootEntryItem createDefaultLootEntry(Item item)
    {
        SetDamage lootFunction=new SetDamage(new LootCondition[]{},new RandomValueRange(0));
        SetCount setCount=new SetCount(new LootCondition[]{},new RandomValueRange(1));
        LootEntryItem lootEntryItem=new LootEntryItem(item,5,1,new LootFunction[]{lootFunction,setCount},new LootCondition[]{},item.getRegistryName().getResourcePath());
        return lootEntryItem;
    }
    private LootEntryItem createDefaultLootEntryMeta(Item item)
    {
        SetDamage lootFunction=new SetDamage(new LootCondition[]{},new RandomValueRange(100));
        SetCount setCount=new SetCount(new LootCondition[]{},new RandomValueRange(1));
        LootEntryItem lootEntryItem=new LootEntryItem(item,5,1,new LootFunction[]{lootFunction,setCount},new LootCondition[]{},item.getRegistryName().getResourcePath());
        return lootEntryItem;
    }
}
package com.reactioncraft.api;

import com.google.common.collect.Maps;
import com.reactioncraft.items.tools.Tools;
import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.Map.Entry;

public class FreezerRecipes
{
    private static final FreezerRecipes SMELTING_BASE = new FreezerRecipes();
    private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    /**
     * Returns an instance of FurnaceRecipes.
     */
    public static FreezerRecipes instance()
    {
        return SMELTING_BASE;
    }

    private FreezerRecipes()
    {
    	//Mirror this to add Recipes
        addSmelting(Items.WATER_BUCKET,new ItemStack(ItemIndex.iceBucket),1);
        addSmelting(Items.LAVA_BUCKET, new ItemStack(ItemIndex.obsidianBucket), 1);
        addSmelting(ItemIndex.superheatedironingot, new ItemStack(Items.IRON_INGOT), 1);
    }


    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     */
    public void addSmeltingRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        this.addSmelting(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addSmelting(Item input, ItemStack stack, float experience)
    {
        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        //if (getSmeltingResult(input) != null) { net.minecraftforge.fml.common.FMLLog.info("Ignored smelting recipe with conflicting input: " + input + " = " + stack); return; }
        this.smeltingList.put(input, stack);
        this.experienceList.put(stack, experience);
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet())
        {
            if (Tools.areItemTypesEqual(stack, entry.getKey()))
            {
                return entry.getValue().copy();
            }
        }

        return ItemStack.EMPTY;
    }
}
package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Random;

/**
 * Created on 12/31/17.
 */
public class Villagers {

    public static void register(IForgeRegistry<VillagerRegistry.VillagerProfession> registry)
    {
        VillagerRegistry.VillagerProfession regular=new VillagerRegistry.VillagerProfession(Reactioncraft.MODID+":regular",Reactioncraft.MODID+":textures/entity/rc_villager.png",Reactioncraft.MODID+":textures/entity/zombie_villager/zombie_rc_villager.png");
        VillagerRegistry.VillagerCareer career=new VillagerRegistry.VillagerCareer(regular,"career1");
        career.addTrade(1, new EntityVillager.ITradeList() {
            @Override
            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
                recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,20+random.nextInt(4)),new ItemStack(ItemIndex.coinMould)));
            }
        });
        registry.register(regular);

        //TODO set trades
        VillagerRegistry.VillagerProfession banker=new VillagerRegistry.VillagerProfession(Reactioncraft.MODID+":banker",Reactioncraft.MODID+":textures/entity/banker.png","");
        VillagerRegistry.VillagerCareer villagerCareer=new VillagerRegistry.VillagerCareer(banker,"career1");
        villagerCareer.addTrade(1, new EntityVillager.ITradeList() {
            @Override
            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
                recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD),new ItemStack(ItemIndex.coins)));
                recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,2),new ItemStack(ItemIndex.coins,1,1)));
                //and so on
            }
        });
        
        //when villager's level goes up, new trades are unlocked
        villagerCareer.addTrade(2, new EntityVillager.ITradeList() {
            @Override
            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
                recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,3),new ItemStack(ItemIndex.coins,1,2)));
            }
        });
        registry.register(banker);
    }
}

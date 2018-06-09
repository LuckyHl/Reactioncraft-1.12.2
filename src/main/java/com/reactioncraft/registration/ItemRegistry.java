package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import com.reactioncraft.items.*;
import com.reactioncraft.items.chisels.ItemBaseChisel;
import com.reactioncraft.items.chisels.ItemBloodStoneChisel;
import com.reactioncraft.items.chisels.ItemDiamondChisel;
import com.reactioncraft.items.tools.*;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;


public class ItemRegistry
{
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> registryEvent)
    {
        init(registryEvent.getRegistry());
    }

    public static void init(IForgeRegistry<Item> forgeRegistry)
    {
        //Food Related Weapons
        ItemIndex.meat_cleaver = register(new ItemBase("meat_cleaver")  .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.knife = (ItemKnife) register(new ItemKnife("Knife")         .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);

        //Bone Drop
        ItemIndex.bones         = register(new ItemBase("bones")        .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);


        //Modified Food Drops
        ItemIndex.raw_human       = register(new ItemFoodBase("raw_human",    3, 0.6f, true),forgeRegistry);
        ItemIndex.cooked_human    = register(new ItemFoodBase("cooked_human", 4, 0.8f, true),forgeRegistry);
        ItemIndex.raw_horse       = register(new ItemFoodBase("raw_horse",    3, 0.6f, true),forgeRegistry);
        ItemIndex.cooked_horse    = register(new ItemFoodBase("cooked_horse", 4, 0.8f, true),forgeRegistry);
        ItemIndex.chicken_head    = register(new ItemChickenHead("chicken_head", 3, 0.6f, true),forgeRegistry);
        ItemIndex.beef_chunk      = register(new ItemFoodBase("beef_chunk",     3, 0.6f, true),forgeRegistry);
        ItemIndex.pork_chunk      = register(new ItemFoodBase("raw_pork",     3, 0.6f, true),forgeRegistry);

        //Food Items
        ItemIndex.ancientFruit = register(new ItemFoodBase("AncientFruit", 3, 0.6f, false),forgeRegistry);
        ItemIndex.edibleFlesh = register(new ItemFoodBase("EdibleFlesh", 3, 0.6f, true),forgeRegistry);
        ItemIndex.cookedCorn         = register(new ItemFoodBase("cookedCorn", 3, 0.6f, true),forgeRegistry);
        ItemIndex.popcornseeds       = register(new ItemBase("popcornseeds"),forgeRegistry);
        ItemIndex.bagofpopcorn       = register(new ItemBase("bagofpopcorn"),forgeRegistry);
        ItemIndex.poppedbagofpopcorn = register(new ItemFoodBase("poppedbagofpopcorn", 3, 0.6f, false),forgeRegistry);
        ItemIndex.unwrappedCorn = register(new ItemFoodBase("UnwrappedCorn", 3, 0.6f, false),forgeRegistry);
        ItemIndex.rawcorn            = register(new ItemFoodBase("rawcorn", 3, 0.6f, false),forgeRegistry);
        ItemIndex.chickenNuggets = register(new ItemFoodBase("ChickenNuggets", 3, 0.6f, true),forgeRegistry);
        ItemIndex.rawNuggets = register(new ItemFoodBase("RawNuggets", 3, 0.6f, true),forgeRegistry);
        ItemIndex.slicedBread = register(new ItemFoodBase("SlicedBread", 3, 0.6f, false),forgeRegistry);
        ItemIndex.hamSandwich = register(new ItemFoodBase("HamSandwich", 3, 0.6f, true),forgeRegistry);
        ItemIndex.hamburger = register(new ItemFoodBase("Hamburger", 3, 0.6f, true),forgeRegistry);
        ItemIndex.cheeseburger = register(new ItemFoodBase("Cheeseburger", 3, 0.6f, false),forgeRegistry);
        ItemIndex.bacon = register(new ItemFoodBase("Bacon", 3, 0.6f, true),forgeRegistry);
        ItemIndex.raw_bacon          = register(new ItemFoodBase("raw_bacon", 3, 0.6f, true),forgeRegistry);
        ItemIndex.cheese             = register(new ItemFoodBase("cheese", 3, 0.6f, true),forgeRegistry);
        ItemIndex.churn              = (ItemBase) register(new ItemBase("churn"),forgeRegistry).setMaxDamage(10);
        ItemIndex.buns               = register(new ItemFoodBase("buns", 3, 0.6f, false),forgeRegistry);
        ItemIndex.salmonRaw          = register(new ItemFoodBase("salmon_raw", 3, 0.6f, false),forgeRegistry);
        ItemIndex.salmon             = register(new ItemFoodBase("salmon", 3, 0.6f, false),forgeRegistry);
        ItemIndex.yellowTailRaw = register(new ItemFoodBase("YellowTailRaw", 3, 0.6f, false),forgeRegistry);
        ItemIndex.yellowTailCooked = register(new ItemFoodBase("YellowTailCooked", 3, 0.6f, false),forgeRegistry);



        //Net Items
        ItemIndex.hilt         = register(new ItemPieceHilt("piece_hilt"),forgeRegistry);
        ItemIndex.net          = register(new ItemPieceNet("piece_net"),forgeRegistry);
        ItemIndex.complete_net = register(new ItemCompleteNet("complete_net", MaterialIndex.EnumToolMaterialNet),forgeRegistry);
        ItemIndex.creative_net = register(new ItemCompleteNet("creative_net", MaterialIndex.EnumToolMaterialNet),forgeRegistry);
        ItemIndex.caught       = register(new ItemCaughtEntity("caught"),forgeRegistry)       .setCreativeTab(null);

        //Desert Items
        ItemIndex.sandStonePaste = register(new ItemBase("SandStonePaste")     .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.uncutLBGem = register(new ItemBase("UncutLBGem")         .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.cutLBGem = register(new ItemBase("CutLBGem")           .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.uncutDBGem = register(new ItemBase("UncutDBGem")         .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.cutDBGem = register(new ItemBase("CutDBGem")           .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);

        //Make The Scroll Act as a Book...Eventually
        ItemIndex.scroll          = register(new ItemBase("scroll")             .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);

        //Special Tools
        ItemIndex.hammer = (ItemBaseHammer) register(new ItemBaseHammer("Hammer", 10)                                 .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstoneHammer  = (ItemBaseHammer) register(new ItemBaseHammer("bloodstoneHammer", 100)                       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.flintChisel = (ItemBaseChisel) register(new ItemBaseChisel("FlintChisel")      .setRegistryName("FlintChisel")      .setUnlocalizedName("FlintChisel")      .setMaxDamage(10)    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.copperChisel = (ItemBaseChisel) register(new ItemBaseChisel("CopperChisel")     .setRegistryName("CopperChisel")     .setUnlocalizedName("CopperChisel")     .setMaxDamage(35)    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.goldChisel = (ItemBaseChisel) register(new ItemBaseChisel("GoldChisel")       .setRegistryName("GoldChisel")       .setUnlocalizedName("GoldChisel")       .setMaxDamage(70)    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.diamondChisel = (ItemBaseChisel) register(new ItemDiamondChisel("DiamondChisel")       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstoneChisel = (ItemBaseChisel) register(new ItemBloodStoneChisel("BloodstoneChisel")    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);

        //Misc Items
        ItemIndex.chainLoop = register(new ItemBase("ChainLoop")           .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstoneclump       = register(new ItemBase("bloodstoneclump")     .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.goldrod               = register(new ItemBase("goldrod")             .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.obsidianingot         = register(new ItemBase("obsidianingot")       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ingotbloodstone       = register(new ItemBase("ingotbloodstone")     .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstonedust        = register(new ItemBase("bloodstonedust")      .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.blackdiamond          = register(new ItemBase("blackdiamond")        .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.dragonstoneshard      = register(new ItemBase("dragonstoneshard")    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.irondust              = register(new ItemBase("irondust")            .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ironShavings          = register(new ItemBase("iron_shavings")        .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.gemdragonstone        = register(new ItemBase("gemdragonstone")      .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.superheatedironingot  = register(new ItemBase("superheatedironingot").setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.goldDust              = register(new ItemBase("goldDust")            .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.moltenglass           = register(new ItemBase("moltenglass")         .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.iceBucket = (ItemBase) register(new ItemBase("IceBucket")     .setMaxStackSize(1).setContainerItem(Items.BUCKET) .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.obsidianBucket = (ItemBase) register(new ItemBase("ObsidianBucket").setMaxStackSize(1).setContainerItem(Items.BUCKET)  .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.mapinabottle = (ItemTossable) register(new ItemTossable("Mapinabottle") .setMaxStackSize(1)       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.shipinabottle = (ItemTossable) register(new ItemTossable("Shipinabottle").setMaxStackSize(1)       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ancientFlower = register(new ItemBase("AncientFlower")       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.wrappedcorn = register(new ItemBase("Wrappedcorn")         .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ancientSeeds =(ItemSeeds) register(new ItemBaseSeed(BlockIndex.ancientPlant, Blocks.FARMLAND,"AncientSeeds").setRegistryName(Reactioncraft.MODID,"ancientseeds").setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        Reactioncraft.proxy.registerItemRenderer(ItemIndex.ancientSeeds,0,"ancientseeds");
        ItemIndex.sugarcaneItemBase     = register(new ItemBase("sugarcaneItemBase")   .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);

        ItemIndex.cornSeed = register((ItemSeeds) new ItemBaseSeed(BlockIndex.cornBlock,Blocks.FARMLAND,"CornSeed").setRegistryName(Reactioncraft.MODID,"cornseed").setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        Reactioncraft.proxy.registerItemRenderer(ItemIndex.cornSeed,0,"cornseed");
        ItemIndex.stalksItemBase        = register(new ItemBase("stalks_item_base")      .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);

        //Door Items
        ItemIndex.woodenBookcasedoor =  register(new ItemBaseDoor("WoodenBookcasedoor", BlockIndex.woodenBookcase),forgeRegistry);
        ItemIndex.ironBookcasedoor =  register(new ItemBaseDoor("IronBookcasedoor",   BlockIndex.ironBookcasedoor),forgeRegistry);
        ItemIndex.cherry_door           =  register(new ItemBaseDoor("item_cherry_door",        BlockIndex.cherrydoor),forgeRegistry);

        //Currency Items
        ItemIndex.bag = register(new ItemBase("Bag")                .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.sack = register(new ItemBase("Sack")              .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.straw = register(new ItemBase("Straw")            .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.coinMould = (ItemBase) register(new ItemBase("CoinMould")       .setMaxDamage(15)  .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.moltengold        = (ItemBase) register(new ItemBase("moltengold")      .setContainerItem(Items.BUCKET).setMaxStackSize(1) .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.moltensilver      = (ItemBase) register(new ItemBase("moltensilver")    .setContainerItem(Items.BUCKET).setMaxStackSize(1) .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.moltencopper      = (ItemBase) register(new ItemBase("moltencopper")    .setContainerItem(Items.BUCKET).setMaxStackSize(1) .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.moltenbronze      = (ItemBase) register(new ItemBase("moltenbronze")    .setContainerItem(Items.BUCKET).setMaxStackSize(1) .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.copperbucket      = (ItemBase) register(new ItemBase("copperbucket")  .setMaxStackSize(1)       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.silverbucket      = (ItemBase) register(new ItemBase("silver_bucket") .setMaxStackSize(1)       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bronzebucket      = (ItemBase) register(new ItemBase("bronzebucket")  .setMaxStackSize(1)        .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.goldbucket        = (ItemBase) register(new ItemBase("goldbucket")    .setMaxStackSize(1)          .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ingotRefinedgold  = register(new ItemBase("ingotRefinedgold")    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ingotBronze       = register(new ItemBase("ingotBronze")         .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ingotSilver       = register(new ItemBase("ingotsilver")         .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ingotCopper       = register(new ItemBase("ingotCopper")         .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ingotmould        = (ItemBase) register(new ItemBase("ingotmould")  .setMaxDamage(15)    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.ruby              = register(new ItemBase("ruby")            .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.coins = (ItemCoin) register(new ItemCoin()    .setCreativeTab(Reactioncraft.ReactioncraftItems).setRegistryName("coin"),forgeRegistry);

        for (int i = 0; i < 16; i++) {
            Reactioncraft.proxy.registerItemRenderer(ItemIndex.coins,i,"coin"+i);
        }

        
        //Armor Items
        ItemIndex.crown = (ItemCurrencyArmor) register(new ItemCurrencyArmor("Crown",       MaterialIndex.EnumArmorMaterialKing, 0, EntityEquipmentSlot.HEAD),forgeRegistry)      .setCreativeTab(Reactioncraft.ReactioncraftItems);
        ItemIndex.kinglyChest = (ItemCurrencyArmor) register(new ItemCurrencyArmor("KinglyChest", MaterialIndex.EnumArmorMaterialKing, 0, EntityEquipmentSlot.CHEST),forgeRegistry)     .setCreativeTab(Reactioncraft.ReactioncraftItems);
        ItemIndex.kinglyLegs = (ItemCurrencyArmor) register(new ItemCurrencyArmor("KinglyLegs",  MaterialIndex.EnumArmorMaterialKing, 0, EntityEquipmentSlot.LEGS),forgeRegistry)      .setCreativeTab(Reactioncraft.ReactioncraftItems);
        ItemIndex.kinglyBoots = (ItemCurrencyArmor) register(new ItemCurrencyArmor("KinglyBoots", MaterialIndex.EnumArmorMaterialKing, 0, EntityEquipmentSlot.FEET),forgeRegistry)      .setCreativeTab(Reactioncraft.ReactioncraftItems);
        ItemIndex.diamondcrown      = (ItemExtraCrowns) register(new ItemExtraCrowns("diamondcrown",       MaterialIndex.EnumArmorMaterialCrown1, 0, EntityEquipmentSlot.HEAD)    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.emeraldcrown      = (ItemExtraCrowns) register(new ItemExtraCrowns("emeraldcrown",       MaterialIndex.EnumArmorMaterialCrown2, 0, EntityEquipmentSlot.HEAD)    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.rubycrown         = (ItemExtraCrowns) register(new ItemExtraCrowns("rubycrown",          MaterialIndex.EnumArmorMaterialCrown3, 0, EntityEquipmentSlot.HEAD)    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.darkbluecrown     = (ItemExtraCrowns) register(new ItemExtraCrowns("darkbluecrown",      MaterialIndex.EnumArmorMaterialCrown4, 0, EntityEquipmentSlot.HEAD)    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstonecrown   = (ItemExtraCrowns) register(new ItemExtraCrowns("bloodstonecrown",    MaterialIndex.EnumArmorMaterialCrown5, 0, EntityEquipmentSlot.HEAD)    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstonehelm    = register((ItemBloodStoneArmor) new ItemBloodStoneArmor(0,EntityEquipmentSlot.HEAD)  .setCreativeTab(Reactioncraft.ReactioncraftItems).setRegistryName(Reactioncraft.MODID,"bloodstonehelm").setUnlocalizedName(Reactioncraft.MODID+".bloodstonehelm"),forgeRegistry);
        ItemIndex.bloodstonechest   = register((ItemBloodStoneArmor)new ItemBloodStoneArmor(1,EntityEquipmentSlot.CHEST) .setCreativeTab(Reactioncraft.ReactioncraftItems).setRegistryName(Reactioncraft.MODID,"bloodstonechest").setUnlocalizedName(Reactioncraft.MODID+".bloodstonechest"),forgeRegistry);
        ItemIndex.bloodstonelegs    = register((ItemBloodStoneArmor) new ItemBloodStoneArmor(2,EntityEquipmentSlot.LEGS)  .setCreativeTab(Reactioncraft.ReactioncraftItems).setRegistryName(Reactioncraft.MODID,"bloodstonelegs").setUnlocalizedName(Reactioncraft.MODID+".bloodstonelegs"),forgeRegistry);
        ItemIndex.bloodstoneboots   = register((ItemBloodStoneArmor) new ItemBloodStoneArmor(3,EntityEquipmentSlot.FEET) .setCreativeTab(Reactioncraft.ReactioncraftItems).setRegistryName(Reactioncraft.MODID,"bloodstoneboots").setUnlocalizedName(Reactioncraft.MODID+".bloodstoneboots"),forgeRegistry);
        
        //Mining Helmets
        ItemIndex.miningHelmLit         = (ItemMiningHelm)     register(new ItemMiningHelm("miningHelmLit",          MaterialIndex.EnumArmorMaterialCrown6, 0, EntityEquipmentSlot.HEAD)   .setCreativeTab(null),forgeRegistry);
        ItemIndex.miningHelmUnlit       = (ItemMiningHelmUL)   register(new ItemMiningHelmUL("miningHelmUnlit",      MaterialIndex.EnumArmorMaterialCrown7, 0, EntityEquipmentSlot.HEAD)   .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        
        //Weapons
        ItemIndex.bat = register(new ItemBase("Bat")                   .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.obbySword = register(new ItemObsidanSword("ObbySword", MaterialIndex.EnumToolMaterialObby),forgeRegistry);
        ItemIndex.obbyPick = (ItemObsidanPick)  register(new ItemObsidanPick("ObbyPick", MaterialIndex.EnumToolMaterialObby)                              .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.obbyShovel = (ItemObsidianShovel) register(new ItemObsidianShovel("ObbyShovel", MaterialIndex.EnumToolMaterialObby)                       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.obbyAxe = (ItemObsidianAxe) register(new ItemObsidianAxe("ObbyAxe", MaterialIndex.EnumToolMaterialObby)                                .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.obbyHoe = (ItemObsidianHoe) register(new ItemObsidianHoe("ObbyHoe", MaterialIndex.EnumToolMaterialObby)                                .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstoneSword = (ItemBloodstoneSword) register(new ItemBloodstoneSword("BloodstoneSword", MaterialIndex.EnumToolMaterialBloodstone)          .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstonePick = (ItemBloodstonePick) register(new ItemBloodstonePick("BloodstonePick", MaterialIndex.EnumToolMaterialBloodstone)             .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstoneShovel = (ItemBloodstoneShovel) register(new ItemBloodstoneShovel("BloodstoneShovel", MaterialIndex.EnumToolMaterialBloodstone)       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstoneAxe = (ItemBloodstoneAxe) register(new ItemBloodstoneAxe("BloodstoneAxe", MaterialIndex.EnumToolMaterialBloodstone)                .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstoneHoe = (ItemBloodstoneHoe) register(new ItemBloodstoneHoe("BloodstoneHoe", MaterialIndex.EnumToolMaterialBloodstone)                .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstoneDiamondPick = (ItemBlackdiamondPick) register(new ItemBlackdiamondPick("BloodstoneDiamondPick", MaterialIndex.EnumToolMaterialBloodstone2) .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        
        //Weapon Parts
        ItemIndex.bloodstoneBlade = register(new ItemBase("BloodstoneBlade")       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.goldenSwordFragment = register(new ItemBase("GoldenSwordFragment")   .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.unbindedSword = register(new ItemBase("UnbindedSword")         .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);


        //Ores
        ItemIndex.meltedventinite  = register(new ItemBase("meltedventinite") .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.meltedwizimite   = register(new ItemBase("meltedwizimite")  .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);

        //Made For Other Mod Items
        ItemIndex.scoop            = register(new ItemBase("scoop")           .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.bloodstoneframe  = register(new ItemBase("bloodstoneframe") .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.honeycomb        = register(new ItemBase("honeycomb")       .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.pollencomb       = register(new ItemBase("pollencomb")      .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
        ItemIndex.blackdiamondbore = register(new ItemBase("Blackdiamondbore").setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
<<<<<<< HEAD
        ItemIndex.bloodstonebore = register(new ItemBase("Bloodstonebore")    .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);
=======
        ItemIndex.bloodstonebore = register(new ItemBase("Bloodstonebore")  .setCreativeTab(Reactioncraft.ReactioncraftItems),forgeRegistry);

        ItemIndex.rcendereye=register((ItemEndEye) new ItemEndEye().setCreativeTab(Reactioncraft.ReactioncraftItems).setRegistryName("end_eye").setUnlocalizedName("reactioncraft.end_eye"),forgeRegistry);
        Reactioncraft.proxy.registerItemRenderer(ItemIndex.rcendereye,0,"end_eye");
>>>>>>> f0aef18053091300e96805a3fdf8b31fad47382e
    }

   
    private static <T extends Item> T register(T item,IForgeRegistry<Item> forgeRegistry)
    {
        forgeRegistry.register(item);

        if (item instanceof ItemModelProvider) 
        {
            ((ItemModelProvider) item).registerItemModel();
        }

        return item;
    }
}
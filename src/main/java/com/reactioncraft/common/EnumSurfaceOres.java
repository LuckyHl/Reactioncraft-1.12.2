package com.reactioncraft.common;

import net.minecraft.util.IStringSerializable;

public enum EnumSurfaceOres implements IStringSerializable
{
    DESERT_COAL(0, 0,   "0",       "0"),
    DARK_BLUE_GEM(1, 1,   "1",       "1"),
    LIGHT_BLUE_GEM(2, 2,   "2",       "2"),
    DESERT_GOLD(3, 3,   "3",       "3"),
    SILVER(4, 4,   "4", 	   "4"),
    COPPER(5, 5,   "5",       "5");
//    three1		(6, 6,   "three1",     "three1"),
//    three2		(7, 7,   "three2",     "three2"),
//    three3		(8, 8,   "three3",     "three3"),
//    four1		(9, 9, 	 "four1",      "four1"),
//    four2		(10, 10, "four2",      "four2"),
//    four3		(11, 11, "four3",      "four3"),
//    five1       (12, 12, "five1",      "five1"),
//    five2		(13, 13, "five2",      "five2"),
//    five3		(14, 14, "five3",      "five3"),
//    six1		(15, 15, "six1",       "six1");

    private static final EnumSurfaceOres[] META_LOOKUP = new EnumSurfaceOres[values().length];
    private static final EnumSurfaceOres[] DYE_DMG_LOOKUP = new EnumSurfaceOres[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumSurfaceOres(int meta, int dyeDamage, String name, String unlocalizedName)
    {
        this.meta = meta;
        this.dyeDamage = dyeDamage;
        this.name = name;
        this.unlocalizedName = unlocalizedName;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public int getDyeDamage()
    {
        return this.dyeDamage;
    }

    
    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public static EnumSurfaceOres byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumSurfaceOres byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String toString()
    {
        return this.unlocalizedName;
    }

    public String getName()
    {
        return this.name;
    }

    
    static
    {
        for (EnumSurfaceOres type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}
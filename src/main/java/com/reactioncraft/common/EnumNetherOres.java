package com.reactioncraft.common;

import net.minecraft.util.IStringSerializable;

public enum EnumNetherOres implements IStringSerializable
{
    BLACKDIAMOND(0, 0,   "0",       "0"),
    DIAMOND(1, 1,   "1",       "1"),
    DRAGONSTONE(2, 2,   "2",       "2"),
    GOLD(3, 3,   "3",       "3"),
    BLOODSTONE(4, 4,   "4", 	   "4");

    private static final EnumNetherOres[] META_LOOKUP = new EnumNetherOres[values().length];
    private static final EnumNetherOres[] DYE_DMG_LOOKUP = new EnumNetherOres[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumNetherOres(int meta, int dyeDamage, String name, String unlocalizedName)
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

    public static EnumNetherOres byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumNetherOres byMetadata(int meta)
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
        for (EnumNetherOres type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}
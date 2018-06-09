package com.reactioncraft.common;

import net.minecraft.util.IStringSerializable;

public enum EnumGlass implements IStringSerializable
{
    WHITE(0, 0,   "0", "one1"),
    ORANGE(1, 1,   "1", "one2"),
    MAGENTA(2, 2,   "2",  "one3"),
    LIGHT_BLUE(3, 3,   "3", "two1"),
    YELLOW(4, 4,   "4", "two2"),
    LIME(5, 5,   "5",   "two3"),
    PINK(6, 6,   "6",  "three1"),
    GRAY(7, 7,   "7",   "three2"),
    SILVER(8, 8,   "8", "three3"),
    CYAN(9, 9, 	 "9",  "four1"),
    PURPLE(10, 10, "10",   "four2"),
    BLUE(11, 11, "11",  "four3"),
    BROWN(12, 12, "12",  "five1"),
    GREEN(13, 13, "13",  "five2"),
    RED	(14, 14, "14","five3"),
    BLACK(15, 15, "15","six1");

    private static final EnumGlass[] META_LOOKUP = new EnumGlass[values().length];
    private static final EnumGlass[] DYE_DMG_LOOKUP = new EnumGlass[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumGlass(int meta, int dyeDamage, String name, String unlocalizedName)
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

    public static EnumGlass byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumGlass byMetadata(int meta)
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
        for (EnumGlass type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}
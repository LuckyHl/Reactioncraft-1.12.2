package com.reactioncraft.items;

import javax.annotation.Nullable;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMiningHelm extends ItemExtraCrowns
{
	protected int enchantability;
    protected String name;
    
    /** The EnumArmorMaterial used for this ItemArmor */
    private final ArmorMaterial material;

    public ItemMiningHelm(String name, ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4)
    {
        super(name, par2EnumArmorMaterial, par3, par4);
        this.material = par2EnumArmorMaterial;
        this.enchantability = 30;
        this.name = name;
		this.setUnlocalizedName(Reactioncraft.MODID + "." + this.name);
		this.setMaxStackSize(1);
    }
    
    /**
	 * Called when a Block is right-clicked with this Item
	 */
    /**
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);
		IBlockState blockState = worldIn.getBlockState(pos);
		Material material = blockState.getMaterial();

		if(stack.getItem() == ItemIndex.miningHelmLit)
		{
			//if(blockState.getBlock() == Blocks.WATER.getDefaultState() || blockState.getBlock() == Blocks.FLOWING_WATER.getDefaultState())
			if (material == Material.WATER && ((Integer)blockState.getValue(BlockLiquid.LEVEL)).intValue() == 0)
			{
				ItemStack unlit = new ItemStack(ItemIndex.miningHelmUnlit);
				player.addItemStackToInventory(unlit);
				player.inventory.deleteStack(player.getHeldItem(hand));
				return EnumActionResult.PASS;
			}
		}
		return EnumActionResult.PASS;
	}
    
	**/
	
    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.enchantability;
    }
}
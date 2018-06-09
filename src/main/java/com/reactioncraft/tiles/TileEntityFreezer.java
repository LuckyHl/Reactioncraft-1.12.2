package com.reactioncraft.tiles;

import com.reactioncraft.ItemHandler;
import com.reactioncraft.api.FreezerRecipes;
import com.reactioncraft.blocks.machines.BlockFreezer;
import com.reactioncraft.registration.instances.BlockIndex;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
<<<<<<< HEAD
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
=======
import net.minecraft.tileentity.TileEntityFurnace;
>>>>>>> f0aef18053091300e96805a3fdf8b31fad47382e
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class TileEntityFreezer extends TileEntityBase implements ITickable
{

    public int fuelburntime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    public int currentItemBurnTime;
    public int cookTime;
    public int totalCookTime;
    private String customName;
    public static final int PROCESS_TIME=200;
    public ItemHandler bottomHandler=new ItemHandler(1,this);
    public ItemHandler itemHandler=new ItemHandler(2,this);

    static final String ITEMS="Items",OUTPUT="Output";


    /**
     * Get the name of this object. For players this returns their username
     */
    public ITextComponent getDisplayName()
    {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentString("Freezer");
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.customName = p_145951_1_;
    }


    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);



        this.fuelburntime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
        bottomHandler.deserializeNBT(compound.getCompoundTag(OUTPUT));
        itemHandler.deserializeNBT(compound.getCompoundTag(ITEMS));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", this.fuelburntime);
        compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);

        compound.setTag(OUTPUT, bottomHandler.serializeNBT());
        compound.setTag(ITEMS,itemHandler.serializeNBT());

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }



    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        if(!world.isRemote)
        {
            IBlockState machineState=world.getBlockState(pos);
            ItemStack fuel=itemHandler.getStackInSlot(1);
            if(!fuel.isEmpty())
            {
                fuelburntime=getItemBurnTime(fuel);
                world.addBlockEvent(pos, BlockIndex.freezer,0,fuelburntime);
            }

            ItemStack input=itemHandler.getStackInSlot(0);
            if(!input.isEmpty())
            {
                ItemStack result= FreezerRecipes.instance().getSmeltingResult(input);
                if(!result.isEmpty())
                {
                    if(currentItemBurnTime>0)
                    {
                        totalCookTime++;
                        if(totalCookTime>=PROCESS_TIME)
                        {
                            if(bottomHandler.insertItem(0,result,true).isEmpty())
                            {
                                bottomHandler.insertItem(0,result,false);
                                input.shrink(1);
                                totalCookTime=0;
                            }
                        }
                        world.addBlockEvent(pos,BlockIndex.freezer,1,totalCookTime);
                        currentItemBurnTime--;
                        if(currentItemBurnTime>0 && !machineState.getValue(BlockFreezer.STATE))world.setBlockState(pos,machineState.withProperty(BlockFreezer.STATE,true));
                    }
                    else{
                        if(!fuel.isEmpty()) {
                            currentItemBurnTime = getItemBurnTime(fuel);
                            fuel.shrink(1);
                        }
                        if(currentItemBurnTime>0) world.setBlockState(pos,machineState.withProperty(BlockFreezer.STATE,true));
                        else {
                            if(machineState.getValue(BlockFreezer.STATE)) world.setBlockState(pos,machineState.withProperty(BlockFreezer.STATE,false));
                        }
                    }
                    world.addBlockEvent(pos,BlockIndex.freezer,2,currentItemBurnTime);
                }
            }
            else{
                if(machineState.getValue(BlockFreezer.STATE)) world.setBlockState(pos,machineState.withProperty(BlockFreezer.STATE,false));
            }
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if(id==0)
        {
            fuelburntime=type;
            return true;
        }
        else if(id==1)
        {
            totalCookTime=type;
            return true;
        }
        else if(id==2)
        {
            currentItemBurnTime=type;
            return true;
        }
        return super.receiveClientEvent(id, type);
    }

    public static int getItemBurnTime(ItemStack stack)
    {
        return TileEntityFurnace.getItemBurnTime(stack);
    }

    public static boolean isItemFuel(ItemStack stack)
    {

        return getItemBurnTime(stack) > 0;
    }




    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) bottomHandler;
            else
                return (T) itemHandler;
        return super.getCapability(capability, facing);
    }
}
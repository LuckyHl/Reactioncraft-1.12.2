package com.reactioncraft.tiles;

import com.reactioncraft.api.BrickOvenRecipes;
import com.reactioncraft.itemhandlers.BrickOvenItemHandler;
import com.reactioncraft.registration.instances.BlockIndex;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class TileEntityBrickOven extends TileEntityBase implements ITickable
{
    /** The number of ticks that the furnace will keep burning */
    public int burnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    public int currentItemBurnTime;
    public int totalCookTime;
    private String customName;
    static final String ITEMS="Item handler",CURRENT_TIME="Current burn time",OUTPUT="Output handler";
    public BrickOvenItemHandler itemHandler=new BrickOvenItemHandler(2,this);
    public ItemHandler outputHandler=new ItemHandler(1,this);
    public static final short OVEN_PROCESS_TIME=200;
    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.furnace";
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
        itemHandler.deserializeNBT(compound.getCompoundTag(ITEMS));

        outputHandler.deserializeNBT(compound.getCompoundTag(OUTPUT));
        this.burnTime = compound.getInteger("BurnTime");
//        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = compound.getInteger(CURRENT_TIME);

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", this.burnTime);
//        compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);
        compound.setInteger(CURRENT_TIME,currentItemBurnTime);
        compound.setTag(ITEMS,itemHandler.serializeNBT());
        compound.setTag(OUTPUT,outputHandler.serializeNBT());
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

        if (!this.world.isRemote)
        {

            ItemStack fuel= itemHandler.getStackInSlot(1);
            if(!fuel.isEmpty()) {
                burnTime=getItemBurnTime(fuel);
                world.addBlockEvent(pos,BlockIndex.brickOvenIdle,2,burnTime);
            }
            ItemStack input=itemHandler.getStackInSlot(0);
            if(!input.isEmpty() )
            {
                ItemStack result=BrickOvenRecipes.instance().getSmeltingResult(input);
                if(!result.isEmpty())
                {
                    if(currentItemBurnTime>0) {
                        totalCookTime++;
                        if (totalCookTime >= OVEN_PROCESS_TIME) {
                            if (outputHandler.insertItem(0, result, true).isEmpty()) {
                                outputHandler.insertItem(0, result, false);
                                input.shrink(1);
                                totalCookTime = 0;
                            }
                        }
                        world.addBlockEvent(pos, BlockIndex.brickOvenIdle, 0, totalCookTime);
                        currentItemBurnTime--;
                    }
                    else{
                        currentItemBurnTime=getItemBurnTime(fuel);
                        fuel.shrink(1);
                    }
                    world.addBlockEvent(pos,BlockIndex.brickOvenIdle,1,currentItemBurnTime);
                }
            }

        }

    }


    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString("Brick Oven");
    }

//    /**
//     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
//     */
//    public void smeltItem()
//    {
//        if (this.canSmelt())
//        {
//            ItemStack input= itemHandler.getStackInSlot(0), output= outputHandler.getStackInSlot(2);
//            ItemStack result = BrickOvenRecipes.instance().getSmeltingResult(input);
//
//            if (Tools.areItemTypesEqual(output, result))
//            {
//                output.setCount(output.getCount()+result.getCount());
//                // Forge BugFix: Results may have multiple items
//            }
//
//            if (input.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && input.getMetadata() == 1 && itemHandler.getStackInSlot(1).getItem() == Items.BUCKET)
//            {
//                itemHandler.setStackInSlot(1,new ItemStack(Items.WATER_BUCKET));
//            }
//           input.shrink(1);
//        }
//    }


    public static int getItemBurnTime(ItemStack stack)
    {
        return TileEntityFurnace.getItemBurnTime(stack);
    }

    public static boolean isItemFuel(ItemStack stack)
    {

        return getItemBurnTime(stack) > 0;
    }


    @Override
    public boolean receiveClientEvent(int id, int type) {
        if(id==0){
            totalCookTime=type;
            return true;
        }
        else if(id==1)
        {
            currentItemBurnTime=type;
            return true;
        }
        else if(id==2)
        {
            burnTime=type;
            return true;
        }
        return super.receiveClientEvent(id, type);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == EnumFacing.UP)
                return (T) itemHandler;
            else if (facing == EnumFacing.DOWN) return (T) outputHandler;
        }
        return super.getCapability(capability, facing);
    }
}
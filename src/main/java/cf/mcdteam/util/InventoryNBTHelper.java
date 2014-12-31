package cf.mcdteam.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Reads/Writes an inventory to a NBT Tag
 * Finished BCWADSWORTH on 12/31/14
 */
public class InventoryNBTHelper
{
	
	public static void readItemStackArrayFromNBT(ItemStack[] inventory, NBTTagCompound tag)
	{
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			
			if (b0 >= 0 && b0 < inventory.length)
			{
				inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}
	
	public static void writeItemStackArrayToNBT(ItemStack[] inventory, NBTTagCompound tag)
	{
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < inventory.length; ++i)
		{
			if (inventory[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tag.setTag("Items", nbttaglist);
	}
	
	//Prevent Instanciantion
	private InventoryNBTHelper(){}
}

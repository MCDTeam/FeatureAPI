package teamUnknown.immersion.core.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Stack
{
	public static ItemStack S (Item item)
	{
		return new ItemStack (item);
	}
	public static ItemStack S (Block block)
	{
		return new ItemStack (block);
	}
	public static ItemStack S (Item item, int amount)
	{
		return new ItemStack (item, amount, 0);
	}
	public static ItemStack S (Block block, int amount)
	{
		return new ItemStack (block, amount, 0);
	}
	public static ItemStack SM (Item item, int metadata)
	{
		return new ItemStack (item, 1, metadata);
	}
	public static ItemStack SM (Block block, int metadata)
	{
		return new ItemStack (block, 1, metadata);
	}
	public static ItemStack S (Item item, int metadata, int amount)
	{
		return new ItemStack (item, amount, metadata);
	}
	public static ItemStack S (Block block, int metadata, int amount)
	{
		return new ItemStack (block, amount, metadata);
	}
}

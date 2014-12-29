package cf.mcdteam.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * This adds world functions to the BlockPos function of Minecraft
 * Finished BCWADSWORTH on 12/28/14
 */
public class WorldBlockPosition extends BlockPos
{
	
	private World world;
	
	public World getWorld()
	{
		return this.world;
	}
	
	public WorldBlockPosition(World world, int x, int y, int z)
	{
		super(x, y, z);
		this.world = world;
	}
	
	public WorldBlockPosition(World world, BlockPos position)
	{
		super(position.getX(), position.getY(), position.getZ());
		this.world = world;
	}
	
	public Block getBlock()
	{
		return world.getBlockState(this).getBlock();
	}
	
	public IBlockState getState()
	{
		return world.getBlockState(this);
	}
	
	public void setBlock(IBlockState state)
	{
		world.setBlockState(this, state);
	}
	
	public void setAir()
	{
		world.setBlockToAir(this);
	}
	
	public boolean isAir()
	{
		return world.isAirBlock(this);
	}
	
	public boolean is(Block block)
	{
		return world.getBlockState(this).getBlock() == block;
	}
	
	public static WorldBlockPosition topBlock(World world, int x, int z)
	{
		return new WorldBlockPosition(world, world.getTopSolidOrLiquidBlock(new BlockPos(x, 1, 0)));
	}
}

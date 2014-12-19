package cf.mcdteam.coreFeature.oreGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import teamUnknown.immersion.core.feature.object.ImmersionBlock;
import teamUnknown.immersion.features.metallurgyFeature.items.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOre extends ImmersionBlock
{
	private OreGenerator generator;
	
	@SideOnly(Side.CLIENT)
	protected IIcon texture;

	public BlockOre(String type, Float hardness, int picklevel)
	{
		super("ore" + type, Material.rock);
		setHardness(hardness);
		setStepSound(Block.soundTypeStone);
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("pickaxe", picklevel);
	}

    /**
     * Used so the client doesnt crash
     * @param type
     */
    public BlockOre(String type){

        super("ore" + type, Material.rock);
        setHardness(2.0F);
        setStepSound(Block.soundTypeStone);
        setCreativeTab(CreativeTabs.tabBlock);
        setHarvestLevel("pickaxe", 3);
    }
	
	public void registerForGeneration(int chunkdensity, int YMin, int YMax, int sizeMin, int sizeMax, int dimension)
	{
		this.generator = new OreGenerator(chunkdensity, YMin, YMax, sizeMin, sizeMax, dimension, this);
	}
	
	protected void generate(World world, Random random, int chunkX, int chunkZ)
	{
		generator.generateForChunk (world, random, chunkX, chunkZ);
	}
	
	private class OreGenerator
	{
		private int chunkdensity;
		private int YMin;
		private int YMax;
		private int sizeMin;
		private int sizeMax;
		private int dimension;
		private BlockOre ore;
		
		public OreGenerator (int chunkdensity, int YMin, int YMax, int sizeMin, int sizeMax, int dimension, BlockOre ore)
		{
			this.chunkdensity = chunkdensity;
			this.YMin = YMin;
			this.YMax = YMax;
			this.sizeMin = sizeMin;
			this.sizeMax = sizeMax;
			this.dimension = dimension;
			this.ore = ore;
		}
		
		public void generateForChunk (World world, Random random, int chunkX, int chunkZ)
		{
			if (dimension == world.provider.dimensionId)
			{
				for (int i = 0; i < chunkdensity; i++)
				{
					new WorldGenMinable(ore, (sizeMin + random.nextInt(sizeMax - sizeMin))).generate(world, random, (chunkX + random.nextInt(16)), (YMin + random.nextInt(YMax - YMin)), (chunkZ + random.nextInt(16)));
				}
			}
		}
	}
}

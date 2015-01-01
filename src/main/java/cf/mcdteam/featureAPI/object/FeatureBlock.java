package cf.mcdteam.featureAPI.object;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Reminder: You must register your block with your own ObjectRegister to have it
 * Registered into the game. Failure to do this will cause nothing to happen.
 */
public class FeatureBlock extends Block implements IFeatureObject
{
    private static final Material DEFAULT_MATERIAL = Material.rock;

    protected FeatureBlock() {
        this(null, DEFAULT_MATERIAL);
    }

    protected FeatureBlock(Material material) {
        this(null, material);
    }

    protected FeatureBlock(String name) {
        this(name, DEFAULT_MATERIAL);
    }

    protected FeatureBlock(String name, Material material) {
        super(material);

        if (name == null)
            name = this.inferName();
        
        this.setUnlocalizedName(name);
    }

    private String inferName() 
    {
        return this.getClass().getSimpleName();
    }

	@Override
	public void craftingRegistration() 
	{

	}

	@Override
	public void forgeOreDict() 
	{

	}
}

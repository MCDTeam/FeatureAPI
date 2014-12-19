package teamUnknown.immersion.core.feature.object;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import teamUnknown.immersion.core.ImmersionCreativeTab;
import teamUnknown.immersion.core.providers.resources.ResourceProvider;

/**
 * Reminder: You must register your block with your own ObjectRegister to have it
 * Registered into the game. Failure to do this will cause nothing to happen.
 */
public class ImmersionBlock extends Block implements IImersionObject
{
    private static final Material DEFAULT_MATERIAL = Material.rock;

    protected ImmersionBlock() {
        this(null, DEFAULT_MATERIAL);
    }

    protected ImmersionBlock(Material material) {
        this(null, material);
    }

    protected ImmersionBlock(String name) {
        this(name, DEFAULT_MATERIAL);
    }

    protected ImmersionBlock(String name, Material material) {
        super(material);

        if (name == null)
            name = this.inferName();

        this.setBlockName(ResourceProvider.getBlockName(name));
        this.setBlockTextureName(ResourceProvider.getTextureName(name));

        //Sets a basic creative tab so that it is accessible. Calling this in the constuructor overrides this
        this.setCreativeTab(ImmersionCreativeTab.tabGeneral);
        //this.setCreativeTab(CreativeTabs.tabBlock);
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

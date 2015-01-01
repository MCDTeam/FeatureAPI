package cf.mcdteam.featureAPI.object;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class FeatureItem extends Item implements IFeatureObject
{
    public FeatureItem(String name) {

        if (name == null)
            name = this.inferName();
        this.setUnlocalizedName(name);
    }

    private String inferName() {
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

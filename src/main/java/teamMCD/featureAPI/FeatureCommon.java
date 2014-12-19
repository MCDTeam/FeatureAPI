package teamUnknown.immersion.core.feature;

import net.minecraftforge.common.MinecraftForge;
import teamUnknown.immersion.core.feature.Feature;
import teamUnknown.immersion.core.feature.Feature.FeatureElement;
import teamUnknown.immersion.core.feature.configuration.IConfigurationProvider;
import teamUnknown.immersion.core.feature.IFeature;
import teamUnknown.immersion.features.spawnFeature.SpawnEventListener;

/**
 *  Feature that forces player to spawn randomly
 */
@Feature(name = "null", version = "null")
public class FeatureCommon implements IFeature 
{
	@Override
	public void preSetup() 
	{
		
	}

	@Override
	public IFeature[] setup() 
	{
		return null;
	}

	@Override
	public void postSetup() 
	{
		
	}
}

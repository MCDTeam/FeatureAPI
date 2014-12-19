package cf.mcdteam.coreFeature.oreGen;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import teamUnknown.immersion.core.feature.Feature;
import teamUnknown.immersion.core.feature.Feature.FeatureElement;
import teamUnknown.immersion.core.feature.Feature.FeatureElement.Element;
import teamUnknown.immersion.core.feature.FeatureCommon;
import teamUnknown.immersion.core.feature.configuration.IConfigurationProvider;


@Feature(name = "Ore Generation", version = "1.0", isBase = true)
public class FeatureOreGen extends FeatureCommon
{
	public boolean dirt = true;
	public OreGeneration generator;
	
	@FeatureElement(Element.CONFIGURATION)
	public void configuration(IConfigurationProvider config)
	{
		dirt = config.getConfig("DirtGeneration", "Dirt Generation Underground", dirt);
	}
	
	@FeatureElement(Element.EVENTBUS_ORE)
	public void register()
	{
		OreGeneration target = new OreGeneration();
		
		MinecraftForge.ORE_GEN_BUS.register(target);
		GameRegistry.registerWorldGenerator(target, 0);
		generator = target;
	}
}

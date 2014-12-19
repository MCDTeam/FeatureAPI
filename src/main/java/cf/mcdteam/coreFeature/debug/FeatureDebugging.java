package cf.mcdteam.coreFeature.debug;

import teamUnknown.immersion.core.feature.Feature;
import teamUnknown.immersion.core.feature.FeatureCommon;
import teamUnknown.immersion.core.feature.configuration.IConfigurationProvider;
import teamUnknown.immersion.coreFeatures.debug.items.DebugItems;

@Feature(name = "Debugging", version = "1.0", isBase = true)
public class FeatureDebugging extends FeatureCommon{

    @Feature.FeatureElement(Feature.FeatureElement.Element.CONFIGURATION)
    public void registerConfiguration(IConfigurationProvider cfg) {

    }

    @Feature.FeatureElement(Feature.FeatureElement.Element.PREINITIALIZATION)
    public void runFeaturePreInitialization() {

        DebugItems.init();
    }


}

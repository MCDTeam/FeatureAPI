package cf.mcdteam.coreFeature.social;

import teamUnknown.immersion.core.feature.Feature;
import teamUnknown.immersion.core.feature.FeatureCommon;
import teamUnknown.immersion.core.feature.configuration.IConfigurationProvider;
import teamUnknown.immersion.core.network.message.MessageSocialPacket;

@Feature(name = "Social", version = "0.1", isBase = true)
public class FeatureSocial extends FeatureCommon {

    @Feature.FeatureElement(Feature.FeatureElement.Element.CONFIGURATION)
    public void initConfiguration(IConfigurationProvider config){

    }

    @Feature.FeatureElement(Feature.FeatureElement.Element.PREINITIALIZATION)
    public void preInit(){

    }

    @Feature.FeatureElement(Feature.FeatureElement.Element.INTITIALIZATION)
    public void init(){

        SocialRegistry.initialize();

        //Network Handler
        MessageSocialPacket.initialize();
    }

    @Feature.FeatureElement(Feature.FeatureElement.Element.POSTINITIALIZATION)
    public void postInit(){

    }

}

package cf.mcdteam.coreFeature.versionCheck;

import teamUnknown.immersion.core.feature.Feature;
import teamUnknown.immersion.core.feature.Feature.FeatureData;
import teamUnknown.immersion.core.feature.Feature.FeatureData.Data;
import teamUnknown.immersion.core.feature.Feature.FeatureElement;
import teamUnknown.immersion.core.feature.Feature.FeatureElement.Element;
import teamUnknown.immersion.core.feature.FeatureCommon;
import teamUnknown.immersion.core.feature.configuration.IConfigurationProvider;
import teamUnknown.immersion.core.meta.ModMetadata;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Feature that allows the client to check for updates and display the link to the user
 */
@Feature(name = "Version Checker", version = "1.0", isBase = true)
public class FeatureVersion extends FeatureCommon {

    public static boolean enableVersionChecker;

    @FeatureData(Data.NONDATA)
    public static String LAST_DISCOVERED_VERSION;

    @FeatureElement(Element.CONFIGURATION)
    public void registerConfiguration(IConfigurationProvider cfg) {
        this.enableVersionChecker = cfg.getConfig("Enable version checker", "Enable version checker", true);
        //this.LAST_DISCOVERED_VERSION = context.getConfiguration().
    }

    @FeatureElement(Element.PREINITIALIZATION)
    public void runFeaturePreInitialization() {
        VersionHelper.execute();
    }

    @FeatureElement(Element.INTITIALIZATION)
    public void runFeatureInitialization() {
        if(this.enableVersionChecker)
        {
            FMLInterModComms.sendRuntimeMessage(ModMetadata.MOD_ID, "VersionChecker", "addVersionCheck", ModMetadata.VERSION_CHECKER_REMOTE_URL);
        }
    }
}

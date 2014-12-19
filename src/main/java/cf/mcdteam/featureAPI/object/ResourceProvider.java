package cf.mcdteam.featureAPI.object;

import teamUnknown.immersion.core.meta.ModMetadata;

/**
 *
 */
public class ResourceProvider {
//TODO: Get feature name into names
    protected static String getNameForRegistration(String name){
        return ModMetadata.MOD_ID + "." + name;
    }

    public static String getItemName(String name){
        return getNameForRegistration(name);
    }

    public static String getBlockName(String name){
        return getNameForRegistration(name);
    }

    public static String getLocalizationStringName(String name){
        return getNameForRegistration(name);
    }

    public static String getTextureName(String name){
        return ModMetadata.MOD_ID + ":" + name;
    }
}

package cf.mcdteam.coreFeature.debug.items;

import cpw.mods.fml.common.registry.GameRegistry;
import teamUnknown.immersion.core.feature.object.ImmersionItem;

public class DebugItems {

    public static ImmersionItem itemAdminDebug = new itemAdminDebug();

    public static void init(){
        GameRegistry.registerItem(itemAdminDebug, "itemAdminDebug");
    }
}

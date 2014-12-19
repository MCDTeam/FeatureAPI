package teamUnknown.immersion.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import teamUnknown.immersion.core.meta.ModMetadata;
import teamUnknown.immersion.features.metallurgyFeature.items.ModBlocks;

public class ImmersionCreativeTab {

    public static CreativeTabs tabGeneral = new TabGeneral(CreativeTabs.getNextID(), ModMetadata.MOD_ID);

    public static class TabGeneral extends CreativeTabs {

        public TabGeneral(int tab, String id){
            super(tab, id);
        }

        @Override
        public Item getTabIconItem() {
            return ModBlocks.gemEnd;
        }
    }
}

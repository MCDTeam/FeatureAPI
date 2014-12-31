package cf.mcdteam.featureAPI.creativeTab;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Creates Creative Tabs and Keeps them for Future
 * Finished BCWADSWORTH on 12/31/14
 */
public class CreativeTabFactory
{
	//All of the creative tabs created by the factory
	public static ArrayList<CreativeTabs> tabs = new ArrayList<CreativeTabs>();
	
	public static void newCT(String id, Item icon)
	{
		tabs.add(new FactoryTab(id, icon));
	}
	
	public static CreativeTabs get(String id)
	{
		for (CreativeTabs tab : tabs)
		{
			if (tab.getTabLabel() == id)
			{
				return tab;
			}
		}
		return null;
	}
	
	//Prevent Intanceization
	private CreativeTabFactory(){}
    
	protected static class FactoryTab extends CreativeTabs {

		private Item icon;
		protected FactoryTab(String id, Item icon)
		{
			super(CreativeTabs.getNextID(), id);
			this.icon = icon;
		}
		
		@Override
		public Item getTabIconItem()
		{
			return icon;
		}
    }
}

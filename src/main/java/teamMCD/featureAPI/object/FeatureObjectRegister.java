package teamUnknown.immersion.core.feature.object;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import teamUnknown.immersion.Immersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class FeatureObjectRegister {
    private final HashMap<String, ImmersionBlock> blocks;
    private final HashMap<String, ImmersionItem> items;

    public FeatureObjectRegister()
    {
        blocks = new HashMap<String, ImmersionBlock>();
        items = new HashMap<String, ImmersionItem>();
        startRegistry();
    }

    /**
     * This is where you call all your blocks and items to be registered
     */
    public abstract void startRegistry();
    
    /**
     * Adds a block to the registry
     * @param block
     */
    public void register(ImmersionBlock block)
    {
    	blocks.put(block.getUnlocalizedName().substring(15), block);
    }

    /**
     * Adds a item to the registry
     * @param item
     */
    public void register(ImmersionItem item)
    {
        items.put(item.getUnlocalizedName().substring(5), item);
    }

    /**
     * Adds all blocks from the activeBlocks map to the GameRegistry
     */
    public void registerToGame()
    {
        for(Entry<String, ImmersionBlock> entry : blocks.entrySet()){
            String name = entry.getKey();
            Block block = entry.getValue();

            GameRegistry.registerBlock(block, name);
        }

        for(Entry<String, ImmersionItem> entry : items.entrySet())
        {
            String name = entry.getKey();
            Item item = entry.getValue();

            GameRegistry.registerItem(item, name);
        }
    }
    
    public void registerCrafting()
    {
    	for (ImmersionBlock block : blocks.values())
    	{
    		block.craftingRegistration();
    	}
    	for (ImmersionItem item : items.values())
    	{
    		item.craftingRegistration();
    	}
    }
    
    public void registerForgeOreDict()
    {
    	for (ImmersionBlock block : blocks.values())
    	{
    		block.forgeOreDict();
    	}
    	for (ImmersionItem item : items.values())
    	{
    		item.forgeOreDict();
    	}
    }

    /**
     * Looks through both registers for an object. Searches blocks first, then items.
     * 
     * @param name The unwrapped name of an object
     * @return Returns the found object. Type is ImmersionBlock for a block object, ImmersionItem for an item object, or null if not found
     */
    public <T> T readObject(String name)
    {
    	if (blocks.containsKey(name))
    	{
    		return (T) blocks.get(name);
    	}
    	
    	if (items.containsKey(name))
    	{
    		return (T) items.get(name);
    	}
    	
    	return (T) null;
    }
    
    /**
     * Looks through the block register for an object.
     * 
     * @param name The unwrapped name of an object
     * @return Returns the found object. Returns null if not found
     */
    public ImmersionBlock readBlock(String name)
    {
    	if (blocks.containsKey(name))
    	{
    		return blocks.get(name);
    	}
    	
    	return null;
    }
    
    /**
     * Looks through the block register for an object.
     * 
     * @param name The unwrapped name of an object
     * @return Returns the found object. Returns null if not found
     */
    public ImmersionItem readItem(String name)
    {
    	if (items.containsKey(name))
    	{
    		return items.get(name);
    	}
    	
    	return null;
    }
}

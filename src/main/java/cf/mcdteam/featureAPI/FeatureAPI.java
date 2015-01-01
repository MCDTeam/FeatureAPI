package cf.mcdteam.featureAPI;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cf.mcdteam.featureAPI.loader.FeatureRepository;


@Mod(modid = ModMetadata.MOD_ID, name = ModMetadata.NAME, version = ModMetadata.VERSION)
public class FeatureAPI 
{
	public static Logger log = LogManager.getLogger(ModMetadata.MOD_ID);
	
	@Instance(ModMetadata.MOD_ID)
	public static FeatureAPI instance;
    
	private FeatureRepository featureRepository;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		featureRepository = new FeatureRepository();
	}
	
	@EventHandler
	public void construction(FMLConstructionEvent event)
	{
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event) 
	{

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void serverabouttostart(FMLServerAboutToStartEvent event)
	{
		
	}
	
	@EventHandler
	public void serverstarting(FMLServerStartingEvent event)
	{
		
	}
	
	@EventHandler
	public void serverstarted(FMLServerStartedEvent event)
	{
		
	}
	
	@EventHandler 
	public void serverstopping (FMLServerStoppingEvent event)
	{
		
	}
	
	@EventHandler 
	public void serverstopped (FMLServerStoppingEvent event)
	{
		
	}

}
package cf.mcdteam.featureAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




import cf.mcdteam.coreFeature.command.CommandHandler;
import cf.mcdteam.featureAPI.proxy.IProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;


/*@Mod(modid = ModMetadata.MOD_ID, name = ModMetadata.NAME, version = ModMetadata.VERSION)
public class FeatureAPI 
{
	public static Logger log = LogManager.getLogger(ModMetadata.MOD_ID);
	
	@Instance(ModMetadata.MOD_ID)
	public static FeatureAPI instance;
    private final FeatureRepository _featureRepository;

    @SidedProxy(clientSide = ModMetadata.CLIENT_PROXY_CLASS, serverSide = ModMetadata.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public FeatureAPI()
    {
        this._featureRepository = new FeatureRepository();
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {

    }

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{	
		log.info("Pre-Init Version: " + ModMetadata.VERSION);
		ModMetadata.CONF_DIR = event.getModConfigurationDirectory();
        
        //get config to send to features
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        this._featureRepository.runPreInitialization(config);
        config.save();

		log.info("Pre-Init Finished");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) 
	{
		log.info("Init Version: " + ModMetadata.VERSION);

        this._featureRepository.runInitialization();
        log.info("Init Finished");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		log.info("Post-Init Version: " + ModMetadata.VERSION);
        /this._featureRepository.runPostInitialization();
		log.info("Post-Init Finished");
	}

}
*/
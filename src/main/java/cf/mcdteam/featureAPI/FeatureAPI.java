package cf.mcdteam.featureAPI;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = ModMetadata.MOD_ID, name = ModMetadata.NAME, version = ModMetadata.VERSION)
public class FeatureAPI 
{
	public static Logger log = LogManager.getLogger(ModMetadata.MOD_ID);
	
	@Instance(ModMetadata.MOD_ID)
	public static FeatureAPI instance;
    private final FeatureRepository _featureRepository;

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
		FMLCommonHandler.instance().getSide();
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
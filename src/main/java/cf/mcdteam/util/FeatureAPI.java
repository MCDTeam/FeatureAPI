package cf.mcdteam.util;

import java.io.File;
import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;





import cf.mcdteam.featureAPI.*;
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


@Mod(modid = ModMetadata.MOD_ID, name = ModMetadata.NAME, version = ModMetadata.VERSION)
public class FeatureAPI 
{
	public static Logger log = LogManager.getLogger(ModMetadata.MOD_ID);

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {

    }

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{	
		log.info("Pre-Init Version: " + ModMetadata.VERSION);
		FileReader reader = new FileReader(new File(event.getModConfigurationDirectory().getPath().concat(File.separator).concat("DumbConfig.txt")));
		reader.changestate();
		log.info(reader.filemap.toString());
		log.info("Pre-Init Finished");
	}
}
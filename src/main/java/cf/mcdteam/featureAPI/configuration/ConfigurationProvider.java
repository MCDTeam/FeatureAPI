package cf.mcdteam.featureAPI.configuration;

import java.io.File;

import cf.mcdteam.featureAPI.FeatureDataCollector;
import cf.mcdteam.featureAPI.IFeature;
import net.minecraftforge.common.config.Configuration;

/**
 *
 */
public class ConfigurationProvider 
{
	public static File configDir = null;

	public static ConfigurationProvider defprov = new ConfigurationProvider();
	
	public File specConfigDir;
	private ConfigurationProvider()
	{
		specConfigDir = configDir;
	}
	
	public ConfigurationProvider(String foldername)
	{
		specConfigDir = new File(configDir.getPath().concat(File.pathSeparator).concat(foldername));
	}
	
	public FeatureConfiguration newConfig (String configName)
	{
		return new FeatureConfiguration();
	}
}

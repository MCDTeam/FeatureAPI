package teamUnknown.immersion.core.feature.configuration;

/**
 *
 */
public interface IConfigurationProvider {
    public boolean getConfig(String key, String comment, boolean defaultValue);
    public int getConfig(String key, String comment, int defaultValue);
    public double getConfig(String key, String comment, double defaultValue);
    public String getConfig(String key, String comment, String defaultValue);
}

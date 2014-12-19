package cf.mcdteam.featureAPI.configuration;

import cf.mcdteam.featureAPI.FeatureDataCollector;
import cf.mcdteam.featureAPI.IFeature;
import net.minecraftforge.common.config.Configuration;

/**
 *
 */
public class FeatureConfigurationProvider implements IConfigurationProvider {

    private final Configuration _config;
    private final String _featureName;

    protected FeatureConfigurationProvider(Configuration config, IFeature feature){
        this._config = config;
        this._featureName = FeatureDataCollector.instance.getFeatureName(feature);
    }

    public static FeatureConfigurationProvider getFeatureConfigurationForFeature(IFeature feature, Configuration configuration){
        return new FeatureConfigurationProvider(configuration, feature);
    }

    @Override
    public boolean getConfig(String key, String comment, boolean defaultValue) {
        return this._config.get(this.getFeatureCategory(), this.getFeatureKey(key), defaultValue).getBoolean();
    }

    @Override
    public int getConfig(String key, String comment, int defaultValue) {
        return this._config.get(this.getFeatureCategory(), this.getFeatureKey(key), defaultValue).getInt();
    }

    @Override
    public double getConfig(String key, String comment, double defaultValue) {
        return this._config.get(this.getFeatureCategory(), this.getFeatureKey(key), defaultValue).getDouble();
    }

    @Override
    public String getConfig(String key, String comment, String defaultValue) {
        return this._config.get(this.getFeatureCategory(), this.getFeatureKey(key), defaultValue).getString();
    }

    private String getFeatureKey(String key){
        return key;
    }

    private String getFeatureCategory(){
        return this._featureName;
    }
}

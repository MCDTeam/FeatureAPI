package cf.mcdteam.featureAPI.configuration;

import net.minecraftforge.common.config.Configuration;
import cf.mcdteam.featureAPI.FeatureDataCollector;
import cf.mcdteam.featureAPI.IFeature;

public class FeatureConfiguration 
{
    private final Configuration _config;
    private final String _featureName;

    protected FeatureConfiguration(Configuration config, IFeature feature){
        this._config = config;
        this._featureName = FeatureDataCollector.instance.getFeatureName(feature);
    }

    public boolean getConfig(String key, String comment, boolean defaultValue) {
        return this._config.get(this.getFeatureCategory(), this.getFeatureKey(key), defaultValue).getBoolean();
    }

    public int getConfig(String key, String comment, int defaultValue) {
        return this._config.get(this.getFeatureCategory(), this.getFeatureKey(key), defaultValue).getInt();
    }

    public double getConfig(String key, String comment, double defaultValue) {
        return this._config.get(this.getFeatureCategory(), this.getFeatureKey(key), defaultValue).getDouble();
    }

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

package cf.mcdteam.featureAPI.logging;

import cf.mcdteam.featureAPI.FeatureDataCollector;
import cf.mcdteam.featureAPI.IFeature;


/**
 *
 */
public class FeatureLogger extends SubSystemLogger implements IGameLogger {

    protected FeatureLogger(IFeature feature){
        super("Feature:" + FeatureDataCollector.instance.getFeatureName(feature));
    }

    public static IGameLogger getLoggerForFeature(IFeature feature){
        return new FeatureLogger(feature);
    }

    @Override
    public void announce(String format, Object... args) {
        // TODO
    }

}

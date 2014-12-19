package teamUnknown.immersion.core.feature.logging;

import teamUnknown.immersion.core.feature.FeatureDataCollector;
import teamUnknown.immersion.core.feature.IFeature;

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

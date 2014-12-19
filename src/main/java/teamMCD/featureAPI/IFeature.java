package teamUnknown.immersion.core.feature;

import java.util.List;


/**
 * Basic building block of the feature system.
 * All features must implement this to start setup
 */
public interface IFeature {

    /**
     * Called at the mod pre-initialization, before setup of features
     * This is a convinience call, nothing is expected to happen here
     */
    public void preSetup();

    /**
     * Called during setup for the feature to do its setup
     * @return a list of dependencies from the current feature list
     */
    public IFeature[] setup();
    
    /**
     * Called on all features that will be continuing into pre-init
     * FeatureData(alternate) If this is true, the alternate feature has been chosen
     */
    public void postSetup();
    
    /**
     * Gets the feature name
     * @return Feature Name
     */
}

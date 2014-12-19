package teamUnknown.immersion.core.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that identifies a feature class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Feature 
{
	/**
	 * The internal name of the feature
	 */
    public String name() default "UNNAMED";
    
    /**
     * The version of the feature
     */
    public String version() default "UNVERSIONED";
    
    /**
     * Whether or not the feature is a base feature
     * if enabled, cannot be turned off
     */
    public boolean isBase() default false;
    
    /**
     * If required and has this as true, @FeatureData(Data.Alternate) will be true if turned off
     * All code will run. The feature is responsible to do processing based on this.
     */
    public boolean hasDisabledCompatility() default false;
    
    /**
     * The interface responsible for telling the code how to use methods within the feature class
     * NOTE: EVERY METHOD WITHIN THE FEATURE THAT WILL BE USED BY BEGINNING CODE
     * MUST HAVE ONE OF THESE ANNOTATIONS
     * NONSETUP will not run
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface FeatureElement 
    {
    	/**
    	 * The Types of events
    	 * FORGE EVENT TYPES SHOULD ONLY BE USED IF WHAT
    	 * YOU WANT TO DO IS NOT INCLUDED ON THE LIST
    	 * NONSETUP is not called naturally
    	 */
    	public enum Element
    	{
    		CONFIGURATION,
    		OBJECT,
    		EVENTBUS_ORE,
    		EVENTBUS_TERRAIN,
    		EVENTBUS_EVENT,
    		ENTITY,
    		MOD_COMPATIBILITY,
    		PREINITIALIZATION,
    		INTITIALIZATION,
    		POSTINITIALIZATION,
    		SERVERABOUTTOSTART,
    		SERVERSTARTING,
    		SERVERSTARTED,
    		SERVERSTOPPING,
    		SERVERSTOPPED,
    		NONSETUP,
            CLIENT
    	}
    	
    	/**
    	 * The type of the method
    	 */
    	public Element value() default Element.NONSETUP; 
    }
    
    /**
     * This is used for marking variables for the populator to populate
     * during setup and beyond if needed. If your stuff is not the correct 
     * type or not public, crashes will result
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface FeatureData
    {
    	/**
    	 * The Types of data given by the populator
    	 * NONDATA will not be populated
    	 */
    	public enum Data
    	{
    		LOGGER,
    		MODINSTANCE,
    		PREFEATURELIST,
    		FULLFEATURELIST,
    		ALTFEATURELIST,
    		COMPLETEFEATURELIST,
    		ALTERNATE,
    		FEATUREMAP,
    		FEATUREOBJECTMAP,
    		NONDATA
    	}
    	
    	/**
    	 * The type of the method
    	 */
    	public Data value() default Data.NONDATA;
    }
}


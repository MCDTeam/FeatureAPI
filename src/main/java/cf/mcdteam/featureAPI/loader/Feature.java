package cf.mcdteam.featureAPI.loader;

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
    public String value() default "UNNAMED";

    /**
     * The interface responsible for telling the code how to use methods within the feature class
     * NOTE: EVERY METHOD WITHIN THE FEATURE THAT WILL BE USED BY BEGINNING CODE
     * MUST HAVE ONE OF THESE ANNOTATIONS
     * Anything that does not have a valid string identifier will not run
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface FeatureElement 
    {  	
    	/**
    	 * The type of the method, designated by the internal string that corresponds with a feature
    	 * loader method
    	 */
    	public String value() default "None"; 
    }
    
    /**
     * This annotation marks a boolean which determines whether the feature is active. Each of the variables
     * inside the annotation mark its behavior
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface FeatureActivator
    {
    	public boolean base() default false;
    	public String[] dependencies() default new String[]();
    }
}


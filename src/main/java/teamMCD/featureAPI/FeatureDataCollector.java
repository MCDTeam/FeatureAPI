package teamUnknown.immersion.core.feature;

import java.util.ArrayList;
import java.util.HashMap;

import teamUnknown.immersion.core.feature.Feature.FeatureData;
import teamUnknown.immersion.core.feature.Feature.FeatureData.Data;
import teamUnknown.immersion.core.feature.object.FeatureObjectRegister;

@Feature(name = "DataCollector", version = "1.0", isBase = true)
public class FeatureDataCollector extends FeatureCommon 
{
	public static FeatureDataCollector instance;
	
	public FeatureDataCollector()
	{
		instance = this;
	}
	@FeatureData(Data.ALTFEATURELIST)
	public ArrayList<IFeature> ALTFEATURELIST;
	
	@FeatureData(Data.FULLFEATURELIST)
	public ArrayList<IFeature> FULLFEATURELIST;
	
	@FeatureData(Data.COMPLETEFEATURELIST)
	public ArrayList<IFeature> COMPLETEFEATURELIST;
	
	@FeatureData(Data.FEATUREMAP)
	public HashMap<String, IFeature> FEATUREMAP;
	
	@FeatureData(Data.FEATUREOBJECTMAP)
	public HashMap<IFeature, FeatureObjectRegister> FEATUREOBJECTMAP;
	
	public IFeature getFeature (String name)
    {
    	if (FEATUREMAP.containsKey(name))
    	{
    		return FEATUREMAP.get(name);
    	}
		return null;
    }

	public String getFeatureName (IFeature feature)
	{
		return feature.getClass().getAnnotation(Feature.class).name();
	}
}

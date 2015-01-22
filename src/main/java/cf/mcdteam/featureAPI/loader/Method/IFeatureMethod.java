package cf.mcdteam.featureAPI.loader.Method;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cf.mcdteam.featureAPI.loader.EForgeModEvent;

public interface IFeatureMethod
{
	public void onFeildFound(Field feild);
	public void onMethodFound(Method method);
	public void onEndPhase();
	public String getKey();
	public EForgeModEvent getEvent();
}

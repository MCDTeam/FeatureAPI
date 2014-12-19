package cf.mcdteam.featureAPI.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{

    @SideOnly(Side.CLIENT)
    @Override
    public void registerRendering() {

    }

    @Override
    public void registerTileEntitys() {

    }
}

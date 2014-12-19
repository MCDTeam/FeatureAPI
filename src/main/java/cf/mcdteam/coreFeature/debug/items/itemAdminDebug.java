package cf.mcdteam.coreFeature.debug.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import teamUnknown.immersion.coreFeatures.debug.api.IDebuggable;
import teamUnknown.immersion.core.feature.object.ImmersionItem;
import teamUnknown.immersion.core.utils.ChatHelper;

import java.util.List;

public class itemAdminDebug extends ImmersionItem{

    public itemAdminDebug(){
        super("adminDebug");

        this.setCreativeTab(CreativeTabs.tabTools);
        this.setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {

        return false;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float sideX, float sideY, float sideZ) {

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        Block lookedAt = world.getBlock(x, y, z);

        if (!world.isRemote) {
            if (player.capabilities.isCreativeMode) {
                if (tileEntity instanceof IDebuggable) {
                    if (((IDebuggable) tileEntity).isDebuggable()) {
                        ((IDebuggable) tileEntity).getDebugMethod();

                        return true;
                    }

                } else {
                    ChatHelper.sendMessageToPlayer(player, "No recignised debug options for: " + EnumChatFormatting.BLUE + lookedAt.getLocalizedName());
                }
            } else {
                ChatHelper.sendMessageToPlayer(player, "You are not in Creative Mode");
            }
        }

        return false;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.GREEN + "[Admin debugging tool]");
        list.add(EnumChatFormatting.RED + "[Destroyed when dropped]");
    }
}

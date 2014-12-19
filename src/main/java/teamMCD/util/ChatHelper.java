package teamUnknown.immersion.core.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class ChatHelper {

    public static void sendMessageToPlayer(EntityPlayer player, Object object) {

        player.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "[Immersion] " + ChatFormatting.GRAY + object.toString()));
    }

    public static void sendBlankMessageToPlayer(EntityPlayer player, Object object) {

        player.addChatMessage(new ChatComponentText(object.toString()));
    }
}

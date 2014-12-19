package cf.mcdteam.coreFeature.social;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.Configuration;
import teamUnknown.immersion.core.meta.ModMetadata;
import teamUnknown.immersion.core.network.PacketHandler;
import teamUnknown.immersion.core.network.message.MessageSocialPacket;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class SocialRegistry {

    public static void initialize() {

        friendConf = new Configuration(new File(ModMetadata.CONF_DIR,"/immersion/ImmersionSocial-Friends.cfg"));
        friendConf.load();
    }

    public static Configuration friendConf;
    public static List<String> clientPlayerFriends = new LinkedList<String>();

    public static boolean addFriend(String ownerName, String friendName) {

        if (ownerName == null || friendName == null) {
            return false;
        }
        friendConf.get(ownerName.toLowerCase(), friendName.toLowerCase(), 1);
        friendConf.save();
        return true;
    }

    public static boolean removeFriend(String ownerName, String friendName) {

        if (ownerName == null || friendName == null) {
            return false;
        }
        ownerName = ownerName.toLowerCase();
        friendName = friendName.toLowerCase();
        if (friendConf.hasCategory(ownerName)) {
            if (friendConf.getCategory(ownerName).containsKey(friendName)) {
                friendConf.getCategory(ownerName).remove(friendName);
                friendConf.save();
                return true;
            }
        }
        return false;
    }

    public static boolean playerHasAccess(String playerName, String ownerName) {

        return playerName != null
                && ownerName != null
                && (playerName.toLowerCase().matches(ownerName.toLowerCase()) || friendConf.hasCategory(ownerName.toLowerCase()) ? friendConf.getCategory(
                ownerName.toLowerCase()).containsKey(playerName.toLowerCase()) ? true : false : false);
    }

    public static void sendFriendsToPlayer(EntityPlayerMP thePlayer) {

        MessageSocialPacket packet = new MessageSocialPacket();
        packet.addByte(MessageSocialPacket.PacketTypes.FRIEND_LIST.ordinal());
        packet.addInt(friendConf.getCategory(thePlayer.getCommandSenderName().toLowerCase()).keySet().size());
        for (String theName : friendConf.getCategory(thePlayer.getCommandSenderName().toLowerCase()).keySet()) {
            packet.addString(theName);
        }
        PacketHandler.sendTo(packet, thePlayer);
    }
}

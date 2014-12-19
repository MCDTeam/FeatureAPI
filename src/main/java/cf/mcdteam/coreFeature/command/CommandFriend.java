package cf.mcdteam.coreFeature.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import teamUnknown.immersion.coreFeatures.social.SocialRegistry;

public class CommandFriend {

    public static void processCommand(ICommandSender commandSender, String[] args){
        if (args.length > 2) {
            if (args[1].equalsIgnoreCase("add")) {
                if (validUsername(args[2])) {
                    if (SocialRegistry.addFriend(commandSender.getCommandSenderName(), args[2])) {
                        commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + args[2] + EnumChatFormatting.GREEN + " " + "info.immersion.command.friend.0"));
                    } else {
                        commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "info.immersion.command.friend.1" + " " + EnumChatFormatting.YELLOW + args[2] + EnumChatFormatting.RED + " " + "info.immersion.command.friend.2"));
                    }
                } else {
                    commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "info.immersion.command.friend.3"));
                }
            } else if (args[1].equalsIgnoreCase("remove")) {
                if (validUsername(args[2])) {
                    if (SocialRegistry.removeFriend(commandSender.getCommandSenderName(), args[2])) {
                        commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + args[2] + EnumChatFormatting.GREEN + " " + "info.immersion.command.friend.4"));
                    } else {
                        commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + args[2] + EnumChatFormatting.RED + " " + "info.immersion.command.friend.5"));
                    }
                } else {
                    commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "info.immersion.command.friend.3"));
                }
            } else {
                commandSender.addChatMessage(new ChatComponentText("info.immersion.command.syntaxError" + " /immersion friend " + EnumChatFormatting.YELLOW + "|<gui|add|remove> " + EnumChatFormatting.YELLOW + "<username>"));
            }
        } else {
            commandSender.addChatMessage(new ChatComponentText("info.immersion.command.syntaxError" + " /immersion friend " + EnumChatFormatting.YELLOW + "<gui|add|remove> " + EnumChatFormatting.YELLOW + "<username>"));
        }
    }

    public static boolean validUsername(String username) {

        return username.replaceAll("[a-zA-Z0-9_]", "").matches("");
    }
}

package cf.mcdteam.coreFeature.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;
import teamUnknown.immersion.core.meta.Commands;
import teamUnknown.immersion.core.meta.Strings;

public class CommandWebsite {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        String subCommand;

        if (args.length > 0) {
            subCommand = args[0];

            if (subCommand.toLowerCase().equals(Commands.COMMAND_WIKI)) {
                processWikiCommand(commandSender);
            } else if (subCommand.toLowerCase().equals(Commands.COMMAND_SOURCE)) {
                processSourceCommand(commandSender);
            } else
                throw new WrongUsageException(Commands.COMMAND_WEBSITE_USAGE, new Object[0]);
        } else
            throw new WrongUsageException(Commands.COMMAND_WEBSITE_USAGE, new Object[0]);
    }

    private static void processWikiCommand(ICommandSender commandSender) {
        commandSender.addChatMessage(new ChatComponentText(Strings.Commands.COMMAND_PAGE_WIKI));
    }

    private static void processSourceCommand(ICommandSender commandSender) {
        commandSender.addChatMessage(new ChatComponentText(Strings.Commands.COMMAND_PAGE_SOURCE));
    }
}

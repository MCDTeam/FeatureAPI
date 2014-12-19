package cf.mcdteam.coreFeature.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import teamUnknown.immersion.core.meta.Commands;

import java.util.List;

public class CommandImmersionCore extends CommandBase {

    @Override
    public String getCommandName() {
        return Commands.COMMAND_IMMERSION;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return Commands.COMMAND_IMMERSION_USAGE;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            String commandName = args[0];
            System.arraycopy(args, 1, args, 0, args.length - 1);

            if (commandName.equalsIgnoreCase(Commands.COMMAND_SOUNDS)) {
//                CommandSounds.processCommand(commandSender, args);
            } else if (commandName.equalsIgnoreCase(Commands.COMMAND_VERSION)) {
                CommandVersion.processCommand(sender, args);
            } else if (commandName.equalsIgnoreCase(Commands.COMMAND_WEBSITE)) {
                CommandWebsite.processCommand(sender, args);
            } else if (commandName.equalsIgnoreCase(Commands.COMMAND_FRIEND)) {
                CommandFriend.processCommand(sender, args);
            } else
                throw new WrongUsageException(Commands.COMMAND_IMMERSION_USAGE, new Object[0]);
        } else
            throw new WrongUsageException(Commands.COMMAND_IMMERSION_USAGE, new Object[0]);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        switch (args.length) {
            case 1: {
                return getListOfStringsMatchingLastWord(args, new String[]{
                        Commands.COMMAND_VERSION, Commands.COMMAND_WEBSITE, Commands.COMMAND_FRIEND});
            }
                case 2: {
                    if (args[0].equalsIgnoreCase(Commands.COMMAND_WEBSITE))
                        return getListOfStringsMatchingLastWord(args,
                                new String[]{Commands.COMMAND_WIKI, Commands.COMMAND_SOURCE});

                    if (args[0].equalsIgnoreCase(Commands.COMMAND_FRIEND))
                        return getListOfStringsMatchingLastWord(args,
                                new String[]{Commands.ADD, Commands.REMOVE});
                }
            default: {
                return null;
            }
        }
    }
}

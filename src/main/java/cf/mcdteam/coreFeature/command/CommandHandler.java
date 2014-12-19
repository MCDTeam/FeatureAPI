package cf.mcdteam.coreFeature.command;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandHandler {

    public static void initCommands(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandImmersionCore());
    }
}

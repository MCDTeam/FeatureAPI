package cf.mcdteam.coreFeature.versionCheck;

import cpw.mods.fml.common.Loader;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import teamUnknown.immersion.Immersion;
import teamUnknown.immersion.core.meta.ModMetadata;
import teamUnknown.immersion.core.meta.Strings;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class VersionHelper implements Runnable{

    private static VersionHelper instance = new VersionHelper();

    // The (publicly available) remote version number authority file
    private static final String REMOTE_VERSION_XML_FILE = "https://raw.githubusercontent.com/Mc-Immersion/Immersion/master/version.xml";

    public static Properties remoteVersionProperties = new Properties();

    // All possible results of the remote version number check
    public static final byte UNINITIALIZED = 0;
    public static final byte CURRENT = 1;
    public static final byte OUTDATED = 2;
    public static final byte ERROR = 3;
    public static final byte FINAL_ERROR = 4;
    public static final byte MC_VERSION_NOT_FOUND = 5;

    // Var to hold the result of the remote version check, initially set to uninitialized
    private static byte result = UNINITIALIZED;
    public static String remoteVersion = null;
    public static String remoteUpdateLocation = null;

    /**
     * Checks the version of the currently running instance of the mod against
     * the remote version authority, and sets the result of the check
     * appropriately
     */
    public static void checkVersion() {

        InputStream remoteVersionRepoStream = null;
        result = UNINITIALIZED;

        try {
            URL remoteVersionURL = new URL(REMOTE_VERSION_XML_FILE);
            remoteVersionRepoStream = remoteVersionURL.openStream();
            remoteVersionProperties.loadFromXML(remoteVersionRepoStream);

            String remoteVersionProperty = remoteVersionProperties.getProperty(Loader.instance().getMCVersionString());

            if (remoteVersionProperty != null) {
                String[] remoteVersionTokens = remoteVersionProperty.split("\\|");

                if (remoteVersionTokens.length >= 2) {
                    remoteVersion = remoteVersionTokens[0];
                    remoteUpdateLocation = remoteVersionTokens[1];
                } else {
                    result = ERROR;
                }

                if (remoteVersion != null) {
                    /*if (!VersionHelper.LAST_DISCOVERED_VERSION.equalsIgnoreCase(remoteVersion)) {
                        VersionFeature.set(ConfigurationHandler.CATEGORY_GENERAL, ConfigurationHelper.LAST_DISCOVERED_VERSION_CONFIGNAME, remoteVersion);
                    }*/

                    if (remoteVersion.equalsIgnoreCase(getVersionForCheck())) {
                        result = CURRENT;
                    } else {
                        result = OUTDATED;
                    }
                }

            } else {
                result = MC_VERSION_NOT_FOUND;
            }
        } catch (Exception e) {
        } finally {
            if (result == UNINITIALIZED) {
                result = ERROR;
            }

            try {
                if (remoteVersionRepoStream != null) {
                    remoteVersionRepoStream.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    private static String getVersionForCheck() {

        String[] versionTokens = ModMetadata.VERSION.split(" ");

        if (versionTokens.length >= 1)
            return versionTokens[0];
        else
            return ModMetadata.VERSION;
    }

    public static void logResult() {

        if (result == CURRENT || result == OUTDATED) {
            Immersion.log.info(getResultMessage());
        } else {
            Immersion.log.fatal(getResultMessage());
        }
    }

    public static String getResultMessage() {

        if (result == UNINITIALIZED) {
            return StatCollector.translateToLocal(Strings.version.UNINITIALIZED_MESSAGE);
        } else if (result == CURRENT) {
            return StatCollector.translateToLocalFormatted(Strings.version.CURRENT_MESSAGE, ModMetadata.NAME, Loader.instance().getMCVersionString());
        } else if (result == OUTDATED && remoteVersion != null && remoteUpdateLocation != null) {
            return StatCollector.translateToLocalFormatted(Strings.version.OUTDATED_MESSAGE, ModMetadata.NAME, remoteVersion, Loader.instance().getMCVersionString(), remoteUpdateLocation);
        } else if (result == ERROR) {
            return StatCollector.translateToLocal(Strings.version.GENERAL_ERROR_MESSAGE);
        } else if (result == FINAL_ERROR) {
            return StatCollector.translateToLocal(Strings.version.FINAL_ERROR_MESSAGE);
        } else if (result == MC_VERSION_NOT_FOUND) {
            return StatCollector.translateToLocalFormatted(Strings.version.MC_VERSION_NOT_FOUND, ModMetadata.NAME, Loader.instance().getMCVersionString());
        } else {
            result = ERROR;
            return StatCollector.translateToLocal(Strings.version.GENERAL_ERROR_MESSAGE);
        }
    }

    public static String getResultMessageForClient() {

        return StatCollector.translateToLocalFormatted(Strings.version.OUTDATED_MESSAGE, EnumChatFormatting.YELLOW + ModMetadata.NAME + EnumChatFormatting.WHITE, EnumChatFormatting.YELLOW + VersionHelper.remoteVersion + EnumChatFormatting.WHITE, EnumChatFormatting.YELLOW + Loader.instance().getMCVersionString() + EnumChatFormatting.WHITE, EnumChatFormatting.YELLOW + VersionHelper.remoteUpdateLocation + EnumChatFormatting.WHITE);
    }

    public static byte getResult() {

        return result;
    }

    @Override
    public void run() {

        int count = 0;

        Immersion.log.info(StatCollector.translateToLocalFormatted(Strings.version.VERSION_CHECK_INIT_LOG_MESSAGE, REMOTE_VERSION_XML_FILE));

        try {
            while (count < ModMetadata.VERSION_CHECK_ATTEMPTS - 1 && (result == UNINITIALIZED || result == ERROR)) {

                checkVersion();
                count++;
                logResult();

                if (result == UNINITIALIZED || result == ERROR) {
                    Thread.sleep(10000);
                }
            }

            if (result == ERROR) {
                result = FINAL_ERROR;
                logResult();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void execute() {

        new Thread(instance).start();
    }
}

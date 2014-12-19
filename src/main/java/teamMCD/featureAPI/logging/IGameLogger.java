package teamUnknown.immersion.core.feature.logging;

/**
 *
 */
public interface IGameLogger extends ILogger {
    // Function for announcing an information for the player
    public void announce(String format, Object... args);
}

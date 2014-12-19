package teamUnknown.immersion.core.feature.logging;

/**
 *
 */
public interface ILogger {

    // Function for logging to the Log output of minecraft
    public void info(String format, Object... args);
    
    public void debug(String format, Object... args);
}

package cf.mcdteam.coreFeature.debug.api;

    /**
     * Allows a tile entity/block to output a debug message when the debugItem is used on it.
     */
    public interface IDebuggable {

        /**
         * Checks if the tile entity/block is in a state that can be debugged.
         *
         * @return True if the tile entity can be debugged
         */
        public abstract boolean isDebuggable();

        /**
         * Gets the debug text for the tile entity/block.
         *
         * @return The method that the debugItem should run
         */

        public abstract void getDebugMethod();
}

package app;

import java.util.HashMap;

/**
 * Data.java - a SettingsGenerator class to store global data
 */
@SuppressWarnings("WeakerAccess")
public class Data {

    /** Dictionary of stuff stored in file */
    private static HashMap<String, Object> stuff = new HashMap<>();

    /**
     * Store item in stuff file
     * @param key - the String key of the stuff
     * @param value - the stuff Object being stored
     */
    public static void store(String key, Object value) {
        stuff.put(key, value);
    }

    /**
     * Store item in stuff file
     * @param key - the String key of the stuff
     * @return The Object stored with key
     */
    public static Object retrieve(String key) {
        return stuff.get(key);
    }

    /**
     * SettingsGenerator color scheme handler
     */
    public static class Theme {

        /*
         * All app colors
         */
        public static String TITLE_BAR = "#3D4956";
        public static String TITLE_BAR_BACK = "#9F9F9F";
        public static String TITLE_BAR_FORWARD = "#9F9F9F";
        public static String TITLE_BAR_DOWNLOAD = "#9F9F9F";
        public static String TITLE_BAR_MINIMIZE = "#9F9F9F";
        public static String TITLE_BAR_CLOSE = "#9F9F9F";
        public static String CONFIRM = "#1F1B24";
        public static String CONFIRM_EX = "#808080";
        public static String CONFIRM_CHECK = "#808080";
        public static String CONFIRM_TEXT = "#808080";
        public static String BODY = "#3D4956";

        /*
         * Set app colors by group
         */

        /**
         * Set the color scheme for the main app
         * @param color - A string representation of a color hex
         */
        public static void setAppColor(String color) {
            TITLE_BAR = color;
            BODY = color;
        }

        /**
         * Set the color scheme for the title bar icons
         * @param color - A string representation of a color hex
         */
        public static void setTitleBatIconColors(String color) {
            TITLE_BAR_BACK = color;
            TITLE_BAR_FORWARD = color;
            TITLE_BAR_DOWNLOAD = color;
            TITLE_BAR_MINIMIZE = color;
            TITLE_BAR_CLOSE = color;
        }

        /**
         * Set the color scheme for the confirmation dialog content
         * @param color - A string representation of a color hex
         */
        public static void setConfirmationContentColors(String color) {
            CONFIRM_CHECK = color;
            CONFIRM_EX = color;
            CONFIRM_TEXT = color;
        }



    }


}

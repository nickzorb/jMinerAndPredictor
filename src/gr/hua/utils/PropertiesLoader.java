package gr.hua.utils;

import gr.hua.data_manipulation.DataManager;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static final String ESCAPE_CHAR = "ESCAPE CHARACTER";
    public static final String STRING_SEPERATOR = "STRING SEPERATOR";
    
    public static final String NEW_LINE = System.getProperty("line.separator");
    private static final String PROPERTIES_FILE_PATH = "./settings/jMnP.prop";
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new FileInputStream(PROPERTIES_FILE_PATH));
            if (PROPERTIES.isEmpty()) {
                initDefaults();
            }
        } catch (Exception ex) {
            Logger.logException(ex);
            String sMessage = "Failed to load properties from file, using"
                    + " default settings";
            Loggable lErrorLog = new SimpleLoggable(sMessage, PropertiesLoader.class);
            Logger.logError(lErrorLog);
            initDefaults();
        }
    }

    private static void initDefaults() {
        /**
         * Data read/write properties
         */
        PROPERTIES.put(DataManager.R_COLUMN_BREAK, ";");
        PROPERTIES.put(DataManager.R_ENCODING, "UTF-8");
        PROPERTIES.put(DataManager.R_LINE_BREAK, "\n");
        PROPERTIES.put(DataManager.R_LITERAL_END, "\"");
        PROPERTIES.put(DataManager.R_LITERAL_START, "\"");
        PROPERTIES.put(DataManager.R_NULL_VALUES, "");
        PROPERTIES.put(DataManager.W_COLUMN_BREAK, ",");
        PROPERTIES.put(DataManager.W_ENCODING, "UTF-8");
        PROPERTIES.put(DataManager.W_LINE_BREAK, "\n");
        PROPERTIES.put(DataManager.W_LITERAL_END, "\"");
        PROPERTIES.put(DataManager.W_LITERAL_START, "\"");
        PROPERTIES.put(DataManager.W_NULL, "");
        PROPERTIES.put(ESCAPE_CHAR, "\\");
        PROPERTIES.put(STRING_SEPERATOR, "|");
        saveProperties();
    }

    public static void saveProperties() {
        try {
            PROPERTIES.store(new FileOutputStream(PROPERTIES_FILE_PATH), null);
        } catch (Exception ex) {
            Logger.logException(ex);
        }
    }

    public static String getProperty(final String s) {
        if (s == null) {
            Logger.logError(new SimpleLoggable("null not allowed as a property"
                    + "identifier", PropertiesLoader.class));
            return null;
        }
        return PROPERTIES.getProperty(s);
    }
    
    public static void addProperty(final String key, final String property) {
        if (key == null || property == null) {
            Logger.logError(new SimpleLoggable("Tried to save a null property"
                    , PropertiesLoader.class));
        } else {
            PROPERTIES.put(key, property);
        }
    }
}

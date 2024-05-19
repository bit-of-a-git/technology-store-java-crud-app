package utils;

import java.util.ArrayList;

public class DisplayTypeUtility {

    private static final ArrayList<String> displayTypes = new ArrayList<>() {{
        add("AMOLED");
        add("LCD");
        add("LED");
        add("TFT");
    }};


    public static boolean isValidDisplayType(String type) {
        //must not be case sensitive
        for (String disType : displayTypes) {
            if (disType.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    // This method was added to return a string of allowed manufacturer names to the user
    public static String formatList() {
        String list = "";
        for (int i = 0; i < displayTypes.size(); i++) {
            list += (displayTypes.get(i));
            if (i < displayTypes.size() - 1) {
                list += (", ");
            }
        }
        return list;
    }
}

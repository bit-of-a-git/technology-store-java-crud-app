package utils;

import java.util.ArrayList;

public class ManufacturerNameUtility {

    private static final ArrayList<String> manufacturerNames = new ArrayList<>() {{
        add("APPLE");
        add("SAMSUNG");
        add("Garmin");
        add("FitBit");
        add("Whoop");
    }};

    public static boolean isValidManuName(String name) {
        //must not be case sensitive
        for (String manuName : manufacturerNames) {
            if (manuName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // This method was added to return a string of allowed manufacturer names to the user
    public static String formatList() {
        String list = "";
        for (int i = 0; i < manufacturerNames.size(); i++) {
            list += (manufacturerNames.get(i));
            if (i < manufacturerNames.size() - 1) {
                list += (", ");
            }
        }
        return list;
    }
}

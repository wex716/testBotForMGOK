package org.example.Util;

public class NumberUtil {
    public static boolean ConvertStringFromNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

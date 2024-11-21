package org.example.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
    public static boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int stringToIntNumber(String text) {
        return Integer.parseInt(text);
    }

    public static boolean isNumberInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }

    public static Pattern isTextPhoneNumber(String text) {

        String phone = text;
        String regex = "8\\(9[0-9]{2}\\)[0-9]{3}-[0-9]{2}-[0-9]{2}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phone);

        return pattern;
    }

    public static boolean isTextWithPunctuation(String text) {
        if (text == null || text.isEmpty()) return false;
        return text.matches("[a-zA-Z0-9\\s.,!?;:'\"-]*");
    }
}
package gr.hua.utils;

public class NumParser {

    public static Double parseDouble(String s) {
        int neg = 1;
        String temp = s;
        if (temp.contains(",")) {
            temp = temp.replace(',', '.');
        }
        if (temp.startsWith("-")) {
            temp = temp.replaceAll("-", "");
            neg = -1;
        }
        try {
            return neg * Double.parseDouble(temp);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer parseInt(String s) {
        int neg = 1;
        String temp = s;
        if (temp.startsWith("-")) {
            temp = temp.replaceAll("-", "");
            neg = -1;
        }
        try {
            return neg * Integer.parseInt(temp);
        } catch (Exception e) {
            return null;
        }
    }
}

package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Formatter {
    public static String solution(List<DifferElement> diff, String format) {
        String result = "";
        if (format.equals("stylish"))
            result = stylish(diff);
        return result;
    }

    private static String stylish(List<DifferElement> diff) {
        StringBuilder stylishFormat = new StringBuilder();
        for (DifferElement el : diff) {
            if (el.getChange().equals("update")) {
                stylishFormat.append("  - " + el.getKey() + ": " + el.getValueOld() + "\n");
                stylishFormat.append("  + " + el.getKey() + ": " + el.getValueNew() + "\n");
            } else if (el.getChange().equals("added")) {
                stylishFormat.append("  + " + el.getKey() + ": " + el.getValueNew() + "\n");
            } else if (el.getChange().equals("removed")) {
                stylishFormat.append("  - " + el.getKey() + ": " + el.getValueOld() + "\n");
            } else if (el.getChange().equals("same")) {
                stylishFormat.append("    " + el.getKey() + ": " + el.getValue() + "\n");
            }
        }
        return "{\n" + stylishFormat.toString() + "}";
    }
}

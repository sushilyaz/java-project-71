package hexlet.code.formatter;

import hexlet.code.DifferElement;

import java.util.List;

public class Stylish {
    public static String stylish(List<DifferElement> diff) {
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

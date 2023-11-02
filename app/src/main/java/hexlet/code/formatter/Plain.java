package hexlet.code.formatter;

import hexlet.code.DifferElement;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String plain(List<DifferElement> diff) {
        StringBuilder plainFormat = new StringBuilder();
        for (DifferElement el : diff) {
            Object val = el.getValue();
            if (el.getChange().equals("updated")) {
                plainFormat.append("Property '" + el.getKey() + "' was updated. From "
                        + modifyValue(el.getValueOld()) + " to " + modifyValue(el.getValueNew()) + "\n");
            } else if (el.getChange().equals("added")) {
                plainFormat.append("Property '" + el.getKey() + "' was added with value: " + modifyValue(val) + "\n");
            } else if (el.getChange().equals("removed")) {
                plainFormat.append("Property '" + el.getKey() + "' was removed" + "\n");
            }
        }
        return plainFormat.deleteCharAt(plainFormat.length() - 1).toString();
    }

    private static String modifyValue(Object mod) {
        if (mod == null) {
            return null;
        } else if (mod instanceof List<?> || mod instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (mod instanceof String) {
            return "'" + mod + "'";
        } else {
            return mod.toString();
        }
    }
}

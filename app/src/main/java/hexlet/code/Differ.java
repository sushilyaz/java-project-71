package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Objects;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> dataFile1 = null;
        Map<String, Object> dataFile2 = null;
        String[] splitFilepaths = filepath1.split("\\.");
        String extension = splitFilepaths[1];
        List<Map<String, Object>> parseFiles = new ArrayList<>();
        if (extension.equals("json")) {
            parseFiles = Parser.parseJSON(filepath1, filepath2);
        } else if (extension.equals("yaml")) {
            parseFiles = Parser.parseYAML(filepath1, filepath2);
        }

        dataFile1 = parseFiles.get(0);
        dataFile2 = parseFiles.get(1);
        List<String> allKeys = new ArrayList<>();
        allKeys.addAll(dataFile1.keySet());
        allKeys.addAll(dataFile2.keySet());
        Set<String> set = new HashSet<>(allKeys);
        List<String> allKeysSet = new ArrayList<>(set);
        Collections.sort(allKeysSet);
        StringBuilder diff = new StringBuilder();
        diff.append("{");
        for (String key : allKeysSet) {
            Object value1 = dataFile1.get(key);
            Object value2 = dataFile2.get(key);
            boolean elementRemoved = dataFile1.containsKey(key) && !dataFile2.containsKey(key);
            boolean elementAdded = !dataFile1.containsKey(key) && dataFile2.containsKey(key);
            boolean elementEqual = Objects.equals(value1, value2);
            if (elementRemoved) {
                diff.append("\n" + "  - " + key + ": " + value1);
            } else if (elementAdded) {
                diff.append("\n" + "  + " + key + ": " + value2);
            } else if (!elementEqual) {
                diff.append("\n" + "  - " + key + ": " + value1);
                diff.append("\n" + "  + " + key + ": " + value2);
            } else if (elementEqual) {
                diff.append("\n" + "    " + key + ": " + value2);
            }
        }
        diff.append("\n");
        diff.append("}");
        return diff.toString();
    }
}

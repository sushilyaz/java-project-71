package hexlet.code;

import java.util.*;
import java.util.stream.Collectors;

public class Differ {
    public static void generate(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
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
            if (value1 == null) {
                diff.append("\n");
                diff.append("+ " + key + ": " + value2);
            } else if (value2 == null) {
                diff.append("\n");
                diff.append("- " + key + ": " + value1);
            } else if (!value1.equals(value2)) {
                diff.append("\n");
                diff.append("- " + key + ": " + value1);
                diff.append("\n");
                diff.append("+ " + key + ": " + value2);
            } else if (value1.equals(value2)) {
                diff.append("\n");
                diff.append("  " + key + ": " + value2);
            }
        }
        diff.append("\n");
        diff.append("}");
        System.out.println(diff.toString());
    }
}

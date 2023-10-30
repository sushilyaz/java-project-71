package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Objects;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String pathStr1 = Files.readString(path1);
        String pathStr2 = Files.readString(path2);

        Map<String, Object> dataFile1 = null;
        Map<String, Object> dataFile2 = null;

        ObjectMapper mapper = new ObjectMapper();

        try {
            dataFile1 = mapper.readValue(pathStr1, new TypeReference<Map<String, Object>>() {
            });
            dataFile2 = mapper.readValue(pathStr2, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

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

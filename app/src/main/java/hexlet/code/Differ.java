package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {

        Map<String, Object> dataFile1 = null;
        Map<String, Object> dataFile2 = null;
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        String path1Str = Files.readString(path1);
        String path2Str = Files.readString(path2);


        ObjectMapper mapper = new ObjectMapper();
        File fileObj1 = new File(path1Str);
        File fileObj2 = new File(path2Str);
        try {
            dataFile1 = mapper.readValue(fileObj1, new TypeReference<Map<String, Object>>() {
            });
            dataFile2 = mapper.readValue(fileObj2, new TypeReference<Map<String, Object>>() {
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
        return diff.toString();
    }
}

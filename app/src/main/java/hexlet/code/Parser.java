package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {
    public static List<Map<String, Object>> parseJSON(String filepath1, String filepath2) throws Exception {
        Map<String, Object> dataFile1 = null;
        Map<String, Object> dataFile2 = null;
        List<Map<String, Object>> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String pathStr1 = Files.readString(path1);
        String pathStr2 = Files.readString(path2);

        try {
            dataFile1 = mapper.readValue(pathStr1, new TypeReference<Map<String, Object>>() {
            });
            dataFile2 = mapper.readValue(pathStr2, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.add(dataFile1);
        result.add(dataFile2);
        return result;
    }

    public static List<Map<String, Object>> parseYAML(String filepath1, String filepath2) throws Exception {
        Map<String, Object> dataFile1 = null;
        Map<String, Object> dataFile2 = null;
        List<Map<String, Object>> result = new ArrayList<>();
        ObjectMapper mapper = new YAMLMapper();
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String pathStr1 = Files.readString(path1);
        String pathStr2 = Files.readString(path2);

        try {
            dataFile1 = mapper.readValue(pathStr1, new TypeReference<Map<String, Object>>() {
            });
            dataFile2 = mapper.readValue(pathStr2, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.add(dataFile1);
        result.add(dataFile2);
        return result;
    }
}

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private static String resultStylish;
    private static String resultPlain;
    private static final String JSON_PATH_1 = "src/test/resources/file1.json";
    private static final String JSON_PATH_2 = "src/test/resources/file2.json";
    private static final String YAML_PATH_1 = "src/test/resources/file1.yaml";
    private static final String YAML_PATH_2 = "src/test/resources/file2.yaml";

    @BeforeAll
    public static void getResult() throws Exception {
        resultStylish = Files.readString(Paths.get("src/test/resources/ResultStylish").toAbsolutePath().normalize());
        resultPlain = Files.readString(Paths.get("src/test/resources/ResultPlain").toAbsolutePath().normalize());
    }

    @Test
    public void testStylishJson() throws Exception {
        String expected = resultStylish;
        String actual = Differ.generate(JSON_PATH_1, JSON_PATH_2, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testStylishYaml() throws Exception {
        String expected = resultStylish;
        String actual = Differ.generate(YAML_PATH_1, YAML_PATH_2, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testPlainJson() throws Exception {
        String expected = resultPlain;
        String actual = Differ.generate(JSON_PATH_1, JSON_PATH_2, "plain");
        assertEquals(expected, actual);
    }
    @Test
    public void testPlainYaml() throws Exception {
        String expected = resultPlain;
        String actual = Differ.generate(YAML_PATH_1, YAML_PATH_2, "plain");
        assertEquals(expected, actual);
    }
}

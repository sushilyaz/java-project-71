import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;
    private static final String JSON_PATH_1 = "src/test/resources/file1.json";
    private static final String JSON_PATH_2 = "src/test/resources/file2.json";
    private static final String YML_PATH_1 = "src/test/resources/file1.yml";
    private static final String YML_PATH_2 = "src/test/resources/file2.yml";

    @BeforeAll
    public static void getResult() throws Exception {
        resultStylish = Files.readString(Paths.get("src/test/resources/ResultStylish").toAbsolutePath().normalize());
        resultPlain = Files.readString(Paths.get("src/test/resources/ResultPlain").toAbsolutePath().normalize());
        resultJson = Files.readString(Paths.get("src/test/resources/ResultJson").toAbsolutePath().normalize());
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
        String actual = Differ.generate(YML_PATH_1, YML_PATH_2, "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testDefault() throws Exception {
        String expected = resultStylish;
        String actual = Differ.generate(YML_PATH_1, YML_PATH_2);
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
        String actual = Differ.generate(YML_PATH_1, YML_PATH_2, "plain");
        assertEquals(expected, actual);
    }
    @Test
    public void testJsonJson() throws Exception {
        String expected = resultJson;
        String actual = Differ.generate(JSON_PATH_1, JSON_PATH_2, "json");
        assertEquals(expected, actual);
    }
    @Test
    public void testJsonYaml() throws Exception {
        String expected = resultJson;
        String actual = Differ.generate(YML_PATH_1, YML_PATH_2, "json");
        assertEquals(expected, actual);
    }
}

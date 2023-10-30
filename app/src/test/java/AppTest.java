import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AppTest {
    private static String stylish;
    private static final String ABSOLUTE_PATH_1 = "src/test/resources/file1.json";
    private static final String ABSOLUTE_PATH_2 = "src/test/resources/file2.json";

    @BeforeAll
    public static void getResult() throws Exception {
        stylish = Files.readString(Paths.get("src/test/resources/ResultJSON").toAbsolutePath().normalize());
    }
    @Test
    public void testStylishJson () throws Exception {
        String expected = stylish;
        String actual = Differ.generate(ABSOLUTE_PATH_1, ABSOLUTE_PATH_2);
        assertEquals(expected, actual);
    }
}

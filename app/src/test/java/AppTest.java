import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private static final String PATH1 = "src/test/resources/stylish1.json";
    private static final String PATH2 = "src/test/resources/stylish2.json";
    private static String stylish;

    @Test
    public void testStylish() throws Exception {
        stylish = Files.readString(Path.of("src/test/resources/ResultStylish.txt"));
        String actual = Differ.generate(PATH1, PATH2);
        String except = stylish;
        assertEquals(actual, except);
    }
}

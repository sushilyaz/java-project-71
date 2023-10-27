package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)

public class App implements Callable {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filePath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filePath2;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionRequested = false;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Object call() throws Exception {
        Map<String, Object> dataFile1 = null;
        Map<String, Object> dataFile2 = null;
        if (helpRequested) {
            CommandLine.usage(this, System.out);
        } else if (versionRequested) {
            System.out.println("Version 1.0");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            File fileObj1 = new File(filePath1);
            File fileObj2 = new File(filePath2);
            try {
                dataFile1 = mapper.readValue(fileObj1, new TypeReference<Map<String, Object>>() {
                });
                dataFile2 = mapper.readValue(fileObj2, new TypeReference<Map<String, Object>>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            Differ.generate(dataFile1, dataFile2);
        }
        return null;
    }
}

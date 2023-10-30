package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)

public class App implements Callable {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.")
    private boolean helpRequested = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionRequested = false;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Object call() throws Exception {
        if (helpRequested) {
            CommandLine.usage(this, System.out);
            return null;
        } else if (versionRequested) {
            System.out.println("Version 1.0");
            return null;
        } else {
            System.out.println(Differ.generate(filepath1, filepath2));
        }
        return null;
    }
}
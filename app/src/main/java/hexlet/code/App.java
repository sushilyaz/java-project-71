package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)

public class App implements Runnable{

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
    public void run() {
        if (helpRequested) {
            CommandLine.usage(this, System.out);
            return;
        } else if (versionRequested) {
            System.out.println("Version 1.0");
            return;
        }
    }
}
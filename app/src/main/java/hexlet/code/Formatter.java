package hexlet.code;

import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;

import java.util.List;

public class Formatter {
    public static String solution(List<DifferElement> diff, String format) {
        String result = "";
        if (format.equals("stylish")) {
            result = Stylish.stylish(diff);
        } else if (format.equals("plain")) {
            result = Plain.plain(diff);
        }
        return result;
    }
}

package view.menu.regexEnumForInput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    Pattern commandPattern;
    String outputName;
    int counter;

    String getOutputString() {
        return "Please enter the " + this.outputName + " :";
    }

    Matcher getStringMatcher(String input) {
        return this.commandPattern.matcher(input);
    }

    Regex(String commandPatternString, String outputName, int counter) {
        this.commandPattern = Pattern.compile(commandPatternString);
        this.outputName = outputName;
        this.counter = counter;
    }
}

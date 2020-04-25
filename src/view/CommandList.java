package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandList {
//    TODO: CommandProcessor regex's
    ;
    private Pattern commandPattern;

    public Pattern getCommandPattern() {
        return commandPattern;
    }

    public Matcher getStringMatcher(String input) {
        return this.commandPattern.matcher(input);
    }

    CommandList(String commandPatternString) {
        this.commandPattern = Pattern.compile(commandPatternString);
    }
}

package view.menu.regexEnumForInput;

import java.util.ArrayList;
import java.util.Scanner;

public final class CreateManagerProfileRegex extends Regex {
    public CreateManagerProfileRegex(String commandPatternString, String outputName, int counter) {
        super(commandPatternString, outputName, counter);
        allRegex.add(this);

    }
    static ArrayList<CreateManagerProfileRegex> allRegex = new ArrayList<>();
    static final CreateManagerProfileRegex USER_NAME = new
            CreateManagerProfileRegex("\\w+", "username", 0);
    static final CreateManagerProfileRegex PASS_WORD = new
            CreateManagerProfileRegex("\\w+", "password", 1);
    static final CreateManagerProfileRegex FIRST_NAME = new
            CreateManagerProfileRegex("[a-zA-Z]+", "first name",2);
    static final CreateManagerProfileRegex LAST_NAME = new
            CreateManagerProfileRegex("[a-zA-Z]+", "last name", 3);
    static final CreateManagerProfileRegex EMAIL = new
            CreateManagerProfileRegex("\\w+@\\w+.com", "email", 4);
    static final CreateManagerProfileRegex PHONE_NUMBER = new
            CreateManagerProfileRegex("09\\d{9}","phone number",5);

    static CreateManagerProfileRegex getRegexByCounter(int counter) {
        for (CreateManagerProfileRegex regex : allRegex) {
            if (regex.counter == counter) {
                return regex;
            }
        }
        return null;
    }

    public static ArrayList<String> checkRegex(Scanner scanner) {
        String command;
        ArrayList<String> info = new ArrayList<>();
        for (int counter = 0; counter < 6; ) {
            System.out.println(CreateManagerProfileRegex.getRegexByCounter(counter).getOutputString());
            if ((command = scanner.nextLine()).matches("back")) {
                return null;
            }
            if (CreateManagerProfileRegex.getRegexByCounter(counter).getStringMatcher(command).matches()) {
                info.add(command);
                counter++;
                continue;
            }
            System.out.println("Wrong. Try again.");
        }
        return info;
    }
}

package view.menu.regexEnumForInput;

import java.util.ArrayList;
import java.util.Scanner;

public final class RegisterRegex extends Regex {
    public RegisterRegex(String commandPatternString, String outputName, int counter) {
        super(commandPatternString, outputName, counter);
        allRegex.add(this);
    }

    static ArrayList<RegisterRegex> allRegex = new ArrayList<>();
    static final RegisterRegex USER_NAME = new
            RegisterRegex("\\w+", "username", 0);
    static final RegisterRegex PASS_WORD = new
            RegisterRegex("\\w+", "password", 1);
    static final RegisterRegex FIRST_NAME = new
            RegisterRegex("[a-zA-Z]+", "first name", 2);
    static final RegisterRegex LAST_NAME = new
            RegisterRegex("[a-zA-Z]+", "last name", 3);
    static final RegisterRegex EMAIL = new
            RegisterRegex("\\w+@\\w+.com", "email", 4);
    static final RegisterRegex PHONE_NUMBER = new
            RegisterRegex("09\\d{9}", "phone number", 5);

    static RegisterRegex getRegexByCounter(int counter) {
        for (RegisterRegex regex : allRegex) {
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
            System.out.println(RegisterRegex.getRegexByCounter(counter).getOutputString());
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
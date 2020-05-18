package view.menu.regexEnumForInput;
import java.util.ArrayList;
import java.util.Scanner;

public final class CreateDiscountCodeRegex extends Regex {
    CreateDiscountCodeRegex(String commandPatternString, String outputName, int counter) {
        super(commandPatternString, outputName, counter);
        allRegex.add(this);
    }

    static ArrayList<CreateDiscountCodeRegex> allRegex = new ArrayList<>();
    static final CreateDiscountCodeRegex INITIAL_DATE = new
            CreateDiscountCodeRegex("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)(\\d){4}$", "Initial Date", 0);
    static final CreateDiscountCodeRegex FINAL_DATE = new
            CreateDiscountCodeRegex("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)(\\d){4}$", "Final Date", 1);
    static final CreateDiscountCodeRegex DISCOUNT_PERCENTAGE = new
            CreateDiscountCodeRegex("[0-9][0-9]", "Discount Percentage", 2);
    static final CreateDiscountCodeRegex MAX_PRICE = new
            CreateDiscountCodeRegex("\\d+", "Max Price", 3);

    static CreateDiscountCodeRegex getRegexByCounter(int counter) {
        for (CreateDiscountCodeRegex regex : allRegex) {
            if (regex.counter == counter) {
                return regex;
            }
        }
        return null;
    }

    public static ArrayList<String> checkRegex(Scanner scanner) {
        String command;
        ArrayList<String> info = new ArrayList<>();
        for (int counter = 0; counter < 4; ) {
            System.out.println(CreateDiscountCodeRegex.getRegexByCounter(counter).getOutputString());
            if ((command = scanner.nextLine()).matches("back")) {
                System.out.println("back");
                return null;
            }
            if (CreateDiscountCodeRegex.getRegexByCounter(counter).getStringMatcher(command).matches()) {
                info.add(command);
                counter++;
                continue;
            }
            System.out.println("Wrong. Try again.");
        }
        return info;
    }
}


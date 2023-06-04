package ir.ac.kntu.HelperClasses;

import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.product.Genre;
import ir.ac.kntu.models.product.Level;

public class ProductHelper {

    public static <T extends Enum<T>> T getInputEnumData(Class<T> inputenum) {
        System.out.println("Choose : ");
        T[] options = inputenum.getEnumConstants();
        for (int i = 0; i < Genre.values().length; i++) {
            System.out.println(String.valueOf(i) + options[i]);
        }
        String input;
        do {
            input = Scan.getLine();
        } while (checkInputEnum(input, inputenum));
        return options[Integer.parseInt(input)];
    }

    private static <T extends Enum> boolean checkInputEnum(String input, Class<T> inputEnum) {
        if (!input.matches("[0-9]+")) {
            System.out.println("Invalid input try again");
            return true;
        }
        if (Integer.parseInt(input) > inputEnum.getEnumConstants().length - 1) {
            System.out.println("Invalid input try again");
            return true;
        }
        if (Integer.parseInt(input) < 0) {
            System.out.println("Invalid input try again");
            return true;
        }
        return false;
    }


}

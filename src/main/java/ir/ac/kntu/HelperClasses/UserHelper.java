package ir.ac.kntu.HelperClasses;

import ir.ac.kntu.models.Store;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.UserType;

public class UserHelper {
    public static User makeUser(Store storeDB) {
        System.out.println("Pleas enter Username :");
        String username = Scan.getLine();
        System.out.println("Pleas enter password :");
        String password = Scan.getLine().trim();
        System.out.println("Pleas enter phone number :");
        String phoneNumber = Scan.getLine().trim();
        System.out.println("Pleas enter email :");
        String email = Scan.getLine().trim();
        TerminalColor.red();
        if (!phoneNumber.matches("[0-9+]+")) {
            System.out.println("phone number is not Valid!");
            TerminalColor.reset();
            return null;
        }
        if (!email.matches(".*@.*")) {
            System.out.println("email is not Valid!");
            TerminalColor.reset();
            return null;
        }
        if (storeDB.findUserByUsername(username) != null) {
            System.out.println("this username already take !");
            TerminalColor.reset();
            return null;
        }
        if (password.length() < 8) {
            System.out.println("Password length must 8 or more !");
            TerminalColor.reset();
            return null;
        }
        TerminalColor.reset();
        User newUser = new User(username, phoneNumber, email, password, UserType.USER);
        storeDB.addUser(newUser);
        return newUser;
    }
}

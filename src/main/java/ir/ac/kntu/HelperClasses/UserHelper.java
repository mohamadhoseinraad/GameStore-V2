package ir.ac.kntu.HelperClasses;

import ir.ac.kntu.models.Store;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.UserType;
import ir.ac.kntu.utils.Scan;
import ir.ac.kntu.utils.TerminalColor;

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

    public static void showProfile(User user) {
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.cyan();
        System.out.print("| Username     : " + user.getUsername());
        TerminalColor.yellow();
        System.out.print(" (" + user.getScore() + ") ");
        TerminalColor.reset();
        System.out.print("  -----  ");
        TerminalColor.cyan();
        System.out.println(user.getWallet() + "$");
        TerminalColor.yellow();
        System.out.println("| Phone number : " + user.getPhoneNumber());
        System.out.println("| Email        : " + user.getEmail());
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.reset();
    }
}

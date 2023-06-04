package ir.ac.kntu.menu.Admin.User;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.HelperClasses.UserHelper;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.UserType;

import java.util.ArrayList;

public class AdminUserSearch {
    private Store storeDB;

    public AdminUserSearch(Store storeDB) {
        this.storeDB = storeDB;
    }

    private User searchMenu(String name) {
        name = name.trim().toUpperCase();
        ArrayList<User> result = storeDB.findUserByUsernames(name);
        printUserSearchResult(result);
        if (result.size() != 0) {
            return handleSelect(result);
        }
        return null;
    }


    private User usernameSearch() {
        System.out.println("Search User by username : ");
        String name = Scan.getLine().trim().toUpperCase();
        ArrayList<User> result = storeDB.findUserByUsernames(name);
        printUserSearchResult(result);
        if (result.size() != 0) {
            User selectedUser = handleSelect(result);
            if (selectedUser == null) {
                return null;
            }
            AdminEditUserMenu adminEditUserMenu = new AdminEditUserMenu(storeDB,selectedUser);
            adminEditUserMenu.showMenu();
        }
        return null;
    }

    private User emailSearch() {
        System.out.println("Search User by e-mail : ");
        String email = Scan.getLine().trim().toLowerCase();
        ArrayList<User> result = storeDB.findUserByEmail(email);
        printUserSearchResult(result);
        if (result.size() != 0) {
            User selectedUser = handleSelect(result);
            if (selectedUser == null) {
                return null;
            }
            AdminEditUserMenu adminEditUserMenu = new AdminEditUserMenu(storeDB,selectedUser);
            adminEditUserMenu.showMenu();
        }
        return null;
    }

    private User phoneSearch() {
        System.out.println("Search User by phone number : ");
        String phoneNumber = Scan.getLine().trim().toLowerCase();
        ArrayList<User> result = storeDB.findUserByPhoneNumber(phoneNumber);
        printUserSearchResult(result);
        if (result.size() != 0) {
            User selectedUser = handleSelect(result);
            if (selectedUser == null) {
                return null;
            }
            AdminEditUserMenu adminEditUserMenu = new AdminEditUserMenu(storeDB,selectedUser);
            adminEditUserMenu.showMenu();
        }
        return null;
    }


    private User handleSelect(ArrayList<User> searchResult) {
        System.out.println("---- chose number : ");
        String input = Scan.getLine();
        if (!input.matches("[0-9]+")) {
            TerminalColor.red();
            System.out.println("Chose valid number!");
            TerminalColor.reset();
        } else {
            int choose = Integer.parseInt(input) - 1;
            if (choose >= searchResult.size() || choose < 0) {
                TerminalColor.red();
                System.out.println("Chose valid number!");
                TerminalColor.reset();
            } else {
                User user = searchResult.get(choose);
                return user;
            }
        }
        return null;

    }

    private User allUsers() {
        ArrayList<User> result = getAllUsers();
        printUserSearchResult(result);
        if (result.size() != 0) {
            User selectedUser = handleSelect(result);
            if (selectedUser == null) {
                return null;
            }
            AdminEditUserMenu adminEditUserMenu = new AdminEditUserMenu(storeDB,selectedUser);
            adminEditUserMenu.showMenu();
        }
        return null;
    }

    private ArrayList<User> getAllUsers() {
        ArrayList<User> result = new ArrayList<>();
        for (User user : storeDB.getUsers()) {
            if (user.getUserType() != UserType.ADMIN) {
                result.add(user);
            }
        }
        return result;
    }

    private void printUserSearchResult(ArrayList<User> result) {
        if (result.size() == 0) {
            System.out.println("Not found ! :(");
        } else {
            int i = 1;
            for (User user : result) {
                TerminalColor.blue();
                System.out.print(i);
                TerminalColor.yellow();
                System.out.print(" | ");
                TerminalColor.blue();
                System.out.println(user);
                TerminalColor.reset();
                i++;
            }
        }
    }

    public User showMenu() {
        AdminUserSearchOption option;
        while ((option = printMenuOptions("Search User", AdminUserSearchOption.class)) != AdminUserSearchOption.EXIT) {
            if (option != null) {
                switch (option) {
                    case ALL: {
                        return allUsers();
                    }
                    case BY_USERNAME: {
                        return usernameSearch();
                    }
                    case BY_EMAIL: {
                        return emailSearch();
                    }
                    case BY_PHONE_NUMBER: {
                        return phoneSearch();
                    }
                    case ADD_USER:{
                        UserHelper.makeUser(storeDB);
                        break;
                    }
                    case BACK: {
                        break;
                    }
                    default:
                        System.out.println("Invalid choose");
                }
            }
            return null;
        }
        System.exit(0);
        return null;
    }

    private  <T extends Enum<T>> T getOption(Class<T> menuEnum) {
        T[] options = menuEnum.getEnumConstants();
        String choiceStr = Scan.getLine().trim();
        int choice = -1;
        if (choiceStr.matches("[0-9]+")) {
            choice = Integer.parseInt(choiceStr) - 1;
        }

        if (choice >= 0 && choice < options.length) {
            return options[choice];
        }
        TerminalColor.red();
        System.out.println("Wrong choice !");
        TerminalColor.reset();
        return null;
    }

    private  <T extends Enum<T>> T printMenuOptions(String title, Class<T> menuEnum) {
        TerminalColor.cyan();
        System.out.println("----------" + title + "----------");
        TerminalColor.reset();
        T[] options = menuEnum.getEnumConstants();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + " - " + options[i]);
        }
        System.out.print("Enter your choice : ");
        return getOption(menuEnum);
    }
}


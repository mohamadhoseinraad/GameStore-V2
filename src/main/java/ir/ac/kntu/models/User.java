package ir.ac.kntu.models;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.models.product.Game;

import java.util.*;

public class User {

    private static int countUser;

    private final String id;

    private String username;

    private String phoneNumber;

    private String email;

    private int hashPassword;

    private double wallet;

    public final UserType userType;

    private Map<String, String> library;

    private ArrayList<String> friends;

    private ArrayList<String> requests;

    public User(String username, String phoneNumber, String email, String password, UserType type) {
        this.username = username.toUpperCase().trim();
        this.phoneNumber = phoneNumber.trim();
        this.email = email.toLowerCase().trim();
        hashPassword = password.hashCode();
        wallet = 0;
        library = new HashMap<>();
        friends = new ArrayList<>();
        requests = new ArrayList<>();
        userType = type;
        if (userType == UserType.USER) {
            id = "USR" + countUser++;
        } else {
            id = "ADM" + countUser++;
        }
    }

    public String getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim().toUpperCase();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public boolean setNewPassword(String newPassword, String oldPassword) {

        if (oldPassword.hashCode() != hashPassword) {
            return false;
        }
        hashPassword = newPassword.hashCode();
        return true;
    }

    public boolean checkPassword(String password) {
        if (password.hashCode() == hashPassword) {
            return true;
        }
        return false;
    }

    public double getWallet() {
        return wallet;
    }

    public void chargeWallet(double value) {
        wallet += value;
    }

    public boolean payWallet(double price) {
        if (wallet < price) {
            return false;
        }
        wallet -= price;
        return true;
    }

    public Map<String, String> getLibrary() {
        return library;
    }

    public boolean addGame(Game game) {
        if (game == null) {
            return false;
        }
        if (!library.containsKey(game.getId()) && wallet >= game.getPrice()) {
            library.put(game.getId(), game.getName());
            wallet -= game.getPrice();
            return true;
        }
        return false;
    }

    public void addCostumeGame(Game game) {
        library.put(game.getId(), game.getName());
    }

    public boolean giftGame(Game game, User friend) {
        if (!friend.getLibrary().containsKey(game.getId()) && wallet >= game.getPrice()) {
            friend.addCostumeGame(game);
            wallet -= game.getPrice();
            return true;
        }
        return false;
    }

    public boolean doHaveGame(Game game) {
        return library.containsKey(game.getId());
    }

    public boolean isFriend(String userId) {
        return friends.contains(userId);
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public ArrayList<User> getFriendsList(Store storeDB) {
        ArrayList<User> friendsList = new ArrayList<>();
        ArrayList<User> allUsers = storeDB.getUsers();
        for (User user : allUsers) {
            if (friends.contains(user.getId())) {
                friendsList.add(user);
            }
        }

        return friendsList;
    }

    public ArrayList<User> getUserNotFriend(Store storeDB) {
        ArrayList<User> friendsList = new ArrayList<>();
        ArrayList<User> allUsers = storeDB.getUsers();
        for (User user : allUsers) {
            if (!friends.contains(user.getId()) && user.getUserType() == UserType.USER) {
                friendsList.add(user);
            }
        }
        friendsList.remove(this);
        return friendsList;
    }


    public boolean addFriend(User user) {
        if (friends.contains(user.getId())) {
            return false;
        }
        friends.add(user.getId());
        requests.remove(user.getId());
        return true;
    }

    public boolean removeFriend(User user) {
        if (friends.contains(user.getId())) {
            friends.remove(user.getId());
            return true;
        }
        return false;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }

    public boolean addRequest(User someUser) {
        if (friends.contains(someUser.getId())) {
            return false;
        }
        if (requests.contains(someUser.getId())) {
            return false;
        }
        return requests.add(someUser.getId());
    }

    public void removeRequest(User user) {
        requests.remove(user.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return username.equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }


    @Override
    public String toString() {
        return "Username :" + username + " | Phone number : " + phoneNumber + " | Email : " + email + lastGameName();
    }

    public void showProfile() {
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.cyan();
        System.out.print("| Username     : " + username);
        TerminalColor.reset();
        System.out.print("  -----  ");
        TerminalColor.cyan();
        System.out.println(wallet + "$");
        TerminalColor.yellow();
        System.out.println("| Phone number : " + phoneNumber);
        System.out.println("| Email        : " + email);
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.reset();
    }

    private String lastGameName() {

        String result = " | 5 last Game  ";
        if (library.size() != 0) {
            int i = 5;
            if (library.size() < 5) {
                i = library.size();
            }
            for (; i > 0; i--) {
                result += i + " - " + library.get(library.size() - i) + "   ";
            }
            return result;
        }
        return " | No Games";
    }

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

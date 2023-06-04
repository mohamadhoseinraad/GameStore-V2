package ir.ac.kntu.models;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.models.product.Game;

import java.util.*;

public class User {

    private static int countUser = 0;

    private final String id;

    private String username;

    private String phoneNumber;

    private String email;

    private int hashPassword;

    private double wallet = 0;

    private int score = 0;

    private Date timeEntered = new Date();

    private Date timeExit = new Date();

    public final UserType userType;

    private Map<String, String> library = new HashMap<>();

    private ArrayList<String> friends = new ArrayList<>();

    private ArrayList<String> requests = new ArrayList<>();

    public User(String username, String phoneNumber, String email, String password, UserType type) {
        this.username = username.toUpperCase().trim();
        this.phoneNumber = phoneNumber.trim();
        this.email = email.toLowerCase().trim();
        hashPassword = password.hashCode();
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

    public Date getTimeEntered() {
        return timeEntered;
    }

    public void setTimeEntered(Date timeEntered) {
        this.timeEntered = timeEntered;
    }

    public Date getTimeExit() {
        return timeExit;
    }

    public void setTimeExit(Date timeExit) {
        this.timeExit = timeExit;
    }

    public boolean isLogin(String password) {
        if (checkPassword(password)) {
            timeEntered.setTime(System.currentTimeMillis());
            timeExit.setTime(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    public void isLogout() {
        if (timeEntered.equals(timeExit)) {
            timeExit.setTime(System.currentTimeMillis());
            long difference = timeExit.getTime() - timeEntered.getTime();
            difference /= 60000;
            score += difference;
        }
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
        return "Username :" + username + " | Phone number : " + phoneNumber + " | Email : " + email + " | Score : " + score + lastGameName();
    }

    public void showProfile() {
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.cyan();
        System.out.print("| Username     : " + username);
        TerminalColor.yellow();
        System.out.print(" (" + score + ") ");
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

}

package ir.ac.kntu.models;

import ir.ac.kntu.models.product.Game;

import java.util.*;

public class Store {
    private ArrayList<User> users;

    private ArrayList<Game> games;

    public Store(Set<User> users, Set<Game> games) {
        this.users = new ArrayList<>(users);
        this.games = new ArrayList<>(games);
    }

    public Store() {
        users = new ArrayList<>();
        games = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void setUsers(ArrayList<User> users) {
        this.users = new ArrayList<>(users);
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = new ArrayList<>(games);
    }

    public User findUserByUsername(String username) {
        username = username.toUpperCase();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> findUserByPhoneNumber(String phoneNumber) {
        ArrayList<User> result = new ArrayList<>();
        for (User u : users) {
            if (u.getPhoneNumber().compareTo(phoneNumber) >= 0 && u.getUserType() != UserType.ADMIN) {
                result.add(u);
            }
        }
        return result;
    }

    public Game findGame(int id, String name) {
        for (Game game : games) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }

    public ArrayList<Game> findGameByName(String name) {
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : games) {
            if (game.getName().startsWith(name)) {
                result.add(game);
            }
        }
        return result;
    }

    public ArrayList<Game> findGameByPrice(double basePrice, double maxPrice) {
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : games) {
            if (game.getPrice() >= basePrice && game.getPrice() <= maxPrice) {
                result.add(game);
            }
        }
        return result;
    }

    public ArrayList<User> findUserByEmail(String email) {
        ArrayList<User> result = new ArrayList<>();
        for (User u : users) {
            if (u.getEmail().startsWith(email) && u.getUserType() != UserType.ADMIN) {
                result.add(u);
            }
        }
        return result;
    }

    public ArrayList<User> findUserByUsernames(String username) {
        ArrayList<User> result = new ArrayList<>();
        for (User u : users) {
            if (u.getUsername().startsWith(username) && u.getUserType() != UserType.ADMIN) {
                result.add(u);
            }
        }
        return result;
    }

    public boolean addGame(Game newGame) {
        if (newGame == null) {
            return false;
        }
        if (findGame(newGame.getId(), newGame.getName()) == null) {
            games.add(newGame);
            return true;
        }
        return false;
    }

    public boolean removeGame(Game game) {
        if (games.contains(game)) {
            games.remove(game);
            return true;
        }
        return false;
    }

    public boolean addUser(User newUser) {
        if (newUser == null) {
            return false;
        }
        return users.add(newUser);
    }

    public boolean removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
            return true;
        }
        return false;
    }

    public boolean loginUser(String username, String password) {
        username = username.toUpperCase().trim();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.checkPassword(password);
            }
        }
        return false;
    }

    public boolean addAdmin(String username, String password) {
        User admin = new User(username, "", "", password, UserType.ADMIN);
        return users.add(admin);
    }
}

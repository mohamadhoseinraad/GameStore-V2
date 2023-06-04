package ir.ac.kntu.models;

import ir.ac.kntu.models.product.games.Game;
import ir.ac.kntu.models.product.Product;
import ir.ac.kntu.models.product.ProductType;
import ir.ac.kntu.models.product.accessories.Accessory;

import java.util.*;

public class Store {
    private ArrayList<User> users;

    //private ArrayList<Game> games;

    private HashMap<ProductType, ArrayList<Product>> products = new HashMap<>();

    public Store(Set<User> users, HashMap<ProductType, ArrayList<Product>> data) {
        this.users = new ArrayList<>(users);
        this.products = new HashMap<>(data);
    }

    public Store() {
        users = new ArrayList<>();
        products.put(ProductType.GAME, new ArrayList<>());
        products.put(ProductType.ACCESSORIES, new ArrayList<>());
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void setUsers(ArrayList<User> users) {
        this.users = new ArrayList<>(users);
    }

    public ArrayList<Game> getGames() {
        ArrayList<Game> games = new ArrayList<>();
        for (Product p : products.get(ProductType.GAME)) {
            games.add((Game) p);
        }
        return games;
    }

    public ArrayList<Accessory> getAccessories() {
        ArrayList<Accessory> accessories = new ArrayList<>();
        for (Product p : products.get(ProductType.ACCESSORIES)) {
            accessories.add((Accessory) p);
        }
        return accessories;
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> productsList = new ArrayList<>();
        for (Product p : products.get(ProductType.GAME)) {
            productsList.add(p);
        }
        for (Product p : products.get(ProductType.ACCESSORIES)) {
            productsList.add(p);
        }
        return productsList;
    }


    public void setGames(ArrayList<Game> games) {
        products.put(ProductType.GAME, new ArrayList<>(games));
    }

    public User findUserByUsername(String username) {
        if (username == null) {
            return null;
        }
        username = username.toUpperCase();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User findUserById(String userId) {
        userId = userId.toUpperCase();
        for (User user : users) {
            if (user.getId().equals(userId)) {
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

    public Product findProduct(String id) {
        for (Product product : getProducts()) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> findProductByName(String name) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : getProducts()) {
            if (product.getName().startsWith(name)) {
                result.add(product);
            }
        }
        return result;
    }

    public ArrayList<Product> findProductByPrice(double basePrice, double maxPrice) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : getProducts()) {
            if (product.getPrice() >= basePrice && product.getPrice() <= maxPrice) {
                result.add(product);
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

    public boolean addProduct(Product newProduct) {
        if (newProduct == null) {
            return false;
        }
        if (findProduct(newProduct.getId()) == null) {
            if (newProduct.getClass() == Game.class) {
                products.get(ProductType.GAME).add(newProduct);
            } else {
                products.get(ProductType.ACCESSORIES).add(newProduct);
            }
            return true;
        }
        return false;
    }

    public boolean removeGame(Game game) {
        if (products.get(ProductType.GAME).contains(game)) {
            products.get(ProductType.GAME).remove(game);
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
                return user.isLogin(password);
            }
        }
        return false;
    }

    public boolean addAdmin(String username, String password) {
        User admin = new User(username, "", "", password, UserType.ADMIN);
        return users.add(admin);
    }


}

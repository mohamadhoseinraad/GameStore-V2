package ir.ac.kntu.HelperClasses;

import ir.ac.kntu.models.*;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.product.Genre;
import ir.ac.kntu.models.product.Level;
import ir.ac.kntu.models.product.accessories.Connection;
import ir.ac.kntu.models.product.accessories.Device;
import ir.ac.kntu.models.product.accessories.GamePad;

public class DefaultData {
    public static Store addDefaultData() {
        Store store = new Store();
        User user1 = new User("1", "09934140117", "mh.shbanirad@icloud.com", "1", UserType.USER);
        User user2 = new User("mo.gamer", "09934140117", "mogamer@gmail.com", "12341234", UserType.USER);
        User amin = new User("admin", "", "", "admin", UserType.ADMIN);
        Game game1 = new Game("Fortnite", "Battle royall action game", 0, Genre.SHOOTING, Level.LEVEL_1);
        Game g2 = new Game("Rainbow six", "Action shooter game", 20, Genre.SHOOTING, Level.LEVEL_1);
        Game g3 = new Game("GTA V", "Story mode game form al life of a person", 35, Genre.SHOOTING, Level.LEVEL_1);
        Game g4 = new Game("Bomb", "Strategy game ", 0, Genre.STRATEGY, Level.LEVEL_4);
        GamePad gp1 = new GamePad("Ps4GP", "For PS4 , PS5", 100, 3, Connection.WIRELESS, Device.PLAY_STATION);
        store.addUser(user1);
        store.addUser(user2);
        store.addUser(amin);
        store.addProduct(game1);
        store.addProduct(g2);
        store.addProduct(g3);
        store.addProduct(g4);
        store.addProduct(gp1);
        user1.chargeWallet(100);
        Community community = new Community(user1.getUsername(), "Awlliii");
        game1.addCommunity(community);
        user1.addProduct(game1);
        user1.addFriend(user2);
        user2.addFriend(user1);
        user1.setScore(30);
        return store;
    }
}

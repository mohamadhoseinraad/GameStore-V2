package ir.ac.kntu.HelperClasses;

import ir.ac.kntu.models.*;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.product.Genre;
import ir.ac.kntu.models.product.Level;

public class DefaultData {
    public static Store addDefaultData() {
        Store store = new Store();
        User user1 = new User("moohraad", "09934140117", "mh.shbanirad@icloud.com", "123qweasd", UserType.USER);
        User user2 = new User("mo.gamer", "09934140117", "mogamer@gmail.com", "12341234", UserType.USER);
        User amin = new User("admin", "", "", "admin", UserType.ADMIN);
        Game game1 = new Game("Fortnite", "Battle royall action game", 0, Genre.SHOOTING, Level.LEVEL_1);
        Game g2 = new Game("Rainbow six", "Action shooter game", 20, Genre.SHOOTING, Level.LEVEL_1);
        Game g3 = new Game("GTA V", "Story mode game form al life of a person", 35, Genre.SHOOTING, Level.LEVEL_1);
        Game g4 = new Game("Bomb", "Strategy game ", 0, Genre.STRATEGY, Level.LEVEL_1);
        store.addUser(user1);
        store.addUser(user2);
        store.addUser(amin);
        store.addGame(game1);
        store.addGame(g2);
        store.addGame(g3);
        user1.chargeWallet(100);
        Community community = new Community(user1.getUsername(), "Awlliii");
        game1.addCommunity(community);
        user1.addGame(game1);
        user1.addFriend(user2);
        user2.addFriend(user1);
        return store;
    }
}

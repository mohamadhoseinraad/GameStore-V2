package ir.ac.kntu.HelperClasses;

import ir.ac.kntu.models.Store;
import ir.ac.kntu.models.Community;
import ir.ac.kntu.models.Game;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.UserType;

public class DefaultData {
    public static Store addDefaultData(){
        Store store = new Store();
        User user1 = new User("moohraad", "09934140117", "mh.shbanirad@icloud.com", "123qweasd", UserType.USER);
        User user2 = new User("mo.gamer", "09934140117", "mogamer@gmail.com", "12341234", UserType.USER);
        User amin = new User("admin", "", "", "admin", UserType.ADMIN);
        Game game1 = new Game("Fortnite", "Battle royall action game", "Action", 0);
        Game g2 = new Game("Rainbow six", "Action shooter game", "Action", 25);
        Game g3 = new Game("GTA V", "Story mode game form al life of a person", "Story", 35);
        Game g4 = new Game("Bomb", "Strategy game ", "Thinking", 0);
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

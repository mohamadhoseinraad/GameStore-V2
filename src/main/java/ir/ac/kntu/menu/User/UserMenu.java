package ir.ac.kntu.menu.User;

import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.User.Friend.UserFriendMenu;
import ir.ac.kntu.menu.User.Library.UserLibrary;
import ir.ac.kntu.menu.User.Profile.ProfileMenu;
import ir.ac.kntu.menu.User.Store.UserStore;
import ir.ac.kntu.models.User;

public class UserMenu extends Menu {

    private Store storeDB;

    private User user;

    public UserMenu(Store store, User user) {
        this.storeDB = store;
        this.user = user;
    }

    @Override
    public void showMenu() {
        UserMenuOption option;
        TerminalColor.purple();
        System.out.println("Welcome " + user.getUsername());
        TerminalColor.reset();
        while ((option = printMenuOptions("User Menu", UserMenuOption.class)) != UserMenuOption.EXIT) {
            if (option != null) {
                switch (option) {
                    case PROFILE: {
                        profile();
                        break;
                    }
                    case STORE: {
                        store();
                        break;
                    }
                    case LIBRARY: {
                        library();
                        break;
                    }
                    case FRIENDS: {
                        friends();
                        break;
                    }
                    case LOGOUT:
                        System.out.println("Back soon :)");
                        return;
                    default:
                        System.out.println("Invalid choose");
                }
            }
        }
        System.exit(0);
    }

    public void profile() {
        ProfileMenu profileMenu = new ProfileMenu(storeDB, user);
        profileMenu.showMenu();
    }

    public void store() {
        UserStore userStore = new UserStore(storeDB, user);
        userStore.showMenu();
    }

    public void library() {
        UserLibrary userLibrary = new UserLibrary(storeDB, user);
        userLibrary.showMenu();
    }

    public void friends() {
        UserFriendMenu userFriendMenu = new UserFriendMenu(storeDB, user);
        userFriendMenu.showMenu();
    }
}

package ir.ac.kntu.menu.Admin;

import ir.ac.kntu.models.Store;
import ir.ac.kntu.menu.Admin.Game.AdminGamesMenu;
import ir.ac.kntu.menu.Admin.User.AdminUserSearch;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.User;

public class AdminMenu extends Menu {

    private Store storeDB;

    private User admin;

    public AdminMenu(Store store, User admin) {
        this.storeDB = store;
        this.admin = admin;
    }

    @Override
    public void showMenu() {
        AdminMenuOption option;
        while ((option = printMenuOptions("Amin Menu", AdminMenuOption.class)) != AdminMenuOption.EXIT) {
            if (option != null) {
                switch (option) {
                    case USERS: {
                        users();
                        break;
                    }
                    case GAMES: {
                        games();
                        break;
                    }
                    case LOGOUT: {
                        return;
                    }
                    default:
                        System.out.println("Invalid choose");
                }
            }
        }
        System.exit(0);
    }

    private void users() {
        AdminUserSearch adminUserSearch = new AdminUserSearch(storeDB);
        adminUserSearch.showMenu();

    }

    private void games() {
        AdminGamesMenu adminGamesMenu = new AdminGamesMenu(storeDB, admin);
        adminGamesMenu.showMenu();
    }
}

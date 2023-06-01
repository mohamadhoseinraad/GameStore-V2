package ir.ac.kntu.menu.User.Store;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.Game;
import ir.ac.kntu.models.User;

public class GameStoreMenu extends Menu {

    private User currentUser;

    private Game currentGame;

    private Store storeDB;

    public GameStoreMenu(User currentUser, Game currentGame, Store storeDB) {
        this.currentUser = currentUser;
        this.currentGame = currentGame;
        this.storeDB = storeDB;
    }

    @Override
    public void showMenu() {
        GameStoreMenuOptions option;
        while (printGame() && (option = printMenuOptions(currentGame.getName(), GameStoreMenuOptions.class)) != GameStoreMenuOptions.EXIT) {
            switch (option) {
                case BUY: {
                    buy();
                    break;
                }
                case GIFT: {
                    gift();
                    break;
                }
                case BACK: {
                    return;
                }
                default: {
                    break;
                }
            }
        }
        System.exit(0);
    }

    public boolean printGame() {
        currentGame.showGame();
        return true;
    }

    public void buy() {
        if (currentUser.doHaveGame(currentGame)) {
            TerminalColor.red();
            System.out.println("You already have this game!");
            TerminalColor.reset();
            return;
        }
        if (currentUser.addGame(currentGame)) {
            TerminalColor.green();
            System.out.println("Buy Successfully :) ");
            TerminalColor.reset();
            return;
        }
        TerminalColor.red();
        System.out.println("You don't have enough money ! :(");
        TerminalColor.reset();
    }

    public void rate() {

    }

    public void gift() {
        System.out.println("Enter username you want to gift game : ");
        String friendUsername = Scan.getLine().trim().toUpperCase();
        if (!currentUser.isFriend(friendUsername)) {
            TerminalColor.red();
            System.out.println("This account is not your friend!");
            TerminalColor.reset();
            return;
        }
        User friend = storeDB.findUserByUsername(friendUsername);
        if (friend == null) {
            TerminalColor.red();
            System.out.println("Not found");
            TerminalColor.reset();
            return;
        }

        if (friend.doHaveGame(currentGame)) {
            TerminalColor.red();
            System.out.println("Your friend already has this game!");
            TerminalColor.reset();
            return;
        }
        if (currentUser.giftGame(currentGame, friend)) {
            TerminalColor.green();
            System.out.println("Gift Successfully :) ");
            TerminalColor.reset();
            return;
        }
        TerminalColor.red();
        System.out.println("You don't have enough money ! :(");
        TerminalColor.reset();
    }

    public void comment() {
    }
}

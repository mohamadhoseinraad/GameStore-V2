package ir.ac.kntu.menu.User.Store;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.User;

import java.util.ArrayList;

public class UserStore extends Menu {

    private Store storeDB;

    private User currentUser;

    public UserStore(Store store, User user) {
        this.storeDB = store;
        this.currentUser = user;
    }

    @Override
    public void showMenu() {
        UserStoreOptions option;
        while ((option = printMenuOptions("Store", UserStoreOptions.class)) != UserStoreOptions.EXIT) {
            if (option != null) {
                switch (option) {
                    case ALL: {
                        allGame();
                        break;
                    }
                    case BY_NAME: {
                        searchByName();
                        break;
                    }
                    case BY_PRICE: {
                        searchBPrice();
                        break;
                    }
                    case BACK: {
                        return;
                    }
                    default:
                        System.out.println("Invalid choose");
                }
            }
        }
        System.exit(0);
    }

    public void allGame() {
        ArrayList<Game> result = getAllGames();
        if (result.size() != 0) {
            Game selectedGame = handleSelect(result);
            if (selectedGame == null) {
                return;
            }
            GameStoreMenu gameStoreMenu = new GameStoreMenu(currentUser, selectedGame, storeDB);
            gameStoreMenu.showMenu();
        }
    }

    private ArrayList<Game> getAllGames() {
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : storeDB.getGames()) {
            result.add(game);
        }
        return result;
    }

    public void searchByName() {
        System.out.println("Search Name of game : ");
        String name = Scan.getLine().trim().toUpperCase();
        ArrayList<Game> result = storeDB.findGameByName(name);
        Game selectedGame = handleSelect(result);
        if (selectedGame == null) {
            return;
        }
        GameStoreMenu gameStoreMenu = new GameStoreMenu(currentUser, selectedGame, storeDB);
        gameStoreMenu.showMenu();

    }

    public void searchBPrice(){
        System.out.println("from : ");
        String basePriceStr = Scan.getLine().trim();
        System.out.println("to : ");
        String maxPriceStr = Scan.getLine().trim();
        if (!maxPriceStr.matches("[0-9][0-9.]*") || !basePriceStr.matches("[0-9][0-9.]*")){
            TerminalColor.red();
            System.out.println("Enter valid amount!");
            TerminalColor.reset();
            return;
        }
        double basePrice = Double.parseDouble(basePriceStr);
        double maxPrice = Double.parseDouble(maxPriceStr);
        ArrayList<Game> result = storeDB.findGameByPrice(basePrice, maxPrice);
        Game selectedGame = handleSelect(result);
        if (selectedGame == null) {
            return;
        }
        GameStoreMenu gameStoreMenu = new GameStoreMenu(currentUser, selectedGame, storeDB);
        gameStoreMenu.showMenu();
    }

    public Game handleSelect(ArrayList<Game> searchResult) {
        while (true) {
            printGameSearchResult(searchResult);
            if (searchResult.size() == 0){
                return null;
            }
            System.out.println("---- chose number : (0 to back )");
            String input = Scan.getLine();
            if (!input.matches("[0-9]+")) {
                TerminalColor.red();
                System.out.println("Chose valid number!");
                TerminalColor.reset();
            } else {
                int choose = Integer.parseInt(input) - 1;
                if (choose == -1) {
                    return null;
                }
                if (choose >= searchResult.size() || choose < 0) {
                    TerminalColor.red();
                    System.out.println("Chose valid number!");
                    TerminalColor.reset();
                } else {
                    Game game = searchResult.get(choose);
                    return game;
                }
            }
        }
    }

    private void printGameSearchResult(ArrayList<Game> result) {
        if (result.size() == 0) {
            System.out.println("Not found ! :(");
        } else {
            int i = 1;
            for (Game game : result) {
                TerminalColor.blue();
                System.out.print(i);
                TerminalColor.yellow();
                System.out.print(" | ");
                TerminalColor.blue();
                System.out.println(game);
                TerminalColor.reset();
                i++;
            }
        }
    }
}

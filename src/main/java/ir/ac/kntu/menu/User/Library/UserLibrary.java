package ir.ac.kntu.menu.User.Library;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.Game;
import ir.ac.kntu.models.User;

import java.util.ArrayList;
import java.util.Map;

public class UserLibrary extends Menu {

    private Store storeDB;

    private User currentUser;

    private ArrayList<Game> userLibrary;

    public UserLibrary(Store store, User user) {
        this.storeDB = store;
        this.currentUser = user;
        userLibrary = getAllGames();
    }

    @Override
    public void showMenu() {
        UseLibraryOptions option;
        while ((option = printMenuOptions("Library", UseLibraryOptions.class)) != UseLibraryOptions.EXIT) {
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
                    case EXPORT_LIBRARY_TO_HTML: {
                        exportHtml();
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

    private void allGame() {
        Game selectedGame = handleSelect(userLibrary);
        if (selectedGame == null) {
            return;
        }
        GameLibraryMenu gameLibraryMenu = new GameLibraryMenu(currentUser, selectedGame, storeDB);
        gameLibraryMenu.showMenu();
    }

    private void exportHtml() {
        ExportUserGames exportUserGames = new ExportUserGames(storeDB,currentUser);
        exportUserGames.showMenu();
    }

    private ArrayList<Game> getAllGames() {
        ArrayList<Game> result = new ArrayList<>();
        for (Map.Entry<Integer, String> gameName : currentUser.getLibrary().entrySet()) {
            Game game = storeDB.findGame(gameName.getKey(), gameName.getValue());
            result.add(game);
        }
        return result;
    }

    private void searchByName() {
        System.out.println("Search Name of game : ");
        String name = Scan.getLine().trim().toUpperCase();
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : userLibrary) {
            if (game.getName().startsWith(name)) {
                result.add(game);
            }
        }
        Game selectedGame = handleSelect(result);
        if (selectedGame == null) {
            return;
        }
        GameLibraryMenu gameLibraryMenu = new GameLibraryMenu(currentUser, selectedGame, storeDB);
        gameLibraryMenu.showMenu();

    }

    private Game handleSelect(ArrayList<Game> searchResult) {
        while (true) {
            printGameSearchResult(searchResult);
            if (searchResult.size() == 0) {
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

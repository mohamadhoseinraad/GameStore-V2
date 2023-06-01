package ir.ac.kntu.menu;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.models.Game;

import java.util.ArrayList;

public class GameSearch {
    private Store storeDB;

    public GameSearch(Store storeDB) {
        this.storeDB = storeDB;
    }

    public Game searchMenu(String name){
        name = name.trim().toUpperCase();
        ArrayList<Game> result = storeDB.findGameByName(name);
        printGameSearchResult(result);
        if (result.size() != 0){
            return handleSelect(result);
        }
        return null;
    }


    public Game searchMenu() {
        System.out.println("Search Name of game : ");
        String name = Scan.getLine().trim().toUpperCase();
        ArrayList<Game> result = storeDB.findGameByName(name);
        printGameSearchResult(result);
        if (result.size() != 0){
            return handleSelect(result);
        }
        return null;
    }

    private Game handleSelect(ArrayList<Game> searchResult) {
        System.out.println("---- chose number : ");
        String input = Scan.getLine();
        if (!input.matches("[0-9]+")) {
            TerminalColor.red();
            System.out.println("Chose valid number!");
            TerminalColor.reset();
        }else {
            int choose = Integer.parseInt(input) - 1;
            if (choose >= searchResult.size() || choose < 0) {
                TerminalColor.red();
                System.out.println("Chose valid number!");
                TerminalColor.reset();
            } else {
                Game game = searchResult.get(choose);
                return game;
            }
        }
        return null;

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


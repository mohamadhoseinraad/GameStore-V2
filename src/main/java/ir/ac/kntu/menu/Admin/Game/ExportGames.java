package ir.ac.kntu.menu.Admin.Game;

import ir.ac.kntu.HelperClasses.GenerateHTML;
import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.product.Game;

import java.util.ArrayList;

public class ExportGames extends Menu {

    private Store storeDB;



    public ExportGames(Store store) {
        this.storeDB = store;
    }

    @Override
    public void showMenu() {
        ExportGamesOptions option;
        while ((option = printMenuOptions("Export html", ExportGamesOptions.class)) != ExportGamesOptions.EXIT) {
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

    private void allGame() {
        ArrayList<Game> result = getAllGames();
        String title = "All Game in Store";
        GenerateHTML.generateHTML(title, convertArray(result));
    }

    private ArrayList<Game> getAllGames() {
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : storeDB.getGames()) {
            result.add(game);
        }
        return result;
    }

    private void searchByName() {
        System.out.println("Search Name of game : ");
        String name = Scan.getLine().trim().toUpperCase();
        ArrayList<Game> result = storeDB.findGameByName(name);
        GenerateHTML.generateHTML("Games with "+name+"in their name", convertArray(result));

    }

    private ArrayList<Object> convertArray(ArrayList<Game> input){
        ArrayList<Object> result = new ArrayList<>();
        for (Game game : input){
            result.add(game);
        }
        return result;
    }

    private void searchBPrice(){
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
        String title = "Games filter by price from " + basePrice + "to" +maxPrice;
        GenerateHTML.generateHTML(title, convertArray(result));
    }

}

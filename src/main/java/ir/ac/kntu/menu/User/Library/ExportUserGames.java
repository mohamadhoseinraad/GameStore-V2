package ir.ac.kntu.menu.User.Library;

import ir.ac.kntu.HelperClasses.GenerateHTML;
import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.User;

import java.util.ArrayList;
import java.util.Map;

public class ExportUserGames extends Menu {

    private Store storeDB;

    private User currentUser;

    private ArrayList<Game> library;



    public ExportUserGames(Store store, User user) {
        this.storeDB = store;
        currentUser = user;
        library = getAllGames();
    }

    private ArrayList<Game> getAllGames() {
        ArrayList<Game> result = new ArrayList<>();
        for (Map.Entry<Integer, String> gameName : currentUser.getLibrary().entrySet()) {
            Game game = storeDB.findGame(gameName.getKey(), gameName.getValue());
            result.add(game);
        }
        return result;
    }

    @Override
    public void showMenu() {
        ExportUserGamesOptions option;
        while ((option = printMenuOptions("Export html", ExportUserGamesOptions.class)) != ExportUserGamesOptions.EXIT) {
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
        String title = "All Game " + currentUser.getUsername();
        GenerateHTML.generateHTML(title, convertArray(result));
    }


    private void searchByName() {
        System.out.println("Search Name of game : ");
        String name = Scan.getLine().trim().toUpperCase();
        ArrayList<Game> result = nameFilter(name);
        GenerateHTML.generateHTML(currentUser.getUsername()+" Games with "+name+"in their name", convertArray(result));

    }

    private ArrayList<Game> nameFilter(String name){
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : library){
            if (game.getName().startsWith(name)){
                result.add(game);
            }
        }
        return result;
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
        ArrayList<Game> result = priceFilter(basePrice, maxPrice);
        String title = currentUser.getUsername()+" Games filter by price from " + basePrice + "to" +maxPrice;
        GenerateHTML.generateHTML(title, convertArray(result));
    }

    private ArrayList<Game> priceFilter(double min, double max){
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : library){
            if (game.getPrice() >= min && game.getPrice() <= max){
                result.add(game);
            }
        }
        return result;
    }

}

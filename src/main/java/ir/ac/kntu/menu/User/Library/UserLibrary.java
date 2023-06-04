package ir.ac.kntu.menu.User.Library;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.product.Product;
import ir.ac.kntu.models.product.accessories.Accessory;

import java.util.ArrayList;
import java.util.Map;

public class UserLibrary extends Menu {

    private Store storeDB;

    private User currentUser;

    private ArrayList<Product> userLibrary;

    private ArrayList<Game> userGames;

    private ArrayList<Accessory> userAccessory;

    public UserLibrary(Store store, User user) {
        this.storeDB = store;
        this.currentUser = user;
        userLibrary = getAllLibrary();
    }

    @Override
    public void showMenu() {
        UseLibraryOptions option;
        while ((option = printMenuOptions("Library", UseLibraryOptions.class)) != UseLibraryOptions.EXIT) {
            if (option != null) {
                switch (option) {
                    case ALL: {
                        allLibrary();
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

    private void allLibrary() {
        Product selectedProduct = handleSelect(userLibrary);
        if (selectedProduct == null) {
            return;
        }
        startProductMenu(selectedProduct);

    }

    private void exportHtml() {
        ExportUserGames exportUserGames = new ExportUserGames(storeDB, currentUser);
        exportUserGames.showMenu();
    }

    private ArrayList<Product> getAllLibrary() {
        ArrayList<Product> result = new ArrayList<>();
        for (Map.Entry<String, String> productName : currentUser.getLibrary().entrySet()) {
            Product product = storeDB.findProduct(productName.getKey());
            result.add(product);
        }
        return result;
    }

    private void searchByName() {
        System.out.println("Search Name of game : ");
        String name = Scan.getLine().trim().toUpperCase();
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : userLibrary) {
            if (product.getName().startsWith(name)) {
                result.add(product);
            }
        }
        Product selectedProduct = handleSelect(result);
        if (selectedProduct == null) {
            return;
        }
        startProductMenu(selectedProduct);
    }

    private Product handleSelect(ArrayList<Product> searchResult) {
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
                    Product product = searchResult.get(choose);
                    return product;
                }
            }
        }
    }

    private void printGameSearchResult(ArrayList<Product> result) {
        if (result.size() == 0) {
            System.out.println("Not found ! :(");
        } else {
            int i = 1;
            for (Product product : result) {
                TerminalColor.blue();
                System.out.print(i);
                TerminalColor.yellow();
                System.out.print(" | ");
                TerminalColor.blue();
                System.out.println(product);
                TerminalColor.reset();
                i++;
            }
        }
    }

    private void startProductMenu(Product selectedProduct) {
        switch (selectedProduct.getProductType()) {

            case GAME: {
                GameLibraryMenu gameLibraryMenu = new GameLibraryMenu(currentUser, (Game) selectedProduct, storeDB);
                gameLibraryMenu.showMenu();
                break;
            }
            case ACCESSORIES: {
                AccessoryLibraryMenu accessoryLibraryMenu = new AccessoryLibraryMenu(currentUser, (Accessory) selectedProduct, storeDB);
                accessoryLibraryMenu.showMenu();
                break;
            }
            default: {

            }
        }
    }
}

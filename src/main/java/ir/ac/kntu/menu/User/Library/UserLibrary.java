package ir.ac.kntu.menu.User.Library;

import ir.ac.kntu.HelperClasses.SelectItemHelper;
import ir.ac.kntu.HelperClasses.StoreHelperClass;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.product.Product;
import ir.ac.kntu.models.product.accessories.Accessory;

import java.util.ArrayList;

public class UserLibrary extends Menu {

    private final Store storeDB;

    private final User currentUser;

    private final ArrayList<Product> userLibrary;

    public UserLibrary(Store store, User user) {
        this.storeDB = store;
        this.currentUser = user;
        userLibrary = StoreHelperClass.getAllLibrary(storeDB, currentUser);
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
        Product selectedProduct = SelectItemHelper.handleSelect(userLibrary);
        if (selectedProduct == null) {
            return;
        }
        startProductMenu(selectedProduct);

    }

    private void exportHtml() {
        ExportUserGames exportUserGames = new ExportUserGames(storeDB, currentUser);
        exportUserGames.showMenu();
    }

    private void searchByName() {
        startProductMenu(SelectItemHelper.searchUserProtectByName(userLibrary));
    }

    private void startProductMenu(Product selectedProduct) {
        if (selectedProduct == null) {
            return;
        }
        switch (selectedProduct.getProductType()) {
            case GAME: {
                GameLibraryMenu gameLibraryMenu = new GameLibraryMenu(currentUser, (Game) selectedProduct, storeDB);
                gameLibraryMenu.showMenu();
                break;
            }
            case ACCESSORIES: {
                AccessoryLibraryMenu accessoryLibraryMenu = new AccessoryLibraryMenu(currentUser,
                        (Accessory) selectedProduct, storeDB);
                accessoryLibraryMenu.showMenu();
                break;
            }
            default: {

            }
        }
    }
}

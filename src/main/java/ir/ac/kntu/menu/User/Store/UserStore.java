package ir.ac.kntu.menu.User.Store;

import ir.ac.kntu.HelperClasses.SelectItemHelper;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.product.Product;

import java.util.ArrayList;

public class UserStore extends Menu {

    private final Store storeDB;

    private final User currentUser;

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
                        all();
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

    public void all() {
        ArrayList<Product> result = SelectItemHelper.getAllProducts(storeDB.getProducts());
        Product selectedProduct = SelectItemHelper.handleSelect(result);
        if (selectedProduct == null) {
            return;
        }
        ProductStoreMenu productStoreMenu = new ProductStoreMenu(currentUser, selectedProduct, storeDB);
        productStoreMenu.showMenu();
    }

    public void searchByName() {
        Product selectedProduct = SelectItemHelper.searchStoreProtectByName(storeDB);
        if (selectedProduct == null) {
            return;
        }
        ProductStoreMenu productStoreMenu = new ProductStoreMenu(currentUser, selectedProduct, storeDB);
        productStoreMenu.showMenu();

    }

    public void searchBPrice() {
        Product selectedProduct = SelectItemHelper.searchProtectByPrice(storeDB);
        if (selectedProduct == null) {
            return;
        }
        ProductStoreMenu productStoreMenu = new ProductStoreMenu(currentUser, selectedProduct, storeDB);
        productStoreMenu.showMenu();
    }

}

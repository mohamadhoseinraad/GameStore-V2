package ir.ac.kntu.menu.User.Store;

import ir.ac.kntu.HelperClasses.GameHelper;
import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.product.Product;
import ir.ac.kntu.models.product.accessories.Accessory;

public class ProductStoreMenu extends Menu {

    private User currentUser;

    private Product currentProduct;

    private Store storeDB;

    public ProductStoreMenu(User currentUser, Product currentProduct, Store storeDB) {
        this.currentUser = currentUser;
        this.currentProduct = currentProduct;
        this.storeDB = storeDB;
    }

    @Override
    public void showMenu() {
        ProductStoreMenuOptions option;
        while (printGame() && (option = printMenuOptions(currentProduct.getName(), ProductStoreMenuOptions.class)) != ProductStoreMenuOptions.EXIT) {
            if (option != null) {
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
        }
        System.exit(0);
    }

    public boolean printGame() {
        currentProduct.showProduct(currentUser);
        return true;
    }

    private void buy() {
        if (currentProduct.getClass() == Game.class) {
            buyGame((Game) currentProduct);
        } else {
            buyAccessory((Accessory) currentProduct);
        }

    }

    private void buyAccessory(Accessory currentProduct) {
        if (!(currentUser.getWallet() >= currentProduct.getPrice())) {
            TerminalColor.red();
            System.out.println("You don't have enough money ! :(");
            TerminalColor.reset();
            return;
        }
        if (currentUser.addProduct(currentProduct)) {
            TerminalColor.green();
            System.out.println("Buy Successfully :) ");
            TerminalColor.reset();
            return;
        }
        TerminalColor.red();
        System.out.println("We don't have this Product already ! :(");
        TerminalColor.reset();
        return;
    }

    private void buyGame(Game currentProduct) {
        if (currentUser.doHaveGame(currentProduct)) {
            TerminalColor.red();
            System.out.println("You already have this game!");
            TerminalColor.reset();
            return;
        }
        if (!GameHelper.checkUserLevel(currentProduct, currentUser)) {
            TerminalColor.red();
            System.out.println("Your level is not match!");
            TerminalColor.reset();
            return;
        }
        if (currentUser.addProduct(currentProduct)) {
            TerminalColor.green();
            System.out.println("Buy Successfully :) ");
            TerminalColor.reset();
            return;
        }
        TerminalColor.red();
        System.out.println("You don't have enough money ! :(");
        TerminalColor.reset();
    }

    public void gift() {
        System.out.println("Enter username you want to gift game : ");
        String friendUsername = Scan.getLine().trim().toUpperCase();
        User friend = storeDB.findUserByUsername(friendUsername);
        if (friend == null) {
            TerminalColor.red();
            System.out.println("Not found");
            TerminalColor.reset();
            return;
        }
        if (!currentUser.isFriend(friend.getId())) {
            TerminalColor.red();
            System.out.println("This account is not your friend!");
            TerminalColor.reset();
            return;
        }
        boolean notEnoughMoney = (currentUser.getWallet() < currentProduct.getPrice());
        if (notEnoughMoney) {
            TerminalColor.red();
            System.out.println("You don't have enough money ! :(");
            TerminalColor.reset();
            return;
        }
        if (currentProduct.getClass() == Game.class) {
            giftGame(friend, (Game) currentProduct);
        } else {
            gitAccessory(friend, (Accessory) currentProduct);
        }
        TerminalColor.reset();

    }

    public void gitAccessory(User friend, Accessory currentProduct) {
        if (!(currentProduct.getAmount() > 0)) {
            TerminalColor.red();
            System.out.println("We don't have this Product already ! :(");
            TerminalColor.reset();
            return;
        }
        if (currentUser.giftAccessory(currentProduct, friend)) {
            TerminalColor.green();
            System.out.println("Gift Successfully :) ");
            TerminalColor.reset();
        }
    }

    public boolean giftGame(User friend, Game currentProduct) {
        if (friend.doHaveGame(currentProduct)) {
            TerminalColor.red();
            System.out.println("Your friend already has this game!");
            TerminalColor.reset();
            return true;
        }
        if (!GameHelper.checkUserLevel(currentProduct, currentUser)) {
            TerminalColor.red();
            System.out.println("Your level is not match!");
            TerminalColor.reset();
            return true;
        }
        if (currentUser.giftGame(currentProduct, friend)) {
            TerminalColor.green();
            System.out.println("Gift Successfully :) ");
            TerminalColor.reset();
            return true;
        }
        return false;
    }
}

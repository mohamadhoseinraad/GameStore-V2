package ir.ac.kntu.menu.Admin.Game;

import ir.ac.kntu.HelperClasses.GameHelper;
import ir.ac.kntu.HelperClasses.SelectItemHelper;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.User;

public class AdminGamesMenu extends Menu {

    private Store storeDB;

    private User admin;

    public AdminGamesMenu(Store storeDB, User admin) {
        this.storeDB = storeDB;
        this.admin = admin;
    }

    @Override
    public void showMenu() {
        AdminGamesMenuOptions option;
        while ((option = printMenuOptions("Admin Menu-Games", AdminGamesMenuOptions.class)) != AdminGamesMenuOptions.EXIT) {
            if (option != null) {
                switch (option) {
                    case ADD_GAME: {
                        addGame();
                        break;
                    }
                    case EDIT_GAME: {
                        editGame();
                        break;
                    }
                    case REMOVE_GAME: {
                        removeGame();
                        break;
                    }
                    case EXPORT_TO_HTML: {
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

    private void exportHtml() {
        ExportProducts exportProducts = new ExportProducts(storeDB);
        exportProducts.showMenu();
    }

    private void addGame() {
        Game newGame = GameHelper.makeGame();
        if (newGame != null) {
            if (storeDB.addProduct(newGame)) {
                TerminalColor.green();
                System.out.println(newGame.getName() + " added to DB");
                TerminalColor.reset();
            }
        }
        return;
    }

    private void editGame() {
        Game game = (Game) SelectItemHelper.searchStoreProtectByName(storeDB);
        if (game == null) {
            return;
        }
        AdminGameEdit adminGameEdit = new AdminGameEdit(game, admin);
        adminGameEdit.showMenu();
    }

    private void removeGame() {
        Game game = (Game) SelectItemHelper.searchStoreProtectByName(storeDB);
        if (storeDB.removeGame(game) && !(game == null)) {
            TerminalColor.green();
            System.out.println(game.getName() + " with " + game.getId() + " id successfully deleted !");
            TerminalColor.reset();
            return;
        }
        TerminalColor.red();
        System.out.println("Fail delete game !");
        TerminalColor.red();
    }

}

package ir.ac.kntu.menu.Admin.Game;

import ir.ac.kntu.HelperClasses.GameHelper;
import ir.ac.kntu.HelperClasses.SelectItemHelper;
import ir.ac.kntu.menu.ExportUserProduct;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.models.product.ProductType;
import ir.ac.kntu.utils.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.product.games.Game;

public class AdminGamesMenu extends Menu {

    private final Store storeDB;

    private final Admin admin;


    public AdminGamesMenu(Store storeDB, Admin admin) {
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
        ExportUserProduct exportProducts = new ExportUserProduct(storeDB.getAdminProducts(admin, ProductType.GAME));
        exportProducts.showMenu();
    }

    private void addGame() {
        Game newGame = GameHelper.makeGame(admin);
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
        Game game = (Game) SelectItemHelper.searchInCostumeProtectByName(storeDB.getAdminProducts(admin, ProductType.GAME));
        if (game == null) {
            return;
        }
        AdminGameEdit adminGameEdit = new AdminGameEdit(game, admin);
        adminGameEdit.showMenu();
    }

    private void removeGame() {
        Game game = (Game) SelectItemHelper.searchInCostumeProtectByName(storeDB.getAdminProducts(admin, ProductType.GAME));

        if (game != null && checkPermission(game) && storeDB.removeProduct(game)) {
            TerminalColor.green();
            admin.removeAccessProduct(game);
            System.out.println(game.getName() + " with " + game.getId() + " id successfully deleted !");
            TerminalColor.reset();
            return;
        }
        TerminalColor.red();
        System.out.println("Fail delete game !");
        TerminalColor.red();
    }

    private boolean checkPermission(Game game) {
        if (!game.creatorId.equals(admin.getId()) && !admin.isMastetAdmin()) {
            TerminalColor.red();
            System.out.println("Fail delete game ! You are not creator of this game !");
            TerminalColor.reset();
            return false;
        }
        return true;
    }

}

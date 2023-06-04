package ir.ac.kntu.menu.Admin.Game;

import ir.ac.kntu.HelperClasses.ProductHelper;
import ir.ac.kntu.utils.Scan;
import ir.ac.kntu.utils.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.product.games.Game;
import ir.ac.kntu.models.product.games.Genre;

public class AdminGameEdit extends Menu {

    private Game currentGame;

    private User admin;

    public AdminGameEdit(Game currentGame, User admin) {
        this.currentGame = currentGame;
        this.admin = admin;
    }

    @Override
    public void showMenu() {
        AdminGameEditOptions option;
        while (showGame() && (option = printMenuOptions("EDIT Games", AdminGameEditOptions.class)) != AdminGameEditOptions.EXIT) {
            if (option != null) {
                switch (option) {
                    case EDIT_NAME: {
                        editName();
                        break;
                    }
                    case EDIT_GENRE: {
                        editGenre();
                        break;
                    }
                    case EDIT_DETAIL: {
                        editDetail();
                        break;
                    }
                    case EDIT_PRICE: {
                        editPrice();
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

    private boolean showGame() {
        currentGame.showProduct(admin);
        return true;
    }

    private void editPrice() {
        System.out.println("Enter new price : ");
        String input = Scan.getLine();
        if (input.matches("[0-9][0-9.]*")) {
            double newPrice = Double.parseDouble(input);
            currentGame.setPrice(newPrice);
            return;
        }
        TerminalColor.red();
        System.out.println("Enter valid price!");
        TerminalColor.reset();
    }

    private void editName() {
        System.out.println("Enter new name : ");
        String input = Scan.getLine().trim().toUpperCase();
        if (input.length() > 2) {
            currentGame.setName(input);
            return;
        }
        TerminalColor.red();
        System.out.println("Minimum Length 3 character!");
        TerminalColor.reset();
    }

    private void editDetail() {
        System.out.println("Enter new detail : ");
        String input = Scan.getLine().trim().toUpperCase();
        if (input.length() > 2) {
            currentGame.setDetails(input);
            return;
        }
        TerminalColor.red();
        System.out.println("Minimum Length 3 character!");
        TerminalColor.reset();
    }

    private void editGenre() {
        Genre genre = ProductHelper.getInputEnumData(Genre.class);
        currentGame.setGenre(genre);
    }

}

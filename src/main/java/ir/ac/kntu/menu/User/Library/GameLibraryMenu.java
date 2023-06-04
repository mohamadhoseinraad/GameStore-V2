package ir.ac.kntu.menu.User.Library;

import ir.ac.kntu.models.Community;
import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.User;

public class GameLibraryMenu extends Menu {

    private User currentUser;

    private Game currentGame;

    private Store storeDB;

    public GameLibraryMenu(User currentUser, Game currentGame, Store storeDB) {
        this.currentUser = currentUser;
        this.currentGame = currentGame;
        this.storeDB = storeDB;
    }

    @Override
    public void showMenu() {
        GameLibraryMenuOptions option;
        while (printGame() && (option = printMenuOptions(currentGame.getName(), GameLibraryMenuOptions.class)) != GameLibraryMenuOptions.EXIT) {
            switch (option) {
                case RATE: {
                    rate();
                    break;
                }
                case COMMENT: {
                    comment();
                    break;
                }
                case SHOW_COMMENTS: {
                    showComments();
                }
                case BACK: {
                    return;
                }
                default: {
                    break;
                }
            }
        }
        System.exit(0);
    }

    private boolean printGame() {
        currentGame.showProduct(currentUser);
        return true;
    }

    private void rate() {
        System.out.println("Enter your rate");
        String rateStr;
        while (!(rateStr = Scan.getLine()).matches("[0-9]|10")) {
            TerminalColor.red();
            System.out.println("Please enter valid rate between 0-10");
            TerminalColor.reset();
        }
        double vote = Double.parseDouble(rateStr);
        currentGame.rating(currentUser, vote);

    }

    private void comment() {
        System.out.println("Enter your comment");
        String userComment;
        while ((userComment = Scan.getLine().trim()).length() < 3) {
            TerminalColor.red();
            System.out.println("Your comment must 3 or more character ! enter your comment again :");
            TerminalColor.reset();
        }
        Community community = new Community(currentUser.getUsername(), userComment);
        currentGame.addCommunity(community);
        TerminalColor.green();
        System.out.println("Successfully submit !");
        TerminalColor.reset();
    }

    private void showComments() {
        currentGame.showAllComment();
    }

}

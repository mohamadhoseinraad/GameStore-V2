package ir.ac.kntu.menu.User.Library;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.Community;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.product.accessories.Accessory;

public class AccessoryLibraryMenu extends Menu {

    private User currentUser;

    private Accessory currentAccessory;

    private Store storeDB;

    public AccessoryLibraryMenu(User currentUser, Accessory currentAccessory, Store storeDB) {
        this.currentUser = currentUser;
        this.currentAccessory = currentAccessory;
        this.storeDB = storeDB;
    }

    @Override
    public void showMenu() {
        SelectedProductMenuOptions option;
        while (printGame() && (option = printMenuOptions(currentAccessory.getName(), SelectedProductMenuOptions.class)) != SelectedProductMenuOptions.EXIT) {
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
        currentAccessory.showProduct(currentUser);
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
        currentAccessory.rating(currentUser, vote);

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
        currentAccessory.addCommunity(community);
        TerminalColor.green();
        System.out.println("Successfully submit !");
        TerminalColor.reset();
    }

    private void showComments() {
        currentAccessory.showAllComment();
    }

}

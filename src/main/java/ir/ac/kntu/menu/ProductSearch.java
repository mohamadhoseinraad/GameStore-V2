package ir.ac.kntu.menu;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.HelperClasses.TerminalColor;
import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.product.Product;

import java.util.ArrayList;

public class ProductSearch {
    private Store storeDB;

    public ProductSearch(Store storeDB) {
        this.storeDB = storeDB;
    }

    public Product searchMenu(String name){
        name = name.trim().toUpperCase();
        ArrayList<Product> result = storeDB.findProductByName(name);
        printSearchResult(result);
        if (result.size() != 0){
            return handleSelect(result);
        }
        return null;
    }


    public Product searchMenu() {
        System.out.println("Search Name of game : ");
        String name = Scan.getLine().trim().toUpperCase();
        ArrayList<Product> result = storeDB.findProductByName(name);
        printSearchResult(result);
        if (result.size() != 0){
            return handleSelect(result);
        }
        return null;
    }

    private Product handleSelect(ArrayList<Product> searchResult) {
        System.out.println("---- chose number : ");
        String input = Scan.getLine();
        if (!input.matches("[0-9]+")) {
            TerminalColor.red();
            System.out.println("Chose valid number!");
            TerminalColor.reset();
        }else {
            int choose = Integer.parseInt(input) - 1;
            if (choose >= searchResult.size() || choose < 0) {
                TerminalColor.red();
                System.out.println("Chose valid number!");
                TerminalColor.reset();
            } else {
                Product product = searchResult.get(choose);
                return product;
            }
        }
        return null;

    }

    private void printSearchResult(ArrayList<Product> result) {
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
}


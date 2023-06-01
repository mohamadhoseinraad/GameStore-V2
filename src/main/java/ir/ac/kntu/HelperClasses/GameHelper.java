package ir.ac.kntu.HelperClasses;

import ir.ac.kntu.models.Game;
import ir.ac.kntu.models.Genre;
import ir.ac.kntu.models.ProductType;

public class GameHelper {
    public static Game makeGame() {
        System.out.println("Pleas enter game name :");
        String name = Scan.getLine();
        Genre genre = getInputgenre();
        System.out.println("Pleas enter detail of game :");
        String detail = Scan.getLine();
        System.out.println("Pleas enter price :");
        String priceSrt = Scan.getLine();
        TerminalColor.red();
        if (!priceSrt.matches("[0-9.]+")) {
            System.out.println("Price is not Valid!");
            TerminalColor.reset();
            return null;
        }
        if (name.length() < 3 || detail.length() < 3) {
            System.out.println("Name and detail must be more than 3 character!");
            TerminalColor.reset();
            return null;
        }
        TerminalColor.reset();
        double price = Double.parseDouble(priceSrt);
        return new Game(name, detail, price, ProductType.GAME, genre);
    }

    public static Genre getInputgenre(){
        System.out.println("Choose genre : ");
        Genre[] genres = Genre.values();
        for (int i = 0 ; i < Genre.values().length; i++){
            System.out.println(String.valueOf(i) + genres[i] );
        }
        String input;
        do {
            input = Scan.getLine();
        } while (checkInputGenre(input));
        return genres[Integer.parseInt(input)];
    }

    private static boolean checkInputGenre(String input){
        if (!input.matches("[0-9]+")){
            System.out.println("Invalid genre try again");
            return true;
        }
        if (Integer.parseInt(input) > Genre.values().length -1){
            System.out.println("Invalid genre try again");
            return true;
        }
        if (Integer.parseInt(input) < 0 ){
            System.out.println("Invalid genre try again");
            return true;
        }
        return false;
    }

    private static void scoreColor(Game game) {
        if (game.getScore() < 3) {
            TerminalColor.red();
        } else if (game.getScore() < 6) {
            TerminalColor.yellow();
        } else {
            TerminalColor.green();
        }
    }

    public static void printGame(Game game){
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.cyan();
        System.out.print("| Name     : " + game.getName());
        TerminalColor.reset();
        System.out.print("  -----  ");
        if (game.getPrice() == 0) {
            TerminalColor.green();
            System.out.println("Free");
        } else {
            TerminalColor.cyan();
            System.out.println(game.getPrice() + "$ coast");
        }
        TerminalColor.yellow();
        System.out.print("| Genre : " + game.getGenre());
        System.out.print(" | Score : ");
        scoreColor(game);
        System.out.print(game.getScore());
        TerminalColor.cyan();
        System.out.println(" (" + game.getRates().size() + ")");
        System.out.println(game.getDetails());
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.reset();
    }
}

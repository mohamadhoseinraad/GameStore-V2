package ir.ac.kntu.models;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.HelperClasses.TerminalColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Game extends Product {
    public static int gamesNumber = 0;

    private final int id;


    private String genre;


    private double score;

    private Map<String, Double> rates;

    public Game(String name, String details, double price, ProductType productType, String genre) {
        super(name, details, price, productType);
        this.genre = genre;
        score = 0;
        id = gamesNumber++;
        rates = new HashMap<>();
    }


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    public int getId() {
        return id;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public void rating(User user, Double rate) {
        if (rate >= 0 && rate <= 10) {
            rates.put(user.getUsername(), rate);
            updateScore();
        }
    }

    private void updateScore() {
        double sumRate = 0;
        int numberOfVoter = rates.size();
        for (Map.Entry<String, Double> userRateMap : rates.entrySet()) {
            sumRate += userRateMap.getValue();
        }
        score = sumRate / numberOfVoter;
    }

    private void scoreColor() {
        if (score < 3) {
            TerminalColor.red();
        } else if (score < 6) {
            TerminalColor.yellow();
        } else {
            TerminalColor.green();
        }
    }


    public static Game makeGame() {
        System.out.println("Pleas enter game name :");
        String name = Scan.getLine();
        System.out.println("Pleas enter game genre :");
        String genre = Scan.getLine();
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
        if (name.length() < 3 || genre.length() < 3 || detail.length() < 3) {
            System.out.println("Name Genre and detail must be more than 3 character!");
            TerminalColor.reset();
            return null;
        }
        TerminalColor.reset();
        double price = Double.parseDouble(priceSrt);
        return new Game(name, detail, price, ProductType.GAME, genre);
    }

    @Override
    public void showProduct() {
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.cyan();
        System.out.print("| Name     : " + getName());
        TerminalColor.reset();
        System.out.print("  -----  ");
        if (getPrice() == 0) {
            TerminalColor.green();
            System.out.println("Free");
        } else {
            TerminalColor.cyan();
            System.out.println(getPrice() + "$ coast");
        }
        TerminalColor.yellow();
        System.out.print("| Genre : " + genre);
        System.out.print(" | Score : ");
        scoreColor();
        System.out.print(score);
        TerminalColor.cyan();
        System.out.println(" (" + rates.size() + ")");
        System.out.println(getDetails());
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.reset();
    }
}

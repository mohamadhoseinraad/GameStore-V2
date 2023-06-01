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


    private Genre genre;


    private double score;

    private Map<String, Double> rates;

    public Game(String name, String details, double price, ProductType productType, Genre genre) {
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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

    @Override
    public void showProduct() {

    }
}

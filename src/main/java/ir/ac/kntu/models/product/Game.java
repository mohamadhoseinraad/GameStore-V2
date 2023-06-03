package ir.ac.kntu.models.product;


import ir.ac.kntu.HelperClasses.GameHelper;
import ir.ac.kntu.HelperClasses.ProductHelper;
import ir.ac.kntu.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Game extends Product {
    public static int gamesNumber = 0;

    private final String id;

    private Genre genre;

    private double score;

    private Level level;

    private Map<String, Double> rates;

    public Game(String name, String details, double price, Genre genre , Level level) {
        super(name, details, price, ProductType.GAME);
        this.genre = genre;
        score = 0;
        id = "GAME"+ gamesNumber++;
        this.level = level;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
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
        GameHelper.printGame(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name=" + getName() +
                ", genre=" + genre +
                ", price=" + getPrice() +
                ", score=" + score +
                ", level=" + level +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

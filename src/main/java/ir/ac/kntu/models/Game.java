package ir.ac.kntu.models;

import ir.ac.kntu.HelperClasses.Scan;
import ir.ac.kntu.HelperClasses.TerminalColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Game implements Cloneable {
    public static int gamesNumber = 0;

    private final int id;

    private String name;

    private String details;

    private String genre;

    private double price;

    private double score;

    private Map<String, Double> rates;

    private ArrayList<Community> communities;

    public Game(String name, String details, String genre, double price) {
        this.name = name.toUpperCase();
        this.details = details;
        this.genre = genre;
        this.price = price;
        score = 0;
        id = gamesNumber++;
        communities = new ArrayList<>();
        rates = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ArrayList<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(ArrayList<Community> communities) {
        this.communities = communities;
    }

    public void addCommunity(Community community){
        communities.add(community);
    }

    public void showAllComment(){
        for (Community community : communities){
            community.showComment();
        }
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
    public String toString() {
        return
                "ID-" + id +
                        " name : " + name +
                        " ,genre :" + genre +
                        " ,price :" + price +
                        " ,score :" + score +
                        " ,details:" +
                        details + "\n";
    }

    public void showGame() {
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.cyan();
        System.out.print("| Name     : " + name);
        TerminalColor.reset();
        System.out.print("  -----  ");
        if (price == 0) {
            TerminalColor.green();
            System.out.println("Free");
        } else {
            TerminalColor.cyan();
            System.out.println(price + "$ coast");
        }
        TerminalColor.yellow();
        System.out.print("| Genre : " + genre);
        System.out.print(" | Score : ");
        scoreColor();
        System.out.print(score);
        TerminalColor.cyan();
        System.out.println(" (" + rates.size() + ")");
        System.out.println(details);
        TerminalColor.blue();
        System.out.println("|----------------------------");
        TerminalColor.reset();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        return id == game.getId() && name.equals(game.getName()) && genre.equals(game.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre);
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
        return new Game(name, detail, genre, price);
    }
}

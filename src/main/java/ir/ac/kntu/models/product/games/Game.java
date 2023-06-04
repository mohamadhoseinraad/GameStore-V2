package ir.ac.kntu.models.product.games;


import ir.ac.kntu.HelperClasses.GameHelper;
import ir.ac.kntu.models.Community;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.product.Product;
import ir.ac.kntu.models.product.ProductType;

import java.util.ArrayList;
import java.util.Objects;

public class Game extends Product {
    private static int gamesNumber = 0;

    private final String id;

    private Genre genre;


    private Level level;

    private boolean isBetaVersion;




    public Game(String name, String details, double price, Genre genre, Level level) {
        super(name, details, price, ProductType.GAME);
        this.genre = genre;
        id = "G" + gamesNumber++;
        this.level = level;
        isBetaVersion = false;
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

    public boolean isBetaVersion() {
        return isBetaVersion;
    }

    public void setBetaVersion(boolean betaVersion) {
        isBetaVersion = betaVersion;
    }


    @Override
    public void showProduct(User currentUser) {
        GameHelper.printGame(this, currentUser);
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
                ", score=" + getScore() +
                ", level=" + level +
                '}';
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
        return id.equals(game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public ArrayList<Community> getCommunities() {
        if (!isBetaVersion) {
            return super.getCommunities();
        }
        return null;
    }

    @Override
    public void setCommunities(ArrayList<Community> communities) {
        if (!isBetaVersion) {
            super.setCommunities(communities);
        }
    }

    @Override
    public void showAllComment() {
        if (!isBetaVersion) {
            super.showAllComment();
        }
    }

    @Override
    public void addCommunity(Community community) {
        if (!isBetaVersion) {
            super.addCommunity(community);
        }
    }
}
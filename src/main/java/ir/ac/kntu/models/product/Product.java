package ir.ac.kntu.models.product;

import ir.ac.kntu.models.Community;

import java.util.ArrayList;

public abstract class Product {

    private String name;

    private String details;

    private double price;

    private ProductType productType;

    private ArrayList<Community> communities;

    public Product(String name, String details, double price, ProductType productType) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.productType = productType;
        communities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(ArrayList<Community> communities) {
        this.communities = communities;
    }

    public void showAllComment(){
        for (Community community : communities){
            community.showComment();
        }
    }

    public void addCommunity(Community community){
        communities.add(community);
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public abstract void showProduct();
}

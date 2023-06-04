package ir.ac.kntu.models.product.accessories;

import ir.ac.kntu.models.User;
import ir.ac.kntu.models.product.Product;
import ir.ac.kntu.models.product.ProductType;

public class Accessory extends Product {

    private int amount = 0;

    private AccessoryType accessoryType;

    public Accessory(String name, String details, double price, int amount, AccessoryType accessoryType) {
        super(name, details, price, ProductType.ACCESSORIES);
        this.amount = amount;
        this.accessoryType = accessoryType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount >= 0) {
            this.amount = amount;
        }
    }

    public void addAmount(int amount) {
        if (amount > 0) {
            this.amount += amount;
        }
    }

    public AccessoryType getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(AccessoryType accessoryType) {
        this.accessoryType = accessoryType;
    }

    @Override
    public void showProduct(User currentUser) {

    }

    @Override
    public String getId() {
        return null;
    }
}

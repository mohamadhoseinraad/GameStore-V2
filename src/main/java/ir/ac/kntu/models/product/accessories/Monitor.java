package ir.ac.kntu.models.product.accessories;

public class Monitor extends Accessory {

    private int size;

    private int refreshRate;

    private int responseTime;

    public Monitor(String name, String details, double price, int amount, int size, int refreshRate, int responseTime) {
        super(name, details, price, amount, AccessoryType.MONITOR);
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
}

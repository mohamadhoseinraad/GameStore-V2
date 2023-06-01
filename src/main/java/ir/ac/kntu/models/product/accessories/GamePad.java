package ir.ac.kntu.models.product.accessories;

public class GamePad extends Accessory {

    private Device device;

    private Connection connection;

    public GamePad(String name, String details, double price, int amount, Connection connection, Device device) {
        super(name, details, price, amount, AccessoryType.GAME_PAD);
        this.device = device;
        this.connection = connection;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

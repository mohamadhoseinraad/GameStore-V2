package ir.ac.kntu.HelperClasses;

import ir.ac.kntu.models.product.Genre;
import ir.ac.kntu.models.product.Level;
import ir.ac.kntu.models.product.accessories.*;

public class AccessoryHelper {
    public static Accessory makeAccessory() {
        System.out.println("Pleas enter name of Accessory :");
        String name = Scan.getLine();
        System.out.println("Pleas enter detail of game :");
        String detail = Scan.getLine();
        System.out.println("Pleas enter price :");
        String priceSrt = Scan.getLine();
        System.out.println("Pleas enter amount :");
        String amountSrt = Scan.getLine();
        TerminalColor.red();
        if (!priceSrt.matches("[0-9.]+") || !amountSrt.matches("[0-9.]+")) {
            System.out.println("Price  or Amount is not Valid!");
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
        return new Accessory(name, detail, price, Integer.parseInt(amountSrt), AccessoryType.NULL);
    }

    public static GamePad makeGamePad() {
        Accessory accessory = makeAccessory();
        if (accessory == null) {
            TerminalColor.red();
            System.out.println("Error in create new product . try again");
            TerminalColor.reset();
            return null;
        }
        System.out.println("Choose Connections type : ");
        Connection connection = ProductHelper.getInputEnumData(Connection.class);
        System.out.println("Choose Devise type : ");
        Device device = ProductHelper.getInputEnumData(Device.class);
        String name = accessory.getName();
        String detail = accessory.getDetails();
        return new GamePad(name, detail, accessory.getPrice(), accessory.getAmount(), connection, device);
    }

    public static Monitor makeMonitor() {
        Accessory accessory = makeAccessory();
        if (accessory == null) {
            TerminalColor.red();
            System.out.println("Error in create new product . try again");
            TerminalColor.reset();
            return null;
        }
        System.out.println("Enter Monitor Size : ");
        String sizeStr = Scan.getLine();
        System.out.println("Enter Monitor Refresh Rate : ");
        String refRateStr = Scan.getLine();
        System.out.println("Enter Monitor Response time : ");
        String resTimeStr = Scan.getLine();
        if (!sizeStr.matches("[1-9.]+") || !resTimeStr.matches("[0-9.]+") ||
                !refRateStr.matches("[1-9.]+")) {
            System.out.println("Size RefreshTIme or Response Time  is not Valid!");
            TerminalColor.reset();
            return null;
        }
        int size = Integer.parseInt(sizeStr);
        int refRate = Integer.parseInt(refRateStr);
        int resTime = Integer.parseInt(refRateStr);
        String name = accessory.getName();
        String detail = accessory.getDetails();
        return new Monitor(name, detail, accessory.getPrice(), accessory.getAmount(), size, resTime, resTime);
    }
}

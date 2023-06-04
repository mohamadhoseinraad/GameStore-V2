package ir.ac.kntu;

import ir.ac.kntu.models.product.Game;
import ir.ac.kntu.models.product.Genre;
import ir.ac.kntu.models.product.Level;
import ir.ac.kntu.models.product.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class Maintest {
    public static void main(String[] args) throws ParseException, InterruptedException {
        Date date1 = new Date();
        date1.setTime(System.currentTimeMillis());
        Thread.sleep(60000);
        Date date2 = new Date();
        date2.setTime(System.currentTimeMillis());
        long difference = date2.getTime() - date1.getTime();
        System.out.println(difference);
        System.out.println(difference / 60000);


    }

}

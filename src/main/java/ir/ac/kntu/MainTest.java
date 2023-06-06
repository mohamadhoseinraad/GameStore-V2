package ir.ac.kntu;

import ir.ac.kntu.HelperClasses.DefaultData;
import ir.ac.kntu.HelperClasses.FeedBacksHelper;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Store;
import ir.ac.kntu.models.User;
import ir.ac.kntu.models.product.FeedBack;
import ir.ac.kntu.models.product.games.Game;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalField;
import java.util.Collections;
import java.util.Date;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {

        Store storeDB = DefaultData.addDefaultData();
        User user = storeDB.findUserByUsername("1");
        Game game = storeDB.getGames().get(2);
        Admin admin = (Admin) storeDB.findUserByUsername("DEV");
        game.addDeveloper(admin);
        FeedBack feedBack = new FeedBack("input", user, game, storeDB.gameDevelopers(game, true));
        FeedBacksHelper.handleFeedBack(game, storeDB.gameDevelopers(game, true));
        System.out.println(feedBack);
        Collections.sort(storeDB.gameDevelopers(game, true));
        storeDB.gameDevelopers(game, true).get(0).deAcceptFeedback(feedBack, game, storeDB.gameDevelopers(game, true));
        System.out.println(feedBack);

    }

}

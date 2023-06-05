package ir.ac.kntu;

import ir.ac.kntu.models.Admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalField;
import java.util.Date;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {


        LocalDate start = LocalDate.of(2022, 10, 20);
        LocalDate end = LocalDate.of(2022, 10, 30);
        Period baseperiod = Period.of(0, 0, 5);
        Period diff = Period.between(start, end);
        System.out.println(diff);
        System.out.println(baseperiod.getMonths());
        System.out.println(baseperiod.multipliedBy(2));
        if (!(diff.getYears() <= baseperiod.getYears() &&
                diff.getMonths() <= baseperiod.getMonths() &&
                diff.getDays() <= baseperiod.getDays())
        ) {
            System.out.println("expired");
        }

//        System.out.println(localDateTimeOld.getYear());
//        System.out.println(localDateTimeOld.getMonth());
//        System.out.println(localDateTimeOld.getDayOfMonth());
//        System.out.println(localDateTimeOld.getHour());
//        System.out.println(localDateTimeOld.getMinute());
//        System.out.println(localDateTimeOld.getSecond());
//        Date date = new Date(localDateTimeOld.getYear(),
//                localDateTimeOld.getDayOfMonth(), localDateTimeOld.getDayOfMonth());

//        Thread.sleep(5000);
//        LocalDateTime localDateTime = LocalDateTime.now();
//        Date date2 = new Date(localDateTime.getYear(), localDateTime.getDayOfMonth(), localDateTime.getDayOfMonth());
        //Date date = new Date(2023, 03, 15);
//        Date date2 = new Date(2023, 04, 31);
//        Date diff = new Date((date2.getYear() - date.getYear()), (date2.getMonth() - date.getMonth()), (date2.getDay() - date.getDay()));


        System.out.println(diff);
    }

}

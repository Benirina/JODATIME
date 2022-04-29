package Enumeration;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EnumJAVA8 {
    private static DateTimeFormatter PATTERN_REVIEW_DATE = DateTimeFormat.forPattern("dd/MM/yy").withLocale(Locale.FRENCH);
    private static DateTimeFormatter PATTERN_MONTH_NAME = DateTimeFormat.forPattern("MMM").withLocale(Locale.FRENCH);
    public static final Map<Integer,Enumeration> lookup = new HashMap<Integer,Enumeration>();
    private enum Enumeration {
        janvier(1),
        février(2),
        mars(3),
        avril(4),
        mais(5),
        juin(6),
        juillet(7),
        août(8),
        septembre(9),
        octobre(10),
        novembre(11),
        décembre(12);

        private int index;

        private Enumeration(int index) {
            this.index = index;
        }

        public int getCode() {
            return index;
        }

       /* public static Enumeration get(int index) {
            for (Enumeration s : values()) {
                if (s.index == index) return s;
            }
            return null;
        }*/



    }
    static {
        for(Enumeration s : EnumSet.allOf(Enumeration.class))
            lookup.put(s.getCode(), s);
    }
    public static Enumeration get(int code) {
        return lookup.get(code);
    }
    public static boolean testingRawText(int index) {
        int indexEnum = lookup.get(index).index;
        if(indexEnum==index){
            System.out.println("Name of month: "+lookup.get(index).name());
            return true;
        }
        return false;
    }
    public static int daysBetweenToDate(DateTime dateDelivery){
        Instant beginInstant = DateTime.now().toInstant();
        System.out.println(" Start date: "+beginInstant);
        Instant endInstant = dateDelivery.toInstant();
        System.out.println(" End date: "+endInstant);

        return Days.daysBetween(beginInstant,endInstant).getDays();
    }

    public static boolean isIndexMonthAsNewYear(String nameMonth) {
        DateTime endDateDelivery = PATTERN_MONTH_NAME.parseDateTime(nameMonth.toLowerCase(Locale.FRENCH)).withDayOfMonth(28).withYear(DateTime.now().getYear());
        int indexOfNameMonthDelivery = endDateDelivery.getMonthOfYear();
        int indexCurrentMonth = DateTime.now().monthOfYear().get();
        if(indexCurrentMonth>indexOfNameMonthDelivery){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String date = "18/07/21";
        LocalDate localDate = PATTERN_REVIEW_DATE.parseLocalDate(date);
        System.out.println(" Date locale: "+localDate);


        DateTime instanceMonth = PATTERN_MONTH_NAME.parseDateTime("juin").withDayOfMonth(01).withYear(DateTime.now().getYear());
        int numberMonthOfYear = instanceMonth.dayOfMonth().getMaximumValue();
       // System.out.println("Month is present: "+testingRawText(numberMonthOfYear)+" Duration two date: "+daysBetweenToDate(instanceMonth));

        DateTime endDateDelivery = PATTERN_MONTH_NAME.parseDateTime("juillet").withDayOfMonth(01).withYear(DateTime.now().getYear());
        int indexEnum = DateTime.now().monthOfYear().get();
        System.out.println(" Max value day of month: "+endDateDelivery.dayOfMonth().getMaximumValue()+"Index current month: "+indexEnum+" month to the parameter is before current month: "+isIndexMonthAsNewYear("février"));
        //DateTime dateDelivery = PATTERN_MONTH_NAME.parseDateTime("juin");


    }
}

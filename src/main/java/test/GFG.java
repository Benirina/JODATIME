package test;


import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;

import java.util.Locale;

import java.time.Period;


class GFG {
    private static final Locale CURRENT_LOCALE = Locale.GERMANY;
    private static final PeriodFormatter formatter = PeriodFormat.wordBased(CURRENT_LOCALE);
    private Period parseDeliveryPeriod(final String deliveryPeriodText) {
        //String periodText = extractPeriodText(deliveryPeriodText);
        try {
            //Retourner nombre de jour ou semaine
            // JODA-TIME
            // return Period.parse(deliveryPeriodText, DELIVERY_PERIOD_PARSER);
            return Period.parse(deliveryPeriodText);
        } catch (Exception exc) {
            //conn.error("Delivery period not parsable [" + periodText + "]" + withException(exc));
        }
        return null;
    }
    public static void main(String[] args) {

        // Get the number of Weeks
        int numberOfWeeks = 3;

        // Parse the numberOfWeeks into Period
        // using ofWeeks() method
        Period p = Period.ofWeeks(numberOfWeeks);


        Period pdays = Period.ofDays(21);

        System.out.println(p.getYears() + " Years\n"
                + p.getMonths() + " Months\n"
                + p.getDays() + " Days");
        Period stringweek = Period.ofWeeks(numberOfWeeks);
        String stringweekdelivery = stringweek.toString();
        String stringpdays = pdays.toString();
        System.out.println(" tree weeks to string: " + stringweekdelivery + " == " + stringpdays);
    }
}

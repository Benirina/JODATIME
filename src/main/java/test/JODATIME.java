package test;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;

import java.util.Locale;

/*import java.time.Period;
import java.util.Locale;*/

class JODATIME {
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
    public static void main(String[] args)
    {

        // Get the number of Weeks
        int numberOfWeeks = 3;

        // Parse the numberOfWeeks into Period
        // using ofWeeks() method
        Period p = Period.weeks(numberOfWeeks);


        Period pdays = Period.days(21);

        System.out.println(p.getYears() + " Years\n"
                + p.getMonths() + " Months\n"
                + p.getDays() + " Days");
        Period stringweek = Period.weeks(numberOfWeeks);
        String stringweekdelivery = stringweek.toString();
        String stringpdays = pdays.toString();
        System.out.println(" tree weeks to string: "+stringweek.getWeeks()+" == "+Period.parse(stringpdays).getDays());
        // TODO Auto-generated method stub
        /*Locale.setDefault(Locale.GERMANY);
        PeriodFormatter periodFormatter = PeriodFormat.wordBased(Locale.GERMANY);
        final Period period = new Period(6, 5, 4, 3);
        final String s = period.toString(periodFormatter);
        // i'm expecting english to be outputted
        System.out.println("s = " + s); // outputs german: 6 Stunden, 5 Minuten,
        // 4 Sekunden und 3 Millisekunden
        */


        /*API JODA-TIME*/
        DateTimeFormatter formatterdatetime = DateTimeFormat.forPattern("dd/MM/yyyy");
        LocalDate startdate = LocalDate.parse("11/12/2022",formatterdatetime);
        LocalDate enddate = LocalDate.now();

        Period period = new Period(enddate,startdate);

        PeriodFormatter formatter = PeriodFormat.wordBased(Locale.GERMANY);
        String textperiod = formatter.print(period);
        System.out.println(formatter.print(period)); // output: 1 Jahr, 2 Monate und 3 Wochen

        formatter = formatter.withLocale(Locale.ENGLISH);
        System.out.println(formatter.print(period)); // output: 1 Jahr, 2 Monate und 3 Wochen (bug???)

        formatter = PeriodFormat.wordBased(Locale.ENGLISH);
        System.out.println(formatter.print(period)); // output: 1 year, 2 months and 3 weeks


        /*
         API JODA TIME
         import:
            import org.joda.time.DateTime;
            import org.joda.time.format.DateTimeFormat;
            import org.joda.time.format.DateTimeFormatter;
         */

        /*
        String dateTime = "11/27/2020 05:35:00";
        DateTimeFormatter datetimeformat = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");

        DateTime joda_time = datetimeformat.parseDateTime(dateTime);
        System.out.println("joda_time : "+joda_time);

        DateTimeFormatter dateTimeFormatOut = DateTimeFormat.forPattern("MM/dd/yyyy");
        System.out.println("date time format out:  "+dateTimeFormatOut.print(joda_time));
        */
        String hourFormat = "yyyy-MM-dd HH";
        String startTime = "2014-02-05 05";
        String endTime   = "2016-02-04 05";
        DateTimeFormatter hourFormatter = DateTimeFormat.forPattern(hourFormat);
        DateTime start = hourFormatter.parseDateTime(startTime);
        DateTime end   = hourFormatter.parseDateTime(endTime );
        Interval interval = new Interval(start, end);
        Period periodinterval = interval.toPeriod();
        System.out.println(String.format(
                "Years: %d  Months: %d  Days: %d  Hours: %d",
                periodinterval.getYears(), periodinterval.getMonths(), periodinterval.getDays(), periodinterval.getHours()
        ));

        Period periodyearMonthDay = new Period(start, end, PeriodType.yearMonthDay());
        System.out.println(String.format(
                "Years: %d  Months: %d  Days: %d",
                periodinterval.getYears(), periodinterval.getMonths(), periodinterval.getDays()
        ));
        PeriodFormatter formatterbasedzermany = PeriodFormat.wordBased(Locale.GERMANY);
        Period daysofweek = Period.parse(stringweek.toString(formatter),formatter);
        System.out.println(String.format(
                "Week: %d ",
                daysofweek.getWeeks()
        ));
    }
}

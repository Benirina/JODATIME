package APIJodatimeAndAPIJavaTime;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lookaheadandlookbehind.LookAhead;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.parser.ParseException;
import org.joda.time.*;
import org.joda.time.format.*;

public class JodaTime {
    private static Logger LOGGER = LogManager.getLogger(JodaTime.class);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.days());

    private static final Locale CURRENT_LOCALE = Locale.GERMANY;
    private static final PeriodFormatter DELIVERY_PERIOD_PARSER = PeriodFormat.wordBased(CURRENT_LOCALE);
    private static final DateTimeFormatter REVIEW_DATE_PARSER = DateTimeFormat.forPattern("d MMM yyyy")
            .withLocale(Locale.FRENCH);
    private static final DateTimeFormatter REVIEW_DATE = DateTimeFormat.forPattern("dd MMM yyyy HH:mm")
            .withLocale(Locale.FRENCH);
    private static final LocalDate NOW = LocalDate.now();
    // La date de livraison aura lieu le, Mardi 22 Mars 2022
    private static final Pattern DELIVERY_PERIOD_PATTERN = Pattern.compile("(\\d+\\s*\\p{Alpha}+\\s*\\d{1,4}+)");

    // Logging
    // private static Logger LOG = Logger.;

    // FormatSymbols dfsFR = new DateFormatSymbols(Locale.FRENCH);
    private static final NumberFormat NUMBER_FORMAT_FRENCH = NumberFormat.getInstance(Locale.FRENCH);

    public static LocalDate toJoda(java.time.LocalDate input) {
        return new LocalDate(input.getYear(), input.getMonthValue(), input.getDayOfMonth());
    }

    public static Days parseDays(String periodStr) {
        Period p = new Period();
        if (periodStr == null) {
            return Days.ZERO;
        }
        try {
            p = PARSER.parsePeriod(periodStr);
        } catch (Exception e) {

        }

        return p.toStandardDays();
    }

    public static java.time.LocalDate parseDateTime(LocalDate date) {
        java.time.format.DateTimeFormatter frm = java.time.format.DateTimeFormatter.ofPattern("dd MMMM yyyy",
                Locale.GERMANY);
        return null;
    }

    public static String formatNumberFrenchLocal(Number number) {
        try {
            return NUMBER_FORMAT_FRENCH.format(number);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {

        Period periodweek = Period.weeks(3);

        // Convert Period to Joda-Time Weeks
        Weeks weeks = periodweek.toStandardWeeks();
        // Convert Period to Joda-Time Days
        Days days = periodweek.toStandardDays();
        // Convert Period to Joda-Time Hours
        Hours heurs = periodweek.toStandardHours();
        // Convert Period to Joda-Time Minutes
        Minutes minutes = periodweek.toStandardMinutes();
        // Convert Period to Joda-Time Seconds
        Seconds seconds = periodweek.toStandardSeconds();

        LOGGER.debug(" String to period of the week: [3 weeks =>  " + periodweek.toString() + " ]");
        String periodString = periodweek.toString(DELIVERY_PERIOD_PARSER);
        LOGGER.debug(" FORMAT STRING PERIOD LOCAL GERMANY : " + periodString);

        Period periodformatlocalgermany = Period.parse(periodString, DELIVERY_PERIOD_PARSER);
        int numberdays = periodformatlocalgermany.toStandardDays().getDays();
        LOGGER.debug(" Number days of tree week: " + periodformatlocalgermany.toStandardDays().getDays());

        // DateTime To Locale Date
        DateTime nowdatetime = DateTime.now();
        String dayName = nowdatetime.dayOfWeek().getAsText(Locale.FRENCH);
        String monthName = nowdatetime.monthOfYear().getAsText(Locale.FRENCH);
        LOGGER.debug(" Name of now: " + dayName);
        LOGGER.debug(" Name of month: " + monthName);

        LocalDate localDatenowdatetime = nowdatetime.toLocalDate();
        // Locale Date To DateTime
        DateTimeZone dateTimeZone = DateTimeZone.forID("Europe/London");
        DateTime datetimelocalDatenowdatetime = localDatenowdatetime.toDateTimeAtStartOfDay();

        LOGGER.debug("[ Date time: " + nowdatetime + " <=> Local date: " + localDatenowdatetime + " ]");
        LOGGER.debug("[ Local date: " + localDatenowdatetime + " ] <=>  Date time: " + nowdatetime + " ]");

        DateTimeFormatter PATTERN_DATE_USA = DateTimeFormat.forPattern("dd MMM yyyy HH:mm").withLocale(Locale.US).withZone(DateTimeZone.UTC);
        String formatDateClient = "26 mars 2022 16:22";
        DateTime now = DateTime.now();
        LOGGER.debug(now + " Convert date now to string: " + now.toString(REVIEW_DATE));

        //https://stackoverflow.com/questions/21188027/runtime-exception-while-trying-to-parse-date-time-using-joda-datetimeformatter
        // https://github.com/JodaOrg/joda-time/issues/240
        // Pour JodaTime, h est une demi-journée. Donc il faut utiliser H (ou éventuellement k).
        DateTime datereview = REVIEW_DATE.parseDateTime(formatDateClient);
        //String date 26 mars 2022 16:22 = > Joda time 2022-03-26T16:22:00.000+01:00 avec Time zone(withZone(DateTimeZone.forID("Europe/Paris")))
        LOGGER.debug("String date " + formatDateClient + " = > Joda time " + datereview);

        String rawtextdate = "Le 26 mars 2022 à 16:22";

        String datetext = StringUtils.substringAfter(rawtextdate, "Le");
        datetext = (datetext.replaceAll("à", "")).trim();
        datetext = datetext.replaceAll("\\s+", " ");
        LOGGER.debug(" Date text reviews : " + REVIEW_DATE.parseDateTime(datetext));
        LOGGER.debug(" Date text reviews : " + LocalDateTime.parse(datetext, REVIEW_DATE).toDate());


        // Convert Period to Joda-Time Duration
        Duration duration = periodweek.toStandardDuration().toDuration();
        long durationofdays = duration.getStandardDays();
        LOGGER.debug(" Duration of 3 weeks to days: " + durationofdays);
        // La date de livraison aura lieu le, Mardi 22 Mars 2022
        String rawdeliveryproduct = "La date de livraison aura lieu le, Mardi 22 Mars 2022 avec un frais de livraison 233€";
        String datestringpreviousdelivery = null;
        Matcher matcher = DELIVERY_PERIOD_PATTERN.matcher(rawdeliveryproduct);
        while (matcher.find()) {
            datestringpreviousdelivery = (matcher.group()).toLowerCase();
            LOGGER.debug(" Date previous delivery product: " + matcher.group().trim());
        }

        DateTime nowbuyproduct = DateTime.now();
        DateTime datedeliveryproduct = REVIEW_DATE_PARSER.parseDateTime(datestringpreviousdelivery);
        LOGGER.debug(" Myself date: " + datedeliveryproduct.getDayOfWeek());
        Duration durationdeliveryproduct = new Duration(nowbuyproduct, datedeliveryproduct);
        LOGGER.debug(" Duration the delivery product: " + durationdeliveryproduct.getStandardDays()
                + " Name day: " + datedeliveryproduct.dayOfWeek().getAsText(Locale.FRENCH));
        /*
         * Getting the duration between date of buy product and date of delivery
         * product We are to convert the date from text to date time joda.
         */
        DateTime nowapply = DateTime.now();
        DateTime threeDaysAgo = nowapply.minusDays(3);
        Duration durationdelivery = new Duration(threeDaysAgo, nowapply);
        LOGGER.debug(" Duration between two dates: " + durationdelivery.getStandardDays() + " days");



        LOGGER.debug("++++++++++++++++++++ Commandé avant 17h, expedié aujourd'hui ++++++++++++++++");
        DateTime nowTime = DateTime.now();
        DateTime commandTime = nowTime;
        DateTime deliveryTime = nowTime;

        Period periodDel = Period.days(1);


        //Duration timeDelivery = new Duration(commandTime);
        LOGGER.debug("Duration: "+periodDel.getDays());

        // Number format to french local
        Number number = 123000000;
        LOGGER.debug(" Format local french: " + formatNumberFrenchLocal(number));


        String x = "22/06/2012";
        String y = "25/10/2014";

        String datestart = x;
        String datestop = y;


        DateTimeFormatter format = DateTimeFormat.forPattern("dd/mm/yyyy");


        DateTime d1 = null;
        DateTime d2 = null;

        try {
            d1 = format.parseDateTime(datestart);
            d2 = format.parseDateTime(datestop);

            DateTime dt1 = new DateTime(d1);
            DateTime dt2 = new DateTime(d2);

            Period periodDateTime = new Period(dt1, dt2);

            String stringPeriodDateTime = periodDateTime.toString();
            //calculate days
            int daysPeriodDateTime = Days.daysBetween(dt1, dt2).getDays();


        } catch (Exception e) {
            e.printStackTrace();
        }


        org.joda.time.format.DateTimeFormatter formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd'T'hh:mm:ss'Z'").withZone(DateTimeZone.UTC);
        DateTime dateTime = formatter.parseDateTime("2022-04-19T06:00:00Z").withZone(DateTimeZone.UTC);
        LOGGER.debug("Date time joda: " + dateTime);


        Pattern DELIVERY_DATE_PATTERN = Pattern.compile("yyyy-MM-dd'T'hh:mm:ss'Z'");

		/*String localDate = "2022-04-19T06:00:00Z";
		Pattern DELIVERY_DATE_PATTERN = Pattern.compile("dd-MMM-yyyy hh:mm:ss a Z").withZone(DateTimeZone.UTC)("yyyy-MM-ddTHH:mm:ssZ");
		Matcher matcherDate = DELIVERY_DATE_PATTERN.matcher("2022-04-19T06:00:00Z");
		LocalDateTime localDateTime = LocalDateTime.parse(localDate);*/
        Matcher matcherDate = DELIVERY_DATE_PATTERN.matcher("2022-04-19T06:00:00Z");
        if (matcherDate.find()) {
            LOGGER.debug(" It's the date correct: " + dateTime);

        }

        String textdate = "2022-02-24";
        DateTimeFormatter REVIEW_DATE = DateTimeFormat.forPattern("yyyy-MM-dd").withLocale(Locale.FRENCH);
        Date localdatetime = LocalDateTime.parse(textdate, REVIEW_DATE).toDate();
        LOGGER.debug(" Date java " + localdatetime);



        /* Parse short month names in Joda Time*/
        DateTimeFormatter PATTERN_MONTH_NAME = DateTimeFormat.forPattern("MMM").withLocale(Locale.FRENCH);
        DateTime instanceMonth = PATTERN_MONTH_NAME.parseDateTime("juin");
        int numberMonthOfYear = instanceMonth.getMonthOfYear();
        String nameMonthOfYear = instanceMonth.monthOfYear().getAsText(Locale.FRENCH);
        Period periodOfMonth = Period.months(6);

        LOGGER.debug(numberMonthOfYear+" text of month: "+nameMonthOfYear+" To period: "+periodOfMonth.toString());













    }
}

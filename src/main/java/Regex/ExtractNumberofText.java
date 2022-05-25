package Regex;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractNumberofText {
    private static final Pattern DELIVERY_PERIOD_PATTERN = Pattern.compile("([0-9]+\\s*)(?=(dias|mes|jour|mois|giorni))");
    private static Pattern ADVANCE_PATTERN = Pattern.compile("(\\d+)\\s*(dias|mese|jour|mois|giorni|mes)");
    private static Pattern EXTRACT_PERIOD_DELIVE_PRODUCT_HOME_PATTERN = Pattern.compile("(\\d+((?=\\s*jour)|(?=€)))");
    private static String rawtextdellivery = "Livraison  entre 7 et 10 jour(s) à partir de 31€";

    public static String returnTextMatcherPattern(String rawtext){
        //Avoiding to call method find() of class Matcher in sight the problem of this value
        Matcher matcher = EXTRACT_PERIOD_DELIVE_PRODUCT_HOME_PATTERN.matcher(rawtextdellivery);
        boolean isTextPresent = matcher.find();//This value is true
        //boolean isPresentText = matcher.find();//This value is false
        if(isTextPresent){
            String textperioddelivery = matcher.group();
            return textperioddelivery;
        }
        return null;
    }
    public static Collection<String> returnFullTextMatcherPattern(String rawtext){
        Collection<String> extractAllSubstring = new ArrayList<String>();
        Matcher matcher = EXTRACT_PERIOD_DELIVE_PRODUCT_HOME_PATTERN.matcher(rawtextdellivery);
        boolean isTextPresent = matcher.find();//This value is true
        String textperioddelivery = matcher.group(0);
        extractAllSubstring.add(textperioddelivery);
        if(isTextPresent){
            String textperioddelivery1 = matcher.group();
            extractAllSubstring.add(textperioddelivery1);
            return extractAllSubstring;
        }
        return null;
    }
    private static String otherPatternRegex(String extractedValue) {
        String inputText = StringUtils.stripAccents(extractedValue);
        Matcher periodMatcher = DELIVERY_PERIOD_PATTERN.matcher(inputText);
        //boolean isTextPresent = periodMatcher.matches();
        if (periodMatcher.find()) {
            String periodDeliveryProduct = periodMatcher.group();
            return periodDeliveryProduct;
        }
        return null;
    }



    public static void main(String[] args) {
        Period p = Period.days(Integer.parseInt(otherPatternRegex(rawtextdellivery)));
        int dayOfPeriod = p.toStandardDays().getDays();
        //System.out.println(" Result: "+returnTextMatcherPattern(rawtextdellivery));
        Collection<String> listMatchers = returnFullTextMatcherPattern(rawtextdellivery);
        System.out.println(" Size of list: "+listMatchers.size());
        listMatchers.forEach(
                string ->{System.out.println(" ******** "+string);}
        );


    }
}

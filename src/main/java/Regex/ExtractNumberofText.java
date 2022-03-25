package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractNumberofText {
    private static Pattern EXTRACT_PERIOD_DELIVE_PRODUCT_HOME_PATTERN = Pattern.compile("(\\d+\\s*jour.{1,3})");
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


    public static void main(String[] args) {
        System.out.println(" Result: "+returnTextMatcherPattern(rawtextdellivery));

    }
}

package lookaheadandlookbehind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LookAhead {
    private static final Pattern PATTERN_LOOKAHEAD = Pattern.compile("(\\d+((?=\\s*jour)|(?=€)))");
    private static final String RAWTEXTMATCHER_LOOKAHEAD = "Livraison  entre 7 et 10 jour(s) à partir de 31€";


    public static String extractNumberLimitTimeDelivery(String rawtext){
        Matcher matcher = PATTERN_LOOKAHEAD.matcher(rawtext);
        if(matcher.find()){
            return matcher.group();
        }
        return null;
    }
    public static void main(String[] args) {
        String pricedelivery = extractNumberLimitTimeDelivery(RAWTEXTMATCHER_LOOKAHEAD);
        System.out.println(" Price of delivery product: "+pricedelivery);
    }
}

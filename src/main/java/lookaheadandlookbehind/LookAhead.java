package lookaheadandlookbehind;

import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LookAhead {
    //https://howtodoinjava.com/log4j2/log4j2-properties-example/
    //https://localcoder.org/propertyconfigurator-in-log4j2
    //https://mkyong.com/logging/apache-log4j-2-tutorials/
    //https://blog.engineering.publicissapient.fr/2008/07/11/les-10-commandements-des-logs-applicatives/
    //https://www.ibm.com/docs/fr/elm/6.0?topic=support-log-file-locations-tracing
    //https://dotclear.placeoweb.com/post/gestion-des-log-journalisation-sur-j2ee
    private static Logger LOGGER = LogManager.getLogger(LookAhead.class);
    //private static Logger LOGGER = LoggerFactory.getLogger(LookAhead.class);
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


        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        String pricedelivery = extractNumberLimitTimeDelivery(RAWTEXTMATCHER_LOOKAHEAD);
        LOGGER.debug(" Price of delivery product: "+pricedelivery);
        //Check if the string can to parse a number or not.
        if(NumberUtils.isParsable(pricedelivery)){
            Float numberFloat = NumberUtils.createFloat(pricedelivery);
            LOGGER.debug("String to float: "+numberFloat);
        }
        //Class StringUtils
        boolean isStringContainsAnyPattern = StringUtils.containsAny(RAWTEXTMATCHER_LOOKAHEAD,"10 jour(s)","31€");
        LOGGER.debug("Result of comparison: "+isStringContainsAnyPattern);

        boolean isResultComparisonTwoString = StringUtils.containsAnyIgnoreCase(RAWTEXTMATCHER_LOOKAHEAD,"10 jour(s)");
        LOGGER.debug("Result of comparison two string: "+isResultComparisonTwoString);

        int numberSequencyCharOrSubstring = StringUtils.countMatches(RAWTEXTMATCHER_LOOKAHEAD,"31€");
        LOGGER.debug("Number of repetition substring: "+numberSequencyCharOrSubstring);

    }
}

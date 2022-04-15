package lookaheadandlookbehind;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lookbehind {
    private static final Pattern PATTERN_LOOKBEHIND =Pattern.compile("((?<=offset=)\\d+)");
    private static final String RAWTEXT_LOOKBEHIND_POSITIF = "https://www.conforama.fr/evaluation/marchand/?miraklShopId=2672&offset=68";
    private static final String RAWTEXT_LOOKBEHIND_NEGATIF = "IPv4 127.47.12.47";


    public static String extractIPMachineLookbehindPositif(Pattern pattern,String rawtextinput){
        Matcher matcher = pattern.matcher(rawtextinput);
        if(matcher.find()){
            return matcher.group();
        }
        return null;
    }
    private static String extractNumberNoCharDollar(String rawtextLookbehindNegatif) {
        return null;
    }


    public static void main(String[] args) {
        int sumnumbergroup = PATTERN_LOOKBEHIND.matcher(RAWTEXT_LOOKBEHIND_POSITIF).groupCount();
        String ipmachine = extractIPMachineLookbehindPositif(PATTERN_LOOKBEHIND,RAWTEXT_LOOKBEHIND_POSITIF);
        String numbernochardollar = extractNumberNoCharDollar(RAWTEXT_LOOKBEHIND_NEGATIF);

        System.out.println(" Last number pagination: "+extractIPMachineLookbehindPositif(PATTERN_LOOKBEHIND,RAWTEXT_LOOKBEHIND_POSITIF));

        Pattern PATTERN_AVERAGE = Pattern.compile("(\\d+((?=%)|(?=.0%)))");
        String rawtext = "width: 86%;";
        System.out.println("Result: "+extractIPMachineLookbehindPositif(PATTERN_AVERAGE,rawtext));

        String rawtextdate = "Le 26 mars 2022 à 16:22";

        String datetext = StringUtils.substringAfter(rawtextdate,"Le");
        datetext = (datetext.replaceAll("à","")).trim();
        datetext = datetext.replaceAll("\\s+"," ");
        System.out.println(" Date text reviews : "+datetext);
    }



}

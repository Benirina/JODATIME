package lookaheadandlookbehind;

import java.util.regex.Pattern;

public class Lookbehind {
    private static final Pattern PATTERN_LOOKBEHIND =Pattern.compile("");
    private static final String RAWTEXT_LOOKBEHIND_POSITIF = "IPv4 127.47.12.47";
    private static final String RAWTEXT_LOOKBEHIND_NEGATIF = "IPv4 127.47.12.47";


    public static String extractIPMachineLookbehindPositif(String rawtextinput){
        return null;
    }
    private static String extractNumberNoCharDollar(String rawtextLookbehindNegatif) {
        return null;
    }
    public static void main(String[] args) {
        int sumnumbergroup = PATTERN_LOOKBEHIND.matcher(RAWTEXT_LOOKBEHIND_POSITIF).groupCount();
        String ipmachine = extractIPMachineLookbehindPositif(RAWTEXT_LOOKBEHIND_POSITIF);
        String numbernochardollar = extractNumberNoCharDollar(RAWTEXT_LOOKBEHIND_NEGATIF);
    }



}

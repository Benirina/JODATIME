package SwitchJAVA8;

import org.apache.commons.lang3.StringUtils;

public class SwitchJAVA8 {
    public static final String WWW_ZALANDO_BE = "zalando.be";
    public static final String WWW_ZALANDO_FR = "zalando.fr";
    public static final String FR = "fr";
    public static final String LANGUAGE_FR = "fr";
    public static final String WWW_ZALANDO_NL = "zalando.nl";
    public static final String LANGUAGE_NL = "nl";
    public static final String WWW_EN_ZALANDO_DE = "en.zalando.de";
    public static final String LANGUAGE = "en";
    public static final String LANGUAGE_EN = LANGUAGE;
    public static final String WWW_ZALANDO_ES = "zalando.es";
    public static final String ES = "es";
    public static final String LANGUAGE_ES = "es";
    public static final String WWW_ZALANDO_PL = "zalando.pl";
    public static final String LANGUAGE_PL = "pl";
    public static final String WWW_ZALANDO_AT = "zalando.at";
    public static final String LANGUAGE_AT = "de";
    public static final String WWW_FR_ZALANDO_BE = "fr.zalando.be";
    public static final String WWW_ZALANDO_HR = "zalando.hr";
    public static final String LANGUAGE_HR = "hr";
    public static final String WWW_ZALANDO_CZ = "zalando.cz";
    public static final String LANGUAGE_CZ = "cs";
    public static final String WWW_ZALANDO_DK = "zalando.dk";
    public static final String LANGUAGE_DK = "da";
    public static final String WWW_ZALANDO_EE = "zalando.ee";
    public static final String LANGUAGE_EE = "et";
    public static final String WWW_ZALANDO_FI = "zalando.fi";
    public static final String LANGUAGE_FI = "fi";
    public static final String WWW_ZALANDO_IE = "zalando.ie";
    public static final String WWW_ZALANDO_IT = "zalando.it";
    public static final String LANGUAGE_IT = "it";
    public static final String WWW_ZALANDO_LV = "zalando.lv";
    public static final String LANGUAGE_LV = "lv";
    public static final String WWW_ZALANDO_LT = "zalando.lt";
    public static final String LANGUAGE_LT = "lt";
    public static final String WWW_ZALANDO_NO = "zalando.no";
    public static final String LANGUAGE_NO = "no";
    public static final String WWW_ZALANDO_SK = "zalando.sk";
    public static final String LANGUAGE_SK = "sk";
    public static final String WWW_ZALANDO_SI = "zalando.si";
    public static final String LANGUAGE_SI = "sl";
    public static final String WWW_ZALANDO_SE = "zalando.se";
    public static final String LANGUAGE_SE = "sv";
    public static final String WWW_FR_ZALANDO_CH = "fr.zalando.ch";
    public static final String LANGUAGE_CH = "ch";
    public static final String WWW_ZALANDO_CO_UK = "zalando.co.uk";
    public static final String LANGUAGE_UNKNOWN = "";
    private static String getLanguagePreference(String host) {
        host = StringUtils.containsIgnoreCase(host,"www.")?StringUtils.substringAfterLast(host,"www."):host;
        String language = "";
        switch (host) {
            case WWW_ZALANDO_BE:case WWW_ZALANDO_FR:case WWW_FR_ZALANDO_BE:case WWW_FR_ZALANDO_CH:
                language = LANGUAGE_FR;
                break;
            case WWW_ZALANDO_NL:
                language = LANGUAGE_NL;
                break;
            case WWW_EN_ZALANDO_DE:case WWW_ZALANDO_IE:case WWW_ZALANDO_CO_UK:
                language = LANGUAGE_EN;
                break;
            case WWW_ZALANDO_ES:
                language = LANGUAGE_ES;
                break;
            case WWW_ZALANDO_PL:
                language = LANGUAGE_PL;
                break;
            case WWW_ZALANDO_AT:
                language = LANGUAGE_AT;
                break;
            case WWW_ZALANDO_HR:
                language = LANGUAGE_HR;
                break;
            case WWW_ZALANDO_CZ:
                language = LANGUAGE_CZ;
                break;
            case WWW_ZALANDO_DK:
                language = LANGUAGE_DK;
                break;
            case WWW_ZALANDO_EE:
                language = LANGUAGE_EE;
                break;
            case WWW_ZALANDO_FI:
                language = LANGUAGE_FI;
                break;
            case WWW_ZALANDO_IT:
                language = LANGUAGE_IT;
                break;
            case WWW_ZALANDO_LV:
                language = LANGUAGE_LV;
                break;
            case WWW_ZALANDO_LT:
                language = LANGUAGE_LT;
                break;
            case WWW_ZALANDO_NO:
                language = LANGUAGE_NO;
                break;
            case WWW_ZALANDO_SK:
                language = LANGUAGE_SK;
                break;
            case WWW_ZALANDO_SI:
                language = LANGUAGE_SI;
                break;
            case WWW_ZALANDO_SE:
                language = LANGUAGE_SE;
                break;
            default:
                language = LANGUAGE_UNKNOWN;
                break;
        }
        return language;
    }


    public static void main(String[] args) {
        System.out.println("Value of method: "+getLanguagePreference("zalando.fr"));
    }
    private static String getLanguageReferenceOfHost(int initialValue) {
        String returnedValue = "";
        switch (initialValue){
            case 1: returnedValue="Too small!";
            break;
            case 2:case 3:case 4:case 5: returnedValue="Good value!";
            break;
            case 6: returnedValue="Too big!";
            break;
            default: returnedValue="Not applicable!";
            break;
        }
        return returnedValue;
    }


}

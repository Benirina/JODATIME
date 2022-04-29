package test;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class CPPlateforme {
    static final Logger LOGGER = LogManager.getLogger(CPPlateforme.class);
    public static BigDecimal toBigDecimal(final String value) {
        if (isBlank(value))
            return null;
        String cleanedNumber = removePattern(value, "[^-\\d,.]+");
        cleanedNumber = replace(cleanedNumber, ",", ".");
        if (isBlank(cleanedNumber))
            throw new RuntimeException("Unable to parse [" + value + "] as number");
        return new BigDecimal(cleanedNumber);
    }

    public static void main(String[] args) {
        String rawDelivery = " En Stock - commandé avant 17h, expédié aujourd'hui!";
        if(StringUtils.contains(rawDelivery,"expédié")) {
            String dateDelivery = StringUtils.substringAfterLast(rawDelivery,"expédié");
            LOGGER.debug("It's text content the motif: "+dateDelivery);
        }
        String inputData = "€ 39,00";
        BigDecimal number = toBigDecimal(inputData);
        LOGGER.debug("Value of input text price: "+number.floatValue());
    }
}

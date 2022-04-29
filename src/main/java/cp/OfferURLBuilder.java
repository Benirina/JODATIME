package cp;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OfferURLBuilder {
    static final Logger LOG = LogManager.getLogger(OfferURLBuilder.class);
    public static String returnUniqueIdentifierOfferURLBuilder(String url){
        //url = StringUtils.removePattern(url, "/+$");

        String uniqueUrlIdentifier = StringUtils.substringAfterLast(url, "/");

        if(uniqueUrlIdentifier.contains("html")){

            uniqueUrlIdentifier = StringUtils.substringBefore(uniqueUrlIdentifier,".html");

        }

        if(StringUtils.isNotBlank(uniqueUrlIdentifier)) uniqueUrlIdentifier = uniqueUrlIdentifier.toLowerCase();

        return StringUtils.replacePattern(uniqueUrlIdentifier, "[^a-zA-Z0-9]+","-");
    }

    public static void main(String[] args) {
        final String urloffer = "https://www.baby-lux.com/trio-poussette-chrome-nacelle-chrome-coque-gemm-joie.html";
        LOG.info("Identifier: "+returnUniqueIdentifierOfferURLBuilder(urloffer));
    }

}

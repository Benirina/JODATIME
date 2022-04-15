package AnalystLoop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

public class ProcessLoop {
    private static Logger LOGGER = LogManager.getLogger(ProcessLoop.class);
    public static void main(String[] args) {
        for (int offerIndex = 0; offerIndex < 1; offerIndex++) {
            //offerIndex++;
            LOGGER.info("Page :: " + offerIndex + " - Crawl Offer :: " + offerIndex);
            String testloop = "my test";
            if (testloop != null) {
                LOGGER.info("process of the rang "+offerIndex+"....");
            }
        }
    }

}

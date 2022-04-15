package IncrementValuesMethodGlobal;

import lookaheadandlookbehind.LookAhead;
import org.apache.commons.lang.math.IntRange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import java.util.stream.IntStream;

public class IncrementVariable {
    private static Logger LOGGER = LogManager.getLogger(IncrementVariable.class);
    private static int brandNumberRange(Integer number){
        IntRange range0To20 = new IntRange(0,20);
        IntRange range21To40 = new IntRange(21,40);
        IntRange range41To60 = new IntRange(41,60);
        IntRange range61To80 = new IntRange(61,80);
        IntRange range81To100 = new IntRange(81,100);
        if(range0To20.containsInteger(number)){
            return 1;
        }else if(range21To40.containsInteger(number)){
            return 2;
        }else if(range41To60.containsInteger(number)){
            return 3;
        }else if(range61To80.containsInteger(number)){
            return 4;
        }else{
            return 5;
        }
    }
    private static void incrementBandRating(Integer number, int countratingBand0to20, int countratingBand21to40, int countratingBand41to60, int countratingBand61to80, int countratingBand81to100, String urlwelcomepageratingandreviews){

        int ratingBand = brandNumberRange(number);
        switch(brandNumberRange(ratingBand)){
            case(1):
                countratingBand0to20++;
                break;
            case(2):
                countratingBand21to40++;
                break;
            case(3):
                countratingBand41to60++;
                break;
            case(4):
                countratingBand61to80++;
                break;
            case(5):
                countratingBand81to100++;
                break;
            default:
                break;
        }
    }
    public static void displayResultIncrement(Integer minInteger,Integer maxInteger){
        //IntRange rangeInteger = new IntRange(12,100);
        int countratingBand0to20 = 0;
        int countratingBand21to40 = 0;
        int countratingBand41to60 = 0;
        int countratingBand61to80 = 0;
        int countratingBand81to100 = 0;

        IntStream streamInteger = IntStream.range(minInteger,maxInteger);
        streamInteger.forEach(
                var_integer -> {
                    incrementBandRating(var_integer, countratingBand0to20, countratingBand21to40, countratingBand41to60, countratingBand61to80, countratingBand81to100, null);
                }
        );
        LOGGER.debug("Rating band 0 to 20 [*]"+ countratingBand0to20);

        LOGGER.debug("Rating band 20 to 40 [**]"+ countratingBand21to40);

        LOGGER.debug("Rating band 40 to 60 [***]"+ countratingBand41to60);

        LOGGER.debug("Rating band 60 to 80 [****]"+ countratingBand61to80);

        LOGGER.debug("Rating band 80 to 100 [*****]"+ countratingBand81to100);

    }
    public static void main(String[] args) {
        displayResultIncrement(12,100);
    }
}

package FunctionComposee;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionAndThenAndCompose {
    private static String cleanDeliveryPrice(String priceDelivery){
        return  (StringUtils.substringBetween(priceDelivery, "-",".").replace("[^\\d+]","")).trim();
    }

    private static String cleanReductionPercent(String rawReducPrice, String endChar) {
        return null;//(StringUtils.containsIgnoreCase(rawReducPrice,endChar))?:(StringUtils.substringBetween(rawReducPrice, "-", endChar).replace("[^\\d+]", "")).trim();
    }
    private static float getReelDeuxChiffreApresVirgule(String chiffre) {
        //arrondi à 2 chiffres apres la virgules
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2) ;
        df.setMinimumFractionDigits(3) ;
        df.setDecimalSeparatorAlwaysShown(true);
        String value = NumberUtils.isCreatable(chiffre)?df.format(Double.parseDouble(chiffre)):null;
        return (value != null)?Float.parseFloat(value.replaceAll(",",".")):0f;
    }

    private static Double cleanReductionPriceNumber(Double reductionPurcent) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2) ;
        df.setMinimumFractionDigits(2) ;
        df.setDecimalSeparatorAlwaysShown(true);
        return (reductionPurcent!=null)?Double.parseDouble((df.format(reductionPurcent)).replaceAll(",",".")):null;
    }

    private static Float cleanReductionPriceNumber(Float reductionPriceNumber) {
        String stringReductionPriceNumber = String.valueOf(reductionPriceNumber);
        int length = stringReductionPriceNumber.length();
        if(length<4) {
            return Float.parseFloat(stringReductionPriceNumber.substring(0, length));
        }
        return Float.parseFloat(stringReductionPriceNumber.substring(0, 4));
    }

    public static void main(String[] args) {

        Float d = cleanReductionPriceNumber(12.2f);
        d = cleanReductionPriceNumber(12.02f);
        d = cleanReductionPriceNumber(19.023f);
        d = cleanReductionPriceNumber(15.0234f);

        Double reduction = cleanReductionPriceNumber(2.1105);
        double pricep = 10 - ((10 * 30.00000000000)/100);
        float df = getReelDeuxChiffreApresVirgule("30.23000000000");
        String rawReducPriceText = "-30%";
        String rawReducPrice = cleanReductionPercent(rawReducPriceText, ".");
        String rawReducPrice1 = (cleanReductionPercent(rawReducPriceText, ".")==null)?cleanReductionPercent(rawReducPrice, "%"):rawReducPrice;

        //https://fr.acervolima.com/interface-de-fonction-en-java-avec-des-exemples/
        //Différence entre Function.andThen et Function.compose: https://www.javacodegeeks.com/2022/02/difference-between-function-andthen-and-function-compose.html
        String priceDeliveryClean = cleanDeliveryPrice("-30.00000000000");



        //System.out.println ( Double.parseDouble(df.format ( -4.327))); //cela t'affiche -4,33
        //System.out.println ( df.format ( 2174534.3279 ) ) ; //2,174,534,33
        /*
            Il est important de comprendre la différence entre les deux:
                    andThen:  function1.andThen(function2) s'appliquera d'abord  function1 à l'entrée et le résultat sera passé au  function2.
                    compose:  function1.compose(function2) appliquera d'abord l'entrée au  function2 et le résultat sera passé au function1
         */
        Function<Double, Double> half = (a) -> a / 2;
        Function<Double, Double> twice = (a) -> a * a;

        Function<Double, Double> squareAndThenCube = half.andThen(twice);
        Double result = squareAndThenCube.apply(3d);
        System.out.println(result);

        Function<Double, Double> squareComposeCube = half.compose(twice);
        result = squareComposeCube.apply(3d);
        System.out.println(result);



        /*
            Explication en détaille: https://functionalprogramming.medium.com/function-composition-in-java-beaf39426f52
         */

        BiFunction<Double, Double, Double> discountStrategy = (discount, price) -> price - discount * price;


        Function<Double, String> getPriceTag = price -> String.format("Discounted Price : %s ", price);

        BiFunction<Double, Double, String>  getDiscountedPriceTag = discountStrategy.andThen(getPriceTag);

        String discountedPrice = getDiscountedPriceTag.apply(0.2,100.0);//Discounted Price : 80.0





    }


}

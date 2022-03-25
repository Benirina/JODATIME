package Regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractInformationRawText {
    private static final Pattern RAW_PRICE_PRODUCT = Pattern.compile("(voiture\\s*de\\s*[0-9]+€) | (livraison.*[0-9]+\\s*jour)");
    private static final Pattern CAPTURING_GROUP_PATTERN = Pattern.compile("(?=voiture)(voiture\\s*de\\s*[0-9]+€)|(?=livraison)(livraison.*[0-9]+\\s*jour)");
    //private static final Pattern SEPARATOR_PATTERN = Pattern.compile("\\s*");
    public static Optional<String> extractLineTextPriceProduct(String rawlinetext){
        Matcher matcher = RAW_PRICE_PRODUCT.matcher(rawlinetext);
        boolean isPricePresent = matcher.find();
        Collection<String> collectionpriceproductandcostdelveryproduct = new ArrayList<String>();
        collectionpriceproductandcostdelveryproduct.add(matcher.group(1));
        while(matcher.find()) {
            collectionpriceproductandcostdelveryproduct.add(matcher.group());
        }
        Optional<String> optionalpriceandcostdeliveryproduct = Optional.of(collectionpriceproductandcostdelveryproduct.stream().map(a->a.toString()).findAny().get());
        return optionalpriceandcostdeliveryproduct;
    }

    //Capturing group regular expression
    public static Optional<String> extractLineTextPriceAndCostDelivery(Pattern pattern,String rawtextinformationproduct){
        Optional<String> textPriceAndCostDeliveryProduct = Optional.empty();
        Matcher matcher = pattern.matcher(rawtextinformationproduct);
        boolean isPresentPriceAndCost = matcher.find();
        return textPriceAndCostDeliveryProduct;
    }

    public static void main(String[] args) {
        String rawlinetext = "Le code d'identification de votre compte est de 02205 dont la solde de compte est de 256€210. Vous pouvez faire un achat d'une voiture de 200€ dont la date de livraison est gratuite 2 jours plus tard.";
        Optional<String> textPriceProduct = extractLineTextPriceProduct(rawlinetext);
        System.out.println("It's price of product available: "+textPriceProduct.get().toString());
        Optional<String> textPriceProductgroup = extractLineTextPriceAndCostDelivery(CAPTURING_GROUP_PATTERN,rawlinetext);
        //System.out.println("Raw text of the price product and the cost delivery product: "+textPriceProductgroup.get().toString());

    }
}

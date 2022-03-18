package test;

import sun.java2d.SurfaceDataProxy;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Implementation the logging of logs in a file.
public class NumberOfString {
    /**
     * Extract period of delivery product. We are the information like
     *
     * "Disponible sous  3 semaine(s) Livraison gratuite"
     * or
     * "Livraison  entre 7 et 10 jour(s) à partir de 29€".
     */
    //+: une seule fois au moins
    //*: zero ou plusieurs fois
    public final static Pattern DELIVERY_PERIOD_PATTERN = Pattern.compile("(\\d*\\s*jour | \\d+\\s*semain)");
    //Input data from "127.12.7.120"
    public final static Pattern IP_HOST_PATTERN = Pattern.compile("\\d{1,3}\\.{1}\\d{1,3}\\.\\d{1,3}\\.{1}\\d{1,3}");

    public static String verifyInputData(String rawinputdata){
        Matcher matcherdeliveryperiod = DELIVERY_PERIOD_PATTERN.matcher(rawinputdata);
        Matcher matcheriphostpattern = IP_HOST_PATTERN.matcher(rawinputdata);
        /*boolean isValidInputData = matcherdeliveryperiod.find();
        System.out.println(" Text valid: "+isValidInputData);*/
        if(matcherdeliveryperiod.find()){
            return matcherdeliveryperiod.group();
        }
        if(matcheriphostpattern.find()){
            return matcheriphostpattern.group();
        }

        return "Cannot using the input data";
    }

    public static void main(String[] args) {
        //Display regex
        System.out.println(" Motor of the pattern: "+DELIVERY_PERIOD_PATTERN.toString());
        String lcRawDelivery = "11 jour(s) livraison";
        String rawipv4host = "IPV4 127.12.7.120";

        System.out.println(" Testing the input data: "+verifyInputData(lcRawDelivery));
        int numberdelivery = Integer.parseInt((lcRawDelivery.replaceAll("[\\p{Alpha}+\\p{Punct}+]","")).trim());
        System.out.println(" Duration: "+numberdelivery);
        String texte =  "un - deux - trois - quatre FIN" ;

        Pattern p = Pattern.compile("-") ;
        Matcher m = p.matcher(texte) ;

        StringBuffer sb =  new StringBuffer() ;
        int i =  1 ;
        while (m.find()) {
            m.appendReplacement(sb,  "[" + i++ +  "]") ;
        }
        m.appendTail(sb);

        System.out.println(" Content of buffer: "+sb.toString());

        /*
        if(lcRawDelivery.contains("semaine(s)") || lcRawDelivery.contains("semaine")){
            String deletespace =  (lcRawDelivery.replaceAll("[\\p{Alpha}+\\p{Punct}+]","")).trim();
            int timelimit = Integer.parseInt(deletespace);
            if(timelimit==0){
                throw new RuntimeException(" Time limit delivery not found ");
            }
            int daydelivery =  timelimit * 7;
            System.out.println(" Limit days of delivery: "+daydelivery);
        }
        //Test a method with real parameters

        String rawnumber = " ";
        Float castrawnumbertofloat = null;
        try{
            castrawnumbertofloat = Float.parseFloat(rawnumber);
        }catch(Exception e){
            System.out.println("Cannot parse raw string to float");
        }
        Float counter = (rawnumber!=null)?castrawnumbertofloat:null;
        Optional<Float> counterwrapper = Optional.ofNullable(counter);
        if(counterwrapper.isPresent())
            System.out.println(" Value of attribute counterwrapper: "+counterwrapper.get().floatValue());
        */

        /**
         * Using regex to extract information of the delivery product.
         */


    }

}

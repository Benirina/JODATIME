package CompletableFeature;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ExempleCompletableFuture {

    //https://learntutorials.net/fr/java/topic/10935/completablefuture
    public static CompletableFuture<Integer> calculateShippingPrice(int weightInGrams) {
        return CompletableFuture.supplyAsync(() -> {
            // supplyAsync is a factory method that turns a given
            // Supplier<U> into a CompletableFuture<U>

            // Let's just say each 200 grams is a new dollar on your shipping costs
            int shippingCosts = weightInGrams / 200;

            try {
                Thread.sleep(2000L); // Now let's simulate some waiting time...
            } catch(InterruptedException e) { /* We can safely ignore that */ }

            return shippingCosts; // And send the costs back!
        });
    }

    public static String toJSON(Object object) throws JSONException, IllegalAccessException {
        String str = "";
        Class c = object.getClass();
        JSONObject jsonObject = new JSONObject();
        for (Field field : c.getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            String value = String.valueOf(field.get(object));
            jsonObject.put(name, value);
        }
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }


    public static String toJSON(List list ) throws JSONException, IllegalAccessException {
        JSONArray jsonArray = new JSONArray();
        for (Object i : list) {
            String jstr = toJSON(i);
            JSONObject jsonObject = new JSONObject(jstr);
            jsonArray.put(jsonArray);
        }
        return jsonArray.toString();
    }
    public static void main(String[] args) {
        int price = 15; // Let's keep it simple and work with whole number prices here
        int weightInGrams = 900;

        calculateShippingPrice(weightInGrams) // Here, we get the future
                .thenAccept(shippingPrice -> { // And then immediately work on it!
                    // This fluent style is very useful for keeping it concise
                    System.out.println("Your total price is: " + (price + shippingPrice));
                });
        System.out.println("Please stand by. We are calculating your total price.");
    }
}

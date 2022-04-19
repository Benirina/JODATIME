package org.json.simple.JsonArrayAndObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;

public class JsonArrayAndObject {
    public static void main(String[] args) {
        JSONParser PARSER_JSON = new JSONParser();

        try (
                Reader inputReader = new FileReader("E:\\EspaceBenirina\\w2p\\fileJson\\Reviews.json"))

        {

            /*JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            long age = (Long) jsonObject.get("age");
            System.out.println(age);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }*/
            //https://www.delftstack.com/fr/howto/java/read-json-file-java/
            //https://java.tutorialink.com/org-json-simple-jsonarray-cannot-be-cast-to-class-org-json-simple-jsonobject/
            JSONObject jsonObject = (JSONObject) PARSER_JSON.parse(inputReader);
            JSONArray jsonArrayReviews = (JSONArray) jsonObject.get("Results");

            System.out.println("Length of reviews table: "+jsonArrayReviews.size());

            for(Object object:jsonArrayReviews){
                JSONObject jsonObjectReviews = (JSONObject) object;
                String ojectUserNameReviews = (String) jsonObjectReviews.get("UserNickname");
                System.out.println(" User name: "+ojectUserNameReviews);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package CrawlerNatif;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import org.apache.commons.text.StringEscapeUtils;

import static org.apache.commons.lang3.StringUtils.*;

public class Element {
    private static Document document = null;

    static {
        try {
            document = Jsoup.connect("http://www.ngdeveloper.com").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String br2nl(String html) {
        if(html==null)
            return html;
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String str = document.html().replaceAll("\\\\n", "\n");
        return Jsoup.parse(str).text();
    }
    //https://devstory.net/10399/jsoup-java-html-parser
    public static Elements getInputElementForm(File fichier, String charset){
        try{
            Document doc = Jsoup.parse(fichier, charset);
            org.jsoup.nodes.Element form = doc.getElementById("registerForm");
            System.out.println("Form action = "+ form.attr("action"));
            return form.getElementsByTag("input");
        }catch(Exception e){
            System.out.println(" "+e.getMessage());
        }
        return null;
    }

    public static void displayInputElements(Elements inputElementsForm){
        inputElementsForm.forEach(
                (a)->{System.out.println(" Key: "+a.attr("name")+ " => Value: "+a.attr("value"));}
        );
    }

    public static void main(String[] args) {
        //Get path of resource
        URL path = ClassLoader.getSystemResource("Formulaire.html");
        //Create file of type html
        File file = new File(path.getFile());
        //Get element of type input
        Elements elementsInputForm = getInputElementForm(file, "Utf-8");

        displayInputElements(elementsInputForm);

        Elements all_divs = document.getElementsByTag("div");
        System.out.println(all_divs.size());


        //In Java, we can use Apache commons-text, StringEscapeUtils.escapeHtml4(str) to escape HTML characters(HTML transformation characters).[https://mkyong.com/java/how-to-escape-html-in-java/]
        String html = "<h1> hello &nbsp world</h1>";
        String text = StringEscapeUtils.escapeHtml4(html);

        System.out.println(" Html to text: "+text);


        /*String seller = "Neuf est vendu par Antoine";
        seller = contains(seller, "par") ? trim(substringAfterLast(seller, "par")) : seller;
        System.out.println(" Name seller : "+seller);*/



    }
}

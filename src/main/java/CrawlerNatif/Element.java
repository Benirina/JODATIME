package CrawlerNatif;


import org.apache.commons.lang3.StringUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            Document document = null;
            try {
                document = Jsoup.parse(file, "Utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements spanelements = document.select(".stars > span");
            for(org.jsoup.nodes.Element spanel:spanelements){
                String style = spanel.attr("style");
                System.out.println(" Style span: "+style);
            }

            displayInputElements(elementsInputForm);

            Elements all_divs = document.getElementsByTag("div");
            System.out.println(all_divs.size());


            //In Java, we can use Apache commons-text, StringEscapeUtils.escapeHtml4(str) to escape HTML characters(HTML transformation characters).[https://mkyong.com/java/how-to-escape-html-in-java/]
            String html = "<h1> hello &nbsp world</h1>";
            String text = StringEscapeUtils.escapeHtml4(html);

            System.out.println(" Html to text: "+text);


            String seller = "Neuf est vendu par Antoine";
            String sellerNameafterPar = StringUtils.contains(seller, "par") ? trim(StringUtils.substringAfterLast(seller, "par")) : seller;
            System.out.println("StringUtils AfterLast[Par] Name seller : "+sellerNameafterPar);
            String sellerNameBeforePar = StringUtils.contains(seller, "par") ?  StringUtils.substringBeforeLast(seller, "par"):seller;
            System.out.println("StringUtils BeforeLast[Par] Text: "+ sellerNameBeforePar);

            String input = "01-01-2012 01:01:01";
            String date = StringUtils.substringBefore(input, " ");
            System.out.println(" Date not hours : "+date);

            String marketplace = "La boutique du net";
            String titleoffremarketplace = "Apc prise parafoudre pm5-fr "+marketplace;
            String stringbeforelast = StringUtils.substringBefore(titleoffremarketplace, marketplace);
            System.out.println("Sous chîne avant la dernière chaîne: "+stringbeforelast);

            String aParser="<name>toto</name>";
            Pattern p=Pattern.compile("<.*>(.*)<.*>");
            Matcher m=p.matcher(aParser);
            while(m.find()) {
                System.out.println(m.group(0));
            }




            String htmlpart = ""
                    + "<div class=\"entrytext\">"
                    + "<p>1</p>"
                    + "<p><font>SBS</font></p>"
                    + "<p>3</p>"
                    + "<p>4</p>"
                    + "<p>5</p>"
                    + "<p><font>KBS2</font></p>"
                    + "<p>7</p>"
                    + "</div>"
                    ;

            Document doc = Jsoup.parse(htmlpart);
            Elements allPs = doc.select("div.entrytext p:has(font:containsOwn(SBS)) ~ p:not(p:has(font:containsOwn(KBS2)) ~ p):not(p:has(font:containsOwn(KBS2)))");

            System.out.println(allPs);


            String htmlVrac = "<section class=\"games_bundle_box2\">\n" +
                    "  <div class=\"games-container\">\n" +
                    "\n" +
                    "    <!-- List Game Unlocked -->\n" +
                    "    <ul class=\"unlock\">\n" +
                    "\n" +
                    "      <!-- Item -->\n" +
                    "      <li>\n" +
                    "        <!-- Preview Thumb -->\n" +
                    "        <a href=\"#game1\" class=\"fancybox-various\" title=\"Desura &amp; Steam for Windows and Mac - This game has been GreenLighted on Steam and all buyers of The IndieGala Flashpoint bundle will receive Steam keys in a few weeks!\">\n" +
                    "          <span class=\"tier1\">\n" +
                    "            Pay minimum \n" +
                    "            <em class=\"color-text\">\n" +
                    "              $1\n" +
                    "            </em>\n" +
                    "            to get Steam &amp; Desura keys! \n" +
                    "          </span>\n" +
                    "          <span class=\"boxed\">\n" +
                    "          </span>\n" +
                    "          <span class=\"steam-temp\">\n" +
                    "          </span>\n" +
                    "          <span class=\"desura-icon\">\n" +
                    "          </span>\n" +
                    "          <img src=\"img_game/ig-flashpoint/knytt.jpg\" alt=\"\">\n" +
                    "          <!-- 110 x 110 -->\n" +
                    "          <span class=\"item-title\">\n" +
                    "            Knytt Underground\n" +
                    "          </span>\n" +
                    "        </a>\n" +
                    "        <!-- End Preview Thumb -->\n" +
                    "\n" +
                    "        <!-- Pop-Up -->\n" +
                    "\n" +
                    "        <div id=\"game1\" class=\"modal_content\">\n" +
                    "\n" +
                    "          <!-- Video Game Box -->\n" +
                    "          <div class=\"game_movie\">\n" +
                    "            <iframe width=\"488\" height=\"300\" src=\"http://www.youtube.com/embed/NwZ2Z7WRQrI?rel=0&amp;vq=hd720\" frameborder=\"0\" allowfullscreen=\"\">\n" +
                    "            </iframe>\n" +
                    "          </div>\n" +
                    "          <!-- End Video Game Box -->\n" +
                    "\n" +
                    "          <!-- Game Description -->\n" +
                    "          <div class=\"game_description\">\n" +
                    "            <h2>\n" +
                    "              <a href=\"http://www.desura.com/games/knytt-underground\" target=\"_blank\">\n" +
                    "                Knytt Underground\n" +
                    "              </a>\n" +
                    "            </h2>\n" +
                    "            <a href=\"http://nifflas.ni2.se/\" target=\"_blank\">\n" +
                    "              Nifflas' Games\n" +
                    "            </a>\n" +
                    "            <p style=\"font-weight: bold;\">\n" +
                    "              IMPORTANT NOTE: This game has been accepted on Steam. As soon as it will available on the Steam store, all buyers will receive a Steam key for it.\n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              Immerse yourself in a unique and challenging underground adventure!\n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              Knytt Underground is the latest iteration in the critically acclaimed Knytt series. It takes place in the same universe as Knytt Stories, the renowned indie game from Nicklas Nygren, a.k.a. 'Nifflas'.\n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              Each room in the huge map offers a small challenge to overcome using the unique tried and tested mechanics that have already reached over 1 million fans worldwide. Blending zen-like gameplay; enjoy an unparalled sense of freedom by running, jumping, climbing, swinging and bouncing. Knytt Underground delivers a unique and captivating, platform adventure experience allowing you to explore over 1,800 rooms and complete multiple story-driven quests. \n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              <a href=\"#\" target=\"_blank\">\n" +
                    "                System requirements\n" +
                    "              </a>\n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              Operating system: Windows (XP / Vista / 7 / 8) or Mac OS 10.6\n" +
                    "              <br>\n" +
                    "              OpenGL compatible graphics card with 512MB memory or more.\n" +
                    "              <br>\n" +
                    "              A 2ghz CPU is needed.\n" +
                    "              <br>\n" +
                    "              The OS X version requires Mac OS 10.6 and must be running in 64-bit mode.\n" +
                    "              <br>\n" +
                    "            </p>\n" +
                    "          </div>\n" +
                    "        </div>\n" +
                    "        <!-- End Pop-Up -->\n" +
                    "      </li>\n" +
                    "      <!-- End Item -->\n" +
                    "\n" +
                    "      <li>\n" +
                    "        <a href=\"#game2\" class=\"fancybox-various\" title=\"Steam for Windows\">\n" +
                    "          <span class=\"boxed\">\n" +
                    "          </span>\n" +
                    "          <span class=\"steam-icon\">\n" +
                    "          </span>\n" +
                    "          <img src=\"img_game/ig-flashpoint/saira.jpg\" alt=\"\">\n" +
                    "          <span class=\"item-title\">\n" +
                    "            Saira\n" +
                    "          </span>\n" +
                    "        </a>\n" +
                    "        <div id=\"game2\" class=\"modal_content\">\n" +
                    "          <div class=\"game_movie\">\n" +
                    "            <iframe width=\"488\" height=\"300\" src=\"http://www.youtube.com/embed/iFPaXcLF5b0?rel=0&amp;vq=hd720\" frameborder=\"0\" allowfullscreen=\"\">\n" +
                    "            </iframe>\n" +
                    "          </div>\n" +
                    "          <div class=\"game_description\">\n" +
                    "            <h2>\n" +
                    "              <a href=\"http://store.steampowered.com/app/48900/\" target=\"_blank\">\n" +
                    "                Saira\n" +
                    "              </a>\n" +
                    "            </h2>\n" +
                    "            <a href=\"http://nifflas.ni2.se/\" target=\"_blank\">\n" +
                    "              Nifflas' Games\n" +
                    "            </a>\n" +
                    "            <p>\n" +
                    "              Saira is a puzzle platformer with non-linear gameplay and a whole universe for you to explore. The game is heavily influenced by classic puzzle adventure games and uses a new unique graphical style combining high resolution photography into a lush and mysterious world.\n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              The eponymous Saira is a photographer who specializes in digitally capturing dangerous places and animals across the universe. For reasons unknown, she finds herself as the only remaining person in the entire galaxy. Saira has no weapons, she will use only her mind and agility to progress through seven star systems and over 60 well-crafted puzzles. Over two hours of originally-scored music will help her maintain focus and unlock one of six vastly unique endings.\n" +
                    "            </p>\n" +
                    "            <ul>\n" +
                    "              <li>\n" +
                    "                Over 60 well-crafted puzzels.\n" +
                    "              </li>\n" +
                    "              <li>\n" +
                    "                A universe full of surprising creatures and locations for you to explore.\n" +
                    "              </li>\n" +
                    "              <li>\n" +
                    "                11 layers of high definition parallax scrolling and a two hour soundtrack gives the universe of Saira it's unique atmosphere.\n" +
                    "              </li>\n" +
                    "              <li>\n" +
                    "                Non-linear gameplay\n" +
                    "              </li>\n" +
                    "              <li>\n" +
                    "                Multiple endings\n" +
                    "              </li>\n" +
                    "            </ul>\n" +
                    "            <p>\n" +
                    "              <a href=\"#\" target=\"_blank\">\n" +
                    "                System requirements\n" +
                    "              </a>\n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              OS: Windows 2000 or later\n" +
                    "              <br>\n" +
                    "              Processor: 2.1Ghz or higher\n" +
                    "              <br>\n" +
                    "              DirectX®: 9\n" +
                    "              <br>\n" +
                    "              Hard Drive: 250 MB for downloading the game + 200 MB for playing\n" +
                    "              <br>\n" +
                    "            </p>\n" +
                    "          </div>\n" +
                    "        </div>\n" +
                    "      </li>\n" +
                    "      <!-- End Item -->\n" +
                    "\n" +
                    "      <!-- Item -->\n" +
                    "      <li>\n" +
                    "        <!-- Preview Thumb -->\n" +
                    "        <a href=\"#game3\" class=\"fancybox-various\" title=\"Steam for Windows\">\n" +
                    "          <span class=\"boxed\">\n" +
                    "          </span>\n" +
                    "          <span class=\"steam-icon\">\n" +
                    "          </span>\n" +
                    "          <img src=\"img_game/ig-flashpoint/musaic.jpg\" alt=\"\">\n" +
                    "          <!-- 110 x 110 -->\n" +
                    "          <span class=\"item-title\">\n" +
                    "            Musaic Box\n" +
                    "          </span>\n" +
                    "        </a>\n" +
                    "        <!-- End Preview Thumb -->\n" +
                    "\n" +
                    "        <!-- Pop-Up -->\n" +
                    "        <div id=\"game3\" class=\"modal_content\">\n" +
                    "\n" +
                    "          <!-- Video Game Box -->\n" +
                    "          <div class=\"game_movie\">\n" +
                    "            <iframe width=\"488\" height=\"300\" src=\"http://www.youtube.com/embed/ZswOQE8MDbo?rel=0&amp;vq=hd720\" frameborder=\"0\" allowfullscreen=\"\">\n" +
                    "            </iframe>\n" +
                    "          </div>\n" +
                    "          <!-- End Video Game Box -->\n" +
                    "\n" +
                    "          <!-- Game Description -->\n" +
                    "          <div class=\"game_description\">\n" +
                    "            <h2>\n" +
                    "              <a href=\"http://store.steampowered.com/app/29130/\" target=\"_blank\">\n" +
                    "                Musaic Box\n" +
                    "              </a>\n" +
                    "            </h2>\n" +
                    "            <a href=\"http://kranx.com/en/\" target=\"_blank\">\n" +
                    "              KranX Productions\n" +
                    "            </a>\n" +
                    "            <br>\n" +
                    "            <br>\n" +
                    "            <p>\n" +
                    "              Uncover all of your grandfather's sheet music, hidden in his home amongst a treasure trove of gorgeous antiques and musical relics. Melodious music box games will let you piece these special compositions together and unleash their symphonious secrets. Unlock Creative Mode and write your own outstanding arrangements. With a house full of secrets and a box full of music the aural excitement never ends.\n" +
                    "            </p>\n" +
                    "            <ul>\n" +
                    "              <li>\n" +
                    "                Beautifully rendered graphics\n" +
                    "              </li>\n" +
                    "              <li>\n" +
                    "                Entrancing musical score\n" +
                    "              </li>\n" +
                    "              <li>\n" +
                    "                Unlock musical mysteries!\n" +
                    "              </li>\n" +
                    "            </ul>\n" +
                    "            <p>\n" +
                    "              <a href=\"#\" target=\"_blank\">\n" +
                    "                System requirements\n" +
                    "              </a>\n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              Supported OS: Microsoft® Windows® 2000/XP/Vista\n" +
                    "              <br>\n" +
                    "              Processor: 800Mhz\n" +
                    "              <br>\n" +
                    "              Graphics: DirectX® compatible with 16 MB of Video Memory\n" +
                    "              <br>\n" +
                    "              Sound: DirectX® compatible Sound Card\n" +
                    "              <br>\n" +
                    "              DirectX® Version: DirectX 8.0\n" +
                    "              <br>\n" +
                    "              Memory: 256 MB RAM (512+MB recommended)\n" +
                    "              <br>\n" +
                    "              Hard Drive: 54 MB or more\n" +
                    "              <br>\n" +
                    "            </p>\n" +
                    "          </div>\n" +
                    "          <!-- End Game Description -->\n" +
                    "\n" +
                    "        </div>\n" +
                    "        <!-- End Pop-Up -->\n" +
                    "\n" +
                    "      </li>\n" +
                    "      <!-- End Item -->\n" +
                    "\n" +
                    "      <!-- Item -->\n" +
                    "      <li>\n" +
                    "        <!-- Preview Thumb -->\n" +
                    "        <a href=\"#game4\" class=\"fancybox-various\" title=\"Steam for Windows\">\n" +
                    "          <span class=\"boxed\">\n" +
                    "          </span>\n" +
                    "          <span class=\"steam-icon\">\n" +
                    "          </span>\n" +
                    "          <img src=\"img_game/ig-flashpoint/yumsters.jpg\" alt=\"\">\n" +
                    "          <!-- 110 x 110 -->\n" +
                    "          <span class=\"item-title\">\n" +
                    "            Yumsters 2: Around the World\n" +
                    "          </span>\n" +
                    "        </a>\n" +
                    "        <!-- End Preview Thumb -->\n" +
                    "\n" +
                    "        <!-- Pop-Up -->\n" +
                    "        <div id=\"game4\" class=\"modal_content\">\n" +
                    "\n" +
                    "          <!-- Video Game Box -->\n" +
                    "          <div class=\"game_movie\">\n" +
                    "            <iframe width=\"488\" height=\"300\" src=\"http://www.youtube.com/embed/m19AcPO_LMQ?rel=0&amp;vq=hd720\" frameborder=\"0\" allowfullscreen=\"\">\n" +
                    "            </iframe>\n" +
                    "          </div>\n" +
                    "          <!-- End Video Game Box -->\n" +
                    "\n" +
                    "          <!-- Game Description -->\n" +
                    "          <div class=\"game_description\">\n" +
                    "            <h2>\n" +
                    "              <a href=\"http://store.steampowered.com/app/29120/\" target=\"_blank\">\n" +
                    "                Yumsters 2: Around the World\n" +
                    "              </a>\n" +
                    "            </h2>\n" +
                    "            <a href=\"http://kranx.com/en/\" target=\"_blank\">\n" +
                    "              KranX Productions\n" +
                    "            </a>\n" +
                    "            <p>\n" +
                    "              Not only are these Yumsters crazy for strawberries, they can rock the bongos. For the love of fruity music, help them earn money by cleaning gardens to promote their band. To really skyrocket, Yumsters need the best equipment to win the ultimate grand prize at the fairy town music showdown. Get fruitilicious in five vibrant locations of Yumsters 2, a sweet Match 3 puzzler. \n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              Key Features:\n" +
                    "            </p>\n" +
                    "            <ul>\n" +
                    "              <li>\n" +
                    "                Match 3, drag-n-drop \n" +
                    "              </li>\n" +
                    "              <li>\n" +
                    "                Rhythm-based gameplay \n" +
                    "              </li>\n" +
                    "              <li>\n" +
                    "                Adorable characters \n" +
                    "              </li>\n" +
                    "              <li>\n" +
                    "                Win the grand prize! \n" +
                    "              </li>\n" +
                    "            </ul>\n" +
                    "            <p>\n" +
                    "              <a href=\"#\" target=\"_blank\">\n" +
                    "                System requirements\n" +
                    "              </a>\n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "              Operating System: Microsoft® Windows® XP/Vista\n" +
                    "              <br>\n" +
                    "              Processor: 600 Mhz\n" +
                    "              <br>\n" +
                    "              Memory: 256 MB (512+ MB recommended)\n" +
                    "              <br>\n" +
                    "              Hard Disk Space: 54 MB (or More) Available HDD Space\n" +
                    "              <br>\n" +
                    "              Video Card: DirectX® compatible with 16 MB of Video Memory\n" +
                    "              <br>\n" +
                    "              Sound Card: DirectX® compatible Sound Card\n" +
                    "              <br>\n" +
                    "              DirectX® Version: DirectX® 7.0\n" +
                    "              <br>\n" +
                    "            </p>\n" +
                    "          </div>\n" +
                    "          <!-- End Game Description -->\n" +
                    "\n" +
                    "        </div>\n" +
                    "        <!-- End Pop-Up -->\n" +
                    "      </li>\n" +
                    "      <!-- End Item -->      \n" +
                    "<li style=\"width: 400px;font-weight: bold; margin-top: 20px; position: relative; left: 210px; font-size: 16px;\">\n" +
                    "Price BLOCKED at \n" +
                    "<span class=\"color-text\">\n" +
                    "$3.99\n" +
                    "</span>\n" +
                    "for the FIRST 8 HOURS\n" +
                    "</li>\n" +
                    "-->\n" +
                    "          </ul>\n" +
                    "\n" +
                    "          <!-- End List Game Locked -->\n" +
                    "\n" +
                    "  </div>\n" +
                    "</section>\n";
            Document dochtmlVrac = Jsoup.parse(htmlVrac);
            Elements bundleList = dochtmlVrac.select("section.games_bundle_box2").first().select("ul.unlock").first().children();
            System.out.println(" Size of elements: "+bundleList.size());
    }
}

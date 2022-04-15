package CrawlerNatif;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;

public class PaginationSegment
{
    public static void extractTableUsingJsoup(String url, String tableId){
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Element table = doc.getElementById(tableId);
            Elements tds = table.getElementsByTag("td");
            for (Element td : tds) {
                System.out.println("\n"+td.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main( String[] args )
    {
        try{
            /*
            int numPages = 5; // the number of pages to scrape
            for (int i = 0; i < numPages; i++) {
                String url = "https://www.actksa.com/ar/training-courses/training-in/Jeddah?page=" + i;

                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("tr");
                int size = data.size();
                System.out.println("doc: " + doc);
                System.out.println("data: " + data);
                System.out.println("size: " + size);
                for (int j = 0; j < size; j++) {
                    String title = data.select("td.wp-60")
                            .eq(j)
                            .text();
                    String detailUrl = data.select("td.wp-60")
                            .select("a")
                            .eq(j)
                            .attr("href");
                    parseItems.add(new ParseItem(title, detailUrl));
                    System.out.println("items .title: " + title);
                }
            }*/

            String url  = "https://www.actksa.com/ar/training-courses/training-in/Jeddah";
            //get first link
            //String link = Jsoup.connect(url).get().select(".pull-right > ul.pagination > li > a").attr("href");
            //an int just to count up links
            //int i = 1;
            //System.out.println("pagination-link_"+ i + "\t" + link);
            //parse next page using link
            //check if the div on next page has more than one link in it
            /*while(Jsoup.connect(link).get().select(".pull-right").size() >1){
                link = Jsoup.connect(link).get().select(".pull-right > ul.pagination > li > a").get(1).attr("href");
                System.out.println("pagination-link_"+ (++i) +"\t" + link);
            }*/

            //https://www.htmlgoodies.com/html5/web-page-scraping-with-jsoup/
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select(".pagination > li > a[href]");
            System.out.println("Size elements: " + links.size());
            int loop = 0;
            Collection<Element> listlinks = new ArrayList<Element>();
            Collection<Element> dataTable = new ArrayList<Element>();
            Elements dataelements = doc.select(".table-hover > tbody > tr > td");
            for(Element linkhref:links) {
                if(loop<links.size()-1) {
                    listlinks.add(linkhref);
                    System.out.println("Lien : https://www.actksa.com" + linkhref.attr("href"));
                    for(Element datatab : dataelements){
                        dataTable.add(datatab);
                        System.out.println("Data of table : " + datatab.attr("tr")+" => "+datatab.text());
                    }
                }
                loop++;
            }
            System.out.println(" Collection des liens ");
            //Collection<Element> dataTable = new ArrayList<Element>();
            listlinks.forEach(
                    href ->{
                        System.out.println("Lien : https://www.actksa.com"+href.attr("href")+" Texte sur le lien: "+href.text());

                    }
            );
            dataTable.forEach(
                    data ->{
                        System.out.println("Data : "+data.text());

                    }
            );
            //System.out.println(" Collection of data table:  "+dataelements);
        }catch(Exception e){
            e.getStackTrace();
        }


    }
}

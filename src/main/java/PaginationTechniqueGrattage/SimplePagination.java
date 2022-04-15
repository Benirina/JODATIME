package PaginationTechniqueGrattage;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

    public class SimplePagination {
        public static void main(String[] args) {
            for(int i = 1; i <= 4; ++i) {
                System.out.println("PAGE " + i);
                try {
                    String url = (i==1) ? "https://www.scrapingbee.com/blog" : "https://www.scrapingbee.com/blog/page/" + i;

                    Document document = Jsoup.connect(url)
                            .timeout(5000)
                            .get();

                    Elements blogs = document.getElementsByClass("p-10");
                    for (Element blog : blogs) {
                        String title = blog.select("h4").text();
                        System.out.println("TITLE: " + title);

                        String link = blog.select("a").attr("href");
                        System.out.println("LINK: " + link);

                        String headerImage = blog.selectFirst("img").attr("src");
                        System.out.println("HEADER IMAGE: " + headerImage);
                        String authorImage = blog.select("img[src*=authors]").attr("src");
                        System.out.println("AUTHOR IMAGE:" + authorImage);

                        System.out.println();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

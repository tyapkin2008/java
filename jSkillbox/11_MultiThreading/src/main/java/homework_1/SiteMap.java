package homework_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.SortedSet;
import java.util.concurrent.RecursiveAction;

import static java.lang.Thread.sleep;

public class SiteMap extends RecursiveAction {
    private String siteName;
    private String link;
    private SortedSet<String> syncSortedSet;

    public SiteMap(SortedSet<String> syncSortedSet, String siteName, String link){
        this.syncSortedSet = syncSortedSet;
        this.siteName = siteName;
        this.link = link;
    }

    // проверим url и вернем либо пустую строку, либо нормализованную, исключим всевозможные файлы
    public String normalizeLink(String link){
        String resultLink = "";
        if(!link.matches(".*[pdf|png|jpg|swf|svg|doc|avi]") && link.matches(this.siteName + ".*") && !link.matches("\\.pdf") && !link.matches(this.siteName + "\\?\\/")){
            resultLink = link.replaceAll("\\?.*|#.*", "");
            if(syncSortedSet.contains(resultLink)){
                resultLink = "";
            }
            else {
                syncSortedSet.add(resultLink);
            }
        }
        return resultLink;
    }

    @Override
    protected void compute() {
        try {
            sleep(1500);
            // говорим сайту, что мы браузер
            Document document = Jsoup.connect(this.link).userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com").get();
            /*
             Находим все ссылки, нормализуем, и если не пустая, создаем ноду карты сайта и
             добавляем в список дочерних текущей страницы
             */
            Elements links = document.select("a");
            if(!links.isEmpty()){
                for(Element l : links ){
                    String link = this.normalizeLink(l.absUrl("href"));
                    if(link.length() > 0){
                        System.out.println(link);
                        SiteMap children = new SiteMap(syncSortedSet, this.siteName, link);
                        children.compute();
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

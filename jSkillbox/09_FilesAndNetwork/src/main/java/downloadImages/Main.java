package downloadImages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static final String siteUrl = "https://lenta.ru/";
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect(siteUrl).get();
            Elements images = doc.select("img");

            images.forEach(element -> {
                String imgSrc = element.attr("abs:src");
                if(imgSrc.length() > 0){
                    System.out.println("Скачиваем " + imgSrc);
                    try {
                        String imageName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1).replace("?","");
                        InputStream inputStream = new URL(imgSrc).openStream();
                        if(!Files.exists(Paths.get("images/" + imageName))){
                            System.out.println("Сохраняем " + imageName);
                            Files.copy(inputStream, Paths.get("images/" + imageName));
                        }
                        else {
                            System.out.println("Файл " + imageName + " загружен ранее");
                        }
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

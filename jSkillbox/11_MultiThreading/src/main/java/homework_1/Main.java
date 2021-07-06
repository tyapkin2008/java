package homework_1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static String siteName = "https://skillbox.ru"; // https://lenta.ru
    private static SortedSet<String> syncSortedSet;
    public static void main(String[] args) {
        /*
        Сортированная коллекция уникальных ссылок.
        * */
        syncSortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
        /*
         Класс, отвечающий за создание карты сайта.
         В конструктор передаем коллекцию ссылок, название сайта для работы с ссылками и
         ссылку на обрабатываемую страницу. В данном случае- главную
         */
        new ForkJoinPool().invoke(new SiteMap(syncSortedSet, siteName, siteName));
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream("src/main/resources/sitemap_skillbox.txt");
            String result = createString(syncSortedSet);
            stream.write(result.getBytes());
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String createString(SortedSet<String> syncSortedSet) {
        /*
        Формируем карту сайта
        Обходим сортированную коллекцию ссылок
        высчитываем табы по количеству знаков  "/" безу учета доменного имени и слэша за ним
        Для этого убираем доменное имя и слэш,
        и из длины полученной строки вычитаем сдлину этой же строки без слэшей.
        */
        StringBuilder result = new StringBuilder();
        syncSortedSet.forEach(link -> {
            String shortLink = link.replaceAll(siteName + "/", "");
            // Если слэша на конце нет, то добавляем.
            // Часть ссылок имею слэш, часть нет.
            // В результате, табы высчитываются некорректно
            if(!shortLink.endsWith("/")){
                shortLink += "/";
            }

            int level = shortLink.length() - shortLink.replaceAll("/", "").length();
            result.append("\n").append("\t".repeat(level) + link);
        });
        return result.toString();
    }
}

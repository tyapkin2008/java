package metroParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final String siteUrl = "https://www.moscowmap.ru/metro.html#lines";
    private static final String fileUrl = "files/mosMetroMap.json";


    public static void main(String[] args) {
        parseMetroMap();
        readMetroMap();
    }

    public static void readMetroMap() {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(fileUrl);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray lines = (JSONArray) jsonObject.get("lines");
            JSONObject stations = (JSONObject) jsonObject.get("stations");
            JSONArray connections = (JSONArray) jsonObject.get("connections");

            System.out.println("Количество пересадок " + connections.size());

            lines.forEach(line -> {
                JSONObject jsonLine = (JSONObject) line;
                String lineNumber = (String) jsonLine.get("number");
                String lineName = (String) jsonLine.get("name");
                System.out.println(lineName);
                JSONArray lineStations = (JSONArray) stations.get(lineNumber);
                System.out.println("Количество станций: " + lineStations.size());
            });
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseMetroMap() {
        Document doc = null;
        // Общий объектдля записи
        JSONObject jsonObject = new JSONObject();
        // Массив линий
        JSONArray lineArray = new JSONArray();
        // Массив пересадок
        JSONArray connectionsArray = new JSONArray();
        // Объект станций, где ключи- номера линий, а значения- списки станций
        JSONObject stationsObject = new JSONObject();

        try {
            FileWriter fileWriter = new FileWriter(fileUrl);
            doc = Jsoup.connect(siteUrl).maxBodySize(0).get();
            // Линии
            Elements lines = doc.select("#metrodata .js-metro-line");
            lines.forEach(element -> {
                // Получаем id линии из атрибута
                String lineId = element.attr("data-line").trim();
                if(lineId.length() > 0)
                {
                    HashMap<String, String> stationObject = new HashMap<>();
                    stationObject.put("number", lineId);
                    stationObject.put("name", element.text());
                    lineArray.add(stationObject);
                }
            });

            // Станции разбиты по блокам для каждой линии. Разбираем каждый блок отдельно
            Elements stations = doc.select("#metrodata .js-metro-stations");
            stations.forEach(element -> {
                // Получаем id линии из атрибута
                String currentLineId = element.attr("data-line").trim();
                if(currentLineId.length() > 0){
                    // Список станций текущей линии
                    List<String> stationsList = new ArrayList();
                    // каждая станция обернута в ссылку, поэтому перебираем все ссылки
                    element.getElementsByTag("a").forEach(el -> {
                        // В ссылке содержатся блоки span.name с названием станции
                        String stationName = el.select(".name").text().trim();
                        stationsList.add(stationName);

                       // Пересадки обернуты в ту же ссылку, что и станция и имеют класс "t-icon-metroln"
                        el.select(".t-icon-metroln").forEach(e -> {
                            // Информация о линии содержится в классе ln-ИД станции. Парсим из строки
                            String line2 = e.attr("class").replace("t-icon-metroln ln-", "").trim();
                            // Название включено в атрибут title и обернуто в кавычки. Получаем название
                            String name2 = e.attr("title").trim();
                            name2 = name2.substring(name2.indexOf("«") + 1, name2.lastIndexOf("»"));
                            if(line2.length() > 0 && name2.length() > 0){
                                // Формируем объект с текущей станцией и линией для массива пересадок
                                HashMap<String, String> stationObject = new HashMap<>();
                                stationObject.put("line", currentLineId);
                                stationObject.put("station", stationName);

                                HashMap<String, String> stationObject2 = new HashMap<>();
                                stationObject2.put("line", line2);
                                stationObject2.put("station", name2);
                                // Формируем массив с текущим соединенем, помещаем тд текущую станцию и целевую станцию
                                JSONArray connectionArray = new JSONArray();
                                connectionArray.add(stationObject);
                                connectionArray.add(stationObject2);
                                // Пишем пересадку в общий массив пересадок
                                connectionsArray.add(connectionArray);
                            }
                        });
                    });
                    stationsObject.put(currentLineId, stationsList);
                }
            });

            // Записываем пересадки, станции и линии
            jsonObject.put("connections", connectionsArray);
            jsonObject.put("stations", stationsObject);
            jsonObject.put("lines", lineArray);

            // Пишем в файл
            jsonObject.writeJSONString(fileWriter);

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

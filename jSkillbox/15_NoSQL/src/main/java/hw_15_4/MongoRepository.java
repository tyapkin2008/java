package hw_15_4;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Updates;
import org.bson.BsonDocument;
import org.bson.Document;
import java.util.*;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.orderBy;

public class MongoRepository {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private MongoCollection<Document> marketCollection;
    private MongoCollection<Document> itemsCollections;
    private String dbName = "skillbox_hw_15_4_3";


    public MongoRepository(){
        mongoClient = new MongoClient("127.0.0.1" , 27017 );
        mongoDatabase = mongoClient.getDatabase(dbName);
        marketCollection = mongoDatabase.getCollection("markets");
        itemsCollections = mongoDatabase.getCollection("items");
    }
    /* Добавление магазина */
    private void addMarket(String marketName){
        List<String> items = new ArrayList<>();

        //marketCollection.insertOne(new Document().append("name", marketName).append("items", items));
        marketCollection.insertOne(Document.parse(String.format("{name: \"%s\"}", marketName)));
        System.out.println("Магазин создан");
    }

    /* Добавление товара */
    private void addItem(String itemName, Integer price){
        //itemsCollections.insertOne(new Document().append("name", itemName).append("price", price));
        itemsCollections.insertOne(Document.parse(String.format("{name: \"%s\", price: %d}", itemName, price)));
        System.out.println("Товар создан");
    }
    /* Добавление товара в магазин */
    public void addItem2Market(String itemName, String marketName){
        Document item = itemsCollections.find(BasicDBObject.parse(String.format("{name: \"%s\"}", itemName))).first();
         if(item == null){
             System.out.println("Товар не найден.");
         }
         else {
             marketCollection.updateOne(
                     BsonDocument.parse(String.format("{name: \"%s\"}", marketName)),
                     Updates.addToSet("items", item));
         }
    }

    /* Очистка базы для служебных целей */
    private void clearDatabase(){
        marketCollection.drop();
        itemsCollections.drop();
        System.out.println("Данные удалены!");
    }
    /* Статистика */
    private void showStatistic(){
        AggregateIterable<Document> outputNew = marketCollection.aggregate(
            Arrays.asList(
                unwind("$items"),
                sort(orderBy(ascending("items.price"))),
                group("$name",
                        avg("avg", "$items.price"),
                        sum("count", 1),
                        max("max", "$items.price"),
                        min("min", "$items.price")
                )
            )
        );
        //new Document("$group", BasicDBObject.parse("{_id:\"$name\", max: { $max: \"$items.price\" }, min: { $min: \"$items.price\" }, avg: { $avg: \"$items.price\" }, count: { $sum: 1 }}"))

        for(Document docOut : outputNew){
            System.out.println("Отчет по магазину " + docOut.get("_id"));
            System.out.println("максимальная цена " + docOut.get("max"));
            System.out.println("минимальная цена " + docOut.get("min"));
            System.out.println("средняя цена " + docOut.get("avg"));
            System.out.println("количество наименований " + docOut.get("count"));

        }


        AggregateIterable<Document> output2 = marketCollection.aggregate(
            Arrays.asList(
                unwind("$items"),
                //new Document("$match", BasicDBObject.parse("{\"items.price\" : {$lt : 100}}")),
                match(lt("items.price", 100)),
                project(fields(include("name", "items"))),
//                new Document("$project", BasicDBObject.parse("{ _id: 0, \"name\" : 1 , \"items\" : 1  }")),
                group("$name"
                    , sum("count", 1)
                )
//                new Document("$group", BasicDBObject.parse("{_id:\"$name\", count: { $sum: 1 }}"))
            )
        );
        System.out.println("Количество товаров дешевле 100 рублей ");
        for(Document docOut2 : output2){
            System.out.println(docOut2.get("_id"));
            System.out.println(docOut2.toJson());
            System.out.println("количество " + docOut2.get("count"));
        }
    }

    /* Обработчик команд пользователя. Точка входа для обработки команд */
    public void runCommand(String[] commandArray){
        String command = commandArray[0].toUpperCase();
        if(command.equals("CLEAR")){
            clearDatabase();
        }
        if(command.equals("ДОБАВИТЬ_МАГАЗИН")){
            addMarket(commandArray[1]);
        }
        if(command.equals("ДОБАВИТЬ_ТОВАР")){
            addItem(commandArray[1], Integer.valueOf(commandArray[2]));
        }
        if(command.equals("ВЫСТАВИТЬ_ТОВАР")){
            addItem2Market(commandArray[1], commandArray[2]);
        }
        if(command.equals("СТАТИСТИКА_ТОВАРОВ")){
            showStatistic();
        }
    }
}

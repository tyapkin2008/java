package hw_15_3;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    private static final String fileName = "C:\\Users\\alex\\trabajo\\skillbox\\files\\mongo.csv";

    public static void main(String[] args) throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader(fileName));
        List<String[]> csvLines = csvReader.readAll();
        if(csvLines.size() > 0){
            MongoClient mongoClient = new MongoClient("127.0.0.1" , 27017 );
            MongoDatabase mongoDatabase = mongoClient.getDatabase("skillbox");
            MongoCollection<Document> studentsCollection = mongoDatabase.getCollection("students");
            studentsCollection.drop();
            csvLines.forEach(line->{
                List<String> courses = Arrays.asList(line[2].split(","));
                studentsCollection.insertOne(new Document().append("name", line[0]).append("age", Integer.valueOf(line[1])).append("courses", courses));
            });


            // общее количество студентов в базе
            System.out.println("Общее количество студентов в базе: " + studentsCollection.countDocuments());

            // количество студентов старше 40 лет.
            BsonDocument query = BsonDocument.parse("{age: {$gt: 40}}");
            System.out.println("Количество студентов старше 40 лет " + studentsCollection.countDocuments(query));

            // имя самого молодого студента
            BsonDocument sortAgeMin = BsonDocument.parse("{age: 1}");
            Iterator<Document> queryMinResult  = studentsCollection.find().sort(sortAgeMin).limit(1).iterator();
            if(queryMinResult.hasNext()){
                Document minAgeStudent = queryMinResult.next();
                System.out.println("Имя самого молодого студента " + minAgeStudent.getString("name"));
            }
            // список курсов самого старого студента
            Iterator<Document> queryMaxResult  = studentsCollection.find().sort(new BasicDBObject("age",-1)).limit(1).iterator();
            if(queryMaxResult.hasNext()){
                Document maxAgeStudent = queryMaxResult.next();
                List<String> courses = (List<String>) maxAgeStudent.get("courses");
                System.out.println("Список курсов самого старого студента: " + String.join(", ", courses));
            }

         }
    }
}

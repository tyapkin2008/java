package MovenentList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

public class MovementListParsing {
    private static final String movementlistFilePath = "files\\movementList.csv";
    private static final String dateFormat = "dd.mm.YY";
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args)  {
        /*Выносим парсинг в отдельный метод*/
        parseFile();

        Map<String, Double> organizationCredit = new HashMap<>();
        Double creditSumSum = accounts.values().stream().flatMapToDouble(account -> {
            double opsum = account.getOperations().stream().
                    filter(operation -> operation.getCredit() >0).flatMapToDouble(
                            operation -> {
                                /* Заодно заполним статистику расходов по организациям */
                                organizationCredit.put(operation.getOrganization(), organizationCredit.getOrDefault(operation.getOrganization(), 0.0) + operation.getCredit());
                                    return DoubleStream.of(operation.getCredit());}
                                    ).sum();
            /* Выведем отдельно для каждого счета */
            System.out.printf("Сумма расходов по счету %s : %.2f руб.%n", account.getNumber(), opsum);
            return DoubleStream.of(opsum);
        }).sum();

        Double debetSum = accounts.values().stream().flatMapToDouble(account -> {
            double opsum = account.getOperations().stream().
                    filter(operation -> operation.getDebet() >0).flatMapToDouble(operation -> DoubleStream.of(operation.getDebet())).sum();
            /* Выведем отдельно для каждого счета */
            System.out.printf("Сумма доходов по счету %s : %.2f руб.%n", account.getNumber(), opsum);
            return DoubleStream.of(opsum);
        }).sum();

        /* По всем счетам */
        System.out.printf("Общая сумма расходов: %.2f руб.%n", creditSumSum);
        System.out.printf("Общая сумма доходов: %.2f руб.%n", debetSum);

        System.out.println("Суммы расходов по организациям:");
        for(String organization : organizationCredit.keySet()){
            System.out.printf("%s        %.2f руб.%n", organization, organizationCredit.get(organization));

        }
    }

    private static void parseFile(){
        try {
            CSVParser parser = CSVParser.parse(Paths.get(movementlistFilePath), Charset.defaultCharset(), CSVFormat.DEFAULT.withHeader());
            String number = "";
            String type = "";
            String currency = "";
            Account account = null;
            Operation operation;
            for(CSVRecord record : parser.getRecords()){
                number = "";
                type = "";
                currency = "";
                if(record.get(1).trim().length() > 0){
                    number = record.get(1).trim();
                }
                if(record.get(0).trim().length() > 0){
                    type = record.get(0).trim();
                }
                if(record.get(2).trim().length() > 0){
                    currency = record.get(2).trim();
                }

                if(number.length() > 0){
                    account = accounts.get(number);
                    if(account == null){
                        account = new Account(number, type, currency);
                        accounts.put(number, account);
                    }
                }
                if(account != null){
                    double debet = Double.parseDouble(record.get(6).trim().replace(",", "."));
                    double credit = Double.parseDouble(record.get(7).trim().replace(",", "."));
                    Date date = new SimpleDateFormat(dateFormat).parse(record.get(3).trim());
                    operation = new Operation(date, record.get(4), record.get(5), debet, credit);
                    account.setOperation(operation);
                }

            }
            parser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

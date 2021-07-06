package hw_15_2;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class RedisTest {

    private static final int SLEEP = 1; // 1 миллисекунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();

        Random random = new Random();

        // Генерим пользователей
        for(int i = 0; i < 20; i++){
            redis.logPageVisit(String.valueOf(i));
        }

        // Бесконечный цикл вывода пользователей
        for(;;) {
            // Счетчик
            int currentCounter = 0;
            // Актуализируем список текущих пользователей
            LinkedList<String> users = redis.getUsers();
            Iterator<String> iterator = users.iterator();
             // Обходим по очереди список пользователей
            while (iterator.hasNext()) {
                currentCounter++;
                String user = iterator.next();
                // Обновляем время
                redis.logPageVisit(user);
                System.out.println("На главной странице показываем пользователя " + user);
                if (currentCounter % 10 == 0) {
                    // Обнулим счетчик
                    if (currentCounter > 10000)
                        currentCounter = 0;
                    // Получаем произваольного пользователя
                    int randKey = random.nextInt(19);
                    String randUser = users.get(randKey);
                    System.out.println("Пользователь " + randUser + " оплатил платную услугу");
                    System.out.println("На главной странице показываем пользователя " + randUser);
                    // Обновляем время у оплатившего
                    redis.logPageVisit(String.valueOf(randUser));
                }
            }
            Thread.sleep(4000);
        }
    }
}

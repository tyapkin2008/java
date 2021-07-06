package hw_15_2;
import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;
import java.util.LinkedList;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> onlineUsers;

    private final static String KEY = "users";

    private double getTs() {
        return new Date().getTime() / 1000;
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        onlineUsers = redisson.getScoredSortedSet(KEY);
    }

    public LinkedList<String> getUsers(){
        LinkedList<String> users = new LinkedList<>();
        for(String user : onlineUsers){
            users.add(user);
        }
        return users;
    }


    void shutdown() {
        redisson.shutdown();
    }

    // Фиксирует посещение пользователем страницы
    void logPageVisit(String user_id)
    {
        //ZADD ONLINE_USERS
        onlineUsers.add(getTs(), user_id);
    }

    // Удаляет
    void deleteOldEntries(int secondsAgo)
    {
        //ZREVRANGEBYSCORE ONLINE_USERS 0 <time_5_seconds_ago>
        onlineUsers.removeRangeByScore(0, true, getTs() - secondsAgo, true);
    }

    int calculateUsersNumber()
    {
        //ZCOUNT ONLINE_USERS
        return onlineUsers.count(Double.NEGATIVE_INFINITY, true, Double.POSITIVE_INFINITY, true);
    }
}
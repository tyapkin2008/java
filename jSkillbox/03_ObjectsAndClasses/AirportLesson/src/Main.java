import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Terminal;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Задание из урока
        Airport airport = Airport.getInstance();
        // Получаем список самолетов
        List<Aircraft> aircrafts = airport.getAllAircrafts();
        // Выводим размер списка
        System.out.println("Количество самолетов " + aircrafts.size());

        // пробы возможностей библиотеки
        System.out.println("Информация об аэропорте " + airport.toString());
        // новый самолет с указанием модели
        Aircraft aircraft = new Aircraft("tu 144");
        System.out.println("Информация о созданном самолете " + aircraft.toString());
        List<Terminal> terminals = airport.getTerminals();
        System.out.println("Количество терминалов " + terminals.size());
        Terminal terminal = new Terminal("first");
        System.out.println("Информация о терминале " + terminal.toString());
        // попробуем работу с терминалом
        // получаем самолеты терминала и выводим количество.
        List<Aircraft> terminalParkedAircrafts = terminal.getParkedAircrafts();
        System.out.print(terminalParkedAircrafts.size() + " припарковано самолетов в терминале ");
        System.out.println(terminal.getName());
        // добавим ранее созданный самолет
        terminal.addParkingAircraft(aircraft);
        System.out.println("Добавили самолет");
        // посмотрим количество. Странным для меня образом, самолет добавился и в список
        // вот тут мне непонятно, как самолет добавился в этот список. Он же уже сормирован
        // но это я вперед забежал. Мы это не проходили еще.
        System.out.print(terminalParkedAircrafts.size() + " припарковано самолетов в терминале ");
        System.out.println(terminal.getName());
        // Тут вопрос нет. Получили список
        List<Aircraft> terminalParkedAircraftsNew = terminal.getParkedAircrafts();
        System.out.print(terminalParkedAircraftsNew.size() + " припарковано самолетов в терминале ");
        System.out.println(terminal.getName());

    }
}

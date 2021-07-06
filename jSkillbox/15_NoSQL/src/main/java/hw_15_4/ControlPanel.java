package hw_15_4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ControlPanel {
    private Scanner scanner;
    private Map<String, Command> availableCommands;
    public ControlPanel(){
        scanner = new Scanner(System.in);
        availableCommands = new HashMap<>();
        // любезно разрешаем пользователю не считать пробелы между словами и в конце команды
        availableCommands.put("ДОБАВИТЬ_МАГАЗИН", new Command("ДОБАВИТЬ_МАГАЗИН", "Команда добавления магазина. Сначала укажите название команды, " +
                "затем название магазина. В одно слово, без пробелов", "^ДОБАВИТЬ_МАГАЗИН\s+\\S+\s*")); // s\S+
        availableCommands.put("ДОБАВИТЬ_ТОВАР", new Command("ДОБАВИТЬ_ТОВАР", "Команда добавления товара. Сначала укажите название команды, " +
                "затем название товара, в одно слово, без пробелов. Затем укажите целое число — цену товара в рублях.", "^ДОБАВИТЬ_ТОВАР\s+\\S+\s+\\d+\s*"));
        availableCommands.put("ВЫСТАВИТЬ_ТОВАР", new Command("ВЫСТАВИТЬ_ТОВАР", "Команда добавления товара в магазин. Cначала укажите название команды, " +
                "затем название товара и магазина. Для простоты будем считать, что в магазине продаются «Вафли».", "^ВЫСТАВИТЬ_ТОВАР\s+\\S+\s+\\S+\s*"));
        availableCommands.put("СТАТИСТИКА_ТОВАРОВ", new Command("СТАТИСТИКА_ТОВАРОВ", "Команда получения информации о товарах во всех магазинах. Команда должна выводить для каждого магазина:\n" +
                "общее количество наименований товаров,\n" +
                "среднюю цену товаров,\n" +
                "самый дорогой и самый дешевый товар,\n" +
                "количество товаров дешевле 100 рублей.", "^СТАТИСТИКА_ТОВАРОВ\s*"));
        availableCommands.put("CLEAR", new Command("CLEAR", "Очистить всё. Внимание, все данные будут удалены!!!", "^CLEAR\s*"));
        availableCommands.put("EXIT", new Command("EXIT", "Выход", "^EXIT\s*"));
    }

    /* Вывод информации о всех командах*/
    public void showInfo(){
        availableCommands.forEach((commandName, command)-> System.out.println(commandName + " : " + command.getText()));
    }

    /* Вывод информации о команде */
    public void showInfo(String command){
        if(availableCommands.containsKey(command)){
            System.out.println(command + " : " + availableCommands.get(command));
        } else {
            System.out.println("Команда не найдена. Список доступных команд: ");
            this.showInfo();
        }
    }

    /* Получение команды */
    public String[] getNextCommand(){
        while(true){
            System.out.println("Введите команду");
            String enter = scanner.nextLine();
            String[] commandArray = normalizeCommand(enter);
            if(validateCommand(commandArray, enter)){
                return commandArray;
            }
            System.out.println("Неопознанная команда");
            showInfo();
        }
    }
    /* Раскладываем команду на массив строк*/
    private String[] normalizeCommand(String command){
        String[] commandInfo = command.replaceAll("\s+", " ").split(" ");
        return commandInfo;
    }

    /* Проверяем корректность команды */
    private boolean validateCommand(String[] commandArray, String enter){
        if(commandArray.length > 0 && commandArray[0].length() > 0){
             String command = commandArray[0];
             if(availableCommands.containsKey(command) && enter.matches(availableCommands.get(command).getRegexRule())){
                 return true;
             }
        } else {
            System.out.println("Передана пустая строка либо некорректная команда!");
        }
        return false;
    }
}

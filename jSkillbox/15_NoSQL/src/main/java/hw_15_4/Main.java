package hw_15_4;

public class Main {

    private static ControlPanel panel;
    private static MongoRepository mongoRepository;

    public static void main(String[] args) {
        // Консоль управления складом
        panel = new ControlPanel();
        // База данных
        mongoRepository = new MongoRepository();
        // При запуске выводим информацию о допустимых командах
        panel.showInfo();
        // Цикл ввода команд
        while (true){
            String[] command = panel.getNextCommand();
            if(command[0].equals("EXIT")){
                System.out.println("BREAK");
                break;
            }
            mongoRepository.runCommand(command);
        }
        System.out.println("Программа завершена");
    }
}

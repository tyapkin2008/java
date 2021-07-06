import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class MySetExperiments {

    public static void main(String[] args) {
        // Доступные команды
        HashSet<String> availableCommand = new HashSet<>(){{
            add("add");
            add("list");
        }};
        // Хранилище для email
        TreeSet<String> emailList = new TreeSet<>();

        Scanner scanner = new Scanner(System.in);
        // Цикл ввода и обработки
        while (true)
        {
            System.out.println("Введите команду:");
            String commandLine = scanner.nextLine();
            if(!commandLine.matches("^\\S+.+"))
            {
                System.out.println("Некорректный ввод. Попробуйте еще раз.");
                continue;
            }
            // Инициализируем вводом пользователя
            String command = commandLine;
            // Если ввод содержит строку с пробелами, то забираем строку до пробела
            if(commandLine.matches("^\\S+\\s+.+"))
            {
                command = commandLine.substring(0, commandLine.indexOf(" ")).trim();
            }
            // Если команда не из списка
            if(!availableCommand.contains(command.toLowerCase()))
            {
                System.out.println("Неопознанная команда. Попробуйте еще раз.");
                continue;
            }
            if(command.equalsIgnoreCase("list"))
            {
                for(String email: emailList)
                {
                    System.out.println(email);
                }
            }
            if(command.equalsIgnoreCase("add"))
            {
                String email = commandLine.substring(commandLine.indexOf(" ")).trim();
                if(email.length() == 0)
                {
                    System.out.println("Не указан email");
                }
                else
                {
                    // проверяем email. Первая часть может быть и ip.
                    if(email.matches("^[a-zA-Z0-9-+_.]+@[a-zA-Z0-9-+_]+\\.\\S{2,3}"))
                    {
                        emailList.add(email);
                    }
                    else
                    {
                        System.out.println("Некорректный email. Попробуйте снова.");
                    }
                }
            }
        }
    }
}

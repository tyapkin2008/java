import java.util.ArrayList;
import java.util.Scanner;

public class MyArrayListExperiments {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> todoList = new ArrayList<>();
        ArrayList<String> availableTypes = new ArrayList<>(){{
            add("add");
            add("list");
            add("edit");
            add("delete");
        }};
        while(true)
        {
            System.out.println("Введите команду:");
            String commandLine = scanner.nextLine().trim();

            // проверим, чтобы в начале была строка
            if(!commandLine.matches("^\\S+.+"))
            {
                System.out.println("Некорректный ввод. Попробуйте еще раз.");
                continue;
            }

            // Инициализируем строку - команду введенным пользователем значением
            String command = commandLine;
            // Но если строка содержит после первых символов пробелы и дальше символы, то плучаем строку до пробела
            if(commandLine.matches("^\\S+\\s+.+"))
            {
                command = commandLine.substring(0, commandLine.indexOf(" "));
            }

            if(availableTypes.indexOf(command.toLowerCase()) < 0)
            {
                System.out.println("Команда не распознана.");
                System.out.println("Допутимые команды: ADD, EDIT, DELETE, LIST");
                continue;
            }
            // разрешим вводить команды в любом регистре
            // сразу проверим, если список, то другие параметры в данной реализации не проверем
            if(command.equalsIgnoreCase("list"))
            {
                for(int i = 0; i < todoList.size(); i++)
                {
                    System.out.println(i + " " + todoList.get(i));
                }
            }
            else
            {
                // Если не список, то выделяем строку параметров
                String todo = commandLine.substring(command.length()).trim();
                // Все команды, кроме list должны содержать параметры
                if(todo.length() > 0)
                {
                    int index = -1;
                    // если строка параметров првым содержит число и потом пробелы
                    if(todo.matches("\\d+\\s+.+"))
                    {
                        index = Integer.parseInt(todo.substring(0, todo.indexOf(" ")).trim());
                        todo = todo.substring(todo.indexOf(" ")).trim();
                    }
                    else if(todo.matches("\\d+"))
                    {
                        index = Integer.parseInt(todo);
                    }
                    // если добавление
                    if(command.equalsIgnoreCase("add"))
                    {
                        // Если индекс неопределен или не указан или выходит за рамки списка, добавляем в конец
                        if(index < 0 || index > todoList.size())
                        {
                            todoList.add(todo);
                        }
                        else
                        {
                            todoList.add(index, todo);
                        }
                    }
                    else if(command.equalsIgnoreCase("edit"))
                    {
                        // Если индекс неопределен или не указан или выходит за рамки списка, добавляем в конец
                        if(index < 0 || index > todoList.size() - 1)
                        {
                            todoList.add(todo);
                        }
                        else
                        {
                            todoList.set(index, todo);
                        }
                    }
                    else if(command.equalsIgnoreCase("delete"))
                    {
                        // если индекс в пределах списка
                        if(index > 0 && index < todoList.size())
                        {
                            todoList.remove(index);
                        }
                        else
                        {
                            System.out.println("Неверный номер дела");
                        }
                    }
                }
                else
                {
                    System.out.println("Не указаны параметры команды");
                }
            }
        }
    }
}

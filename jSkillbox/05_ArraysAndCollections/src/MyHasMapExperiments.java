import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MyHasMapExperiments {
    public static void main(String[] args) {
        Map<String, String> phoneBook = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        for(;;){
            System.out.println("Введите информацию:");
            String inputString = scanner.nextLine().trim();
            // Если команда вывода списка
            if(inputString.equalsIgnoreCase("list")){
                printPhoneBook(phoneBook);
                continue;
            }
            if(inputString.length() == 0){
                System.out.println("Пустая строка. Повторите ввод.");
                continue;
            }
            // Если введенная строка похожа на номер телефона
            if(inputString.matches("[0-9+)(\\-\\s]+")){
                String phoneNumber = normalizePhone(inputString);
                // Если номер есть в книжке, выводим контакт
                if (phoneBook.containsValue(phoneNumber)){
                     for (String key : phoneBook.keySet()) {
                        if(phoneNumber.equals(phoneBook.get(key))){
                            System.out.println(phoneBook.get(key) + " : " + key);
                            break;
                        }
                    }
                }
                else {
                    // если номер не найден, создаем контакт. Просим ввести имя контакта
                    System.out.println("Контакт с номером " + inputString + " не найден.");
                    // Цикл проверки ввда имени. Проверяем только на пустую строку
                    for(;;){
                        System.out.println("Введите имя контакта:");
                        String contactName = scanner.nextLine().trim();
                        // проверим, чтобы такого контакта не было
                        if(phoneBook.containsKey(contactName)){
                            System.out.println("Контакт с таким именем существует.\nВведите другое имя.");
                            continue;
                        }
                        if(contactName.length() > 0){
                            phoneBook.put(contactName, phoneNumber);
                            break;
                        }
                        System.out.println("Нельзя вводить пустое имя!");
                    }
                }
            }
            else if(inputString.matches("^[a-zA-Zа-яА-Я.\\-\\s+]+")){
                // если строка - это имя(допускаем буквы, дефис, пробелы, точки)

                if(phoneBook.containsKey(inputString)){
                    // если имя есть в книжке, выводим контакт
                    System.out.println(inputString + " : " + phoneBook.get(inputString));
                }
                else {
                    // если имени нет, то просим ввести номер и сохраняем, предварительно проверив номер и подготовив для записи
                    for(;;){
                        System.out.println("Контакт не найден в телефонной книге\nВведите номер:");
                        String contactPhone = scanner.nextLine().trim();

                        if(contactPhone.length() > 0 && contactPhone.matches("[0-9+)(\\-\\s]+")){
                            if(phoneBook.containsValue(normalizePhone(contactPhone))){
                                System.out.println("Номер существует у другого контакта. Введите другой.");
                                continue;
                            }
                            phoneBook.put(inputString, normalizePhone(contactPhone));
                            break;
                        }
                        System.out.println("Некорректный номер. Введите еще раз.");
                    }
                }
            }
        }
    }
    // Нормализация номера для хранения
    private static String normalizePhone(String phoneString){
        return phoneString.replaceAll("[+()\\-\\s]+", "");
    }
    // Вывод книжки на экран
    private static void printPhoneBook(Map<String, String> phoneBook){
        for(String contact : phoneBook.keySet()){
            System.out.println(contact + " : " + phoneBook.get(contact));
        }
    }
}

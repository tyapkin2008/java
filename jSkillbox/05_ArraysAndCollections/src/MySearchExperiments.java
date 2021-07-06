import java.util.*;

public class MySearchExperiments {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String avalableWords = "ABCEHKMOPTX";
        /*
        Генерация номера
        */
        for(int firstCharIndex = 0; firstCharIndex < avalableWords.length(); firstCharIndex++){
            for (int num = 0; num <=  9; num++){
                for(int secondCharIndex = 0; secondCharIndex < avalableWords.length(); secondCharIndex++){
                    for(int thirdCharIndex = 0; thirdCharIndex < avalableWords.length(); thirdCharIndex++){
                        for(int region = 1; region <= 199; region++){
                            String regionCode = String.valueOf(region);
                            if(regionCode.length() == 1){
                                regionCode = "0" + regionCode;
                            }
                            StringBuilder number = new StringBuilder();
                            number.append(avalableWords.charAt(firstCharIndex));
                            number.append(num);
                            number.append(num);
                            number.append(num);
                            number.append(avalableWords.charAt(secondCharIndex));
                            number.append(avalableWords.charAt(thirdCharIndex));
                            number.append(regionCode);
                            list.add(String.valueOf(number));
                        }
                    }
                }
            }
        }
        Collections.sort(list);
        Scanner scanner = new Scanner(System.in);
        // Цикл ввода и обработки информации
        for(;;){
            System.out.println("Введите номер ");
            String number = scanner.nextLine().trim();
            // Проверяем ввод пользователя на пустую строку и схожесть с номером
            if(number.length() == 0){
                System.out.println("Вы ввели пустую строку. Попробуйте еще раз.");
                continue;
            }
            if(!number.matches("^["+avalableWords+"][0-9]{3}["+avalableWords+"]{2}[0]?[0-9]{1,3}")){
                System.out.println("Введен некорректный номер. Попробуйте еще раз.");
                continue;
            }
            // Посик перебором
            boolean searchResult = false;
            long startTime = System.nanoTime();
            if(list.contains(number)){
                searchResult = true;
            }
            long duration = System.nanoTime() - startTime;
            System.out.println("Поиск перебором: номер " + (searchResult ? "" : "не ") + "найден, поиск занял " + duration + "нс");

            int index = Collections.binarySearch(list,number);
            System.out.println("Найденный " + index);
            searchResult = false;
            startTime = System.nanoTime();
            if(index != -1){
                searchResult = true;
            }
            duration = System.nanoTime() - startTime;
            System.out.println("Бинарный поиск: номер " + (searchResult ? "" : "не ") + "найден, поиск занял " + duration + "нс");

            Set hashList = new HashSet(list);
            searchResult = false;
            startTime = System.nanoTime();
            if(hashList.contains(number)){
                searchResult = true;
            }
            duration = System.nanoTime() - startTime;
            System.out.println("Поиск в HashSet: номер " + (searchResult ? "" : "не ") + "найден, поиск занял " + duration + "нс");

            Set treeList = new TreeSet(list);
            searchResult = false;
            startTime = System.nanoTime();
            if(treeList.contains(number)){
                searchResult = true;
            }
            duration = System.nanoTime() - startTime;
            System.out.println("Поиск в TreeSet: номер " + (searchResult ? "" : "не ") + "найден, поиск занял " + duration + "нс");
        }
    }
}
/*
Замер в милисекундах
X555CX167
Поиск перебором: номер найден, поиск занял 35нс
Бинарный поиск: номер найден, поиск занял 0нс
Поиск в HashSet: номер найден, поиск занял 0нс
Поиск в TreeSet: номер найден, поиск занял 0нс
Замер в "нано"
X555CX167
Поиск перебором: номер найден, поиск занял 35313359нс
Бинарный поиск: номер найден, поиск занял 383нс
Поиск в HashSet: номер найден, поиск занял 30267нс
Поиск в TreeSet: номер найден, поиск занял 43293нс
* */
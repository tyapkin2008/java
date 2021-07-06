import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        // Строка с датой рождения
        String myBirthday = "10.01.1978";
        System.out.println("Дата рождения: " + myBirthday);

        // Получаем дату рождения в виде чисел.
        String myBirthdayElements[] = myBirthday.split("\\.");
        int myYear = Integer.parseInt(myBirthdayElements[2]);
        // Месяцы начинаются с нуля, поэтому уменьшаем на 1
        int myMonth = Integer.parseInt(myBirthdayElements[1]) - 1;
        int myDay = Integer.parseInt(myBirthdayElements[0]);

        // Задаем формат последующего вывода
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy - EEE");

        // Текущий календарь
        Calendar calendar = Calendar.getInstance();

        // Еще один календарь и устанавливаем дату рождения
        Calendar myBirthDayCalendar = Calendar.getInstance();
        myBirthDayCalendar.set(myYear, myMonth, myDay);

        // переменная содержит возраст
        int myAge = 0;
        // в цикле ко дню рождения добавляем год, пока дата не станет больше текущей
        while(myBirthDayCalendar.before(calendar))
        {
            System.out.println(formatter.format(myBirthDayCalendar.getTime()));
            myBirthDayCalendar.add(Calendar.YEAR, 1);
            myAge++;
        }
        // т.к. у нас выводится и плюсуется и нулевой год, т.е. год рождения, то минусуем итоговый возраст
        System.out.println("Возраст: " + --myAge);
    }
}

import java.util.Scanner;

public class Loader
{
    public static void main(String[] args)
    {
        /*
        Выбираем слова из текста и выводим в консоль
         */
        String engText = "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro quisquam est, qui dolorem ipsum, quia dolor sit, amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit, qui in ea voluptate velit esse, quam nihil molestiae consequatur, vel illum, qui dolorem eum fugiat, quo voluptas nulla pariatur? At vero eos et accusamus et iusto odio dignissimos ducimus, qui blanditiis praesentium voluptatum deleniti atque corrupti, quos dolores et quas molestias excepturi sint, obcaecati cupiditate non provident, similique sunt in culpa, qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio, cumque nihil impedit, quo minus id, quod maxime placeat, facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet, ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.";
        // Создаем массив строк, разделив по пробелу
        String textWords[] = engText.split("\\s");
        // Переменная для подсчета строки
        int wordCounter = 0;
        // Перебираем строки
        for(int i = 0; i < textWords.length; i++)
        {
            // Оставляем в текущей строке толко латинские буквы
            String currentWord = textWords[i].replaceAll("[^a-zA-Z]", "");
            // проверяем длину, осталось ли что-нибудь
            if(currentWord.length() > 0)
            {
                wordCounter++;
                System.out.println(currentWord);
            }
        }
        System.out.println("Выведено " + wordCounter + " слов.");


        /*
        Считаем сумму дохода
        * */
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(text);
        // Сумма дохода
        int currentSum = 0;

        text = text.replaceAll("[^0-9\\s]", "");
        String words[] = text.split("\\s");
        for(int i = 0; i < words.length; i++)
        {
            if(words[i].trim().length() > 0)
            {
                int salary = Integer.parseInt(words[i].trim());
                if(salary > 0)
                {
                    currentSum += salary;
                }
            }
        }
        System.out.println("Всего заработано " + currentSum + " руб.");

        /*
        Обработка ввода строки ФИО и последующего форматирования
         */
        // Цикл поыток ввода
        while(true)
        {
            System.out.println("Введите фамилию имя и отчество:");
            String lastName = "";
            String name = "";
            String secondName = "";
            // Переменная для учета, сколько частей ФИО получено
            int counter = 0;
            // Запрашиваем ввод от пользователя
            Scanner scanner = new Scanner(System.in);
            // Сохраняем введенную строку в переменную и обрезаем пробелы по краям
            String scannerString = scanner.nextLine().trim();
            // Разбиваем строку по пробелу и сохраняем в массивы строк
            String strName[] = scannerString.split("\\s");
            for(int i = 0; i < strName.length; i++)
            {
                // Убираем из текуще строки все, кроме русских и латинских букв(на случай иностранного имени)
                String currentWord = strName[i].replaceAll("[^а-яА-Яa-zA-Z]", "");
                // Если строка имеет длину
                if(currentWord.length() > 0)
                {
                    // В зависимости от текущей итерации, заполняем соотествующие элементы ФИО
                    counter++;
                    if(counter == 1)
                        lastName = currentWord;
                    else if(counter == 2)
                        name = currentWord;
                    else if(counter == 3)
                        secondName = currentWord;
                }
            }

            /*
            Проверяем счетчик.
            Если нулевой, значит ни одного слова не распознано, т.е. пустая строка или одни пробелы
            Если меньше трех, то недостаточно данных для ФИО
            Если больше трех , то много слов введено.
            Сообщаем об ошибке и ожидаем нового ввода
            Если счетчик равен трем, то выводим ФИО и останавливаем цикл ввода
            */
            if(counter == 0)
            {
                System.out.println("Вы ввели пустую строку! Попробуйте еще раз.");
            }
            else if(counter < 3)
            {
                System.out.println("Недостаточно слов введено! Попробуйте еще раз.");
            }
            else if(counter > 3)
            {
                System.out.println("Введено слишком много слов! Попробуйте еще раз.");
            }
            else
            {
                System.out.println("Фамилия: " + lastName);
                System.out.println("Имя: " + name);
                System.out.println("Отчество: " + secondName);
                // прерываем цикл попыток ввода
                break;
            }
        }
        /*
        Блок ввода и нормализации номера телефона
         */
        Scanner phoneScanner = new Scanner(System.in);
        // Цикл ввода номера
        while(true)
        {
            System.out.println("Введите номер телефона:");
            String phoneString = phoneScanner.nextLine();
            // Если номер удается распознать, то цикл прерываем
            if(numberIsCorrect(phoneString))
            {
                break;
            }
        }

    }

    public static boolean numberIsCorrect(String phoneString)
    {
        // Устанавливаем переменную результата обработки отрицательным
        boolean result = false;
        // Убираем все, кроме цифр
        String resultString = phoneString.replaceAll("[^0-9]", "");
        // Если цифр слишком мало или много, то сообщаем об ошибке (и вернется результатом false)
        if(resultString.length() < 10 || resultString.length() > 11)
        {
            System.out.println("Введено недостаточно цифр для определения номера");
        }
        else
        {
            // Если длина 11, т.е. номер в первой семеркой или восьмеркой- убираем
            if(resultString.length() == 11)
            {
                resultString = resultString.substring(1);
            }
            // Формируем строку из символов и частей полученного номера
            StringBuilder stringBuilder = new StringBuilder("+7 (");
            stringBuilder.append(resultString.substring(0,3));
            stringBuilder.append(") ");
            stringBuilder.append(resultString.substring(3,6) + "-" );
            stringBuilder.append(resultString.substring(6,8) + "-" );
            stringBuilder.append(resultString.substring(8,10));
            // Выводим сформированный номер, возвращаем результат true
            System.out.println("Полученный номер телефона: " + stringBuilder.toString());
            result = true;
        }
        return result;
    }
}
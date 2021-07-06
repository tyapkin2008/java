public class Main
{
    public static void main(String[] args){
        Container container = new Container();
        container.count += 7843;
        System.out.println("container.count = " + container.count);
        System.out.println(sumDigits(1231348));
    }

    public static Integer sumDigits(Integer number){
        //@TODO: write code here
        // переменная будет содержать сумму
        int sum = 0;
        String numString = number.toString();
        System.out.println("Получили строку из числа: " + numString);
        // разбираем строку на символы
        // проверяем длину полученной строки
        if(numString.length() > 0)
        {
            // перебираем символы
            for(int i = 0; i < numString.length() ; i++)
            {
                System.out.print("Символ № " + (i + 1));
                // Получаем символ по индексу
                String currentNumString = String.valueOf(numString.charAt(i));
                System.out.println(" : " + currentNumString);
                // увеличиваем сумму на число, преобразованное из строки
                sum +=  Integer.parseInt(currentNumString);

                /*
                Задание со *
                Сохраняем текущий символ в Character
                * */
                Character currentNumChar = Character.valueOf(numString.charAt(i));
                System.out.println("Число через Character: "+ Character.getNumericValue(currentNumChar));

            }
        }
        return sum;
    }
}


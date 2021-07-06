import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {

        /* Задание 1*/
        String text = "Каждый охотник желает знать, где сидит фазан";

        String[] colors = text.split(",?\\s+");
        // Временное хранилище строки
        String tmpString;
        // Обходим элементы до половины массива
        for(int i = 0; i < colors.length / 2; i++)
        {
            tmpString = colors[i];
            colors[i] = colors[colors.length - 1 - i];
            colors[colors.length - 1 - i] = tmpString;
        }
        // Выводим разврнутые цвета
        for(int i = 0; i < colors.length; i++)
        {
            System.out.println(colors[i]);
        }

        /* Задание 2*/
        double[] temperature = new double[30];
        int countnNormalTemperature = 0;
        double minTemperature = 32.0;
        double maxTemperature = 40.0;
        double minNorma = 36.2;
        double maxNorma = 36.9;
        double totalSummTemperaature = 0.0;
        DecimalFormat formatter = new DecimalFormat("#0.0°C");
        for(int i = 0; i < temperature.length; i++)
        {
            double currentTemperature = minTemperature + (double) Math.random() * (maxTemperature - minTemperature);
            temperature[i] = currentTemperature;
            totalSummTemperaature += currentTemperature;
            if(currentTemperature >= minNorma && currentTemperature <= maxNorma)
            {
                countnNormalTemperature++;
            }
            System.out.println(formatter.format(temperature[i]));
        }
        double averageTemperature = totalSummTemperaature / temperature.length;
        System.out.println("Пациентов с нормальной температурой: " + countnNormalTemperature);
        System.out.println("Средняя температура по больнице " + formatter.format(averageTemperature));

        /* Задание 3 */

        String[][] stringArray = new String[7][];
        for(int i = 0; i < stringArray.length; i++)
        {
            stringArray[i] = new String[7];
            for(int j = 0; j < stringArray[i].length; j++)
            {
                String currentSheetValue = " ";
                /*
                В зависимости от индекса элемента массива первого уровня,
                формируется логика заполнения массивов второго уровня
                0 0-6
                1 1-5
                2 2-4
                3 3-3
                4 2-4
                5 1-5
                6 0-6
                индекс заполняемой # ячейки
                либо равен текущему индексу 1 уровня
                либо равен последнему индексу 1 уровня - текущий индекс 1 уровня
                */
                if(j == i || j == stringArray[i].length - 1 - i)
                {
                    currentSheetValue = "#";
                }
                stringArray[i][j] = currentSheetValue;
            }
        }

        for(int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length; j++) {
                System.out.print(stringArray[i][j]);
            }
            System.out.println();
        }
    }
}

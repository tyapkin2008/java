import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final int CONTAINERS_IN_TRUCK = 12;
        final int BOXES_IN_CONTAINER = 27;

        while(true)
        {
            System.out.println("Введите количество ящиков(0-завершить):");
            Scanner scanner = new Scanner(System.in);
            String enterString = scanner.next();
            int count = Integer.parseInt(enterString);
            if (count == 0)
            {
                System.out.println("Программа завершена");
                break;
            }
            else if(count <= 0)
            {
                System.out.println("Некорректное значение!");
                continue;
            }

            /* формируем котейнеры и грузовики */
            // ящиков в грузовике
            int boxInTruck = CONTAINERS_IN_TRUCK * BOXES_IN_CONTAINER;

            for(int i = 0; i <= count; i++)
            {
                // выводим грузовик, если количество контейнеров кратно
                // к-ву контейнеров в грузовике * к-во ящиков в контейнере
                if((double) i % boxInTruck == 0)
                {
                    System.out.println("Грузовик " + Math.round((double) i / boxInTruck + 1) + ":");
                }
                // выводим очередной контейнер, если количество кратно к-ву ящиков в контейнере
                if((double) i % BOXES_IN_CONTAINER == 0)
                {
                    System.out.println("   Контейнер " + Math.round((double) i / BOXES_IN_CONTAINER + 1) + ":");
                }
                System.out.println("       Ящик " + (1 + i));
            }
        }
    }

}

public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        System.out.println(cat.getStatus());
        /*
        Задание
        Создать 5-7 кошек и вызвать у них различные методы:
         */

        // первая кошка
        Cat murka = new Cat();
        // мяукнуть
        murka.meow();

        // вторая кошка
        Cat barsik = new Cat();
        // Вторая кошка попить
        barsik.drink(12.5);

        // третья кошка
        Cat pushok = new Cat();
        // третья кошка покормить
        pushok.feed(13.1);

        // четвертая кошка
        Cat murzik = new Cat();
        // четвертая кошка тоже попила
        murzik.drink(34.5);

        /*
        Задание
        напечатайте в консоль вес созданных кошек
         */
        // Выводим вес всех кошек
        System.out.println("Вес безымянного кота " + cat.getWeight());
        System.out.println("Вес Мурки " + murka.getWeight());
        System.out.println("Вес Барсика " + barsik.getWeight());
        System.out.println("Вес Пушок " + pushok.getWeight());
        System.out.println("Вес Мурзика " + murzik.getWeight());
        /*
        Задание
        покормите 2 кошки и после этого распечатать их вес (убедитесь что вес изменился)
         */
        // покормим Мурку
        murka.feed(12.5);
        // покормим Мурзика
        murzik.feed(11.5);
        // Выводим новый вес Мурки
        System.out.println("Новый вес Мурки " + murka.getWeight());
        // Выводим новый вес Мурзика
        System.out.println("Новый вес Мурзика " + murzik.getWeight());

        /*
        Задание
        перекормите кошку
         */
        // Перекормим Пушка
        for(;;)
        {
             // Подкармливаем Пушка
            pushok.feed(500.23);
            if(pushok.getStatus() == "Exploded")
            {
                System.out.println("Пушок переел. Его вес теперь " + pushok.getWeight());
                break;
            }
        }
        /*
        задание
        "замяукайте" кошку до смерти
         */
        // Замяукаем Барсика
        for(;;)
        {
            barsik.meow();
            if(barsik.getStatus() == "Dead")
            {
                System.out.println("Барсик домяукался. Его вес теперь " + barsik.getWeight());
                break;
            }
        }
        /*
        Урок 2
         */
        /*
        Задание
        Создать в классе Cat метод, который будет возвращать сумму съеденной еды текущей кошки
        */
        // Создаем новую кошку
        Cat vaska = new Cat();
        // Вывод начального веса съеденного Васькой
        System.out.println("Начальный вес съеденного Васькой " + vaska.getTotalFoodWeight());
        // Покормим несколько раз Ваську
        vaska.feed(80.0);
        vaska.feed(49.5);
        vaska.feed(20.5);
        // Вывод текущего веса Васькиной съеденной еды
        System.out.println("Текущий вес съеденного Васькой " + vaska.getTotalFoodWeight());

        /*
        Создать в классе Cat метод “сходить в туалет” pee(),
        который будет уменьшать вес кошки и что-нибудь печатать.
         */

        System.out.println("Начальный вес Васьки " + vaska.getWeight());
        vaska.pee();
        System.out.println("Текущий вес Васьки " + vaska.getWeight());
        vaska.pee();
        System.out.println("Текущий вес Васьки " + vaska.getWeight());

        /* Урок 3.
         */
        System.out.println("Общее количество кошек " + Cat.getCount());
        Cat vasilij = new Cat();
        System.out.println("Общее количество кошек " + Cat.getCount());
        // Перекормим Василия
        for(;;)
        {
            // Подкармливаем Василия
            vasilij.feed(500.23);
            if(vasilij.getStatus() == "Exploded")
            {
                System.out.println("Василий переел. Его вес теперь " + vasilij.getWeight());
                break;
            }
        }
        System.out.println("Общее количество кошек после гибели Василия " + Cat.getCount());
        /* Задание со  *
        Запретить какие-либо действия с мертвым котом
         */
        // Ранее мы лишили жизни барсика, на нем и проверим
        barsik.pee();
        barsik.feed(23.5);
        barsik.meow();
        barsik.drink(54.0);
        /*
        Урок 4 enum цвет
        * */
        // Барсик белый
        barsik.setColor(CatColors.WHITE);
        // Мурка рыжая
        murka.setColor(CatColors.RED);
        // Узнаем цвет Барсика
        System.out.println("Цвет Барсика " + barsik.getColor());

        /*
        Задание 5.
        * */
        // Конструктор с весом
        Cat mashka = new Cat(2233.07);
        // проверим вес
        System.out.println("Вес Машки, который мы установили: " + mashka.getWeight());
        // Котенок
        Cat kitten = getKitten();
        // проверим вес котенка
        System.out.println("Вес котенка: " + kitten.getWeight());

         /*
        Задание 6.
        * */
        // Копирование кошки
        mashka.setName("Машка");
        Cat copyMashka = mashka.copy();
        // Проверим корректность копирования
        System.out.println("Имя Машки " + mashka.getName());
        System.out.println("Имя копии Машки " + copyMashka.getName());
        copyMashka.setName("Новая Машка");
        System.out.println("После изменения имени у копии");
        System.out.println("Имя Машки " + mashka.getName());
        System.out.println("Имя копии Машки " + copyMashka.getName());

        // И попробуем Копирование кошки через конструктор
        Cat secondCopyMaska = new Cat(mashka);
        // Проверим корректность копирования
        System.out.println("Имя Машки " + mashka.getName());
        System.out.println("Имя второй копии Машки " + secondCopyMaska.getName());
        secondCopyMaska.setName("Машка-3");
        System.out.println("После изменения имени у копии");
        System.out.println("Имя Машки " + mashka.getName());
        System.out.println("Имя копии Машки " + secondCopyMaska.getName());


    }

    // Задание 5. Создание котенка
    private static Cat getKitten()
    {
        return new Cat(1100.0);
    }
}

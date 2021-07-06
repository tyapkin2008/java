
public class Cat
{
    // количество ног
    public static final int EYES_COUNT = 2;
    // количество глаз
    public static final int LEGS_COUNT = 4;
    // Минимальный вес
    public static final double MIN_HEIGHT = 1000.0;
    // максимальный вес
    public static final double MAX_HEIGHT = 9000.0;

    private double originWeight;
    private double weight;
    private String name;

    private double minWeight;
    private double maxWeight;
    // Общее количество котов
    private static int count = 0;

    // вес съеденной кошкой еды
    private double totalFoodWeight = 0;

    // Цвет
    private CatColors color;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        // При создании кота увеличиваем количество
        count += 1;
    }

    // Задание 5. Конструктор с весом
    public Cat(double weight)
    {
        this();
        this.weight = weight;
    }

    // Задание 6. Копирование кошки через метод
    public Cat copy()
    {
        Cat newCat = new Cat();
        newCat.setName(this.getName());
        newCat.weight = this.weight;
        newCat.originWeight = this.originWeight;
        newCat.minWeight = this.minWeight;
        newCat.maxWeight = this.maxWeight;

        return newCat;
    }

    // Задание 6. Копирование через конструктор
    public Cat(Cat cat)
    {
        this.setName(cat.getName());
        this.weight = cat.weight;
        this.originWeight = cat.originWeight;
        this.minWeight = cat.minWeight;
        this.maxWeight = cat.maxWeight;
     }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // задание 4 Enum
    public void setColor(CatColors color)
    {
        this.color = color;
    }

    // Задание 6
    public CatColors getColor()
    {
        return color;
    }
    /*
    Задание c *: придумайте способ проверять жива ли кошка или нет на основе значения ее веса,
    и используя это запретите кормить, пить, ходить в туалет неживой кошке.
     */
    // метод определяет по весу, жива или нет
    public boolean isLiving()
    {
        return weight > minWeight && weight < maxWeight;
    }

    public static int getCount()
    {
        return count;
    }

    public void meow()
    {
        // Если кот мертвый, то запрещаем выполнять действия
        if(!isLiving())
        {
            System.out.println("Мертвые не мяукают");
        }
        else
        {
            weight = weight - 1;
            System.out.println("Meow");
            // Если вес стал критическим, то кот умирает и общее кол-во уменьшается
            if(weight < minWeight) {
                count--;
            }
        }
    }

    public void pee()
    {
        // Если кот мертвый, то запрещаем выполнять действия
        if(!isLiving())
        {
            System.out.println("Мертвые не ходят в туалет");
        }
        else
        {
            weight = weight - 1;
            System.out.println("Pee");
            // Если вес стал критическим, то кот умирает и общее кол-во уменьшается
            if(weight < minWeight) {
                count--;
            }
        }
    }

    public void feed(Double amount)
    {
        // Если кот мертвый, то запрещаем выполнять действия
        if(!isLiving())
        {
            System.out.println("Мертвые не кушают");
        }
        else {
            weight = weight + amount;
            totalFoodWeight += amount;
            // Если вес стал критическим, то кот умирает и общее кол-во уменьшается
            if (weight > maxWeight) {
                count--;
            }
        }
    }

    public void drink(Double amount)
    {
        // Если кот мертвый, то запрещаем выполнять действия
        if(!isLiving())
        {
            System.out.println("Мертвые кошки не пьют");
        }
        else
        {
            weight = weight + amount;
            // Если вес стал критическим, то кот умирает и общее кол-во уменьшается
            if(weight > maxWeight) {
                count--;
            }
        }
    }

    public Double getWeight()
    {
        return weight;
    }

    // Метод возвращает вес съеденной кошкой еды
    public Double getTotalFoodWeight()
    {
        return totalFoodWeight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
}
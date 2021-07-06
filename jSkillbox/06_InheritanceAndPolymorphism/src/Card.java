public class Card extends Account{
    // Закрываем прямой доступ к переменной
    private static double PERCENT = 1.01;

    public Card(){
        super();
    }

    public Card(double sum){
        super(sum);
    }


    // снять со счета
    @Override
    public void withdrawToAccount(double sum) {
        // Увеличиваем сумму снятия на процент
        sum *= PERCENT;
        super.withdrawToAccount(sum);
    }
}

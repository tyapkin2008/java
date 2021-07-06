public class Account {
    // Закрываем доступ к переменной. Только через методы
    private double balance;

    public Account(){
    }

    public Account(double sum){
        balance = sum;
    }

    // Пополнить баланс
    public void replenishAccount(double sum){
        balance += sum;
    }

    // Снять сумму
    public void withdrawToAccount(double sum){
        if(balance < sum){
            System.out.println("Сумма превышает остаток на счете.");
        }
        else {
            balance -= sum;
        }
    }

    // Показать состояние счета
    public void showBalance(){
        System.out.printf("Сумма на счете %s%n", balance);
    }
}

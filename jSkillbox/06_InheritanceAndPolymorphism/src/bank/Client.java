package bank;

public abstract class Client {

    protected double balance;

    public void showBalance(){
        System.out.println("текущий баланс: " + String.valueOf(balance));
    }

    /*Метод проверки возможности снятия для всех типов счетов*/
    public boolean checkWithdrawToAccount(double sum){
        boolean result = true;
        if(sum > this.balance){
            System.out.println("Сумма превышает остаток");
            result = false;
        }
        return result;
    }

    public abstract void withdrawToAccount(double sum);

    public abstract void replenishAccount(double sum);

}

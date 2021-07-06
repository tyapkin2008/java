public class Deposit extends Account{
    // Закрываем доступ к переменной и реализуем изменения и получение через методы
    private long lastDateReplenish;
    // Количество дней блокировки после последнего снятия
    private static final int BLOCKED_DAYS = 30;

    public Deposit(){
        super();
    }

    public Deposit(double sum){
        super(sum);
    }

    private void setLastDateReplenish(){
        lastDateReplenish = System.currentTimeMillis();
    }

    private long getLastDateReplenish(){
        return lastDateReplenish;
    }

    // снять со счета
    @Override
    public void withdrawToAccount(double sum) {
        if(getLastDateReplenish() == 0 || (System.currentTimeMillis() - getLastDateReplenish()) * 24 * 60 * 60 * 1000 > BLOCKED_DAYS){
            setLastDateReplenish();
            super.withdrawToAccount(sum);
        }else{
            System.out.println("Запрещено снятие в течение " + BLOCKED_DAYS + " дн.");
        }
    }
}

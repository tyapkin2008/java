public class Account implements Comparable<Account>
{
    // Сумма на счете
    private long money;
    private String accNumber;
    // Признак блокировки
    private boolean isBlocked;

    public Account(long money) {
        this.money = money;
        // генерим номер счета
        this.accNumber = "acc_" + System.nanoTime();
        this.setBlocked(false);
    }

    public boolean isBlocked() {
        return this.isBlocked;
    }

    public void setBlocked(boolean blocked) {
        this.isBlocked = blocked;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setMoney(long amount){
        this.money += amount;
    }

    public long getMoney(){
        return this.money;
    }

    @Override
    public int compareTo(Account o) {
        return this.getAccNumber().compareTo(o.getAccNumber());
    }
}

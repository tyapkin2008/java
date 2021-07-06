import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    private final long maxAmountWithoutControl = 5000;

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccount(Account account) {
        this.accounts.put(account.getAccNumber(), account);
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        synchronized(accounts.get(fromAccountNum).compareTo(accounts.get(toAccountNum)) > 0 ? accounts.get(fromAccountNum) : accounts.get(toAccountNum)){
            synchronized (accounts.get(fromAccountNum).compareTo(accounts.get(toAccountNum)) > 0 ? accounts.get(toAccountNum) : accounts.get(fromAccountNum)){
                // Проверим, чтобы хватало средств и оба счета не были заблокированы
                if(amount <= accounts.get(fromAccountNum).getMoney() && !accounts.get(fromAccountNum).isBlocked() && !accounts.get(toAccountNum).isBlocked()){
                    // Если сумма превышает установлнную максимальную сумму без проверки, проводим проверку, и если не пройдена, устанавливаем признаки блокировки счетов
                    if(amount > maxAmountWithoutControl && isFraud(fromAccountNum, toAccountNum, amount)){
                        System.out.println("Операция заблокирована. Счет списания :" + fromAccountNum + ". Счет зачисления " + toAccountNum);
                        accounts.get(fromAccountNum).setBlocked(true);
                        accounts.get(toAccountNum).setBlocked(true);
                    } else {
                        System.out.println("Перевод " + amount + " from " + fromAccountNum + " to " + toAccountNum);
                        accounts.get(fromAccountNum).setMoney(-amount);
                        accounts.get(toAccountNum).setMoney(amount);
                    }
                } else {
                    System.out.println("Операция невозможна. Недостаточно среств или счет заблокирован.");
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum){
        return accounts.get(accountNum).getMoney();
    }

    public synchronized long getTotalSum(){
        return accounts.values().stream().mapToLong(a -> a.getMoney()).sum();
    }
}

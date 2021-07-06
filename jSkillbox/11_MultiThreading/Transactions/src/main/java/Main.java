import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Random random = new Random();
        // посчитаем сумму вручную для контроля
        long bankSum = 0;
        for (int i = 0; i < 1000; i++){
            // Сгенерим произвольную сумму на счете
            long accSum = random.nextInt(1000000);
            bankSum += accSum;
            Account account = new Account(accSum);
            bank.setAccount(account);
        }
        System.out.println("Сумма в банке " + bank.getTotalSum());

        // Имитируем транзакции между произвольными счетами
        // Произвольно будем выбирать 2 счета и переводить
        HashMap<String, Account> accounts = bank.getAccounts();
        String[] accountsNumbers = accounts.keySet().toArray(String[]::new);
        int accountsSize = accounts.size();
        for(int i = 0; i < 10000; i++){
            new Thread(()-> {
                try {
                    int accountFromIndex = random.nextInt(accountsSize);
                    int accountToIndex = random.nextInt(accountsSize);
                    long amount = random.nextInt(100000);
                    bank.transfer(accountsNumbers[accountFromIndex], accountsNumbers[accountToIndex], amount);
                    System.out.println("Сумма в банке " + bank.getTotalSum());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).run();
        }
    }
}


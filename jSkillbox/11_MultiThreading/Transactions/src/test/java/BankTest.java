import junit.framework.TestCase;

public class BankTest extends TestCase {
    Bank bank;
    Account account1;
    Account account2;
    Account account3;

    public void setUp() throws Exception {
        bank = new Bank();
        account1 = new Account(1000);
        account2 = new Account(2000);
        account3 = new Account(3000);
        bank.setAccount(account1);
        bank.setAccount(account2);
        bank.setAccount(account3);

        super.setUp();
    }

    public void testGetAccounts() {
        assertEquals(3, bank.getAccounts().size());
    }

    public void testSetAccount() {
        bank.setAccount(new Account(4000));
        assertEquals(4, bank.getAccounts().size());
    }

    public void testIsFraud() {

    }

    public void testTransfer() throws InterruptedException {
        bank.transfer(account1.getAccNumber(), account2.getAccNumber(), 500);
        if(account1.isBlocked() || account2.isBlocked()){
            assertEquals(1000, account1.getMoney());
            assertEquals(2000, account2.getMoney());
        }else{
            assertEquals(500, account1.getMoney());
            assertEquals(2500, account2.getMoney());
        }

    }

    public void testGetBalance() {
        assertEquals(1000, bank.getBalance(account1.getAccNumber()));
        assertEquals(2000, bank.getBalance(account2.getAccNumber()));
        assertEquals(3000, bank.getBalance(account3.getAccNumber()));

    }

    public void testGetTotalSum() {
        assertEquals(6000, bank.getTotalSum());
    }
}
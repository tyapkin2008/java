import junit.framework.TestCase;

public class AccountTest extends TestCase {
    Account account;
    Account account2;
    public void setUp() throws Exception {
        account = new Account(1000);
        account2 = new Account(2000);
        super.setUp();
    }

    public void testIsBlocked() {
        account.setBlocked(true);
        account2.setBlocked(false);
        assertEquals(true, account.isBlocked());
        assertEquals(false, account2.isBlocked());
    }

    public void testSetBlocked() {
        account.setBlocked(true);
        account2.setBlocked(false);
        assertEquals(true, account.isBlocked());
        assertEquals(false, account2.isBlocked());
    }

    public void testGetAccNumber() {
        assertEquals(true, account.getAccNumber().length() > 0);
        assertEquals(true, account2.getAccNumber().length() > 0);

    }

    public void testTransferTo() {
        account.transferTo(account2, 500);
        // Если хотя бы один из аккаунтов был заблокирован, то счета должны остаться нетронутыми
        if(account.isBlocked() || account2.isBlocked()){
            assertEquals(1000, account.getMoney());
            assertEquals(2000, account2.getMoney());
        }else{
            assertEquals(500, account.getMoney());
            assertEquals(2500, account2.getMoney());
        }
    }

    public void testTransferToWithBlockedAkk() {
        // Принудительно блокируем счет. Суммы дожны остаться нетронутыми
        account.setBlocked(true);
        account.transferTo(account2, 500);
        assertEquals(1000, account.getMoney());
        assertEquals(2000, account2.getMoney());
    }

    public void testGetMoney() {
        assertEquals(1000, account.getMoney());
        assertEquals(2000, account2.getMoney());
    }

    public void testCompareTo() {
        assertNotSame(account, account2);
    }
}
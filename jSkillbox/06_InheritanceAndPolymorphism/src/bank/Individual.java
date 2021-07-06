package bank;
/*Физ лицо*/
public class Individual extends Client{

    @Override
    public void replenishAccount(double sum) {
        this.balance += sum;
    }

    @Override
    public void withdrawToAccount(double sum) {
        if(checkWithdrawToAccount(sum)){
            this.balance -= sum;
        }
    }
}

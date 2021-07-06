package bank;
/*Юр лицо*/
public class LegalEntity extends Client{
    private final double COMMISSION = 1.01;
    @Override
    public void replenishAccount(double sum) {
        this.balance += sum;
    }

    @Override
    public void withdrawToAccount(double sum) {
        /*Снятие с комиссией*/
        if(checkWithdrawToAccount(sum * COMMISSION)){
            this.balance -= sum * COMMISSION;
        }
    }
}

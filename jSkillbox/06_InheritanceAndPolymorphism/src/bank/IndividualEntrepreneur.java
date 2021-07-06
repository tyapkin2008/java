package bank;
/*ИП
* пополнение с комиссией 1%,
* если сумма меньше 1000 рублей,
* и 0,5%, если сумма больше либо равна 1000 рублей.
*  */
public class IndividualEntrepreneur extends Client{
    private final double COMMISSION_LES_THOUSAND = 1;
    private final double COMMISSION_EQUAL_OVER_THOUSAND = 0.5;
    private final double COMPARATOR_SUM = 1000;
    @Override
    public void replenishAccount(double sum) {
        if(sum < COMPARATOR_SUM){
            this.balance += sum * (1 - COMMISSION_LES_THOUSAND / 100);
        }else{
            this.balance += sum * (1 - COMMISSION_EQUAL_OVER_THOUSAND / 100);
        }
    }

    @Override
    public void withdrawToAccount(double sum) {
        if(checkWithdrawToAccount(sum)){
            this.balance -= sum;
        }
    }
}

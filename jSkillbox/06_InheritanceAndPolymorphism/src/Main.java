import bank.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Физ лицо");
        Client fiz = new Individual();
        fiz.replenishAccount(1000);
        fiz.showBalance();
        fiz.withdrawToAccount(500);
        fiz.showBalance();

        System.out.println("Юр лицо");
        Client legalEntity = new LegalEntity();
        legalEntity.replenishAccount(1000);
        legalEntity.showBalance();
        legalEntity.withdrawToAccount(500);
        legalEntity.showBalance();

        System.out.println("ИП");
        Client individualEntrepreneur = new IndividualEntrepreneur();
        individualEntrepreneur.replenishAccount(800);
        individualEntrepreneur.showBalance();
        individualEntrepreneur.replenishAccount(1200);
        individualEntrepreneur.showBalance();
    }
}

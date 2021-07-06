package company;

public class TopManager extends User{

    private static final double PERCENT = 1.5;
    private static final double MIN_INCOME_FOR_BONUS = 10000000;
    private double minSalary = 60000;
    private double maxSalary = 80000;

    @Override
    public int getMonthSalary() {
        if(company.getIncome() > MIN_INCOME_FOR_BONUS)
            return (int) (monthSalary + monthSalary * PERCENT);
        return (int) monthSalary;
    }

    @Override
    public double generateRandomDefaultSalary(double min, double max) {
        return super.generateRandomDefaultSalary(minSalary, maxSalary);
    }
}


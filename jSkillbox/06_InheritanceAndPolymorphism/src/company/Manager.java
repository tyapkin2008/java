package company;

public class Manager extends User{

    private static final double PERCENT = 0.05;
    private static final double MIN_INCOME = 115000;
    private static final double MAX_INCOME = 140000;

    private double getIncome() {
        return (double) (Math.random() * (MAX_INCOME - MIN_INCOME + 1)) + MIN_INCOME;
    }

    @Override
    public int getMonthSalary() {
        return (int) (monthSalary + getIncome() * PERCENT);
    }

    @Override
    public double generateRandomDefaultSalary(double min, double max) {
        return super.generateRandomDefaultSalary(minSalary, maxSalary);
    }
}

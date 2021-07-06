package company;

public class Operator extends User{

    private double minSalary = 20000;
    private double maxSalary = 22000;

    @Override
    public int getMonthSalary() {
        return (int) monthSalary;
    }

    @Override
    public double generateRandomDefaultSalary(double min, double max) {
        return super.generateRandomDefaultSalary(minSalary, maxSalary);
    }
}


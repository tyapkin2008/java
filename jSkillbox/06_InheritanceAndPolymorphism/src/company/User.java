package company;

public abstract class User implements Employee{

    protected Company company;
    protected double monthSalary;
    protected double minSalary = 40000;
    protected double maxSalary = 45000;

    public void hire(Company company){
        this.company = company;
        this.monthSalary = generateRandomDefaultSalary(minSalary, maxSalary);
    }

    public void fire(){
        this.company = null;
        this.monthSalary = 0.0;
    }

    @Override
    public double generateRandomDefaultSalary(double min, double max) {
        return (double) (Math.random() * (max - min + 1)) + min;
    }
}

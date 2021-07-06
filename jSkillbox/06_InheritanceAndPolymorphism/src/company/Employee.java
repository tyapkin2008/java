package company;

public interface Employee {
    void hire(Company company);
    void fire();
    double generateRandomDefaultSalary(double min, double max);
    int getMonthSalary();
}

package company;

import java.util.*;

public class Test {
    public static void main(String[] args) {

        Company company = new Company();
        company.setIncome(11000000);

        List<Employee> operators = new ArrayList<>();
        for(int i = 0; i < 180; i++){
            Employee operator = new Operator();
            operators.add(operator);
        }
        company.hireAll(operators);

        for(int i = 0; i < 80; i++){
            Employee manager = new Manager();
            company.hire(manager);
        }

        for(int i = 0; i < 10; i++){
            Employee topManager = new TopManager();
            company.hire(topManager);
        }
        List<Employee> topSalaryStaff = company.getTopSalaryStaff(15);

        for(Employee employee: topSalaryStaff){
            System.out.printf("%,d руб.\n", employee.getMonthSalary());
        }

        List<Employee> lowSalaryStaff = company.getLowestSalaryStaff(30);

        for(Employee employee: lowSalaryStaff){
            System.out.printf("%,d руб.\n", employee.getMonthSalary());
        }
        if(company.getEmployeeCount() > 0){

            int fireCount = company.getEmployeeCount() / 2;
            List<Employee> employeeList = company.getEmployeeList();
            for(int i = 0; i < fireCount; i++){

                Employee employee = employeeList.get(i);
                company.fire(employee);
            }
        }

        topSalaryStaff = company.getTopSalaryStaff(15);

        for(Employee employee: topSalaryStaff){
            System.out.printf("%,d руб.\n", employee.getMonthSalary());
        }

        lowSalaryStaff = company.getLowestSalaryStaff(30);

        for(Employee employee: lowSalaryStaff){
            System.out.printf("%,d руб.\n", employee.getMonthSalary());
        }

    }
}

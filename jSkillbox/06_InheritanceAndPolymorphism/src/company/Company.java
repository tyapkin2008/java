package company;

import java.util.*;

public class Company {

    private double income;
    private List<Employee> employeeList = new ArrayList<>();


    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public int getEmployeeCount(){
        return employeeList.size();
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getIncome(){
        return income;
    }

    public void hire(Employee employee){
        employee.hire(this);
        employeeList.add(employee);
    }

    public void hireAll(List<Employee> userList){
        for (Employee employee: userList){
            employee.hire(this);
            employeeList.add(employee);
        }
    }

    public void fire(Employee employee){
        employee.fire();
        employeeList.remove(employee);
    }

   List<Employee> getTopSalaryStaff(int count){
       count = checkCount(count);
       List<Employee> sortedList = new ArrayList<>();
       if(count > 0) {
           Collections.sort(employeeList, new EmployeeComparator());
           Collections.reverse(employeeList);
           for(int i = 0; i < count; i++){
               Employee employee = employeeList.get(i);
               sortedList.add(employee);
           }
       }
       Collections.sort(sortedList, new EmployeeComparator());
       Collections.reverse(sortedList);
       return sortedList;
   }

    List<Employee> getLowestSalaryStaff(int count){
        count = checkCount(count);
        List<Employee> sortedList = new ArrayList<>();
        if(count > 0) {
            Collections.sort(employeeList, new EmployeeComparator());
            for(int i = 0; i < count; i++){
                Employee employee = employeeList.get(i);
                sortedList.add(employee);
            }
        }
        Collections.sort(sortedList, new EmployeeComparator());
        Collections.reverse(sortedList);
        return sortedList;
    }

    private int checkCount(int count){
        if(count <=0 || count > employeeList.size()){
            count = employeeList.size();
        }
        return count;
    }
}

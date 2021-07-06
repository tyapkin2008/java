import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main
{
    private static String staffFile = "07_AdvancedOOPFeatures/LambdaExpressions/data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        /*Задание 1*/
        int yearWorkStartDate = 2017;
        ArrayList<Employee> staff = loadStaffFromFile();
        Calendar workStartDate = Calendar.getInstance();

        Optional<Employee> employee = staff.stream().filter((b) -> {
            workStartDate.setTime(b.getWorkStart());
            return workStartDate.get(Calendar.YEAR) == yearWorkStartDate;
        }).max(Comparator.comparingInt(Employee::getSalary));

        if(employee.isPresent()){
            System.out.println("Сотрудник с самой высокой ЗП, принятый в 2017 году: " + employee.get().getName());
            System.out.println("Его зарплата: " + employee.get().getSalary());
        }
        /*Задание 1*/
        /*
         * airport.jar время вылета и модели самолётов, вылетающих в ближайшие два часа.
         * */
        System.out.println("Вылеты в ближайшие 2 часа");
        long milliSecInTwoHour = 2 * 60 * 60 * 1000;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Airport airport = Airport.getInstance();
        List<Terminal> terminals = airport.getTerminals();
        terminals.stream().flatMap(terminal -> terminal.getFlights().stream()).
                filter(flight -> {
                    return flight.getDate().getTime() >= System.currentTimeMillis() && flight.getDate().getTime() - System.currentTimeMillis() <= milliSecInTwoHour;
                }).
                sorted(Comparator.comparing(flight -> flight.getDate())).
                forEach(flight -> {
                    System.out.print("Время вылета: " + formatter.format(flight.getDate().getTime()));
                    System.out.println(" модель: " + flight.getAircraft().getModel());
                });
        /*
        terminals.stream().forEach(terminal ->
                terminal.getFlights().stream().
                sorted(Comparator.comparing(flight -> flight.getDate())).
                filter(flight -> {
                    return flight.getDate().getTime() >= System.currentTimeMillis() && flight.getDate().getTime() - System.currentTimeMillis() <= milliSecInTwoHour;
                }).forEach(flight -> {
                    System.out.print("Время вылета: " + formatter.format(flight.getDate().getTime()));
                    System.out.println(" модель: " + flight.getAircraft().getModel());
                })
        );
        */
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}
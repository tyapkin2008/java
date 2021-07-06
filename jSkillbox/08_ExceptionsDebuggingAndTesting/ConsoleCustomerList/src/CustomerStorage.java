import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws Exception {
        String[] components = data.split("\\s+");
        if(components.length < 4){
            throw new IllegalArgumentException("test");
        } else if(!components[2].matches("^[a-zA-Z-_.]+[@][a-zA-Z-_.]+[.][a-zA-Z]{2,3}")) {
            throw new Exception("invalid email");
        } else if(!components[3].matches("^[+][0-9]{11}")) {
            throw new Exception("invalid phone number");
        } else {
            String name = components[0] + " " + components[1];
            storage.put(name, new Customer(name, components[3], components[2]));
        }
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}
package MovenentList;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String type;
    private String number;
    private String currency;
    private List<Operation> operations;

    public Account(String number, String type, String currency) {
        this.number = number;
        this.type = type;
        this.currency = currency;
        operations = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations.addAll(operations);
    }

    public void setOperation(Operation operation) {
        this.operations.add(operation);
    }

    @Override
    public String toString(){
        return this.type + " " + this.number + " " + this.currency;
    }

}

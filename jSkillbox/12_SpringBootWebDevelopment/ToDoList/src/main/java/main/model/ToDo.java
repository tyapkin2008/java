package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date date;

    private String text;

    private boolean completed;

    public ToDo(){
        this.setCompleted(false);
        this.setDate(new Date());
    }

    public ToDo(String text){
        this.setText(text);
        this.setCompleted(false);
        this.setDate(new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

package main;

import main.model.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static AtomicInteger currentId = new AtomicInteger(1);
    private static ConcurrentHashMap<Integer, ToDo> todos = new ConcurrentHashMap<Integer, ToDo>();

    public static List<ToDo> getAllToDos(){
        ArrayList<ToDo> todosList = new ArrayList<ToDo>();
        todosList.addAll(todos.values());
        return todosList;
    }

    public static int addToDo(ToDo todo){
        int id = currentId.getAndAdd(1);
        todo.setId(id);
        todos.put(id, todo);
        return id;
    }

    public static ToDo GetToDo(int id){
        return todos.get(id);
    }

    public static boolean UpdateToDo(ToDo newTodo, int id){
        boolean result = false;
        if (todos.get(id) != null){
            // У нас может поменяться статус и текст
            todos.get(id).setText(newTodo.getText());
            todos.get(id).setCompleted(newTodo.isCompleted());
            result = true;
        }
        return result;
    }

    public static boolean delete(int id){
        boolean result = false;
        if (todos.get(id) != null){
            todos.remove(id);
            result = true;
        }
        return result;
    }

    public static boolean delete(){
        boolean result = false;
        if (todos.size() > 0){
            todos.clear();
            result = true;
        }
        return result;
    }

}

package main;

import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos/")
public class ToDoListController {

    //@Autowired
    private ToDoRepository toDoRepository;

    public ToDoListController(ToDoRepository toDoRepository){
        this.toDoRepository =  toDoRepository;
    }
    /*Список записей*/
    @GetMapping()
    public List<ToDo> list(){
        ArrayList<ToDo> toDos = new ArrayList<>();
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        for(ToDo toDo : toDoIterable){
            toDos.add(toDo);
        }
        return toDos;
    }

    /*Получение записи*/
    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if(optionalToDo.isPresent()){
            return new ResponseEntity(optionalToDo.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /*Обновление записи*/
    @PutMapping("{id}")
    public ResponseEntity get(ToDo requestTodo, @PathVariable int id){
        /*
        * Получаем запись из БД, обновляем поля, сохраняем.
        * */
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if(optionalToDo.isPresent()){
            ToDo toDo = optionalToDo.get();
            toDo.setCompleted(requestTodo.isCompleted());
            toDo.setText(requestTodo.getText());
            toDoRepository.save(toDo);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /*Добавление записи*/
    @PostMapping()
    public int add(ToDo todo){
        ToDo newTodo = toDoRepository.save(todo);
        return newTodo.getId();
    }

    /*Удаление записи*/
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id){
        toDoRepository.deleteById(id);
        /*
        * Чтобы вернуть правильный ответ, проверим наличие элемента в БД
        * Если нет, то будем считать, что удаление прошло успешно
        * */
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if(optionalToDo.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /*Удаление записей*/
    @DeleteMapping()
    public ResponseEntity delete(){
        toDoRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

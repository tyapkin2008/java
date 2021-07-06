package main;

import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class DefaultController {

    ToDoRepository toDoRepository;

    public DefaultController(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    @RequestMapping("/")
    public String index(Model model){
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        ArrayList<ToDo> toDoList = new ArrayList<>();
        for(ToDo toDo : toDoIterable){
            toDoList.add(toDo);
        }
        model.addAttribute("toDoList", toDoList);
        model.addAttribute("count", toDoList.size());
        return "index";
    }

}

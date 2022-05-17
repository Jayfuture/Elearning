package in.bushansirgur.springbootthymeleaf.controller;

import in.bushansirgur.springbootthymeleaf.dao.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.bushansirgur.springbootthymeleaf.entity.Todo;

@Controller
public class TodoController {
	
	@Autowired
	private TodoRepository eRepo;
	
	@GetMapping({"/list", "/"})
	public ModelAndView getAllTodo() {
		ModelAndView mav = new ModelAndView("list-todos");
		mav.addObject("todos", eRepo.findAll());
		return mav;
	}
	
	@GetMapping("/addTodoForm")
	public ModelAndView addTodoForm() {
		ModelAndView mav = new ModelAndView("add-todo-form");
		Todo newTodo = new Todo();
		mav.addObject("todo", newTodo);
		return mav;
	}
	
	@PostMapping("/saveTodo")
	public String saveTodo(@ModelAttribute Todo todo) {
		eRepo.save(todo);
		return "redirect:/list";
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam int todoId) {
		ModelAndView mav = new ModelAndView("add-todo-form");
		Todo todo = eRepo.findById(todoId).get();
		mav.addObject("todo", todo);
		return mav;
	}
	
	@GetMapping("/deleteTodo")
	public String deleteTodo(@RequestParam int todoId) {
		eRepo.deleteById(todoId);
		return "redirect:/list";
	}
}

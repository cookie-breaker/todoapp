package com.todo.todo.controller;

import com.todo.todo.model.Todo;
import com.todo.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoWebController {
    private final TodoService todoService;

    @Autowired
    public TodoWebController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public String showTodoList(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "todoList";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoForm";
    }

    @PostMapping("/create")
    public String createTodo(@ModelAttribute Todo todo) {
        todoService.saveTodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        todoService.getTodoById(id).ifPresent(todo -> model.addAttribute("todo", todo));
        return "todoForm";
    }

    @PostMapping("/update")
    public String updateTodo(@ModelAttribute Todo todo) {
        todoService.saveTodo(todo);
        return "redirect:/todos";
    }



    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";
    }



}

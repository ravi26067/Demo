package com.allengroup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allengroup.demo.errors.ResourceNotFoundException;
import com.allengroup.demo.model.Todo;
import com.allengroup.demo.service.TodoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/todos/v1")
public class TodoController {

    @Autowired
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/fetch")
    public List<Todo> getAllTodos() throws ResourceNotFoundException {
        return todoService.getAllTodos();
    }

    @PostMapping("/create")
    public Todo createTodos(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping("/update/{id}")
    public Todo updateTodo(@PathVariable(value = "id") Long id, @RequestBody Todo todo)
            throws ResourceNotFoundException {
        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteTodo(@PathVariable(value = "id") Long id) {
        todoService.deleteTodo(id);
    }

}
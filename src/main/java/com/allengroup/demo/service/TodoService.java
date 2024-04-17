package com.allengroup.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allengroup.demo.errors.ResourceNotFoundException;
import com.allengroup.demo.model.Todo;

@Service
public interface TodoService {
    List<Todo> getAllTodos() throws ResourceNotFoundException;

    Todo createTodo(Todo todo);

    Todo getTodoById(Long id) throws ResourceNotFoundException;

    Todo updateTodo(Long id, Todo todo) throws ResourceNotFoundException;

    void deleteTodo(Long id);
}

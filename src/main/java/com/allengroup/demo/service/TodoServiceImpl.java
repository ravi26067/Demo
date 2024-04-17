package com.allengroup.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allengroup.demo.errors.ResourceNotFoundException;
import com.allengroup.demo.model.Todo;
import com.allengroup.demo.repository.TodoRepository;

@Component
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() throws ResourceNotFoundException {
        List<Todo> todos = todoRepository.findAll();
        if (todos.isEmpty()) {
            throw new ResourceNotFoundException("No Todos Found");
        }
        return todos;
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo getTodoById(Long id) throws ResourceNotFoundException {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        return todoOptional.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id : " + id));
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) throws ResourceNotFoundException {
        Optional<Todo> existingTodoOptional = todoRepository.findById(id);
        if (existingTodoOptional.isPresent()) {
            Todo existingTodo = existingTodoOptional.get();
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setCompleted(todo.isCompleted());
            return todoRepository.save(existingTodo);
        } else {
            throw new ResourceNotFoundException("Todo not found for this id : " + id);
        }
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

}

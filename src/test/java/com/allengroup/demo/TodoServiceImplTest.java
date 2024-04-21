package com.allengroup.demo;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.allengroup.demo.errors.ResourceNotFoundException;
import com.allengroup.demo.model.Todo;
import com.allengroup.demo.repository.TodoRepository;
import com.allengroup.demo.service.TodoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    private Todo testTodo;

    public void setUp() {
        testTodo = new Todo();
        testTodo.setId(1L);
        testTodo.setTitle("Test Todo");
        testTodo.setCompleted(false);
    }

    @Test
    public void testGetAllTodos_Success() {
        List<Todo> todos = new ArrayList<>();
        todos.add(testTodo);

        when(todoRepository.findAll()).thenReturn(todos);

        try {
            List<Todo> result = todoService.getAllTodos();
            assertEquals(1, result.size());
            assertEquals(testTodo, result.get(0));
        } catch (ResourceNotFoundException e) {
            fail("Resource not found exception should not be thrown");
        }

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetAllTodos_EmptyList() throws ResourceNotFoundException {
        when(todoRepository.findAll()).thenReturn(new ArrayList<>());
        todoService.getAllTodos();
    }

}
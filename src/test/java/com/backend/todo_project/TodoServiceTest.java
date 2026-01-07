package com.backend.todo_project;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TodoServiceTest {

    private TodoService service;

    @Before
    public void setup() {
        service = new TodoService();
    }

    @Test
    public void addTodo_shouldAddTodo() {
        Todo todo = new Todo(1L, "verifier les rapport");
        service.addTodo(todo);
        assertEquals(1, service.getAllTodos().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTodo_null_shouldFail() {
        service.addTodo(null);
    }

    @Test
    public void completeTodo() {
        Todo todo = new Todo(1L, "test");
        service.addTodo(todo);
        service.completeTodo(1L);
        assertTrue(todo.isCompleted());
    }

    @Test(expected = IllegalArgumentException.class)
    public void completeTodo_wrongId() {
        service.completeTodo(99L);
    }

    @Test
    public void deleteTodo() {
        Todo todo = new Todo(1L, "Delete me");
        service.addTodo(todo);
        service.deleteTodo(1L);
        assertEquals(0, service.getAllTodos().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTodo_wrongId() {
        service.deleteTodo(2L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void todo_titleEmpty() {
        new Todo(1L, "   ");
    }
}

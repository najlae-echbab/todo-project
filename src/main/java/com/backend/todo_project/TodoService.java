package com.backend.todo_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TodoService {

    private final List<Todo> todos = new ArrayList<>();

    public void addTodo(Todo todo) {
        if (todo == null) {
            throw new IllegalArgumentException("Todo cannot be null");
        }

        if (todo.getId() == null) {
            throw new IllegalArgumentException("Todo id cannot be null");
        }

        String title = todo.getTitle();
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Todo title cannot be null or empty");
        }

        for (Todo t : todos) {
            if (Objects.equals(t.getId(), todo.getId())) {
                throw new IllegalArgumentException("Todo id already exists");
            }
        }

        todos.add(todo);
    }

    public List<Todo> getAllTodos() {
        return Collections.unmodifiableList(todos);
    }

    public void completeTodo(Long id) {
        Todo todo = findById(id);
        todo.setCompleted(true);
    }

    public void deleteTodo(Long id) {
        Todo todo = findById(id);
        todos.remove(todo);
    }

    private Todo findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        for (Todo t : todos) {
            if (id.equals(t.getId())) {
                return t;
            }
        }

        throw new IllegalArgumentException("Todo not found");
    }
}

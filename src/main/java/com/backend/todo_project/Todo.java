package com.backend.todo_project;

import java.util.Objects;

public class Todo {

    private Long id;
    private String title;
    private boolean completed;

    public Todo(Long id, String title) {
        if (id == null) {
            throw new IllegalArgumentException("Todo id cannot be null");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Todo title cannot be null or empty");
        }
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    public Todo(Long id, String title, boolean completed) {
        this(id, title);
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

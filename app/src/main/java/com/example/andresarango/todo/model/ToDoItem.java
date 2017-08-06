package com.example.andresarango.todo.model;


public class ToDoItem {

    public Long _id;
    public String title;

    public ToDoItem(){
        title = "No title";
    }

    public ToDoItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

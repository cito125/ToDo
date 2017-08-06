package com.example.andresarango.todo.model;


public class ToDoItem {

    private final String mTitle;

    private ToDoItem(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }
}

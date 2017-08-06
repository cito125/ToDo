package com.example.andresarango.todo.model;


public class ToDoItem {

    private final String mTitle;

    private ToDoItem(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public static class ToDoItemBuilder{
        private final String title;

        public ToDoItemBuilder(String title) {
            this.title = title;
        }

        public ToDoItem build(){
            return new ToDoItem(title);
        }
    }

}

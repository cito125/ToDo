package com.example.andresarango.todo.todo_adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.andresarango.todo.R;
import com.example.andresarango.todo.model.ToDoItem;

class ToDoViewHolder extends RecyclerView.ViewHolder {
    private final TextView mToDoItemTextView;

    ToDoViewHolder(View itemView) {
        super(itemView);
        mToDoItemTextView = (TextView) itemView.findViewById(R.id.todo_item_textview);
    }

    void bind(ToDoItem toDoItem) {
        mToDoItemTextView.setText(toDoItem.getTitle());
    }
}

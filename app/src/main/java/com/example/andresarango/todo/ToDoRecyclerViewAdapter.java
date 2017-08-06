package com.example.andresarango.todo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andresarango.todo.model.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoRecyclerViewAdapter.ToDoViewHolder> {

    private final List<ToDoItem> mToDoList = new ArrayList<>();

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ToDoViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.todo_viewholder, parent, false));
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        holder.bind(mToDoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }

    public void setToDoList(List<ToDoItem> toDoList){
        mToDoList.clear();
        mToDoList.addAll(toDoList);
        notifyDataSetChanged();
    }

    public void addToDoListItem(ToDoItem toDoItem){
        mToDoList.add(toDoItem);
        notifyItemInserted(mToDoList.size() - 1);
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        private final TextView mToDoItemTextView;

        public ToDoViewHolder(View itemView) {
            super(itemView);
            mToDoItemTextView = (TextView) itemView.findViewById(R.id.todo_item_textview);
        }

        public void bind(ToDoItem toDoItem) {
            mToDoItemTextView.setText(toDoItem.getTitle());
        }
    }
}

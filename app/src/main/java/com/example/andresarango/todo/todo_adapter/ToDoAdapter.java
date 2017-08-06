package com.example.andresarango.todo.todo_adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.todo.R;
import com.example.andresarango.todo.model.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {

    private final List<ToDoItem> mToDoList = new ArrayList<>();
    private final OnClickListener mListener;

    public ToDoAdapter(OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ToDoViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.todo_viewholder, parent, false));
    }

    @Override
    public void onBindViewHolder(final ToDoViewHolder holder, final int position) {
        holder.bind(mToDoList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onToDoItemClick(mToDoList.get(holder.getAdapterPosition()), holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }

    public void addToDoListItem(ToDoItem toDoItem) {
        mToDoList.add(toDoItem);
        notifyItemInserted(mToDoList.size() - 1);
    }

    public void updateToDoListItemAtIndex(ToDoItem toDoItem, int index) {
        mToDoList.set(index, toDoItem);
        notifyItemChanged(index);
    }

    public void removeToDoItem(int position) {
        mListener.onToDoItemRemoved(mToDoList.get(position));
        mToDoList.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnClickListener {
        void onToDoItemClick(ToDoItem toDoItem, int position);

        void onToDoItemRemoved(ToDoItem toDoItem);
    }
}

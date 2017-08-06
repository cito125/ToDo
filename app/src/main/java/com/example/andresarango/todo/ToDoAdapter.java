package com.example.andresarango.todo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andresarango.todo.model.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

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
    public void onBindViewHolder(ToDoViewHolder holder, final int position) {
        holder.bind(mToDoList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onToDoItemClick(mToDoList.get(position),position);
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
        notifyItemInserted(index);
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

    public interface OnClickListener {
        void onToDoItemClick(ToDoItem toDoItem, int position);
    }
}

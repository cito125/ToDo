package com.example.andresarango.todo;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoRecyclerViewAdapter.ToDoViewHolder> {

    private final List<String> mToDoList = new ArrayList<>();

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        public ToDoViewHolder(View itemView) {
            super(itemView);
        }
    }
}

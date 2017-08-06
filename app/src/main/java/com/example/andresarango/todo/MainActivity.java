package com.example.andresarango.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.andresarango.todo.model.ToDoItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mToDoRecyclerView;
    private ToDoAdapter mToDoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.action_bar_title));

        mToDoRecyclerView = (RecyclerView) findViewById(R.id.to_do_recyclerview);
        mToDoAdapter = new ToDoAdapter();
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mToDoRecyclerView.setAdapter(mToDoAdapter);

        ToDoItem toDoListItem = new ToDoItem(mTitle);
        toDoListItem.setmTitle("YEEEER");
        mToDoAdapter.addToDoListItem(toDoListItem);



    }
}

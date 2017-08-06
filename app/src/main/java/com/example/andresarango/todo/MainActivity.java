package com.example.andresarango.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andresarango.todo.model.ToDoItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ToDoAdapter.OnClickListener {

    private RecyclerView mToDoRecyclerView;
    private ToDoAdapter mToDoAdapter;
    private Button mAddItemButton;
    private EditText mAddItemEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.action_bar_title));
        }

        initializeViews();
    }

    private void initializeViews() {
        mToDoRecyclerView = (RecyclerView) findViewById(R.id.to_do_recyclerview);
        mToDoAdapter = new ToDoAdapter(this);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mToDoRecyclerView.setAdapter(mToDoAdapter);
        mAddItemEditText = (EditText) findViewById(R.id.add_item_edittext);
        mAddItemButton = (Button) findViewById(R.id.add_item_button);
        mAddItemButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String text = mAddItemEditText.getText().toString();
        if (text.isEmpty()) {
            String toastMessage = "Please add a to do Item.";
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG).show();
        } else {
            mToDoAdapter.addToDoListItem(new ToDoItem(text));
        }
    }

    @Override
    public void onToDoItemClick(ToDoItem toDoItem, int position) {
        Toast.makeText(getApplicationContext(), toDoItem.getTitle(), Toast.LENGTH_LONG).show();
    }
}

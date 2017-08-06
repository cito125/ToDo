package com.example.andresarango.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andresarango.todo.model.ToDoItem;
import com.example.andresarango.todo.todo_adapter.ToDoAdapter;

import static com.example.andresarango.todo.EditItemActivity.TODO_ITEM_POSITION;
import static com.example.andresarango.todo.EditItemActivity.TODO_ITEM_TITLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ToDoAdapter.OnClickListener {

    private static final int TODO_ITEM_REQUEST_CODE = 1;
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

    @Override
    public void onClick(View v) {
        String text = mAddItemEditText.getText().toString();
        if (text.isEmpty()) {
            String toastMessage = getString(R.string.toast_for_not_entering_to_do_item);
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG).show();
        } else {
            mToDoAdapter.addToDoListItem(new ToDoItem(text));
        }
    }

    @Override
    public void onToDoItemClick(ToDoItem toDoItem, int position) {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra(TODO_ITEM_TITLE, toDoItem.getTitle());
        intent.putExtra(TODO_ITEM_POSITION, position);
        startActivityForResult(intent, TODO_ITEM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TODO_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String title = data.getStringExtra(TODO_ITEM_TITLE);
            int position = data.getIntExtra(TODO_ITEM_POSITION, -1);

            if (position > -1) {
                ToDoItem editedToDoItem = new ToDoItem(title);
                mToDoAdapter.updateToDoListItemAtIndex(editedToDoItem, position);
            }

        }
    }

    private void initializeViews() {
        mToDoRecyclerView = (RecyclerView) findViewById(R.id.to_do_recyclerview);
        mToDoAdapter = new ToDoAdapter(this);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mToDoRecyclerView.setAdapter(mToDoAdapter);
        mAddItemEditText = (EditText) findViewById(R.id.add_item_edit_text);
        mAddItemButton = (Button) findViewById(R.id.add_item_button);
        mAddItemButton.setOnClickListener(this);
    }
}

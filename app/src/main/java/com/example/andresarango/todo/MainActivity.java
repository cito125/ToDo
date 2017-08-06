package com.example.andresarango.todo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andresarango.todo.model.ToDoItem;
import com.example.andresarango.todo.todo_adapter.ToDoAdapter;

import nl.qbusict.cupboard.QueryResultIterable;

import static com.example.andresarango.todo.EditItemActivity.TODO_ITEM_ID;
import static com.example.andresarango.todo.EditItemActivity.TODO_ITEM_POSITION;
import static com.example.andresarango.todo.EditItemActivity.TODO_ITEM_TITLE;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ToDoAdapter.OnClickListener{

    private static final int TODO_ITEM_REQUEST_CODE = 1;
    private RecyclerView mToDoRecyclerView;
    private ToDoAdapter mToDoAdapter;
    private Button mAddItemButton;
    private EditText mAddItemEditText;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.action_bar_title));
        }

        ToDoItemDatabase databaseHelper = new ToDoItemDatabase(this);
        mDatabase = databaseHelper.getWritableDatabase();

        initializeViews();
        fillRecyclerView();
    }

    private void fillRecyclerView() {
        Cursor toDoItemsCursor = cupboard().withDatabase(mDatabase).query(ToDoItem.class).getCursor();
        try {
            QueryResultIterable<ToDoItem> iterable = cupboard().withCursor(toDoItemsCursor).iterate(ToDoItem.class);
            for (ToDoItem toDoItem : iterable) {
                mToDoAdapter.addToDoListItem(toDoItem);
            }
        } finally {
            toDoItemsCursor.close();
        }
    }

    @Override
    public void onClick(View v) {
        String text = mAddItemEditText.getText().toString();
        if (text.isEmpty()) {
            String toastMessage = getString(R.string.toast_for_not_entering_to_do_item);
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG).show();
        } else {
            addToDoItem(new ToDoItem(text));
        }
    }

    @Override
    public void onToDoItemClick(ToDoItem toDoItem, int position) {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra(TODO_ITEM_TITLE, toDoItem.getTitle());
        intent.putExtra(TODO_ITEM_POSITION, position);
        intent.putExtra(EditItemActivity.TODO_ITEM_ID, toDoItem._id);
        startActivityForResult(intent, TODO_ITEM_REQUEST_CODE);
    }

    @Override
    public void onToDoItemRemoved(ToDoItem toDoItem) {
        cupboard().withDatabase(mDatabase).delete(toDoItem);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TODO_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String title = data.getStringExtra(TODO_ITEM_TITLE);
            int position = data.getIntExtra(TODO_ITEM_POSITION, -1);

            if (position > -1) {
                ToDoItem editedToDoItem = new ToDoItem(title);
                editedToDoItem._id = data.getLongExtra(TODO_ITEM_ID, -10L);
                cupboard().withDatabase(mDatabase).put(editedToDoItem);
                mToDoAdapter.updateToDoListItemAtIndex(editedToDoItem, position);
            }

        }
    }

    private void initializeViews() {
        initializeRecyclewView();
        mAddItemEditText = (EditText) findViewById(R.id.add_item_edit_text);
        mAddItemButton = (Button) findViewById(R.id.add_item_button);
        mAddItemButton.setOnClickListener(this);
    }

    private void initializeRecyclewView() {
        mToDoRecyclerView = (RecyclerView) findViewById(R.id.to_do_recyclerview);
        mToDoAdapter = new ToDoAdapter(this);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mToDoRecyclerView.setAdapter(mToDoAdapter);
        ItemTouchHelper.SimpleCallback itemTouchCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mToDoAdapter.removeToDoItem(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        itemTouchHelper.attachToRecyclerView(mToDoRecyclerView);
    }

    private void addToDoItem(ToDoItem toDoItem) {
        cupboard().withDatabase(mDatabase).put(toDoItem);
        mToDoAdapter.addToDoListItem(toDoItem);
    }
}

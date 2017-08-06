package com.example.andresarango.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TODO_ITEM_TITLE = "Title of ToDo item";
    public static final String TODO_ITEM_POSITION = "Position of ToDo item in MainActivity.class recyclerview";
    public static final String TODO_ITEM_ID = "ToDo Item id";
    private EditText mToDoEditText;
    private Button mToDoSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.action_bar_title));
        }

        initializeViews();

        Intent intent = getIntent();
        String title = intent.getStringExtra(TODO_ITEM_TITLE);
        mToDoEditText.setText(title, TextView.BufferType.EDITABLE);
    }

    private void initializeViews() {
        mToDoEditText = (EditText) findViewById(R.id.edit_item_edit_text);
        mToDoSaveButton = (Button) findViewById(R.id.edit_item_save_button);
        mToDoSaveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String newTitleForToDoItem = mToDoEditText.getText().toString();
        if (newTitleForToDoItem.isEmpty()) {
            String toastMessage = getString(R.string.toast_for_not_entering_to_do_item);
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
            return;
        }

        String originalTitleForToDoItem = getIntent().getStringExtra(TODO_ITEM_TITLE);

        if (!originalTitleForToDoItem.equals(newTitleForToDoItem)) {
            Intent intent = new Intent();
            intent.putExtra(TODO_ITEM_TITLE, newTitleForToDoItem);
            intent.putExtra(TODO_ITEM_POSITION, getIntent().getIntExtra(TODO_ITEM_POSITION, -10));
            intent.putExtra(TODO_ITEM_ID, getIntent().getLongExtra(TODO_ITEM_ID, -10L));
            setResult(Activity.RESULT_OK, intent);
        }
        finish();

    }
}

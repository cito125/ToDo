package com.example.andresarango.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {

    public static final String TODO_ITEM_TITLE = "Title of ToDo item";
    public static final String TODO_ITEM_POSITION = "Position of ToDo item in MainActivity.class recyclerview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent intent = getIntent();
        String title = intent.getStringExtra(TODO_ITEM_TITLE);
        int position = intent.getIntExtra(TODO_ITEM_POSITION, -100);
    }
}

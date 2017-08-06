package com.example.andresarango.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andresarango.todo.model.ToDoItem;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class ToDoItemDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;

    public ToDoItemDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        cupboard().register(ToDoItem.class);
    }

    public SQLiteDatabase getDatabase(){
        return this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        cupboard().withDatabase(sqLiteDatabase).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        cupboard().withDatabase(sqLiteDatabase).upgradeTables();
    }
}

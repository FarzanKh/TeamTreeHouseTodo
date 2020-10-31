package com.example.teamtreehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TODO_TABLE = "TODO_TABLE";
    public static final String COLUMN_TODO_NAME = "TODO_NAME";
    public static final String COLUMN_ACTIVE_TODO = "ACTIVE_TODO";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "todo.db", null, 1);
    }

    // This is called the first time a database is accessed.. code to create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TODO_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TODO_NAME + " TEXT, " + COLUMN_ACTIVE_TODO + " BOOL)";
        db.execSQL(createTableStatement);

    }


    // This is called if the database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Todo todo) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TODO_NAME, todo.getName());
        cv.put(COLUMN_ACTIVE_TODO, todo.isActive());

        long insert = db.insert(TODO_TABLE, null, cv);
        return insert != -1;

    }


    public boolean deleteOne(Todo todoToDelete) {
        // find todoToDelete in the database. if it found, delete it and return true
        // if it is not found, return false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TODO_TABLE + " WHERE " + COLUMN_ID + " = " + todoToDelete.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();

    }


    public List<Todo> getEveryone() {
        List<Todo> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TODO_TABLE + " ORDER BY " + COLUMN_TODO_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through the cursor and create new row
            do {
                int todoID = cursor.getInt(0);
                String todoName = cursor.getString(1);
                boolean isActive = cursor.getInt(2) == 1 ? true: false;
                Todo newTodo = new Todo(todoID, todoName, isActive);
                returnList.add(newTodo);
            } while (cursor.moveToNext());
        } else {
            // we will not add anything to the list

        }
        cursor.close();
        db.close();
        return returnList;
    }
}

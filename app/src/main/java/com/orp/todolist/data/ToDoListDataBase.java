package com.orp.todolist.data;

import android.content.Context;

import com.orp.todolist.model.TaskModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//singleton design patterns to get one object only
//use room to save data in app when it offline
@Database(entities = {TaskModel.class} , exportSchema = false , version = 1)
public abstract class ToDoListDataBase extends RoomDatabase {
    private static ToDoListDataBase instance;

    public static synchronized ToDoListDataBase getInstance(Context context){
        if (instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(),ToDoListDataBase.class , "todo_list_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public static final ExecutorService dataBaseExecutor = Executors.newFixedThreadPool(4);

    public abstract ToDoListDao toDoListDao();
}

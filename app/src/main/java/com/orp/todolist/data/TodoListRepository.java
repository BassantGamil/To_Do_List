package com.orp.todolist.data;

import android.content.Context;

import com.orp.todolist.model.TaskModel;

import java.util.List;

public class TodoListRepository {
    ToDoListDataBase todoListDatabase;
    ToDoListDao todoListDao;

    public TodoListRepository(Context context) {
        todoListDatabase = ToDoListDataBase.getInstance(context);

        todoListDao = todoListDatabase.toDoListDao();
    }


    public void addNewTask(final TaskModel task) {
        ToDoListDataBase.dataBaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoListDao.addNewTask(task);
            }
        });
    }

    public List<TaskModel> getAllTasks(){
        return todoListDao.getAllTasks();
    }
}

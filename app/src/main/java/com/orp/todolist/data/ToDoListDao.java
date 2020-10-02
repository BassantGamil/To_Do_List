package com.orp.todolist.data;

import com.orp.todolist.model.TaskModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

//Data access object
@Dao
interface ToDoListDao {
    @Query("Select * from taskmodel")
    List<TaskModel> getAllTasks();

    @Insert
    long addNewTask(TaskModel task);
}

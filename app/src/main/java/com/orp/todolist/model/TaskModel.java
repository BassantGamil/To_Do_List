package com.orp.todolist.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class TaskModel {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String description;
    String date;
    String time;

    public TaskModel(int id, String name, String description, String date, String time) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
    }
    @Ignore
    public TaskModel(String name, String description, String date, String time) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

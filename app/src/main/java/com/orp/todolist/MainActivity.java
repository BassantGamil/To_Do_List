package com.orp.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orp.todolist.data.ToDoListDataBase;
import com.orp.todolist.data.TodoListRepository;
import com.orp.todolist.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TodoListRepository todoListRepository;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(this, new ArrayList<TaskModel>());

        recyclerView.setAdapter(taskAdapter);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddNewTask.class));
            }
        });
        todoListRepository = new TodoListRepository(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ToDoListDataBase.dataBaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final List<TaskModel> allTasks = todoListRepository.getAllTasks();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        taskAdapter.addData(allTasks);
                    }
                });
            }
        });//kotlin coroutines by kotlin
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
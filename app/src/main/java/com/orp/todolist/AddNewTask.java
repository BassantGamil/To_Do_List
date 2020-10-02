package com.orp.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.orp.todolist.data.TodoListRepository;
import com.orp.todolist.model.TaskModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNewTask extends AppCompatActivity {
    EditText nameEditTxt;
    EditText descriptionEditTxt;
    TextView dateTxtView;
    TextView timeTxtView;

    TodoListRepository todoListRepository;
    String selectedDate, selectedTime = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        nameEditTxt = (EditText) findViewById(R.id.name);
        descriptionEditTxt = (EditText) findViewById(R.id.description);
        dateTxtView = findViewById(R.id.date_txt_view);
        timeTxtView = findViewById(R.id.time_txt_view);

//        addDateBtn.findViewById(R.id.add_date_btn);
//        addDateBtn.setOnClickListener(this);
//
//        addTimeBtn.findViewById(R.id.add_time_btn);
//        addTimeBtn.setOnClickListener(this);
//
//
//        saveBtn.findViewById(R.id.save_btn);
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onSavedClicked();
//            }
//        });
        todoListRepository = new TodoListRepository(this);

    }

    public void onAddClickedDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        selectedDate = year + "/" + month + "/" + day;
                        dateTxtView.setText(selectedDate);
                        showToast("Your selected" + selectedDate);
                    }
                }, 2000, 1, 1);
        datePickerDialog.show();
    }


    public void onAddClickedTime(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        selectedTime = hour + ":" + minute;
                        timeTxtView.setText(selectedTime);
                        showToast("Selected time is" + selectedTime);
                    }
                }, 12, 00, true);
        timePickerDialog.show();
    }

    public void onSavedClicked(View view) {
        String name = nameEditTxt.getText().toString();
        String description = descriptionEditTxt.getText().toString();

        if (dataIsValid(name, description)) {
            TaskModel taskModel = new TaskModel(name, description, selectedDate, selectedTime);
            todoListRepository.addNewTask(taskModel);
            showToast("Task added successfully");
            startActivity(new Intent(this , MainActivity.class));
        } else {
            showErrorMessage("Please complete your data before saving");
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void showErrorMessage(String errorMessage) {
        Snackbar.make(findViewById(R.id.save_btn), errorMessage, Snackbar.LENGTH_LONG).show();
    }

    boolean dataIsValid(String name, String description) {
        if (name.isEmpty()) {
            nameEditTxt.setError("Name is required");
        }
        if (description.isEmpty()) {
            descriptionEditTxt.setError("Description is required");
        }
        return true;
    }
}

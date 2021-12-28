package com.nasr.todox;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {

    TodoViewModel todoViewModel;
    Button addTodoButton;
    private String dateText = "",titleText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTodoButton = findViewById(R.id.addTodoButton);

        addTodoButton.setOnClickListener(this);

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.todosList);

        TodoAdapter todoAdapter = new TodoAdapter();

        todoAdapter.context = this;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(todoAdapter);

        todoViewModel.todo.observe(this, new Observer<TodoItem>() {
            @Override
            public void onChanged(TodoItem todoItem) {
                System.out.println("get data here");
                todoAdapter.setNewData(todoItem);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addTodoButton)
        {
            dateText = "";
            titleText = "";
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("New Item");

// Set up the input
            final EditText input = new EditText(this);
            final Button button = new Button(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            button.setOnClickListener(this);

// Set up the buttons

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    titleText = input.getText().toString();

                    if(!titleText.equals("") && !dateText.equals(""))
                    {

                        todoViewModel.setNewData(new TodoItem(titleText,dateText));
                    }
                    else {
                        Toast.makeText(MainActivity.this, "You should choose date and write a title",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });

            builder.show();

            showDialog();

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void showDialog(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date =  dayOfMonth + "/" + month + "/" + year;
//        dateText.setText(date);
        dateText = date;

    }
}
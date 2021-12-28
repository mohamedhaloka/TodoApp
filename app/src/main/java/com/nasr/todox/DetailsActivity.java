package com.nasr.todox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {


    private TextView title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String todoTitle = extras.getString("todoTitle");
            String todoDesc = extras.getString("todoDate");


            title.setText(todoTitle);
            description.setText(todoDesc);

            //The key argument here must match that used in the other activity
        }



    }
}
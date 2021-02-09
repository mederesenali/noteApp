package com.example.my_noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.codinginflow.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.codinginflow.architectureexample.EXTRA_DESCRIPTION";


    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        button = findViewById(R.id.btnSave);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                if (title.trim().isEmpty() || description.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insert a title and description", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent data = new Intent();
                data.putExtra(EXTRA_TITLE, title);
                data.putExtra(EXTRA_DESCRIPTION, description);
                setResult(RESULT_OK, data);
                finish();

            }
        });
    }
}
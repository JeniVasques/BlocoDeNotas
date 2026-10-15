package com.example.blocodenotas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText editText = findViewById(R.id.editTextNote);
        Button buttonAdd = findViewById(R.id.buttonAddNote);

        buttonAdd.setOnClickListener(v -> {
            String newNote = editText.getText().toString().trim();
            if (!newNote.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("NEW_NOTE", newNote); // resposta no envelope
                setResult(RESULT_OK, resultIntent);         // "deu certo"
                finish();                                   // fecha e volta pra MainActivity
            }
        });
    }
}
package com.example.blocodenotas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter adapter;

    private List<String> items;
    private ActivityResultLauncher<Intent> addNoteLauncher; // o "jeito novo"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        Button addNoteButton = findViewById(R.id.add_button);

        // ANTES: items = new ArrayList<>();  (sempre começava vazio)
        // AGORA: carrega o que estava salvo
        items = NotesStorage.load(this);

        adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);

        // Registra ANTES o que fazer quando a AddNoteActivity responder
        addNoteLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String newNote = result.getData().getStringExtra("NEW_NOTE");
                        if (newNote != null) {
                            items.add(newNote);
                            adapter.notifyDataSetChanged();
                            NotesStorage.save(this, items); // ← salva!
                        }
                    }
                });

        addNoteButton.setOnClickListener(v ->
                addNoteLauncher.launch(new Intent(this, AddNoteActivity.class)));
    }
}
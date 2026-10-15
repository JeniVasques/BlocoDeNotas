package com.example.blocodenotas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_NOTE = 1; // "etiqueta" do pedido
    private MyAdapter adapter;
    private List<String> items;  // AQUI ficam as notas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // liga ao XML

        ListView listView = findViewById(R.id.listView);
        Button addNoteButton = findViewById(R.id.add_button);

        items = new ArrayList<>();
        adapter = new MyAdapter(this, items); // adapter recebe a lista
        listView.setAdapter(adapter);         // lista recebe o adapter

        addNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_NOTE); // "vai e me traz uma resposta"
        });
    }

    // Chamado automaticamente quando a AddNoteActivity fecha
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK && data != null) {
            String newNote = data.getStringExtra("NEW_NOTE");
            if (newNote != null) {
                items.add(newNote);              // 1. coloca na lista
                adapter.notifyDataSetChanged();  // 2. avisa a tela para redesenhar
            }
        }
    }
}
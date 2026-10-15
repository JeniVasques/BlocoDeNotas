package com.example.blocodenotas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> values; // a MESMA lista da MainActivity

    public MyAdapter(Context context, List<String> values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
    }

    // Chamado uma vez para CADA linha que aparece na tela
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) { // reaproveita linhas antigas (mais leve)
            rowView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView textView = rowView.findViewById(R.id.textView);
        Button openButton = rowView.findViewById(R.id.button);
        Button deleteButton = rowView.findViewById(R.id.delete_button);

        textView.setText(values.get(position));

        openButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("ITEM", values.get(position)); // coloca a nota no "envelope"
            context.startActivity(intent);
        });

        deleteButton.setOnClickListener(v -> {
            Toast.makeText(context, "Nota excluída", Toast.LENGTH_SHORT).show();
            values.remove(position);
            notifyDataSetChanged();
        });

        return rowView;
    }
}
package com.example.blocodenotas;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
public class NotesStorage {

    private static final String PREFS = "notas_prefs"; // nome do "caderninho"
    private static final String KEY = "notas";         // nome da "página"

    // Lista → texto JSON → guarda
    public static void save(Context context, List<String> notes) {
        JSONArray array = new JSONArray(notes);
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY, array.toString()).apply();
    }

    public static List<String> load(Context context) {
        List<String> notes = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY, "[]"); // "[]" = lista vazia, se for a 1ª vez

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                notes.add(array.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace(); // se o texto estiver corrompido, começa vazio
        }
        return notes;
    }

}

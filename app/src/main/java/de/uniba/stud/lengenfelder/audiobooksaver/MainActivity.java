package de.uniba.stud.lengenfelder.audiobooksaver;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AppDatabase db;
    private List<Audiobook> audiobooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());

        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Add Button was clicked!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // Create dummy list for testing
        audiobooks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            audiobooks.add(new Audiobook(i, "Audiobook " + i, "Descreption of audiobook " + i, "some.url.wtf"));
        }

        ListView mainListView = findViewById(R.id.mainListView);
        mainListView.setAdapter(new AudiobooksAdapter(this, audiobooks));

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getApplicationContext(), "Item " + position + " was clicked!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}

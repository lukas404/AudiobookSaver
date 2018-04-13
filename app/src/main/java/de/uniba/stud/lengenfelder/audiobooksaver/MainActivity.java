package de.uniba.stud.lengenfelder.audiobooksaver;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());

        audiobooks = new ArrayList<>();
        audiobooks = db.audiobookDao().getAll();

        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAudiobook = new Intent(getApplicationContext(), AddActivity.class);
                addAudiobook.putParcelableArrayListExtra("AUDIOBOOKS_LIST", (ArrayList<? extends Parcelable>) audiobooks);
                startActivity(addAudiobook);
            }
        });

        mainListView = findViewById(R.id.mainListView);
        mainListView.setAdapter(new AudiobooksAdapter(this, audiobooks));

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getApplicationContext(), "Item " + position + " was clicked!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        db.audiobookDao().insertAllAudiobooks(audiobooks);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().hasExtra("AUDIOBOOKS_LIST")) {
            audiobooks = getIntent().getParcelableArrayListExtra("AUDIOBOOKS_LIST");
            mainListView.setAdapter(new AudiobooksAdapter(this, audiobooks));
            Toast confirmAdd = Toast.makeText(getApplicationContext(), "New audiobook was added!", Toast.LENGTH_SHORT);
            confirmAdd.show();
        }

    }
}

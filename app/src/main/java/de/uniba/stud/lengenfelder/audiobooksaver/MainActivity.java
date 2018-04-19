package de.uniba.stud.lengenfelder.audiobooksaver;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
                startActivity(addAudiobook);
            }
        });

        mainListView = findViewById(R.id.mainListView);
        mainListView.setAdapter(new AudiobooksAdapter(this, audiobooks));

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String uri = audiobooks.get(position).getUri();
                if (uri != null) {
                    Intent resumeListening = new Intent(Intent.ACTION_VIEW);
                    resumeListening.setData(Uri.parse(uri));
                    startActivity(resumeListening);
                    Toast.makeText(getApplicationContext(), uri, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No progress saved for this audiobook!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData uriInClipboard = getApplicationContext().getSystemService(ClipboardManager.class).getPrimaryClip();

                Audiobook clicked = (Audiobook) parent.getItemAtPosition(position);
                clicked.setUri(uriInClipboard.getItemAt(0).getText().toString());
                db.audiobookDao().updateUri(clicked);

                Toast.makeText(getApplicationContext(), "Progress successfully saved!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateListView();
    }

    private void updateListView() {
        audiobooks = db.audiobookDao().getAll();
        mainListView.setAdapter(new AudiobooksAdapter(this, audiobooks));
    }
}

package de.uniba.stud.lengenfelder.audiobooksaver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = AppDatabase.getInstance(getApplicationContext());
        setContentView(R.layout.activity_add);

        final EditText titleEditText = findViewById(R.id.titleEditText);
        final EditText descEditText = findViewById(R.id.descEditText);
        Button addBtn = findViewById(R.id.confirmBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Audiobook newBook = new Audiobook(titleEditText.getText().toString(), descEditText.getText().toString(), null);
                db.audiobookDao().insertAudiobook(newBook);
                Toast confirmAdd = Toast.makeText(getApplicationContext(), "List was updated!", Toast.LENGTH_SHORT);
                confirmAdd.show();
                finish();
            }
        });
    }
}

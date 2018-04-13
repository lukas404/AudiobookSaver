package de.uniba.stud.lengenfelder.audiobooksaver;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    private List<Audiobook> audiobooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText titleEditText = findViewById(R.id.titleEditText);
        final EditText descEditText = findViewById(R.id.descEditText);
        Button addBtn = findViewById(R.id.confirmBtn);

        audiobooks = getIntent().getParcelableArrayListExtra("AUDIOBOOKS_LIST");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Audiobook newBook = new Audiobook(audiobooks.size() + 1, titleEditText.getText().toString(), descEditText.getText().toString(), null);
                audiobooks.add(newBook);
                Intent backToList = new Intent(getApplicationContext(), MainActivity.class);
                backToList.putParcelableArrayListExtra("AUDIOBOOKS_LIST", (ArrayList<? extends Parcelable>) audiobooks);
                startActivity(backToList);
            }
        });
    }
}

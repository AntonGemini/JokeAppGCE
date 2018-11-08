package com.udacity.jokepresenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        ListView jokeList = findViewById(R.id.lv_jokelist);
        Intent intent = getIntent();
        ArrayList<String> jokes = intent.getExtras().getStringArrayList("jokes");
        JokeAdapter adapter = new JokeAdapter(this, jokes);
        jokeList.setAdapter(adapter);

        //jokeText.setText(joke);
    }
}

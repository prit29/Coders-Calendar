package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.noobsever.codingcontests.R;

public class SuggestUsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView suggestion_text;
    MaterialButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_us);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Suggest Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        suggestion_text = findViewById(R.id.suggestion_text);
        send = findViewById(R.id.send_suggestion);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("suggestion>>", suggestion_text.getText().toString());
                suggestion_text.setText("");
            }
        });
    }
}
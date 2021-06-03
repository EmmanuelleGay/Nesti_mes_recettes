package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText editText = findViewById(R.id.search_editTxt_recipe);
        Button btnSearchOk =  findViewById(R.id.search_btn_ok);

        String token = getIntent().getStringExtra("token");

        btnSearchOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String term = editText.getText().toString();
                Intent intent = new Intent(SearchActivity.this, ResultActivity.class);

                //add extra "term"
                intent.putExtra("term", term);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
    }
}
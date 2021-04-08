package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class EasyActivity extends AppCompatActivity {

    public String[] easyRecipes = {
            "Gateau au yaourt",
            "Crèpes",
            "Muffins",
            "Cookies"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        final Button btnBack = (Button) findViewById(R.id.easy_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyActivity.this.finish();
            }
        });

        //LIST VIEW : on attrape le composant :
        ListView listView = findViewById(R.id.easy_listview);
        //on crée un adpatateur pour faire le lien entre la donnée et la listView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, this.easyRecipes);
        // on relie les deux
        listView.setAdapter(adapter);

        //évènement sur chaque élément => methode lambda
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Position:" + String.valueOf(position) + ", Recette :" + easyRecipes[position],
                    Toast.LENGTH_SHORT).show();
        })
        ;
    }
    ;
}

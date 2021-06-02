package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.IngredientAdapter;
import adapter.ResultAdapter;
import entity.Ingredient;
import entity.Recipe;
import model.IngredientViewModel;
import model.ResultViewModel;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //reception du terme a rechercher
        String term = this.getIntent().getStringExtra("term");

        //modification du textView
        TextView textView = findViewById(R.id.result_txtView_term);
        textView.setText(term);

        ResultViewModel resultViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(ResultViewModel.class);
        resultViewModel.getRecipes(term).observe(this, recipes -> {

        ListView list_view = (ListView) findViewById(R.id.result_listView);
        ResultAdapter resultAdapter = new ResultAdapter(this,R.layout.line_ingredient,(ArrayList<Recipe>) recipes);
        list_view.setAdapter(resultAdapter);


            });


    }
}


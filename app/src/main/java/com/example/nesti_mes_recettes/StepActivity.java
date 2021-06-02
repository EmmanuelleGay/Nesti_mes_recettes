package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapter.IngredientAdapter;
import adapter.StepAdapter;
import entity.Ingredient;
import entity.Step;
import model.IngredientViewModel;
import model.StepViewModel;

public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        final String recipeId = getIntent().getStringExtra("recipeId");

        // pour faire appel au model
        StepViewModel stepViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(StepViewModel.class);

        stepViewModel.getSteps(recipeId). observe(this, steps ->

        {
            ListView list_view = (ListView) findViewById(R.id.prepation_listView);
            StepAdapter stepAdapter = new StepAdapter(this,R.layout.line_preparation,(ArrayList<Step>) steps);
            list_view.setAdapter(stepAdapter);

        });;


        final Button btnIngredient = (Button) findViewById(R.id.goToIngredient_btn);
        btnIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepActivity.this.finish();
            }
        });

        final FloatingActionButton btnShoppingCart = (FloatingActionButton) findViewById(R.id.prep_ShoppingCart_btn);
        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StepActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }
}
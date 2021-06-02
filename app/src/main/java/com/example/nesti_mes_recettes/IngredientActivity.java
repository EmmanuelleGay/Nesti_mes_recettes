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
import entity.Ingredient;
import entity.Recipe;
import model.IngredientViewModel;

public class IngredientActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        //je la récupère car envoyée depuis gluten activity
        final String recipeId = getIntent().getStringExtra("recipeId");

        // pour faire appel au model
        IngredientViewModel ingViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(IngredientViewModel.class);

        ingViewModel.getIngredients(recipeId).observe(this, ingredients ->

        {
            ListView list_view = (ListView) findViewById(R.id.ingredient_listView);
            IngredientAdapter ingredientAdapter = new IngredientAdapter(this, R.layout.line_ingredient, (ArrayList<Ingredient>) ingredients);
            list_view.setAdapter(ingredientAdapter);

            list_view.setOnItemClickListener(((parent, view, position, id) -> {
                //to do new intent pour aller ??
            }));

        });
        ;

        final Button btnPreparation = (Button) findViewById(R.id.goToPreparation_btn);
        btnPreparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngredientActivity.this, StepActivity.class);
                //on envoie l'id
                intent.putExtra("recipeId", recipeId);
                startActivity(intent);
            }
        });

        final FloatingActionButton btnShoppingCart = (FloatingActionButton) findViewById(R.id.ing_shoppingCart_btn);
        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngredientActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

    }

    ;

}

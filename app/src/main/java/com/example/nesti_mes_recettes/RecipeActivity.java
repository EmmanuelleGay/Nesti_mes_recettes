package com.example.nesti_mes_recettes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;
import model.GlutenViewModel;

public class RecipeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gluten);

        // Création du model : si création d'un nouveau model, adapter le nom de la classe du modele
        GlutenViewModel viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(GlutenViewModel.class);

        viewModel.getRecipes().observe(this, recipes -> {
            // Attraper le composant ListView
            ListView list_view = (ListView) findViewById(R.id.gluten_listView);
            // Créer un adaptateur
            RecipeAdapter adapter = new RecipeAdapter(
                    this,
                    R.layout.line_recipe,
                    (ArrayList<Recipe>) recipes);
            // Attacher l’adaptateur au composant
            list_view.setAdapter(adapter);

            list_view.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(RecipeActivity.this, IngredientActivity.class);
                    // on récupére la recette en cours
                final Recipe recipe = (Recipe) parent.getItemAtPosition(position);

                //on envoie l'id
                intent.putExtra("recipeId", String.valueOf(recipe.getIdRecipe()));
                startActivity(intent);

        //        break;

            });
        });


        final Button btnBack = (Button) findViewById(R.id.gluten_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeActivity.this.finish();
            }
        });

        //    requestApi();
    }




}
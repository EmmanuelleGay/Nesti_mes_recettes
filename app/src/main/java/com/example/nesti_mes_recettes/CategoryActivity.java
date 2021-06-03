package com.example.nesti_mes_recettes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;
import model.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String tagName =  getIntent().getStringExtra("tagName");

        int titleId =  getResources().getIdentifier("txt_title_"+ tagName,"string", getApplicationContext().getPackageName());

        TextView textViewTitle = (TextView) findViewById(R.id.category_txt_title);
        textViewTitle.setText(titleId);

        // Création du model : si création d'un nouveau model, adapter le nom de la classe du modele
        CategoryViewModel viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(CategoryViewModel.class);

        viewModel.setCategoryName(tagName);
        viewModel.getRecipes().observe(this, recipes -> {
            // Attraper le composant ListView
            ListView list_view = (ListView) findViewById(R.id.category_listView);
            // Créer un adaptateur
            RecipeAdapter adapter = new RecipeAdapter(
                    this,
                    R.layout.line_recipe,
                    (ArrayList<Recipe>) recipes);
            // Attacher l’adaptateur au composant
            list_view.setAdapter(adapter);

            list_view.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(CategoryActivity.this, IngredientActivity.class);
                    // on récupére la recette en cours
                final Recipe recipe = (Recipe) parent.getItemAtPosition(position);

                //on envoie l'id
                intent.putExtra("recipeId", String.valueOf(recipe.getIdRecipe()));
                startActivity(intent);
        //        break;

            });
        });


        final Button btnBack = (Button) findViewById(R.id.category_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryActivity.this.finish();
            }
        });

        //    requestApi();
    }


}
package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;

public class SeasonnalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasonnal);

        final Button btnBack = (Button) findViewById(R.id.seasonnal_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeasonnalActivity.this.finish();
            }
        });

        ArrayList<Recipe> seasonnalRecipes = readJSONRecipe("season.json");
        Log.i("LogNesti", "Tableau recu : " + seasonnalRecipes.size());
        ListView listView = (ListView) findViewById(R.id.seasonnal_listView);
        RecipeAdapter adapter = new RecipeAdapter(this, R.layout.line_recipe, seasonnalRecipes);
        listView.setAdapter(adapter);
    }

    /**
     * read Json and return string
     *
     * @param pFileJsonName
     * @return String Json
     */
    private String readJson(String pFileJsonName) {
        //traitement du Json
        // init
        StringBuilder builder = new StringBuilder();
        AssetManager asset_manager = this.getAssets();
        InputStreamReader isr;

        BufferedReader data;

        try {
            isr = new InputStreamReader(asset_manager.open(pFileJsonName));
            data = new BufferedReader(isr);
            String line;
            while ((line = data.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            Log.e("LogNesti", "Erreur de lecture du Json");
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * take JSon and return array of object Recipe
     *
     * @param pFileJsonName
     * @return
     */
    private ArrayList<Recipe> readJSONRecipe(String pFileJsonName) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        String stringJSON = this.readJson(pFileJsonName);

        try {
            JSONArray arrayJson = new JSONArray(stringJSON);
            Log.i("LogNesti", "Nbre d'enregistrement : " + arrayJson.length());
            //on parcourt le tableau
            for (int i = 0; i < arrayJson.length(); i++) {
                JSONObject objectJson = arrayJson.getJSONObject(i);

                Recipe r = new Recipe();
                r.setCat(objectJson.getString("cat"));
                r.setTitle(objectJson.getString("title"));
                r.setAuthor(objectJson.getString("author"));

                int img = this.getResourceImage(objectJson.getString("img"));
                r.setImgId(img);
                int s = this.getResourceImage("star_" + objectJson.getInt("difficulty"));
                r.setRating(s);

                recipes.add(r);
            }
        } catch (Exception e) {
            Log.e("LogNesti", "Erreur de convertion du JSON");
            e.printStackTrace();
        }
        return recipes;

    }

    private int getResourceImage(String nameImage) {
        String path = getPackageName() + ":drawable/" + nameImage;
        return getResources().getIdentifier(path, null, null);
    }

}

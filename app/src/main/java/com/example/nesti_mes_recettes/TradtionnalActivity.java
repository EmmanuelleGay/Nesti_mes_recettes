package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;

public class TradtionnalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tradtionnal);

        final Button btnBack = (Button) findViewById(R.id.traditionnal_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TradtionnalActivity.this.finish();
            }
        });

        //tableau recettes traditionnelles
        // on lit les infos du fichiers xml (cf méthode ci dessosus qui parse les éléments
        ArrayList<Recipe> traditionnal_recipes = readXmlRecipes(R.xml.list_traditionnal);

      /*  Recipe r1 = new Recipe();
        r1.setCat("tradition");
        r1.setTitle("Macarons");
        r1.setAuthor("Cédric Grolet");
        r1.setImgId(this.getResourceImage("r_macarons"));

        traditionnal_recipes.add(r1);

        Recipe r2 = new Recipe();
        r2.setCat("tradition");
        r2.setTitle("Eclair au chocolat");
        r2.setAuthor("Philippe Conticini");
        r2.setImgId(this.getResourceImage("r_eclair_chocolat"));

        traditionnal_recipes.add(r2);
        */


        ListView list_view = (ListView) findViewById(R.id.traditionnal_listView);
        //  ArrayAdapter<Recipe> adapter = new ArrayAdapter<Recipe>(this,android.R.layout.simple_list_item_single_choice,traditionnal_recipes);
        RecipeAdapter adapter = new RecipeAdapter(this, android.R.layout.simple_list_item_1, traditionnal_recipes);
        list_view.setAdapter(adapter);

    }

    /**
     * return id of an image
     *
     * @param nameImage
     * @return
     */
    private int getResourceImage(String nameImage) {
        String path = getPackageName() + ":drawable/" + nameImage;
        return getResources().getIdentifier(path, null, null);
    }

    private ArrayList<Recipe> readXmlRecipes(int plistId) {
        XmlPullParser xmlPullParser = this.getResources().getXml(plistId);
        ArrayList<Recipe> listRecipe = new ArrayList<>();
        try {
            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if ( xmlPullParser.getEventType() == XmlPullParser.START_TAG){
                    if (xmlPullParser.getName().equals("recipe")) {
                        Recipe r = new Recipe();
                        r.setCat(xmlPullParser.getAttributeValue(null, "cat"));
                        r.setTitle(xmlPullParser.getAttributeValue(null, "title"));
                        r.setAuthor(xmlPullParser.getAttributeValue(null, "author"));
                        int i = this.getResourceImage(xmlPullParser.getAttributeValue(null,"img"));
                        r.setImgId(i);
                        int s = this.getResourceImage(xmlPullParser.getAttributeValue(null,"difficulty"));
                        r.setRating(s);

                        //alimentation de la liste
                        listRecipe.add(r);
                    }
                }
                //enregistrement suivant
                xmlPullParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            Log.e("LogNesti", "Erreur lecture XML : " + e.getMessage());
            e.printStackTrace();
        }
        return listRecipe;
    }
}
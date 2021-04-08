package model;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nesti_mes_recettes.GlutenActivity;
import com.example.nesti_mes_recettes.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.RecipeAdapter;
import entity.Recipe;


public class GlutenViewModel extends AndroidViewModel {

    public GlutenViewModel(@NonNull Application application) {

        super(application);
    }

    //attribut recettes est une liste d'objets
    private MutableLiveData<List<Recipe>> recipes;


    //Si les données des recettes n'existent pas (sont nulles), alors on les instancie et on les remplit
    public LiveData<List<Recipe>> getRecipes() {
        if (recipes == null) {
            recipes = new MutableLiveData<List<Recipe>>();
            loadRecipes();
        }
        return recipes;
    }

    private void loadRecipes() {
        String url = "http://10.0.2.2/www/PHP/NestiECommerce/public/api/category/glutenFree";
        requestApi(url);
    }

    private void requestApi(String url) {
        // String url = "http://192.168.1.14/www/PHP/NestiECommerce/public/api/category/glutenFree";
        //adresee pour emulateur :
   //     String url = "http://10.0.2.2/www/PHP/NestiECommerce/public/api/category/glutenFree";
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                   //     GlutenActivity glt = new GlutenActivity();
                  //      glt.showJson(response);
                        JsonArrayToRecipes(response);
                        Log.i("NestiLog", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication().getApplicationContext(),
                                "Une erreur est survenue à l'interrogation de l'API",
                                Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        );
        request_queue.add(array_request);
    }

    /**
     * @param jsonArray
     */
    public void JsonArrayToRecipes(JSONArray jsonArray) {
        // ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.setValue(new ArrayList<>());
        try {
            Log.i("LogNesti", "Nombre d'enregistrements :" + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Recipe r = new Recipe();
                r.setTitle(jsonObject.getString("title"));
                r.setRating(
                        this.getResourceImageIdentifier("star_" + jsonObject.getInt("difficulty"))
                );
                r.setAuthor(jsonObject.getString("author"));
                r.setImgId(
                        this.getResourceImageIdentifier(jsonObject.getString("img"))
                );
                recipes.getValue().add(r);
            }
        } catch (Exception e) {
            Log.e("LogNesti", "Erreur de conversion du Json");
            e.printStackTrace();
        }


  //      ListView listView = (ListView) getApplication().findViewById(R.id.gluten_listView);

 //       RecipeAdapter adapter = new RecipeAdapter(this, R.layout.line_recipe, recipes);
//        listView.setAdapter(adapter);
    }



    private int getResourceImageIdentifier(String nameImage) {
        String path = getApplication().getPackageName() + ":drawable/" + nameImage;
        return getApplication().getResources().getIdentifier(path, null, null);
    }

}

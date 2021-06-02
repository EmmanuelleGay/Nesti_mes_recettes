package model;

import android.app.Application;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.Ingredient;
import entity.Recipe;

public class ResultViewModel extends AndroidViewModel {

    public ResultViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<Recipe>> recipes;

    public LiveData<List<Recipe>> getRecipes(String search) {
        if (recipes == null) {
            recipes = new MutableLiveData<List<Recipe>>();
            loadRecipes(search);
        }
        return recipes;
    }


    private void loadRecipes(String search){
        String url = "http://10.0.2.2/www/PHP/NestiECommerce/public/api/search/" + search ;
        requestApi(url);
    }

    public void requestApi(String url){
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JsonArrayToRecipe(response);
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
    public void JsonArrayToRecipe(JSONArray jsonArray) {

        recipes.setValue(new ArrayList<>());
        try {
            Log.i("LogNesti", "Nombre d'enregistrements :" + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Recipe recipe = new Recipe();
                recipe.setIdRecipe(jsonObject.getString("idRecipe"));
                recipe.setTitle(jsonObject.getString("name"));
                recipes.getValue().add(recipe);
            }
        } catch (Exception e) {
            Log.e("LogNesti", "Erreur de conversion du Json Ingredient");
            e.printStackTrace();
        }

    }

}

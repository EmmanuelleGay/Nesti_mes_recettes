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
import entity.Step;

public class StepViewModel extends AndroidViewModel {

    public StepViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<Step>> steps;


    public LiveData<List<Step>> getSteps(String idRecipe) {
        if (steps == null) {
            steps = new MutableLiveData<List<Step>>();
            loadIngredients(idRecipe);
        }
        return steps;
    }

    private void loadIngredients(String idRecipe){
        String url = "http://10.0.2.2/www/PHP/NestiECommerce/public/api/recipes/" + idRecipe + "/step";
        requestApi(url);
    }

    public void requestApi(String url){
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JsonArrayToSteps(response);
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
    public void JsonArrayToSteps(JSONArray jsonArray) {

        steps.setValue(new ArrayList<>());
        try {
            Log.i("LogNesti", "Nombre d'enregistrements :" + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Step step = new Step();
                step.setOrderParagraph(String.valueOf(jsonObject.getInt("order")+1));
                step.setContentParagraph(jsonObject.getString("paragraph"));

                steps.getValue().add(step);
            }
        } catch (Exception e) {
            Log.e("LogNesti", "Erreur de conversion du Json Ingredient");
            e.printStackTrace();
        }

    }
}

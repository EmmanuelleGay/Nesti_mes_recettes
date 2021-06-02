package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;

import java.util.ArrayList;

import entity.Recipe;

public class ResultAdapter extends ArrayAdapter<Recipe> {
    public ResultAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Recipe> recipes){
        super(context, textViewResourceId, recipes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View result = convertView;
        if(convertView==null){
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_result_search, parent,false);
        }
        Recipe recipe = getItem(position);

        TextView nameRecipe = result.findViewById(R.id.lineResult_txtView_nameRecipe);
        nameRecipe.setText(recipe.getTitle());

        return result;
    }
}

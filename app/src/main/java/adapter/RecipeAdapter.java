package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;

import java.util.ArrayList;

import entity.Recipe;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Recipe> recipes) {
        super(context, textViewResourceId, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View result = convertView;
        if(convertView==null){
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_recipe, parent,false);
        }
        Recipe one_recipe = getItem(position);

        //assigner au textView la valeur obtenue
        TextView title = result.findViewById(R.id.line_recipe_txtView_title);
        title.setText(one_recipe.getTitle());

        TextView author = result.findViewById(R.id.line_recipe_txtView_author);
        author.setText(one_recipe.getAuthor());

        ImageView imageView = result.findViewById(R.id.line_recipe_imageView_recipe);
        imageView.setImageResource(one_recipe.getImgId());

        ImageView starView = result.findViewById(R.id.line_recipe_imageView_star);
        starView.setImageResource(one_recipe.getRating());

        return result;
    }

}

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

import entity.Step;

public class StepAdapter extends ArrayAdapter<Step> {

        public StepAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Step> steps) {
                super(context, textViewResourceId, steps);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View result = convertView;
                if (convertView == null) {
                        result = LayoutInflater.from(getContext()).inflate(R.layout.line_preparation, parent, false);
                }
                Step one_step = getItem(position);

                TextView orderStep = result.findViewById(R.id.line_preparation_txt_order);
                orderStep.setText(one_step.getOrderParagraph());

                TextView contentStep = result.findViewById(R.id.line_preparation_txt_content);
                contentStep.setText(one_step.getContentParagraph());

                return result;
        }

}




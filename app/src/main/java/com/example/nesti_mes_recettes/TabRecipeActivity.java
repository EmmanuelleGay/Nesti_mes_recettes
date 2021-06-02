package com.example.nesti_mes_recettes;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListView;

import com.example.nesti_mes_recettes.ui.main.SectionsPagerAdapter;

public class TabRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_recipe);
        // ca crée un adapter
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view,
//                        PageViewModel viewModel = new ViewModelProvider()
//                        .AndroidViewModelFactory(this.getApplication())
//                        .create(PageViewModel.class);
//
//                viewModel.getIngredient.observe(this, ingredient -> {
//                    ListView list_view = (ListView) findViewById(R.id.fragment_listView_details);
//                    IngredientAdapter adapter = new IngredientAdapter(
//                            this,
//                            R.layout.line_ingredient,
//                            (ArrayList<Ingredient>) ingredient);
//                    // Attacher l’adaptateur au composant
//                    list_view.setAdapter(adapter);
//
//                });
//
//
//
//                        "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ListView listView = findViewById(R.id.fragment_listView_details);


    }

}
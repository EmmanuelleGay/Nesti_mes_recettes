package com.example.nesti_mes_recettes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //   Gestion du bouton associé à la ressource bt_easy
        final Button btnEasy = (Button) findViewById(R.id.main_btn_easy);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //demande confirmation avec action
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Facile à faire");
                alertDialog.setMessage("Voulez vous choisir une recette facile à faire?");
                //confirmation oui
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            //affiche msg
                            Toast.makeText(getApplicationContext(), btnEasy.getText(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, EasyActivity.class);
                            startActivity(intent);
                        }
                });
                // confirmation non
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });

        final Button btnTraditionnal = (Button) findViewById(R.id.main_btn_traditionnal);
        btnTraditionnal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Traditionnelle");
                alertDialog.setMessage("Voulez vous choisir une recette traditionnelle?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Toast.makeText(getApplicationContext(), btnTraditionnal.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, TradtionnalActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });
        final Button btnSeason = (Button) findViewById(R.id.main_btn_season);
        btnSeason.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View v) {
      //          Toast toast_season = Toast.makeText(getApplicationContext(), btnSeason.getText(), Toast.LENGTH_SHORT);
      //          toast_season.show();
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("C'est de saison");
                alertDialog.setMessage("Voulez vous choisir une recette de saison?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Toast.makeText(getApplicationContext(), btnSeason.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SeasonnalActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });
        final Button btnfreeGluten = (Button) findViewById(R.id.main_btn_gluten);
        btnfreeGluten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Sans gluten");
                    alertDialog.setMessage("Voulez vous choisir une recette sans gluten?");
                    alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            Toast.makeText(getApplicationContext(), btnfreeGluten.getText(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, GlutenActivity.class);
                            startActivity(intent);
                        }
                    });
                    alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //rien
                        }
                    });
                    alertDialog.show();
                }
        });
    }
            /**
             * Gestion ouverture/fermeture du menu general
             *
             * @param pMenu Menu
             * @return boolean true
             */
            @Override
            public boolean onCreateOptionsMenu(Menu pMenu) {
                MenuInflater inflater = this.getMenuInflater();
                inflater.inflate(R.menu.menu_general, pMenu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(@NonNull MenuItem pItem) {
                switch (pItem.getItemId()) {
                    case R.id.menu_search:
                        //   Log.i("LogNesti", "Menu : Recherche");
                        Intent searchIntent = new Intent (MainActivity.this, SearchActivity.class);
                        startActivity(searchIntent);
                        Toast t_search = Toast.makeText(this, "Menu : Recherche", Toast.LENGTH_SHORT);
                        t_search.show();
                        break;
                    case R.id.menu_list:
                        //      Log.i("LogNesti", "Menu : Liste de course");
                        Toast t_list = Toast.makeText(this, "Menu : liste de course", Toast.LENGTH_SHORT);
                        t_list.show();
                        break;
                    case R.id.menu_contact:
                        Intent contactIntent = new Intent(MainActivity.this, TabRecipeActivity.class);
                        startActivity(contactIntent);
                        Toast t_contact = Toast.makeText(this, "Menu : contact", Toast.LENGTH_SHORT);
                        t_contact.show();
                        break;
                    case R.id.menu_team:
                        Toast t_team = Toast.makeText(this, "Menu : équipe", Toast.LENGTH_SHORT);
                        t_team.show();
                        break;
                    case R.id.menu_project:
                        Toast t_project = Toast.makeText(this, "Menu : projet", Toast.LENGTH_SHORT);
                        t_project.show();
                        break;
                }
                return true;
            }
        }
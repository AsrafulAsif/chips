package com.example.tab_dataui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    // initialize variables
    TextView textView,textView2;
    boolean[] selectedProduct;
    ArrayList<Integer> langList = new ArrayList<>();
    String[] langArray = {"Potol","Alu","Kumra","begun","lau","Potol","Alu","Kumra","begun","lau","Potol","Alu","Kumra","begun","lau","Potol","Alu","Kumra","begun","lau"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        // assign variable
        textView = findViewById(R.id.textView);
//        textView2 = findViewById(R.id.textView2);



        // initialize selected language array
        selectedProduct = new boolean[langArray.length];

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // set title
                builder.setTitle("Select Product");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(langArray, selectedProduct, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            langList.add(i);
                            // Sort array list
                            Collections.sort(langList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            langList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j < langList.size(); j++) {

                            stringBuilder.append(langArray[langList.get(j)]);

                            if (j != langList.size() - 1) {

                                stringBuilder.append(", ");
                            }
                        }

                        textView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        for (int j = 0; j < selectedProduct.length; j++) {
                            selectedProduct[j] = false;
                            langList.clear();
                            textView.setText("");
                        }
                    }
                });

                // show dialog
                builder.show();
            }
        });


        //for loop

        for (String text:langArray){
            ChipGroup chipGroup = findViewById(R.id.chip_group_main);
            Chip chip = new Chip(this); //your newly created chip
            chip.setText(text);
            chipGroup.addView(chip);
        }




    }
}
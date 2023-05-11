package com.example.project;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Activity.CartActivity;
import com.example.project.Activity.MainActivity2;
import com.example.project.Activity.ShowDetailActivity;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    SearchView mySearchView;
    ListView myList;
    ArrayList<String> List;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        mySearchView = (SearchView) findViewById(R.id.searchView);

        myList = (ListView) findViewById(R.id.myList);

        List = new ArrayList<String>();
        List.add("ChocoBrownie");
        List.add("Green kulfi");
        List.add("Crust Cone");
        List.add("Rainbow Scoop");
        List.add("Oreo Cream");
        List.add("Choco Cone");
        List.add("Cornetto");


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List);

        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });



    }


}

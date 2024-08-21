package com.example.resturantadmin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodItems extends AppCompatActivity {

    private ArrayList<Food> foods;
    private FoodAdapter adapter;
    private RecyclerView foodRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);

        foodRecycler = findViewById(R.id.foodRecycler);
        foodRecycler.setLayoutManager(new LinearLayoutManager(this));

        foods = (ArrayList<Food>) getIntent().getSerializableExtra("foods");

        adapter = new FoodAdapter(this, foods);
        foodRecycler.setAdapter(adapter);
    }
}

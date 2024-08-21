package com.example.resturantadmin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<Food> foods;
    private Context context;

    public FoodAdapter(Context context, ArrayList<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_details_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food foodItem = foods.get(position);
        holder.foodName.setText(foodItem.getName());
        holder.foodPrice.setText(String.valueOf(foodItem.getPrice()));

        // Convert byte array to Bitmap
        byte[] imageBytes = foodItem.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.foodImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView foodName;
        public TextView foodPrice;
        public ImageView foodImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodPrice = itemView.findViewById(R.id.food_Price);
            foodImage = itemView.findViewById(R.id.food_image);
        }
    }
}

package com.example.fblaschoolapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Food> foods;

    public Adapter (Context ctx, List<Food> foods )
    {
        this.inflater = LayoutInflater.from(ctx);
        this.foods = foods;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //binding the data
        holder.foodTitle.setText(foods.get(position).getFoodTitle());
        holder.calories.setText(foods.get(position).getCalories());
        Picasso.get().load(foods.get(position).getImageURL()).into(holder.coverImage);

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodTitle,calories;
        ImageView coverImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodTitle = itemView.findViewById(R.id.txtFoodItem);
            calories = itemView.findViewById(R.id.txtCalories);
            coverImage = itemView.findViewById(R.id.imgViewList);

        }
    }
}

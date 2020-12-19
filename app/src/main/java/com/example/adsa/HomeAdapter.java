package com.example.adsa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<String> itemNames;
    private final ArrayList<Integer> itemImages;
    private final ArrayList<Activity> itemActivities;
    private final Context context;

    public HomeAdapter(Context context, ArrayList<String> itemNames, ArrayList<Integer> itemImages, ArrayList<Activity> itemActivities) {
        this.itemImages = itemImages;
        this.itemNames = itemNames;
        this.context = context;
        this.itemActivities = itemActivities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerHolder recyclerHolder = (RecyclerHolder) holder;
        recyclerHolder.label.setText(itemNames.get(position));
        recyclerHolder.image.setImageResource(itemImages.get(position));
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView label;
        private final ImageView image;

        public RecyclerHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.item_name);
            image = itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), itemNames.get(getAdapterPosition()) + " Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}

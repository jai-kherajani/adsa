package com.example.adsa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<String> itemNames;
    private final ArrayList<Integer> itemImages;
    private final ArrayList<String> itemActivities;
    private final Context context;

    public HomeAdapter(Context context, ArrayList<String> itemNames, ArrayList<Integer> itemImages, ArrayList<String> itemActivities) {
        this.itemImages = itemImages;
        this.itemNames = itemNames;
        this.context = context;
        this.itemActivities = itemActivities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new RecyclerHolder(view, null);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerHolder recyclerHolder = (RecyclerHolder) holder;
        recyclerHolder.label.setText(itemNames.get(position));
        recyclerHolder.image.setImageResource(itemImages.get(position));
        recyclerHolder.activityName = itemActivities.get(position);
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView label;
        private final ImageView image;
        private String activityName;

        public RecyclerHolder(View itemView, String activityName) {
            super(itemView);
            label = itemView.findViewById(R.id.item_name);
            image = itemView.findViewById(R.id.item_image);
            this.activityName = activityName;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(view.getContext(), itemNames.get(getAdapterPosition()) + " Clicked", Toast.LENGTH_SHORT).show();
            try {
                view.getContext().startActivity(new Intent(view.getContext(), Class.forName("com.example.adsa." + activityName)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

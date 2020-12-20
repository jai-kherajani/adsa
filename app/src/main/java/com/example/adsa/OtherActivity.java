package com.example.adsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class OtherActivity extends AppCompatActivity {

    private final String TAG = "OTHER ACTIVITY";
    private final ArrayList<String> itemNames = new ArrayList<>();
    private final ArrayList<Integer> itemImages = new ArrayList<>();
    private final ArrayList<String> itemActivities = new ArrayList<>();

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        Toolbar myToolbar = findViewById(R.id.other_toolbar);
        setSupportActionBar(myToolbar);

        Collections.addAll(itemNames, "About", "Surveys", "Grievances", "Policies");
        Collections.addAll(itemImages, R.drawable.about, R.drawable.survey, R.drawable.grievances, R.drawable.policies);
        Collections.addAll(itemActivities, "AboutActivity", "ComingSoon", "ComingSoon", "PoliciesActivity");
        homeAdapter = new HomeAdapter(OtherActivity.this, itemNames, itemImages, itemActivities);
        recyclerView = findViewById(R.id.recycler_view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        recyclerView.setLayoutManager(new CustomGridLayoutManager(OtherActivity.this, 2));
        Log.d(TAG, "HomeAdapter Size : " + homeAdapter.getItemCount());
        recyclerView.setAdapter(homeAdapter);
    }
}
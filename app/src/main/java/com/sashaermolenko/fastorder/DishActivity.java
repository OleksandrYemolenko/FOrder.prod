package com.sashaermolenko.fastorder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sashaermolenko.fastorder.Adapters.DishRecyclerAdapter;
import com.sashaermolenko.fastorder.Items.DishItem;

import java.util.ArrayList;

public class DishActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private DishRecyclerAdapter adapter;
    private Context context;
    private View view;
    ArrayList<DishItem> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        context = this;

        items.add(new DishItem("Title", "url", 0, "300", "asd"));

        recyclerView = (RecyclerView) findViewById(R.id.dishRecView);

        manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        adapter = new DishRecyclerAdapter(context, items);
        recyclerView.setAdapter(adapter);
    }
}

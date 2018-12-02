package com.sashaermolenko.fastorder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.r0adkll.slidr.Slidr;
import com.sashaermolenko.fastorder.Adapters.DishRecyclerAdapter;
import com.sashaermolenko.fastorder.Items.DishItem;

import java.util.ArrayList;

public class DishActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private DishRecyclerAdapter adapter;
    private Context context;
    ArrayList<DishItem> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        context = this;

        items.add(new DishItem("Title", "http://images.media-allrecipes.com/userphotos/960x960/3758394.jpg", 0, "300", "asd"));
        items.add(new DishItem("Big Title ooooo", "http://images.media-allrecipes.com/userphotos/960x960/3758394.jpg", 0, "300", "asd"));

        recyclerView = (RecyclerView) findViewById(R.id.dishRecView);

        manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        adapter = new DishRecyclerAdapter(context, items);
        recyclerView.setAdapter(adapter);

        Slidr.attach(this);
    }
}

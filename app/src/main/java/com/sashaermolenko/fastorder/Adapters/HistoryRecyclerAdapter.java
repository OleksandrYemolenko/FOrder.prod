package com.sashaermolenko.fastorder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sashaermolenko.fastorder.DishActivity;
import com.sashaermolenko.fastorder.Items.HistoryItem;
import com.sashaermolenko.fastorder.Items.MenuItem;
import com.sashaermolenko.fastorder.MainActivity;
import com.sashaermolenko.fastorder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<HistoryItem> items = new ArrayList<>();

    public HistoryRecyclerAdapter(Context context) {
        this.context = context;
        this.items = MainActivity.historyItems;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.historyitem, parent, false);
        return new HistoryRecyclerAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final HistoryItem historyItem = items.get(position);

        holder.bind(historyItem);

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DishActivity.class);
                context.startActivity(i);
                //MainActivity.ChangeAct(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView price, date;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            price = (TextView) itemView.findViewById(R.id.price_hist);
            date = (TextView) itemView.findViewById(R.id.date);

            price.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));
            date.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));
        }

        public void bind(final HistoryItem recyclerItem) {

            date.setText(recyclerItem.getDate());
            price.setText(recyclerItem.getPrice() + '₴');
        }


    }
}

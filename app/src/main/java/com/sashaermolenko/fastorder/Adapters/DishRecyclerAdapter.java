package com.sashaermolenko.fastorder.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sashaermolenko.fastorder.Items.DishItem;
import com.sashaermolenko.fastorder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DishRecyclerAdapter extends RecyclerView.Adapter<DishRecyclerAdapter.RecyclerViewHolder> {
    private final Context context;
    ArrayList<DishItem> items = new ArrayList<>();

    public DishRecyclerAdapter(Context context, ArrayList<DishItem> items) {
        this.context = context;
        this.items = items;
    }
    
    public void addAll(List<DishItem> items) {
        int pos = getItemCount();
        this.items.addAll(items);
        notifyItemRangeInserted(pos, this.items.size());
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dishitem, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        final DishItem dishItem = items.get(position);

        holder.bind(dishItem);

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean expanded = dishItem.getExpandable();
                dishItem.setExpanded(!expanded);
                notifyItemChanged(position);
                dishItem.setVisOfFullName(!expanded);
            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pr = Integer.valueOf(holder.total_price.getText().toString()) + Integer.valueOf(dishItem.getPrice());
                Integer amount = Integer.valueOf(holder.amount.getText().toString()) + 1;
                holder.total_price.setText(Integer.toString(pr));
                holder.amount.setText(Integer.toString(amount));

                //notifyItemChanged(position);
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pr = Math.max(Integer.valueOf(dishItem.getPrice()), Integer.valueOf(holder.total_price.getText().toString()) - Integer.valueOf(dishItem.getPrice()));
                Integer amount = Math.max(1, Integer.valueOf(holder.amount.getText().toString()) - 1);
                holder.total_price.setText(Integer.toString(pr));
                holder.amount.setText(Integer.toString(amount));

                //notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView title, price, description, total_price, total;
        private Button plus, minus, purchase;
        private ImageView image;
        private View subItem;
        private EditText amount;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            subItem = itemView.findViewById(R.id.sub_item);

            amount = (EditText) itemView.findViewById(R.id.dish_amount);
            plus = (Button) itemView.findViewById(R.id.btn_plus);
            minus = (Button) itemView.findViewById(R.id.btn_minus);
            purchase = (Button) itemView.findViewById(R.id.purchase);
            title = (TextView) itemView.findViewById(R.id.title);
            total = (TextView) itemView.findViewById(R.id.total);
            total_price = (TextView) itemView.findViewById(R.id.total_price);
            price = (TextView) itemView.findViewById(R.id.price);
            description = (TextView) itemView.findViewById(R.id.description);
            title.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));
            total.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));
            total_price.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));
            price.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));
            description.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));

            image = (ImageView) itemView.findViewById(R.id.imgD);

            //image.setImageResource(R.drawable.no_img); //
        }

        public void bind(final DishItem recyclerItem) {
            boolean expanded = recyclerItem.getExpandable();

            title.setText(recyclerItem.getName());
            total_price.setText(recyclerItem.getPrice());
            price.setText(recyclerItem.getPrice());
            description.setText(recyclerItem.getDescription());
            // TODO добавить текст button.setText();
            Picasso.with(context).load(recyclerItem.getURL()).into(image);

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
            price.setVisibility(expanded ? View.INVISIBLE : View.VISIBLE);
        }
    }
}

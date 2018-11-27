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
import com.sashaermolenko.fastorder.Items.MenuItem;
import com.sashaermolenko.fastorder.MainActivity;
import com.sashaermolenko.fastorder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.RecyclerViewHolder>{

    private Context context;
    private ArrayList<MenuItem> items = new ArrayList<>();

    public MenuRecyclerAdapter(Context context) {
        this.context = context;
        items.add(new MenuItem("Работает", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg/267px-Eq_it-na_pizza-margherita_sep2005_sml.jpg", 0));
        items.add(new MenuItem("Работает", "https://2.bp.blogspot.com/-9AclNNACtaI/WF4KJjpYwxI/AAAAAAAABOE/-4bK_W-JgbIb8e_nqkTL2_IfXoAnXor0gCLcB/s1600/tutorialimage_bottombar.png", 1));
        items.add(new MenuItem("Работает", "https://2.bp.blogspot.com/-9AclNNACtaI/WF4KJjpYwxI/AAAAAAAABOE/-4bK_W-JgbIb8e_nqkTL2_IfXoAnXor0gCLcB/s1600/tutorialimage_bottombar.png", 2));
        items.add(new MenuItem("Работает", "https://2.bp.blogspot.com/-9AclNNACtaI/WF4KJjpYwxI/AAAAAAAABOE/-4bK_W-JgbIb8e_nqkTL2_IfXoAnXor0gCLcB/s1600/tutorialimage_bottombar.png", 3));
        items.add(new MenuItem("Работает", "https://2.bp.blogspot.com/-9AclNNACtaI/WF4KJjpYwxI/AAAAAAAABOE/-4bK_W-JgbIb8e_nqkTL2_IfXoAnXor0gCLcB/s1600/tutorialimage_bottombar.png", 4));
    }

    public void addAll(List<MenuItem> items) {
        int pos = getItemCount();
        this.items.addAll(items);
        notifyItemRangeInserted(pos, this.items.size());
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menuitem, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        final MenuItem menuItem = items.get(position);

        holder.bind(menuItem);

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DishActivity.class);
                i.putExtra("category", Integer.toString(menuItem.getId()));
                i.putExtra("category_name", menuItem.getName());
                    context.startActivity(i);

                //MainActivity.ChangeAct(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            title.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));

            image = (ImageView) itemView.findViewById(R.id.imgMenu);
        }

        public void bind(final MenuItem recyclerItem) {

            title.setText(recyclerItem.getName());
           // image.setImageResource(R.drawable.ic_dashboard_black_24dp);
            Picasso.with(context.getApplicationContext()).load(recyclerItem.getURL()).into(image);
        }
    }
}

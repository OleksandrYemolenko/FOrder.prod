package com.sashaermolenko.fastorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sashaermolenko.fastorder.Fragments.CartFragment;
import com.sashaermolenko.fastorder.Fragments.HistoryFragment;
import com.sashaermolenko.fastorder.Fragments.MenuFragment;
import com.sashaermolenko.fastorder.Fragments.SettingsFragment;
import com.sashaermolenko.fastorder.Items.CartItem;
import com.sashaermolenko.fastorder.Items.HistoryItem;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int flag = 0;
    private static Context context;
    private FrameLayout frameLayout;
    private BottomNavigationView navigation;
    public static ArrayList<CartItem> cartItems = new ArrayList<>();
    public static ArrayList<HistoryItem> historyItems;
    public static ArrayList<String> spots;
    public static ArrayList<JSONObject> spotsObjects;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction;
            switch (item.getItemId()) {
                case R.id.navigation_menu:
                    MenuFragment menuFragment = new MenuFragment();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, menuFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_cart:
                    CartFragment cartFragment = new CartFragment();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, cartFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_history:
                    HistoryFragment historyFragment = new HistoryFragment();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, historyFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_settings:
                    SettingsFragment settingsFragment = new SettingsFragment();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, settingsFragment);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        save();
    }

    private void bind() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);

        FragmentTransaction fragmentTransaction;
        MenuFragment menuFragment = new MenuFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, menuFragment );
        fragmentTransaction.commit();

        historyItems = getHistoryItems();

//        historyItems = null;

        if(historyItems == null) {
            addItem("asd", "asd");
        }

        context = this;
    }

    private ArrayList<HistoryItem> getHistoryItems() {
        //importing all data into JSON objects array(phones) from database
        ArrayList<HistoryItem> historyItems = JSONHelper.importFromJSON(this);

        return historyItems;
    }

    public static void addItem(String date, String price) {
        try {
            HistoryItem hi = new HistoryItem(date, price);
            ArrayList<HistoryItem> his = new ArrayList<>();
            his.add(hi);
            if(historyItems != null) {
                his.addAll(historyItems);
            }
            historyItems = his;
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }

        for(int i = 1; i < historyItems.size(); ++i) {
            if(historyItems.get(i).getDate().length() == 3) {
                historyItems.remove(i);
                break;
            }
        }
    }

    public void save(){

        //exporting all received data into JSON object in database

        boolean result = JSONHelper.exportToJSON(this, historyItems);

//        if(result){
//            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
//        }
//        else{
//            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
//        }
    }
}

package com.example.colorwibe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Color_recycler extends AppCompatActivity {

    private RecyclerView rv;
    private ColorItemAdapter mColoradapter;
    private ArrayList<ColorItem> mColorlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_recycler);
        getSupportActionBar().setTitle("Colors");

        mColorlist = new ArrayList<>();
        rv=findViewById(R.id.Color_rv);
        rv.setLayoutManager(new LinearLayoutManager(Color_recycler.this));
        mColorlist.add(new ColorItem("#FF0000"," ", ""));
        mColorlist.add(new ColorItem("#7CFC00"," ", ""));
        mColorlist.add(new ColorItem("#FF6000"," ", ""));
        mColorlist.add(new ColorItem("#FF0000"," ", ""));
        mColorlist.add(new ColorItem("#7CFC00"," ", ""));
        mColorlist.add(new ColorItem("#FF6000"," ", ""));
        mColorlist.add(new ColorItem("#FF0000"," ", ""));
        mColorlist.add(new ColorItem("#7CFC00"," ", ""));
        mColorlist.add(new ColorItem("#FF6000"," ", ""));
        rv.setAdapter(new ColorItemAdapter(Color_recycler.this,mColorlist));


    }
}

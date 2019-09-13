package com.example.colorwibe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Color_recycler extends AppCompatActivity {

    private RecyclerView rv;
    private ColorItemAdapter mColoradapter;
    private ArrayList<ColorItem> mColorlist;
    private List exColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_recycler);
        getSupportActionBar().setTitle("Colors");
        rv=findViewById(R.id.Color_rv);
        rv.setLayoutManager(new LinearLayoutManager(Color_recycler.this));

        exColor=new ArrayList<String>();
        mColorlist = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {
            exColor= extras.getStringArrayList("Colors");
            Log.i("volley", String.valueOf(exColor));
            for (int i = 0; i < exColor.size(); i++)
            {
                mColorlist.add(new ColorItem(exColor.get(i).toString(),"",""));
            }
        }

        rv.setAdapter(new ColorItemAdapter(Color_recycler.this,mColorlist));


    }
}

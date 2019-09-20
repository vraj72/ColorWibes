package com.example.colorwibe;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Color_recyclerD extends AppCompatActivity {

    private RecyclerView rv;
    private ColorItemAdapter mColoradapter;
    private ArrayList<ColorItem> mColorlist;
//    private List exColor = new ArrayList();
    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_recycler_d);
        getSupportActionBar().setTitle("Favorites");

        mydb=new DatabaseHelper(this);


        rv=findViewById(R.id.Color_rv_d);
        rv.setLayoutManager(new LinearLayoutManager(Color_recyclerD.this));
        mColorlist = new ArrayList<>();


        getData();

        mColoradapter=new ColorItemAdapter(this,mColorlist);
        rv.setAdapter(mColoradapter);
    }

    void getData()
    {
        Cursor res = mydb.getHash();

        if(res.getCount() == 0)
        {
            TextView no = findViewById(R.id.nothing_in_database);
            no.setVisibility(View.VISIBLE);
        }
        else{
            while(res.moveToNext()){
                mColorlist.add(new ColorItem(res.getString(0),"0",""));
            }
        }
    }
}

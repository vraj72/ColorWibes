package com.example.colorwibe;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Color_recycler extends AppCompatActivity implements ColorItemAdapter.OnItemClickedListener{

    private RecyclerView rv;
    private ColorItemAdapter mColoradapter;
    private ArrayList<ColorItem> mColorlist;
    private List exColor;
    private Context context;
    private Button addF,fav_page;
    private int aSt=0;
    private ArrayList addFavlist = new ArrayList();
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_recycler);
        getSupportActionBar().setTitle("Colors");

        mydb=new DatabaseHelper(this);

        rv=findViewById(R.id.Color_rv);
        rv.setLayoutManager(new LinearLayoutManager(Color_recycler.this));
        TextView no = findViewById(R.id.nu);
        context=this;
        addF=findViewById(R.id.addFavorite);


        exColor=new ArrayList<String>();
        mColorlist = new ArrayList<>();
        mColorlist.clear();
        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {
            exColor= extras.getStringArrayList("Colors");
            Log.i("volleyR", String.valueOf(exColor));
            no.setText("Number of Colors Fetched "+exColor.size());
            for (int i = exColor.size()-1; i >-1 ; i--)
            {
                String h=exColor.get(i).toString();
                h=h.toUpperCase();
                Log.i("volleyRu",h+h.toUpperCase());
                if(h.length()>4)
                { Log.i("volleyR", exColor.get(i).toString());
                mColorlist.add(new ColorItem(h,"",""));}
                else if(h.length()==4){
                    String u="#";
                    for(int j=1;j<4;j++)
                    {
                        u=u+h.charAt(j)+h.charAt(j);
                        Log.i("volleyRc", u);
                    }
                    mColorlist.add(new ColorItem(u,"",""));
                }
            }
        }
        mColoradapter=new ColorItemAdapter(Color_recycler.this,mColorlist);
        rv.setAdapter(mColoradapter);
        mColoradapter.setOnItemClickListener(Color_recycler.this);


        addF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFavlist.clear();
                Log.i("volleyfav",mColoradapter.addFavList.toString());
                for (ColorItem cl : mColoradapter.addFavList) {
                    String h= cl.getHash();
                    if(!addFavlist.contains(h))
                    addFavlist.add(h);
                }
                Log.i("volleyfav",addFavlist.toString());
                InDatabase();

            }
        });

        fav_page=findViewById(R.id.favorite_activity);
        fav_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fav = new Intent(Color_recycler.this, Color_recyclerD.class);
                startActivity(fav);
            }
        });



    }


    @Override
    public void onItemClick(int position) {
        ColorItem clickedItem = mColorlist.get(position);
        String hash = clickedItem.getHash();
//        Toast.makeText(Color_recycler.this,hash+" Coped!" , Toast.LENGTH_SHORT).show();




//            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) Color_recycler.getSystemService(context.CLIPBOARD_SERVICE);
//            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", hash);
//            clipboard.setPrimaryClip(clip);


    }

    void InDatabase()
    {
        for(int i=0;i<addFavlist.size();i++)
        {
            String h= (String) addFavlist.get(i);
            Log.i("volleyinsert",h);
            if(mydb.insertHash(h)==false){
                Log.i("volleyinsert","not inserted "+h);
            }

        }

    }
}

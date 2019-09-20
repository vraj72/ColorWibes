package com.example.colorwibe;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String server_url;
    TextView response;
    List colors;
    List list1 = new ArrayList<String>();
    String std="0123456789abcdefABCDEF#";
    private RequestQueue mRequestQueue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        final EditText url_e = findViewById(R.id.url_edit);
        Button url_b=findViewById(R.id.url_enter);
        response=findViewById(R.id.textView_response);
        colors=new ArrayList<String>();
//        list1.add("#ff0000");//for debugging
        colors.add("#7CFC00");
        colors.add("#FF6000");
        colors.add("#FF0000");
//        get("https://www.facebook.com");


        url_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                server_url="https://"+url_e.getText().toString();
                if(server_url.equals("https://")){
                            Toast.makeText(MainActivity.this,"Enter url" , Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"Please wait... while your request is being processed" , Toast.LENGTH_LONG).show();

                    list1.clear();



                    getData(server_url);
                      if(list1.size()>0){
                    Intent recycle= new Intent(MainActivity.this,Color_recycler.class);
                    recycle.putStringArrayListExtra("Colors",(ArrayList<String>) list1);
                    startActivity(recycle);}
                      else {Toast.makeText(MainActivity.this,"Oops!! we could not found colors Try with different URL" , Toast.LENGTH_SHORT).show();}

                }
            }
        });


        Button fav_page=findViewById(R.id.favorite_activity_main);
        fav_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fav = new Intent(MainActivity.this, Color_recyclerD.class);
                startActivity(fav);
            }
        });
    }


    void getData(String url){

        Log.i("volleyLink","getdata");

        Document doc = null;
        try {
            doc=Jsoup.connect(url).get();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this,"URL not found",Toast.LENGTH_LONG).show();
            return;
        }
        Log.i("volleyLink","getdatahtml");

        String code =doc.toString();
        Log.i("volley",code);
//        gethashesHTML(doc);


        Elements links=doc.select("head").select("link[rel=stylesheet]");
        for(Element item : links){
             String href=item.attr("href").toString();
            String newurl=null;
             if(href.contains("http"))
             {newurl=href;}
             else
             {newurl=url+href;}
            Log.i("volleyLink",newurl);
            if( newurl.contains(".css"))
        {
            Document newdoc = null;
            try {
                newdoc=Jsoup.connect(newurl).get();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this,"URL not found",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            String temp=newdoc.body().toString();
            getHash(temp);
        }
        }


    }

    void gethashesHTML(Document doc){
        Elements internal =doc.select("button");
        for(Element i : internal){
            {String k=i.attr("class");
                k+=i.attr("id");
                Log.i("volleyi",k);}
        String Icss=i.toString();

        }
    }

    void getHash(String temp){
        Log.i("volleyLink","gethash"+temp.length());

        String back="color";
        String back2="background";
        String[] list=temp.split(";");
        for (String i: list){
            if(i.contains(back) || i.contains(back2)){
                if(i.length()<25){
                    int t=i.indexOf(":#");

                    t=t+1;
                    String h="";
                    int l=i.length();
                    while( t<l){
                        if(std.contains(String.valueOf(i.charAt(t))))
                        {h=h+String.valueOf(i.charAt(t));
                        t=t+1;}
                        else break;
                    }
                    Log.i("volleyH",h);

                    if(!list1.contains(h))
                    list1.add(h);
                }
            }
        }
        Log.i("volleyR", String.valueOf(list1));
    }


    public String get(String url) {
        final String[] r = {""};
        StringRequest stringRequest =new StringRequest(Request.Method.GET,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.i("volleyABC" ,"got response    "+response);
                Toast.makeText(MainActivity.this," "+response,Toast.LENGTH_LONG).show();

                r[0] =response;
            }
        },new Response.ErrorListener()  {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("volleyABC" ,"got error    ");

            }
        });
        mRequestQueue.add(stringRequest);
        return r[0];
    }

}

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


import org.json.JSONException;
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
        colors.add("#ff0000");//for debugging
        colors.add("#7CFC00");
        colors.add("#FF6000");
        colors.add("#FF0000");


        url_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                server_url=url_e.getText().toString();
                if(server_url.equals("")){
                            Toast.makeText(MainActivity.this,"Enter url" , Toast.LENGTH_SHORT).show();

                }
                else{

                    getData(server_url);

//                    Intent recycle= new Intent(MainActivity.this,Color_recycler.class);
//                    recycle.putStringArrayListExtra("Colors",(ArrayList<String>) colors);
//                    startActivity(recycle);
                }


            }
        });
    }


    void getData(String url){

        Document doc = null;
        try {
            doc=Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String code =doc.toString();
        Log.i("volley",code);
        gethashesHTML(doc);


        Elements links=doc.select("head").select("link[rel=stylesheet]");
        for(Element item : links){
             String href=item.attr("href").toString();
            String newurl=url+href;
            Log.i("volley",newurl);

            Document newdoc = null;
            try {
                newdoc=Jsoup.connect(newurl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String temp=newdoc.body().toString();
            code=code+temp;
            response.setText(code);
            getHashes(code);
            Log.i("volley", String.valueOf(temp.length()));
        }


    }

    void getHashes(String code){
        Log.i("volleyL", String.valueOf(code.length()));

    }

    void gethashesHTML(Document doc){
        Elements internal =doc.select("style");
        for(Element i : internal){
        Log.i("volleyi",i.toString());
        String Icss=i.toString();
        getHashes(Icss);
        }
    }

}

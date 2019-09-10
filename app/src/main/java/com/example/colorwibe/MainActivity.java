package com.example.colorwibe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    String server_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText url_e = findViewById(R.id.url_edit);
        Button url_b=findViewById(R.id.url_enter);


        url_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                server_url=url_e.getText().toString();
                if(server_url.equals("")){
                            Toast.makeText(MainActivity.this,"Enter url" , Toast.LENGTH_SHORT).show();

                }
                else{

                    getData(server_url);

                }


            }
        });
    }

    void getData(String server_url){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("volleyABC response", response);
                Toast.makeText(MainActivity.this,response, Toast.LENGTH_SHORT).show();
                serach(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    Log.i("volleyABC" ,Integer.toString(error.networkResponse.statusCode));
                    Toast.makeText(MainActivity.this,error.networkResponse.statusCode,Toast.LENGTH_SHORT).show();//it will not occur as authenticating at start
                    error.printStackTrace();}
                catch (Exception e)
                {
                    Log.i("volleyABC" ,"exception");
                    Toast.makeText(MainActivity.this,"Enter Valid url",Toast.LENGTH_SHORT).show();} //occur if connection not get estabilished
            }

        });
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }

    void serach(String html){
        int index = 0 ;
        index=html.indexOf("#");
//        Toast.makeText(MainActivity.this,"123 "+Integer.toString(index),Toast.LENGTH_SHORT).show();
    }
}

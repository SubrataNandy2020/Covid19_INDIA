package myapp.nandy.covid_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class only extends AppCompatActivity {
ImageButton i1,i2;
int count=0;
String con,act,rec,dea;
TextView co,ac,re,de;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only);
        co=findViewById(R.id.confirm);
        ac=findViewById(R.id.active);
        re=findViewById(R.id.recovered);
        de=findViewById(R.id.death);



            String url="https://corona.lmao.ninja/v2/all";
            StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject=new JSONObject(response.toString());



                        con=jsonObject.getString("cases");
                        act=jsonObject.getString("active");
                        rec=jsonObject.getString("recovered");
                        dea=jsonObject.getString("deaths");


                        co.setText(con);
                        ac.setText(act);
                        re.setText(rec);
                        de.setText(dea);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(only.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);




        i1=findViewById(R.id.ib1);
        i1.setImageResource(R.raw.india1);

        i2=findViewById(R.id.ib2);
        i2.setImageResource(R.raw.world1);


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent j= new Intent(getApplicationContext(),World.class);
               startActivity(j);

            }
        });
    }

    }


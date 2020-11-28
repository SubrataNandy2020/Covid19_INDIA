package myapp.nandy.covid_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class World extends AppCompatActivity {
TableLayout t1;
String country,conf,act,de;
int sno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);
        tableFun();
    }
    public void tableFun(){
        final TableLayout tl1=findViewById(R.id.tl1);
        TableRow tr1=new TableRow(this);

        TextView tv0=new TextView(this);
        tv0.setText("   SNo.");
        tv0.setGravity(Gravity.CENTER);
        tv0.setTextColor(Color.WHITE);
        tr1.addView(tv0);


        TextView tv1=new TextView(this);
        tv1.setText("Country");
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextColor(Color.parseColor("#07E4FF"));
        tr1.addView(tv1);


        TextView tv2=new TextView(this);
        tv2.setText(" "+" Confirm ");
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextColor(Color.YELLOW);
        tr1.addView(tv2);


        TextView tv3=new TextView(this);
        tv3.setText(" "+"   Recovered  ");
        tv3.setGravity(Gravity.CENTER);
        tv3.setTextColor(Color.GREEN);
        tr1.addView(tv3);

        TextView tv4=new TextView(this);
        tv4.setText(" "+"    Deaths ");
        tv4.setGravity(Gravity.CENTER);
        tv4.setTextColor(Color.RED);
        tr1.addView(tv4);
        tl1.addView(tr1);
      //upper part end

        // middle part
        String url="https://api.covid19api.com/summary";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    JSONArray jsonArray=jsonObject.getJSONArray("Countries");
                    for(int i=0;i<244;i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        TableRow tr2=new TableRow(getApplicationContext());

                        TextView tx=new TextView(getApplicationContext());

                        tx.setText(""+(i+1));
                        tx.setGravity(Gravity.CENTER);
                        tx.setTextColor(Color.WHITE);
                        tr2.addView(tx);

                        TextView tv5=new TextView(getApplicationContext());
                        country=jsonObject1.getString("Country");
                        tv5.setText(country);
                        tv5.setGravity(Gravity.CENTER);
                        tv5.setTextColor(Color.WHITE);
                        tr2.addView(tv5);

                        TextView tv6=new TextView(getApplicationContext());
                        conf=jsonObject1.getString("TotalConfirmed");
                        tv6.setText(" "+conf);
                        tv6.setGravity(Gravity.CENTER);
                        tv6.setTextColor(Color.WHITE);
                        tr2.addView(tv6);

                        TextView tv7=new TextView(getApplicationContext());
                        act=jsonObject1.getString("TotalRecovered");
                        tv7.setText(" "+act);
                        tv7.setGravity(Gravity.CENTER);
                        tv7.setTextColor(Color.WHITE);
                        tr2.addView(tv7);

                        TextView tv8=new TextView(getApplicationContext());
                        de=jsonObject1.getString("TotalDeaths");
                        tv8.setText(" "+de);
                        tv8.setGravity(Gravity.CENTER);
                        tv8.setTextColor(Color.WHITE);
                        tr2.addView(tv8);
                        tl1.addView(tr2);

                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(World.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}


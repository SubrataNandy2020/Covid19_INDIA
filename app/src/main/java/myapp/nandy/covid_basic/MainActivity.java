package myapp.nandy.covid_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    String st,act,con,rec,ded;
    int count=0;
    TextView state,active,confirmed,recovered,deaths;
    Button b1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        state=findViewById(R.id.state);
        confirmed=findViewById(R.id.confirm);
        active=findViewById(R.id.active);
        recovered=findViewById(R.id.recovered);
        deaths=findViewById(R.id.death);

        b1=findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
            }
        });


        fetchData();
    }
    private void fetchData(){


        String url="https://api.covid19india.org/data.json";

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {





                       JSONObject jsonObject=new JSONObject(response.toString());
                    JSONArray jsonparent=jsonObject.getJSONArray("statewise");



                        JSONObject JO=jsonparent.getJSONObject(count);
                        st=JO.getString("state");
                        act=JO.getString("active");
                        con=JO.getString("confirmed");
                        rec=JO.getString("recovered");
                        ded=JO.getString("deaths");

                        state.setText(st);
                        active.setText(act);
                        confirmed.setText(con);
                        recovered.setText(rec);
                        deaths.setText(ded);



                   int x= count++;
                    if(count==38){
                        count=0;
                    }


                    fetchPieData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void fetchPieData(){

        AnimatedPieView mAnimatedPieView = findViewById(R.id.piechart);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(Integer.parseInt(active.getText().toString()),Color.parseColor("#445BE4"),"active"))
                .addData(new SimplePieInfo(Integer.parseInt(confirmed.getText().toString()),Color.parseColor("#FFA726"),"confirmed"))
                .addData(new SimplePieInfo(Integer.parseInt(recovered.getText().toString()),Color.parseColor("#4AFF24"),"recovered"))
                .addData(new SimplePieInfo(Integer.parseInt(deaths.getText().toString()),Color.parseColor("#F30505"),"deaths")).drawText(true).strokeMode(false)
      .duration(2000);

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();




    }


}

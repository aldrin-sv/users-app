package com.example.usersapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv1=(TextView) findViewById(R.id.vv1);

        callApi();
    }

    private void callApi() {
        String apiUrl="https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest request=new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        StringBuilder result=new StringBuilder();

                        for(int i=0;i<(response.length());i++)
                        {


                            try {
                                JSONObject ob=response.getJSONObject(i);

                                String getName=ob.getString("name");
                                String getUserName=ob.getString("username");
                                String getEmail =ob.getString("email");
                                String getAddress= ob.getString("address");
                                String getPhone= ob.getString("phone");
                                String getWebsite= ob.getString("website");
                                String getCompany= ob.getString("company");


                                result.append("First Name: ").append(getName).append("\n");
                                result.append("Last Name: ").append(getUserName).append("\n");
                                result.append("Purpose: ").append(getEmail).append("\n");
                                result.append("Address: ").append(getAddress).append("\n");
                                result.append("Phone: ").append(getPhone).append("\n");
                                result.append("Website: ").append(getWebsite).append("\n");
                                result.append("Company: ").append(getCompany).append("\n");
                                result.append("----------------------------------").append("\n");

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }




                        }

                        tv1.setText(result.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error Occurred",Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
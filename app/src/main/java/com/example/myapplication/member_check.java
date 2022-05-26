package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class member_check extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        //String userPass = intent.getStringExtra("userPass");
        String teamName = intent.getStringExtra("teamName");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                                    String team_name = "";
                            JSONArray jsonArray =new JSONArray(response);
                            for( int i= 0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String number = jsonObject.getString("userID");
                                team_name +=number + " ";
                            }
                            Toast.makeText(member_check.this, response.toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(member_check.this, MainActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("teamName",teamName);
                                intent.putExtra("team_member",team_name);
                                startActivity(intent);
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "실패ㅑㅑㅑ", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                };
        MemberCheckRequest memberCheckRequest = new MemberCheckRequest(userID,teamName,responseListener);
        RequestQueue queue = Volley.newRequestQueue(member_check.this);
        queue.add(memberCheckRequest);
        Toast.makeText(getApplicationContext(), "구성원", Toast.LENGTH_SHORT).show();
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView member,my_name;
    private Button Main_team,go_member_check;
    private ImageButton Main_on, Main_setting;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_main);
        Main_setting = findViewById(R.id.Main_setting);
        Main_team = findViewById(R.id.team_name);
        Main_on = findViewById(R.id.Main_on);
        member = findViewById(R.id.member);
        my_name = findViewById(R.id.my_name);
        go_member_check = findViewById(R.id.go_member_check);
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        //String userPass = intent.getStringExtra("userPass");
        String teamName = intent.getStringExtra("teamName");
        String team_member = intent.getStringExtra("team_member");
        ArrayList team = intent.getStringArrayListExtra("team_array");
        my_name.setText(userID+"님");
        Main_team.setText(teamName+ "팀");
        member.setText(team_member);
        Main_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://219.250.99.170:8002/?status=ON"));
                startActivity(intent);

            }

        });

        Main_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

        go_member_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Showmember.class);
                intent.putExtra("userID", userID);
                intent.putExtra("userID", teamName);
                intent.putExtra("userID", team_member);
                intent.putStringArrayListExtra("team_array",team);
                startActivity(intent);
            }
        });
    }
}


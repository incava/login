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

public class MainActivity extends AppCompatActivity {
    private TextView member;
    private Button Main_team;
    private ImageButton Main_on, Main_setting;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_main);
        Main_setting = findViewById(R.id.Main_setting);
        Main_team = findViewById(R.id.team_name);
        Main_on = findViewById(R.id.Main_on);
        member = findViewById(R.id.member);
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");
        String teamName = intent.getStringExtra("teamName");
        String team_member = intent.getStringExtra("team_member");
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

        Main_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usersID = userID.toString();
                //EditText에 입력되어있는것을 가져온다.
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { //json : 파싱해주는 역할
                        try {
                            JSONObject jsonObject = new JSONObject(response); //성공여부
                            boolean success =  jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "맴버 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "맴버 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Insertmember insertmember = new Insertmember(usersID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(insertmember);
            }
        });
    }
}

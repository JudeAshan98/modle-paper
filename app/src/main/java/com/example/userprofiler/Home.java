package com.example.userprofiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button reg_btn;
    public String IntEx = "test";
    DBHandler mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb  = new DBHandler(Home.this);

        reg_btn = (Button)findViewById(R.id.reg_btn);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,ProfileManagement.class);
                intent.putExtra("test",IntEx);
                startActivity(intent);
            }
        });
    }
}

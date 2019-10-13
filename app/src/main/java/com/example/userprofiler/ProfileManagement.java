package com.example.userprofiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProfileManagement extends AppCompatActivity {

    public String takeExt;
    public String SendIntent = "test";
    Button btn_Edit,reg_btn;
    DBHandler myDb;
    EditText user_et,dob_et,pw_et;
    RadioButton gender;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        myDb = new DBHandler(this);
        reg_btn = (Button)findViewById(R.id.Register_btn);
        btn_Edit = (Button)findViewById(R.id.update_btn);
        Intent intent = new Intent();
        takeExt = intent.getStringExtra("test");
        user_et = (EditText)findViewById(R.id.user_et);
        radioGroup = (RadioGroup) findViewById(R.id.Gender);
        dob_et = (EditText)findViewById(R.id.dob_et);
        pw_et = (EditText)findViewById(R.id.pw_et);

        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileManagement.this,EditProfile.class);
                intent.putExtra("test",SendIntent);
                startActivity(intent);
            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectGender = radioGroup.getCheckedRadioButtonId();
                gender = (RadioButton)findViewById(selectGender);
               boolean isInserted =  myDb.addInfo(user_et.getText().toString(),dob_et.getText().toString(),gender.getText().toString(),pw_et.getText().toString());
               if(isInserted ==true){
                   Toast.makeText(ProfileManagement.this,"Registration successfully",Toast.LENGTH_LONG).show();
               }
               else{
                   Toast.makeText(ProfileManagement.this,"Registration Failed",Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}

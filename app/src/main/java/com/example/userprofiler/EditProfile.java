package com.example.userprofiler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class EditProfile extends AppCompatActivity {

    public String GetExtra;
    DBHandler mydb;

    EditText user_et,dob_et,pw_et,id;
    Button search_btn,update_btn,Delete_btn;
    RadioGroup Gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mydb = new DBHandler(this);
        Intent intent = new Intent();
        intent.getStringExtra("test");
        user_et = (EditText)findViewById(R.id.user_et);
        dob_et = (EditText)findViewById(R.id.dob_et);
        pw_et = (EditText)findViewById(R.id.pw_et);
        search_btn =(Button)findViewById(R.id.search_btn);
        update_btn = (Button)findViewById(R.id.update_btn);
        Gender = (RadioGroup)findViewById(R.id.Gender);
        id =(EditText)findViewById(R.id.id);
        Delete_btn =(Button)findViewById(R.id.Delete_btn);
        id.setVisibility(View.INVISIBLE);
        Searchdata();
        updateUser();
        deletedata();

    }

    public void Searchdata(){
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res = mydb.readAllInfo(user_et.getText().toString());
                //Log.d(res.getString(2),"id");
                if (res.getCount()==0){
                    show_message("Error", "No data found!");
                    return;
                }
                else{
                    while(res.moveToNext()){
                        id.setText(res.getString(0));
                        dob_et.setText(res.getString(2));
                  //      Gender.getCheckedRadioButtonId(res.getColumnIndex(3););
                        pw_et.setText(res.getString(4));
                    }
                }
            }
        });

    }


    public void updateUser(){
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res = mydb.updateInfo(user_et.getText().toString(),dob_et.getText().toString(),Gender.toString(),id.getText().toString(),pw_et.getText().toString());
                if (res==true){
                    makeText(EditProfile.this,"User information update successfully!",Toast.LENGTH_LONG).show();
                }
                else{
                    makeText(EditProfile.this,"User information update Failed!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void deletedata(){
        Delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res =mydb.deleteInfo(id.getText().toString());
                if (res==true){
                    Toast.makeText(EditProfile.this,"User information deleted Successfully!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(EditProfile.this,"User information deleted Failed!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public  void show_message(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true );
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

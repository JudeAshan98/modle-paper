package com.example.userprofiler;

import android.provider.BaseColumns;

import java.util.Date;

public class UserProfile {


    private UserProfile() {
    }

    public class users implements BaseColumns {
        public  final String user_name;
        public  final String password;
        public  final String gender;
        public final Date dob;

        public users(String user_name, String password, String gender, Date dob) {
            this.user_name = user_name;
            this.password = password;
            this.gender = gender;
            this.dob = dob;
        }
    }
}

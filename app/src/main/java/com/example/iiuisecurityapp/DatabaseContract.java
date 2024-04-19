package com.example.iiuisecurityapp;

import android.provider.BaseColumns;

public class DatabaseContract {
    public DatabaseContract() {}
    public static abstract class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        //public static final String table2="login";
        public static final String COL_FULLNAME = "fullname";
        public static final String COL_address = "address";
        public static final String COL_CNIC = "cnic";
        public static final String COL_Email = "email";
        public static final String COL_Password = "password";
        public static final String COL_newPassword = "newpassword";
        public static final String COL_confirmPass = "confirmpassword";
        public static final String COL_AGE = "age";
    }
    public static abstract class login implements BaseColumns{
        public static final String table2="login";
        public static final String COL_Email="email";
        public static final String COL_Password="password";
    }
}

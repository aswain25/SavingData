package com.example.admin.savingdata;

import android.provider.BaseColumns;

/**
 * Created by Admin on 3/28/2018.
 */

public class LocalDataContract
{
    public static final String NAME = "Person";
    public static final int VERSION = 1;

    public static final String CREATE_PERSON_TABLE = "CREATE TABLE " + Person.TABLE + "(" + Person.FIRST_NAME + " TEXT, " + Person.LAST_NAME + " TEXT, " + Person.GENDER + " TEXT)";

    public static class Person implements BaseColumns
    {

        public static final String TABLE = "person";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String GENDER = "gender";
        public static final String GETALL = "SELECT * FROM ";
    }
}

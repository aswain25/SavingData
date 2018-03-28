package com.example.admin.savingdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/28/2018.
 */

public class LocalDataSource extends SQLiteOpenHelper
{

    public LocalDataSource(Context context)
    {
        super(context, LocalDataContract.NAME, null, LocalDataContract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(LocalDataContract.CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    public long savePerson(Person person)
    {
        SQLiteDatabase database = getWritableDatabase();

        //create the content value for saving the object
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDataContract.Person.FIRST_NAME, person.getFirstName());
        contentValues.put(LocalDataContract.Person.LAST_NAME, person.getLastName());
        contentValues.put(LocalDataContract.Person.GENDER, person.getGender());

        //instert the object in the table
        long rowNumber = database.insert(LocalDataContract.Person.TABLE, null, contentValues);

        database.close();
        return rowNumber;
    }

    public List<Person> getAllPerson()
    {
        SQLiteDatabase database = getWritableDatabase();
        List<Person> personList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + LocalDataContract.Person.TABLE,null);

        if (cursor.moveToFirst())
        {
            do
            {
                Person person = new Person(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                personList.add(person);
            }
            while (cursor.moveToNext());
        }
        database.close();
        return personList;
    }
}

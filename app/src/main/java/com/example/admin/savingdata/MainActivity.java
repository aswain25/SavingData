package com.example.admin.savingdata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    EditText etSharedPref;
    TextView tvSharedPref;
    EditText etFirstName;
    EditText etLastName;
    EditText etGender;
    TextView tvPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
    }

    private void bindViews()
    {
        etSharedPref = findViewById(R.id.etSharedPref);
        tvSharedPref = findViewById(R.id.tvSharedPref);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etGender = findViewById(R.id.etGender);
        tvPerson = findViewById(R.id.tvAllPersons);
    }

    public void handlingSharedPref(View view)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPref", MODE_PRIVATE);
        switch (view.getId())
        {
            case R.id.btnSharedPref:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("data", etSharedPref.getText().toString());
                editor.commit();
                break;
            case R.id.btnRestorePref:
                tvSharedPref.setText(sharedPreferences.getString("data", "default"));
                break;
        }
    }
    public void handlingSQLite(View view)
    {
        LocalDataSource dataSource = new LocalDataSource(this);
        switch (view.getId())
        {
            case R.id.btnSavePerson:
                Person person = new Person(etFirstName.getText().toString(), etLastName.getText().toString(), etGender.getText().toString());
                long rowNumber = dataSource.savePerson(person);

                Toast.makeText(this, String.valueOf(rowNumber), Toast.LENGTH_LONG);
                break;
            case R.id.btnRecievePerson:
                tvPerson.setText(dataSource.getAllPerson().toString());
                break;
        }
    }
}
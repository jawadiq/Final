package com.dreamlogix.afinal;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText txtUser, txtPass;
    Button Rege;
    private DataBaseHelper dbhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbhelp = new DataBaseHelper(this);

        txtUser = (EditText)findViewById(R.id.user);
        txtPass = (EditText)findViewById(R.id.pass);
        Rege = (Button)findViewById(R.id.register);

        Rege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbhelp.insertData(txtUser.getText().toString(),txtPass.getText().toString());
                if (result)
                    Toast.makeText(Registration.this,"Data inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Registration.this,"Data insert failed",Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void toAlarm(View view){
        Intent i = new Intent(this,MainAlarm.class);
        startActivity(i);
    }
}

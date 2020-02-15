package com.example.retrofit_login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {

    EditText username,password,name,mobileno;
    Button reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username=findViewById(R.id.mUsername);
        password=findViewById(R.id.mPassword);
        name=findViewById(R.id.mName);
        mobileno=findViewById(R.id.mMobile);

        reg=findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reguder();
            }
        });

    }

    private void reguder() {
        String sName = name.getText().toString().trim();
        String sMobile = mobileno.getText().toString().trim();
        String sEmail = username.getText().toString().trim();
        String sPass = password.getText().toString().trim();

        if (TextUtils.isEmpty(sName)){
            name.setError("Enter name");
            return;
        }
        else if (TextUtils.isEmpty(sMobile)){
            mobileno.setError("Enter Mobile no");
            return;
        }
        else if (TextUtils.isEmpty(sEmail)){
            username.setError("Enter Email");
            return;
        }
        else if (TextUtils.isEmpty(sPass)){
            password.setError("Enter password");
            return;
        }
        else{
            Call<User> userCall = MainActivity.serviceApi.doRegistration(sName,sMobile,sEmail,sPass);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(Registration.this, "Success" + response.message(), Toast.LENGTH_SHORT).show();
                    username.setText("");

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(Registration.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    username.setText("");

                }
            });
        }
    }
}

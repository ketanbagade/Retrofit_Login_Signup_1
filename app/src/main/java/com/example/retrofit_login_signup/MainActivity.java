package com.example.retrofit_login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText user,pass ;
    public static ServiceApi serviceApi;
    Button signup,log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);

        user =findViewById(R.id.mUsername);
        pass = findViewById(R.id.mPassword);

        log= findViewById(R.id.login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = user.getText().toString().trim();
                String password= pass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    user.setError("enter email...");
                    return;
                }
                else if (TextUtils.isEmpty(password)){
                    pass.setError("enter password...");
                    return;
                }
                else{
                    Call<User>userCall =MainActivity.serviceApi.dologin(email,password);
                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.body().getResponse().equals("data")){

                                startActivity(new Intent(getApplicationContext(),HomePage.class));
                                Toast.makeText(MainActivity.this, "Succfull Login" + response.message(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "Enter valid cridetial"+response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });

                }
            }
        });

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });
    }
}

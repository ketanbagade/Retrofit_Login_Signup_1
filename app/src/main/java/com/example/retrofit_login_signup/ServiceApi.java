package com.example.retrofit_login_signup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

    @FormUrlEncoded
    @POST("reg.php")
    Call<User> doRegistration(@Field("name") String name,
                    @Field("phone") String sMobile,
                    @Field("email") String sEmail,
                    @Field("password") String sPass


    );
    @FormUrlEncoded
    @POST("login.php")
    Call<User> dologin(@Field("email") String email,
                       @Field("password") String pass


    );





}

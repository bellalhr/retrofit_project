package com.bellalhrlux.retrofitpost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login/set_jwt_data")
    Call<Todo> createUser(@Field("username") String username,@Field("password") String pass,@Field("remember") int remamber,@Field("url_redirec") String url_redir);



    @GET("userInfo")
    Call<UserInfor> getUserInfo(@Header("Authorization") String author);


    //Call<Todo> getUserAuthentication(@Query("username") String username,@Query("password") String pass);
}

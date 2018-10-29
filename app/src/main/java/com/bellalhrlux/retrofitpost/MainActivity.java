package com.bellalhrlux.retrofitpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL="https://login.jomakhata.com/";
    private static final String BASE_URL_USER_INFO="https://www.jomakhata.com/";
    private ApiInterface apiInterface;
    private TextView setMessageTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMessageTV=findViewById(R.id.setMessage);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);

        //Users users = new Users("fahadbillah@yahoo.com", "123", 1, "");
        apiInterface.createUser("fahadbillah@yahoo.com", "123", 1, "").enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                if (response.isSuccessful())
                {
                    setMessageTV.setText(response.body().getKey().toString()+"\n Massage is "+response.body().getMessage());

                    //GET USER ALL INFORMATION
                    getUserInfo("bearer "+response.body().getKey().toString());

                }
                    //Toast.makeText(MainActivity.this, "Key is "+response.body().getKey().toString()+"Massage "+
                            //response.body().getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getUserInfo(String s) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_USER_INFO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getUserInfo(s).enqueue(new Callback<UserInfor>() {
            @Override
            public void onResponse(Call<UserInfor> call, Response<UserInfor> response) {
                if(response.isSuccessful())
                {
                    UserInfor userInfor=response.body();
                    int infors=userInfor.getSuccess();


                    if(infors==1)
                    {
                        Log.e("user",
                                userInfor.getData().get(0).getUsername()+"\n"
                                +userInfor.getData().get(0).getCountryName()
                        );
                    }
                   // List<UserInfor.Datum> result=response.body();
                    Toast.makeText(MainActivity.this, "Success "+infors, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Error "+response.message().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserInfor> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

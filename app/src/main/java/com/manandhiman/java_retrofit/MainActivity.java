package com.manandhiman.java_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        RetrofitInstance.getInstance().apiInterface.getPopularProducts().enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                //response body is an list of objects
                for(Model item: response.body()){
                    textView.setText(null);
                    textView.setText(textView.getText() +" "+ item.getName()+" "+ item.getPrice()+"\n");

                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.e("Retrofit error","Retrofit"+t.getLocalizedMessage());
            }
        });

    }
}
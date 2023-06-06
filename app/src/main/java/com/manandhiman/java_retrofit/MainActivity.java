package com.manandhiman.java_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         textView = findViewById(R.id.textView);
         editText = findViewById(R.id.editText);

        RetrofitInstance.getInstance().apiInterface.getPopularProducts().enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                //response body is an list of objects
                textView.setText(null);
                for(Model item: response.body()){
                    textView.setText(textView.getText() +" "+ item.getName()+" "+ item.getPrice()+"\n");
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.e("Retrofit error","Retrofit"+t.getLocalizedMessage());
            }
        });

    }

    public void searchProduct(View view){
        String id = editText.getText().toString();
        if(id == null) return;

        RetrofitInstance.getInstance().apiInterface.getProductById(id).enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
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
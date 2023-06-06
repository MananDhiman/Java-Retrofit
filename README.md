# Java-Retrofit

A barebones android app to familiarise myself with Retrofit Library that is used to handle API calls in android apps.

## AndroidManifest.xml

```xml
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```

## build.gradle (app)
```gradle
implementation 'com.google.code.gson:gson:2.9.0'
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

## Model Class (Model.java)
```java
private String id, name, price, category, rating;

//constructor, getters and setters
```

## ApiInterface.java
```java
public interface ApiInterface {
    @GET("products/get_popular_products.php")
    Call<List<Model>> getPopularProducts();

    //other methods may be defined here
}
```

## RetrofitInstance.java
```java
public class RetrofitInstance {
    private final String BASE_URL = "http://192.168.1.14/shophere/";

    static RetrofitInstance instance;
    ApiInterface apiInterface;

    RetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static RetrofitInstance getInstance(){
        if(instance == null){
            instance = new RetrofitInstance();
        }
        return instance;
    }

}
```

## MainActivity.java
```java
textView.setText(null);
//currently can't access list genereated from response.body() outside the instance even when using new List()
RetrofitInstance.getInstance().apiInterface.getPopularProducts().enqueue(new Callback<List<Model>>() {
    @Override
    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
        //response body is an list of objects
        //use recycler view here
        for(Model item: response.body())
            textView.setText(textView.getText() +" "+ item.getName()+" "+ item.getPrice()+"\n");
    }

    @Override
    public void onFailure(Call<List<Model>> call, Throwable t) {
        Log.e("Retrofit error","Retrofit error --> "+t.getLocalizedMessage());
    }
});
```
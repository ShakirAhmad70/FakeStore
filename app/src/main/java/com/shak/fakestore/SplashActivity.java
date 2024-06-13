package com.shak.fakestore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class SplashActivity extends AppCompatActivity {

//    private static final String PREF_NAME = "fakeStorePrefs";
//    SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedPreferences.edit();
//    private static final String ID_KEY = "id";
//    private static final String TITLE_KEY = "title";
//    private static final String DESCRIPTION_KEY = "description";
//    private static final String PRICE_KEY = "price";
//    private static final String CATEGORY_KEY = "category";
//    private static final String IMAGE_URL_KEY = "image";
//    private static final String RATE_KEY = "rate";
//    private static final String COUNT_KEY = "count";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Handler().postDelayed(() -> {

//        AndroidNetworking.initialize(this);
//        AndroidNetworking.get("https://fakestoreapi.com/products")
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONArray(new JSONArrayRequestListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        JSONObject obj;
//                        try {
//                            for (int i = 0; i < response.length(); i++) {
//                                obj = response.getJSONObject(i);
//                                editor.putInt(ID_KEY, obj.getInt("id"));
//                                editor.putString(TITLE_KEY, obj.getString("title"));
//                                editor.putString(DESCRIPTION_KEY, obj.getString("description"));
//                                editor.putString(PRICE_KEY, Double.toString(obj.getDouble("price")));
//                                editor.putString(CATEGORY_KEY, obj.getString("category"));
//                                editor.putString(IMAGE_URL_KEY, obj.getString("image"));
//                                editor.putString(RATE_KEY, Double.toString(obj.getJSONObject("rating").getDouble("rate")));
//                                editor.putInt(COUNT_KEY, obj.getJSONObject("rating").getInt("count"));
//                                editor.apply();
//                            }
//                        }
//                        catch (Exception e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Toast toast = new Toast(SplashActivity.this);
//                        View view = LayoutInflater.from(SplashActivity.this).inflate(R.layout.toast_layout, findViewById(R.id.mainLay));
//                        toast.setView(view);
//                        toast.setGravity(Gravity.TOP, 0,0);
//                        TextView toastText = view.findViewById(R.id.toastText);
//                        toastText.setText(R.string.no_internet);
//                        toast.setDuration(Toast.LENGTH_LONG);
//                        for(int i=1; i<=10; i++){
//                            toast.show();
//                            try {
//                                Thread.sleep(3000);
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                    }
//                });

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
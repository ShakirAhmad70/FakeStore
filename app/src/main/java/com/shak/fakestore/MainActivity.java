package com.shak.fakestore;


import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
//    private static final String PREF_NAME = "fakeStorePrefs";
//    SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//    private static final String TITLE_KEY = "title";
//    private static final String IMAGE_URL_KEY = "image";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        RecyclerView recyclerView = findViewById(R.id.itemsRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList<ItemModel> arrItems = new ArrayList<>();
        RecyclerAdapter adapter = new RecyclerAdapter(arrItems, getApplicationContext());
        recyclerView.setAdapter(adapter);

        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://fakestoreapi.com/products")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length(); i++){
                            String title, imgUrl;
                            try {
                                title = response.getJSONObject(i).getString("title");
                                imgUrl = response.getJSONObject(i).getString("image");
                                arrItems.add(new ItemModel(title, imgUrl));
                                adapter.notifyItemInserted(i);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        final Toast toast = new Toast(MainActivity.this);
                        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.toast_layout, findViewById(R.id.mainLay));
                        toast.setView(view);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView toastText = view.findViewById(R.id.toastText);
                        toastText.setText(R.string.no_internet);
                        toast.setDuration(Toast.LENGTH_LONG);

                        final Handler handler = new Handler();
                        final int toastCount = 10;
                        final int delay = 3000; // 3 seconds delay

                        for (int i = 0; i < toastCount; i++) {
                            handler.postDelayed(toast::show, delay);
                        }
                    }

                });

    }
}
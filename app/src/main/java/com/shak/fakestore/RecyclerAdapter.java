package com.shak.fakestore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final ArrayList<ItemModel> arrItems;
    private final Context context;

    public RecyclerAdapter(ArrayList<ItemModel> arrItems, Context context) {
        this.arrItems = arrItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] itemTitle = arrItems.get(position).getTitle().split(" ");
        StringBuilder truncatedTitle = new StringBuilder();
        for (int i = 0; i < Math.min(itemTitle.length, 3); i++) {
            truncatedTitle.append(itemTitle[i]);
            if (i < 2) {
                truncatedTitle.append(" ");
            }
        }
        holder.txtTitleRow.setText(truncatedTitle.append("...").toString());
        String imageUrl = arrItems.get(position).getImageUrl();
        Glide.with(context).load(imageUrl).into(holder.imgRow);

        holder.rowLayout.setOnClickListener(view -> {
            Intent iShow = new Intent(context, ShowItemActivity.class);
            iShow.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            AndroidNetworking.initialize(context);
            AndroidNetworking.get("https://fakestoreapi.com/products")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject obj;
                            try {
                                obj = response.getJSONObject(holder.getAdapterPosition());

                                int id = obj.getInt("id");
                                String title = obj.getString("title");
                                String description = obj.getString("description");
                                double price = obj.getDouble("price");
                                String category = obj.getString("category");
                                String imageUrl1 = obj.getString("image");
                                JSONObject rating = obj.getJSONObject("rating");
                                double rate = rating.getDouble("rate");
                                int count = rating.getInt("count");

                                iShow.putExtra("id", id);
                                iShow.putExtra("title", title);
                                iShow.putExtra("description", description);
                                iShow.putExtra("price", price);
                                iShow.putExtra("category", category);
                                iShow.putExtra("image", imageUrl1);
                                iShow.putExtra("rate", rate);
                                iShow.putExtra("count", count);


                                //start the activity
                                context.startActivity(iShow);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return arrItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRow;
        TextView txtTitleRow;
        RelativeLayout rowLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRow = itemView.findViewById(R.id.imgRow);
            txtTitleRow = itemView.findViewById(R.id.txtTitleRow);
            rowLayout = itemView.findViewById(R.id.rowLayout);
        }
    }
}
package com.example.examengapsi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.examengapsi.R;
import com.example.examengapsi.model.Producto;

import java.util.ArrayList;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ProductsListHolder> {

    Context context;
    ArrayList<Producto> productsList;

    public ProductsListAdapter(Context context, ArrayList<Producto> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsListHolder holder, int position) {
        holder.idTvPrice.setText(productsList.get(position).getListPrice().toString());
        holder.idTvName.setText(productsList.get(position).getProductDisplayName());
        Glide.with(context).load(productsList.get(position).getSmImage()).into(holder.idTvImage);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ProductsListHolder extends RecyclerView.ViewHolder {

        TextView idTvPrice, idTvName;
        ImageView idTvImage;

        public ProductsListHolder(@NonNull View itemView) {
            super(itemView);

            idTvPrice = itemView.findViewById(R.id.idTvPrice);
            idTvName = itemView.findViewById(R.id.idTvName);
            idTvImage = itemView.findViewById(R.id.idTvImage);
        }
    }
}

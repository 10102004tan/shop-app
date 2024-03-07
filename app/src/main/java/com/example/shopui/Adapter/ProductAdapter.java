package com.example.shopui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopui.Models.Product;
import com.example.shopui.MyInterface.IClickItemProductListener;
import com.example.shopui.databinding.ProductItemLayoutBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolderProduct> {


    private List<Product> products;
    private IClickItemProductListener iClickItemProductListener;


    public ProductAdapter(List<Product> products,IClickItemProductListener listener) {
        this.products = products;
        this.iClickItemProductListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,parent,false);
//        return new MyViewHolderProduct(view);

        ProductItemLayoutBinding binding = ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolderProduct(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderProduct holder, int position) {
        Product product = products.get(position);
        holder.binding.titleTxt.setText(product.getTitle());
        holder.binding.scoreTxt.setText(String.valueOf(product.getScore()));
        holder.binding.reviewTxt.setText(String.valueOf(product.getReview()));
        holder.binding.feeTxt.setText("$" + String.valueOf(product.getPrice()));
        holder.binding.pic.setImageResource(product.getPicUrl());

        
        holder.binding.productLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemProductListener.clickItemProduct(product);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolderProduct extends RecyclerView.ViewHolder{
        private ProductItemLayoutBinding binding;
        public MyViewHolderProduct(@NonNull ProductItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

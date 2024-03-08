package com.example.shopui.Adapter;

import android.service.autofill.OnClickAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopui.Models.Product;
import com.example.shopui.MyInterface.IClickItemProductListener;
import com.example.shopui.databinding.ProductItemLayoutBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolderProduct> {


    private List<Product> products;
    private IClickItemProductListener iClickItemProductListener;

    public void setClickItemProductListener(IClickItemProductListener iClickItemProductListener) {
        this.iClickItemProductListener = iClickItemProductListener;
    }

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        final int pos = position;
        holder.position = pos;


    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    class MyViewHolderProduct extends RecyclerView.ViewHolder{
        private ProductItemLayoutBinding binding;
        private int position;
        public int getPositionProduct() {
            return position;
        }

        public MyViewHolderProduct(@NonNull ProductItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickItemProductListener.clickItemProduct(position,binding);
                }
            });

            this.binding.iconstar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "updating", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

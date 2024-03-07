package com.example.shopui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shopui.Models.Product;
import com.example.shopui.databinding.DetailsLayoutBinding;

public class DetailsActivity extends AppCompatActivity {


    private DetailsLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        Product product = (Product)data.getSerializable("product");

        binding.pic.setImageResource(product.getPicUrl());
        binding.titleTxt.setText(product.getTitle());
        binding.reviewTxt.setText(String.valueOf(product.getReview()));
        binding.descriptionTxt.setText(String.valueOf(product.getDescription()));

        binding.btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, "Add " + product.getTitle() +" successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
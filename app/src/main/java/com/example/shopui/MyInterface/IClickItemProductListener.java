package com.example.shopui.MyInterface;

import com.example.shopui.Models.Product;
import com.example.shopui.databinding.ProductItemLayoutBinding;

public interface IClickItemProductListener {

    void clickItemProduct(int position,ProductItemLayoutBinding binding);
}

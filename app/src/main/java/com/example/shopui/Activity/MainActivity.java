package com.example.shopui.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shopui.Adapter.ProductAdapter;
import com.example.shopui.BroadcastReceiver.InternetCheckServies;
import com.example.shopui.Models.Product;
import com.example.shopui.MyInterface.IClickItemProductListener;
import com.example.shopui.R;
import com.example.shopui.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products;
    private SQLiteDatabase mydatabase;
    private ActivityMainBinding binding;
    private BroadcastReceiver broadcastReceiver = null;

    private static class Temp {
        public boolean complete;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        broadcastReceiver = new InternetCheckServies();
        checkInternet();

        products = new ArrayList<>();
//        products = createListProducts();
//        pushData();


        getData(this);
//        Log.d("test","thread 1");


//        ProductAdapter adapter = new ProductAdapter(products, new IClickItemProductListener() {
//            @Override
//            public void clickItemProduct(Product product) {
//                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("product",product);
//                intent.putExtra("data",bundle);
//                startActivity(intent);
//            }
//        });
//        binding.PopularView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        binding.PopularView.setAdapter(adapter);




    }

    private void checkInternet(){
        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private List<Product> createListProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("men T - shirt",R.drawable.item_1,15,4,500,"\n" +
                "Newly available as a long-sleeve T-shirt. Smooth 'AIRism' fabric with the look of cotton."));
        products.add(new Product("Apple Watch 3",R.drawable.item_2,15,4,500,"test1"));
        products.add(new Product("Iphone 13 Pro Max",R.drawable.item_3,15,4,500,"Dù là giải trí khi xem phim, chơi game hay kiểm tra email, đọc tài liệu thì màn hình lớn tới 6,7 inch của iPhone 13 Pro Max cũng luôn cho trải nghiệm tuyệt vời nhất.\n" +
                "\n" +
                "Không chỉ lớn, đây còn là màn hình chất lượng hàng đầu thế giới smartphone với tấm nền OLED tuyệt đẹp, công nghệ Super Retina XDR siêu nét và độ sáng tối đa đạt mức khó tin, lên tới 1200 nits cho nội dung HDR. Trước mắt bạn là một không gian giải trí mãn nhãn, một thiết bị di động lý tưởng để giải quyết nhanh công việc với màn hình thực sự xuất sắc."));
        products.add(new Product("Mac Book M1",R.drawable.item_4,12,3,10000,"Nếu bạn muốn mua Macbook Air 2020 M1 chính hãng thì hãy nhanh tay liên hệ đến Hotline: 1800.2097 hay đến trực tiếp cửa hàng thuộc hệ thống CellphoneS để tiến hành đặt hàng với mức giá cạnh tranh nhất. Với chế độ bảo hành chính hãng tại trung tâm ủy quyền Apple Việt Nam lên đến 12 tháng, 1 đổi 1 trong 30 ngày nếu có lỗi từ nhà sản xuất, bạn có thể hoàn toàn an tâm khi mua sản phẩm Macbook Air M1 tại CellphoneS."));
        return products;
    }

    private void pushData(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("product");
        Product product = new Product("Iphone 13 Pro Max",R.drawable.item_3,15,4,500,"Dù là giải trí khi xem phim, chơi game hay kiểm tra email, đọc tài liệu thì màn hình lớn tới 6,7 inch của iPhone 13 Pro Max cũng luôn cho trải nghiệm tuyệt vời nhất.\n" +
                "\n" +
                "Không chỉ lớn, đây còn là màn hình chất lượng hàng đầu thế giới smartphone với tấm nền OLED tuyệt đẹp, công nghệ Super Retina XDR siêu nét và độ sáng tối đa đạt mức khó tin, lên tới 1200 nits cho nội dung HDR. Trước mắt bạn là một không gian giải trí mãn nhãn, một thiết bị di động lý tưởng để giải quyết nhanh công việc với màn hình thực sự xuất sắc.");
        myRef.setValue(product, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "push ok", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getData(Context context){
        final Temp temp = new Temp();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("product");
        List<Product> products = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                products.clear();
//                    temp.complete = false;
                    Product product = snapshot.getValue(Product.class);
                    products.add(product);
//                    temp.complete = true;

                ProductAdapter adapter = new ProductAdapter(products, new IClickItemProductListener() {
                    @Override
                    public void clickItemProduct(Product product) {
                        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("product",product);
                        intent.putExtra("data",bundle);
                        startActivity(intent);
                    }
                });
                binding.PopularView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                binding.PopularView.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
//        while(!temp.complete) {
//        }
    }
}






//        binding = .inflate(getLayoutInflater());
//        View view = binding.getRoot();
//        setContentView(view);


//        mydatabase = openOrCreateDatabase("ecomapp.db",MODE_PRIVATE,null);
//
//        /*Create table/
//
//         */
//
//        try {
//            String sql = "CREATE TABLE products(id INTEGER primary key autoincrement," +
//                    "title TEXT," +
//                    "picUrl TEXT," +
//                    "review INTEGER," +
//                    "score DOUBLE," +
//                    "price DOUBLE," +
//                    "description TEXT)";
//            mydatabase.execSQL(sql);
//
//        }
//        catch (Exception e){
//            Log.d("Error",e.getMessage());
//        }


// insert data
//        ContentValues values = new ContentValues();
//        values.put("title","Iphone17");
//        values.put("picUrl","item_1");
//        values.put("review",123);
//        values.put("score",5);
//        values.put("price",1234);
//        values.put("description","Demo Desc");
//        String msg = "";
//insert -1 : fail
//insert 1 : success
//        if (mydatabase.insert("products",null,values) == -1){
//            msg = "Insert fail";
//        }
//        else{
//            msg = "Insert success";
//        }

//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

//        products = createListProducts();

//        Cursor c = mydatabase.query("products",null,null,null,null,null,null);
//        c.moveToNext();
//        while (c.isAfterLast() == false){
//            Product product = new Product(c.getString(1),R.drawable.item_1,c.getInt(3),c.getDouble(4),c.getDouble(5),c.getString(6));
//            c.moveToNext();
//            products.add(product);
//        }
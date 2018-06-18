package com.example.webwerks.neostore.view.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.APIClient;
import com.example.webwerks.neostore.common.base.APIInterface;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.model.ProductDetailsModel;
import com.example.webwerks.neostore.view.home.adapter.HomeBannerSliderAdapter;
import com.example.webwerks.neostore.view.product.adapter.ProductDetailsAdapter;
import com.example.webwerks.neostore.view.product.fragment.EnterQuantityFragment;
import com.example.webwerks.neostore.view.product.fragment.RatingPopUpFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ImageView imgSelected;
    private ProductDetailsAdapter mAdapter;
    private RecyclerView pdList;
    private ImageView share;
    private Button buy,rate;
    private DialogFragment dialogFragment;

    private TextView txtPdName, txtPdCategory, txtPdCenter,txtPdCost, txtPdOutofStock,txtDescription;
    private RatingBar rbPdRating;
    ProductDetailsModel productDetailsModel;

    private APIInterface apiInterface;
    private String TAG=ProductDetailsActivity.class.getSimpleName();

    @Override
    protected String setTitle() {
        return getIntent().getStringExtra("Data");
    }

    @Override
    public int getContentView() {
        return R.layout.activity_product_details;
    }

    @Override
    public void initView() {
        imgSelected=findViewById(R.id.img_selected);

        txtPdName=findViewById(R.id.pd_name);
        txtPdCategory=findViewById(R.id.pd_category);
        txtPdCenter=findViewById(R.id.pd_center);
        txtPdCost=findViewById(R.id.pd_cost);
        txtPdOutofStock=findViewById(R.id.txtOutOfStock);
        txtDescription=findViewById(R.id.description);
        rbPdRating=findViewById(R.id.rb_pd_rating);
        pdList=findViewById(R.id.recycler_view);
        share=findViewById(R.id.share);
        buy=findViewById(R.id.buy_now);
        rate=findViewById(R.id.rate);

        apiInterface= APIClient.getClient().create(APIInterface.class);
        Call call=apiInterface.getProductDetails(getIntent().getStringExtra("id"));
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                productDetailsModel= (ProductDetailsModel) response.body();
                if(productDetailsModel.getStatus()==200){
                    txtPdName.setText(productDetailsModel.getData().getName());
                    if(productDetailsModel.getData().getProduct_category_id()==1)
                     txtPdCategory.setText("Category - Tables");
                    else if(productDetailsModel.getData().getProduct_category_id()==2)
                        txtPdCategory.setText("Category - Chairs");
                    else if(productDetailsModel.getData().getProduct_category_id()==3)
                        txtPdCategory.setText("Category - Sofas");
                    else txtPdCategory.setText("Category - Cupboards");
                    Glide.with(getApplicationContext()).load(productDetailsModel.getData().
                            getProductImagesModels().get(0).getImage())
                            .into(imgSelected);
                    txtPdCenter.setText(productDetailsModel.getData().getProducer());
                    txtPdCost.setText("Rs. "+productDetailsModel.getData().getCost());
                    txtDescription.setText(productDetailsModel.getData().getDescription());
                    rbPdRating.setRating(productDetailsModel.getData().getRating());
                    setAdapter();
                }

                else{
                    Toast.makeText(ProductDetailsActivity.this, "Error in fetching data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });

    }

    @Override
    public void setListener() {
        share.setOnClickListener(this);
        buy.setOnClickListener(this);
        rate.setOnClickListener(this);
    }

    @Override
    protected boolean needActionBar() {
        return true;
    }

    public void setAdapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        pdList.setLayoutManager(layoutManager);
        mAdapter= new ProductDetailsAdapter(this,productDetailsModel.getData().getProductImagesModels(),imgSelected);
        pdList.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        Bundle bundle=new Bundle();
        switch (view.getId()){
            case R.id.share:
                intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String choose=getString(R.string.choose);
                intent.putExtra(intent.EXTRA_TEXT,"");
                Intent chooser=Intent.createChooser(intent,choose);
                if(intent.resolveActivity(getPackageManager())!= null)
                    startActivity(chooser);
                break;
            case R.id.buy_now:
                dialogFragment=new EnterQuantityFragment();
                bundle.putString("product_image",productDetailsModel.getData().
                        getProductImagesModels().get(0).getImage());
                bundle.putString("product_name",productDetailsModel.getData().getName());
                bundle.putInt("product_id", productDetailsModel.getData().getId());
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(),"buy_product");
                break;
            case R.id.rate:
                dialogFragment=new RatingPopUpFragment();
                bundle.putString("product_image",productDetailsModel.getData().
                        getProductImagesModels().get(0).getImage());
                bundle.putString("product_name",productDetailsModel.getData().getName());
                bundle.putString("product_id", String.valueOf(productDetailsModel.getData().getId()));
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(),"rate_product");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home: onBackPressed();
                return true;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pdList.smoothScrollToPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}

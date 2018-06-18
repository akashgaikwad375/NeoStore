package com.example.webwerks.neostore.view.product.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.common.base.BaseAsyncTask;
import com.example.webwerks.neostore.common.base.onAsyncTaskRequest;
import com.example.webwerks.neostore.model.ProductListModel;
import com.example.webwerks.neostore.view.product.adapter.ProductListingAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListingActivity extends BaseActivity implements onAsyncTaskRequest {

    private ProductListingAdapter mAdapter;
    private RecyclerView productList;
    String productId;
    List<ProductListModel> data = new ArrayList<>();
    Map<String, Object> parameter = new HashMap<>();
    private String url = "http://staging.php-dev.in:8844/trainingapp/api/products/getList";

    @Override
    protected String setTitle() {
        return getIntent().getStringExtra("Title");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_product_listing;
    }

    @Override
    protected void initView() {
        productList = findViewById(R.id.recycler_view);
        productId = getIntent().getStringExtra("product_category_id");
        parameter.put("product_category_id", productId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        productList.setHasFixedSize(true);
        BaseAsyncTask task = new BaseAsyncTask(this,"GET",parameter);
        task.execute(url);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected boolean needActionBar() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }

    @Override
    public void onSuccess(String obj) {
        try {
            JSONObject jsonObject = new JSONObject((String) obj);
            int status = jsonObject.optInt("status");
            JSONArray dataArray = jsonObject.optJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject object = dataArray.getJSONObject(i);
                ProductListModel plModel = new ProductListModel();
                plModel.setId(object.optInt("id"));
                plModel.setProduct_category_id(object.optInt("product_category_id"));
                plModel.setName(object.optString("name"));
                plModel.setProducer(object.optString("producer"));
                plModel.setDescription(object.optString("description"));
                plModel.setCost(object.optInt("cost"));
                plModel.setRating(object.optInt("rating"));
                plModel.setView_count(object.optInt("view_count"));
                plModel.setCreated(object.optString("created"));
                plModel.setModified(object.optString("modified"));
                plModel.setProduct_images(object.getString("product_images"));
                data.add(plModel);
            }
            mAdapter = new ProductListingAdapter(this, data);
            productList.addItemDecoration(new
                        DividerItemDecoration(this,
                        DividerItemDecoration.VERTICAL));
            productList.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
        }
    }

    @Override
    public void onFaliure(String obj) {
        Toast.makeText(getApplicationContext(),
                "Error in fetching data.", Toast.LENGTH_SHORT).show();
    }
}
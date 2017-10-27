package com.example.webwerks.neostore.view.product.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.model.ProductListModel;
import com.example.webwerks.neostore.view.product.adapter.ProductListingAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListingActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView title;
    private ProductListingAdapter mAdapter;
    private RecyclerView productList;
    String productId;
    List<ProductListModel> data = new ArrayList<>();
    Map<String, Object> parameter = new HashMap<>();
    private String url = "http://staging.php-dev.in:8844/trainingapp/api/products/getList";

    @Override
    protected int getContentView() {
        return R.layout.activity_product_listing;
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        title = toolbar.findViewById(R.id.title);
        productList = findViewById(R.id.recycler_view);

        Intent intent = getIntent();
        productId = intent.getStringExtra("product_category_id");
        parameter.put("product_category_id", productId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        productList.setHasFixedSize(true);
        ProductListingAsyncTask task = new ProductListingAsyncTask(parameter, this);
        task.execute(url);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String s = getIntent().getStringExtra("Title");
        title.setText(s);
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

    public class ProductListingAsyncTask extends AsyncTask<String, Void, String> {

        private final String TAG = ProductListingAsyncTask.class.getSimpleName();
        private Map<String, Object> mdata;
        int statusCode;
        StringBuffer sb = new StringBuffer("");

        public ProductListingAsyncTask(Map<String, Object> data, ProductListingActivity productListingActivity) {

            if (data != null)
                mdata = data;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                StringBuilder result = new StringBuilder(strings[0]);
                result.append("?product_category_id=" + productId);
                URL url = new URL(result.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                statusCode = connection.getResponseCode();
                if (statusCode == 200) {
                    Log.e(TAG, "doInBackground: " + statusCode);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                } else {
                    Log.e(TAG, "doInBackground: " + statusCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("onPostExecute", s);
            try {
                if (statusCode == 200) {
                    JSONObject jsonObject = new JSONObject(s);
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

                    mAdapter = new ProductListingAdapter(getApplicationContext(), data);
                    productList.addItemDecoration(new
                            DividerItemDecoration(getApplicationContext(),
                            DividerItemDecoration.VERTICAL));
                    productList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Error in fetching data.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
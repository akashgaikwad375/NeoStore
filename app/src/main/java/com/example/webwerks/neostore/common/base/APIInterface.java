package com.example.webwerks.neostore.common.base;

import com.example.webwerks.neostore.model.BaseModel;
import com.example.webwerks.neostore.model.ProductDetailsModel;

import java.util.SimpleTimeZone;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("products/getDetail")
    Call<ProductDetailsModel> getProductDetails(@Query("product_id") String product_id);

    @FormUrlEncoded
    @POST("users/forgot")
    Call<BaseModel> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("users/change")
    Call<BaseModel> resetPassword(@Header("access_token") String access_token,
                                  @Field("old_password") String old_password,
                                  @Field("password") String password,
                                  @Field("confirm_password") String confirm_password);

    @FormUrlEncoded
    @POST("addToCart")
    Call<BaseModel> addToCart(@Header("access_token") String access_token,
                                  @Field("product_id") int product_id,
                                  @Field("quantity") int quantity);

    @FormUrlEncoded
    @POST("products/setRating")
    Call<BaseModel> productRating(@Field("product_id") String product_id,
                              @Field("rating") int rating);
}

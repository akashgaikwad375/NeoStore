package com.example.webwerks.neostore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by webwerks on 7/11/17.
 */

public class ProductImagesModel {

    @SerializedName("id")
    private int id;
    @SerializedName("product_id")
    private int product_id;
    @SerializedName("image")
    private String image;
    @SerializedName("created")
    private String created;
    @SerializedName("modified")
    private String modified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

}

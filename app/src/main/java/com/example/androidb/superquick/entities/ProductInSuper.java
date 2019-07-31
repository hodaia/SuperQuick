package com.example.androidb.superquick.entities;

import com.parse.ParseObject;
import com.parse.ParseClassName;

@ParseClassName("ProductInSuper")
public class ProductInSuper extends ParseObject {
    private int productInSuper_superId;
    private int productInSuper_productId;
    private int productInSuperPrice;
    private int productInSuper_columnId;
    private int productInSuper_rowId;

    public ProductInSuper() {
    }

    public ProductInSuper(int productInSuper_superId, int productInSuper_productId, int productInSuper_price) {
        setProductInSuper_productId(productInSuper_productId);
        setProductInSuper_superId(productInSuper_superId);
        setProductInSuperPrice(productInSuper_price);
    }

    public int getProductInSuper_superId() {
        return getInt("productInSuper_superId");
    }

    public void setProductInSuper_superId(int productInSuper_superId) {
        put("productInSuper_superId", productInSuper_superId);
    }

    public int getProductInSuper_productId() {
        return getInt("productInSuper_productId");
    }

    public void setProductInSuper_productId(int productInSuper_productId) {
        put("productInSuper_productId", productInSuper_productId);
    }

    public int getProductInSuperPrice() {
        return getInt("productInSuperPrice");
    }

    public void setProductInSuperPrice(int productInSuperPrice) {
        put("productInSuperPrice", productInSuperPrice);
    }

    public int getProductInSuper_columnId() {
        return getInt("productInSuper_columnId");
    }

    public void setProductInSuper_columnId(int productInSuper_columnId) {
        put("productInSuper_columnId",productInSuper_columnId);
    }

    public int getProductInSuper_rowId() {
        return getInt("productInSuper_rowId");
    }

    public void setProductInSuper_rowId(int productInSuper_rowId) {
        put("productInSuper_rowId",productInSuper_rowId);
    }
}

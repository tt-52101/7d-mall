package com.dunshan.mall.model;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车数据包装
 * @author dunshan
 */
public class OmsCartItemVo implements Serializable {

    private List<OmsCartItem> cartItemList;

    public List<OmsCartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<OmsCartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}

package com.dunshan.mall.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dunshan on 2018/8/27.
 * 购物车中促销信息的封装
 */
public class CartPromotionItemVo {

    private List<CartPromotionItem> cartPromotionItemList;
    /**
     * 类型
     */
    private Long type;

    /**
     * 用户ID
     */
    private Long id;


    public List<CartPromotionItem> getCartPromotionItemList() {
        return cartPromotionItemList;
    }

    public void setCartPromotionItemList(List<CartPromotionItem> cartPromotionItemList) {
        this.cartPromotionItemList = cartPromotionItemList;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

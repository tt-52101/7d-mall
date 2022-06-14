package com.dunshan.mall.product.service;


import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.product.domain.CartProduct;
import com.dunshan.mall.product.domain.PromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PromotionProductService {

    /**
     * 获取购物车中用于选择商品规格的商品信息
     *
     * @param id
     * @return
     */
    CartProduct getCartProduct(Long id);

    /**
     *  查询所有商品的优惠相关信息
     * @param ids
     * @return
     */
    List<PromotionProduct> getPromotionProductList(List<Long> ids);

    /**
     * 商品可用优惠券
     * @param productId
     * @param productCategoryId
     * @return
     */
    List<SmsCoupon> getAvailableCouponList(Long productId, Long productCategoryId);
}

package com.dunshan.mall.product.dao;

import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.product.domain.CartProduct;
import com.dunshan.mall.product.domain.PromotionProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 前台系统自定义商品Dao
 * Created by dunshan on 2018/8/2.
 */
@Repository
@Mapper
public interface ProductDao {
    CartProduct getCartProduct(@Param("id") Long id);
    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);
    List<SmsCoupon> getAvailableCouponList(@Param("productId") Long productId, @Param("productCategoryId") Long productCategoryId);
}

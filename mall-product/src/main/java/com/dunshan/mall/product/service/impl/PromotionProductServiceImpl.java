package com.dunshan.mall.product.service.impl;

import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.product.dao.ProductDao;
import com.dunshan.mall.product.domain.CartProduct;
import com.dunshan.mall.product.domain.PromotionProduct;
import com.dunshan.mall.product.service.PromotionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dunshan
 * @program: mall-microservice
 * @description: 促销商品查询
 * @date 2022-05-21 14:36:03
 */
@Service
public class PromotionProductServiceImpl implements PromotionProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public CartProduct getCartProduct(Long id) {
        return productDao.getCartProduct(id);
    }

    @Override
    public List<PromotionProduct> getPromotionProductList(List<Long> ids) {
        return productDao.getPromotionProductList(ids);
    }

    @Override
    public List<SmsCoupon> getAvailableCouponList(Long productId, Long productCategoryId) {
        return productDao.getAvailableCouponList(productId, productCategoryId);
    }
}

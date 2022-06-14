package com.dunshan.mall.product.controller;

import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.product.domain.CartProduct;
import com.dunshan.mall.product.domain.PromotionProduct;
import com.dunshan.mall.product.service.PromotionProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dunshan
 * @program: mall-microservice
 * @description: 自定义商品查询
 * @date 2022-05-21 14:40:41
 */
@Controller
@Api(tags = "PmsProductController", description = "前台商品管理")
@RequestMapping("/product/promotion")
public class PromotionProductController {

    @Autowired
    PromotionProductService promotionProductService;


    @ApiOperation("获取购物车中用于选择商品规格的商品信息")
    @RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CartProduct getCartProduct(@PathVariable Long id) {
        return promotionProductService.getCartProduct(id);
    }

    @ApiOperation("查询所有商品的优惠相关信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<PromotionProduct> getPromotionProductList(@RequestBody List<Long> productIdList) {
        return promotionProductService.getPromotionProductList(productIdList);
    }

    @ApiOperation("商品可用优惠券")
    @RequestMapping(value = "/coupon/list", method = RequestMethod.POST)
    @ResponseBody
    public List<SmsCoupon> getAvailableCouponList(@RequestParam("productId") Long productId, @RequestParam("productCategoryId") Long productCategoryId) {
        return promotionProductService.getAvailableCouponList(productId, productCategoryId);
    }


}

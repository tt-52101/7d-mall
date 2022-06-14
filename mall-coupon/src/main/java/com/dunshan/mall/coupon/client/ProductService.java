package com.dunshan.mall.coupon.client;

import com.dunshan.mall.coupon.domain.PromotionProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("mall-product")
public interface ProductService {

    @RequestMapping(value = "/product/promotion/list", method = RequestMethod.POST)
    List<PromotionProduct> getPromotionProductList(@RequestBody List<Long> productIdList);
}

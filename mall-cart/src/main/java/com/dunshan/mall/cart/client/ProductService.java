package com.dunshan.mall.cart.client;

import com.dunshan.mall.cart.domain.CartProduct;
import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.PmsProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@FeignClient("mall-product")
@Component
public interface ProductService {

    @RequestMapping(value = "/product/promotion/cart/{id}", method = RequestMethod.GET)
    CartProduct getCartProduct(Long productId);


    @RequestMapping(value = "/product/query/", method = RequestMethod.POST)
    CommonResult<PmsProduct> selectByProduct(@RequestParam("productId") Long productId);
}

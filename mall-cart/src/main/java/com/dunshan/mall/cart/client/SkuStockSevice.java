package com.dunshan.mall.cart.client;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.PmsSkuStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("mall-stock")
@Component
public interface SkuStockSevice {

    @PostMapping("/ware/waresku/query/productId")
    CommonResult<List<PmsSkuStock>> pmsSkuStocksById(@RequestParam("productSkuCode") String productSkuCode);
}

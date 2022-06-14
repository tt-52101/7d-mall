package com.dunshan.mall.stock.service;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.PmsSkuStock;

import java.util.List;
import java.util.Map;


public interface WareSkuService {


    List<PmsSkuStock> getSkuHasStock(List<Long> skuIds);

    List<PmsSkuStock> queryPage(Map<String, Object> params);

    PmsSkuStock getById(Long id);

    int save(PmsSkuStock pmsSkuStock);

    /**
     * 更新
     *
     * @param pmsSkuStock
     * @return
     */
    int pmsSkuStockId(PmsSkuStock pmsSkuStock);

    CommonResult removeByIds(List<Long> asList);

    List<PmsSkuStock> getSkuCode(String productSkuCode);
}

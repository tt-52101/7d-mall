package com.dunshan.mall.brand.service;

import com.dunshan.mall.brand.domain.HomeContentResult;
import com.dunshan.mall.common.api.CommonPage;
import com.dunshan.mall.model.PmsBrand;
import com.dunshan.mall.model.PmsProduct;

import java.util.List;

/**
 * 前台品牌管理Service
 * Created by dunshan on 2020/5/15.
 */
public interface BrandService {
    /**
     * 分页获取推荐品牌
     */
    List<PmsBrand> recommendList(Integer pageNum, Integer pageSize);

    /**
     * 获取品牌详情
     */
    PmsBrand detail(Long brandId);

    /**
     * 分页获取品牌关联商品
     */
    CommonPage<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize);


    HomeContentResult content(Integer page, Integer size);
}

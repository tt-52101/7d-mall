package com.dunshan.mall.product.service;

import com.dunshan.mall.model.PmsProduct;
import com.dunshan.mall.product.domain.PmsProductDetail;
import com.dunshan.mall.product.domain.PmsProductCategoryNode;

import java.util.List;

/**
 * @author dunshan
 * @program: mall-microservice
 * @description:
 * @date 2022-05-21 14:00:19
 */
public interface ProductService {
    /**
     * 综合搜索商品
     */
    List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

    /**
     * 以树形结构获取所有商品分类
     */
    List<PmsProductCategoryNode> categoryTreeList();

    /**
     * 获取前台商品详情
     */
    PmsProductDetail detail(Long id);

    /**
     * 根据商品Id 查询商品
     *
     * @param id
     * @return
     */
    PmsProduct selectByProduct(Long id);
}

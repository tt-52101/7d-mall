package com.dunshan.mall.product.controller;

import com.dunshan.mall.common.api.CommonPage;
import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.PmsProduct;
import com.dunshan.mall.product.domain.PmsProductCategoryNode;
import com.dunshan.mall.product.domain.PmsProductDetail;
import com.dunshan.mall.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @program: mall-microservice
 * @description: 商品服务
 * @date 2022-05-21 13:56:36
 */
@Controller
@Api(tags = "PmsProductController", description = "前台商品管理")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation(value = "综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> search(@RequestParam(required = false) String keyword,
                                                       @RequestParam(required = false) Long brandId,
                                                       @RequestParam(required = false) Long productCategoryId,
                                                       @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                       @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                       @RequestParam(required = false, defaultValue = "0") Integer sort) {
        List<PmsProduct> productList = productService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("以树形结构获取所有商品分类")
    @RequestMapping(value = "/categoryTreeList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategoryNode>> categoryTreeList() {
        List<PmsProductCategoryNode> list = productService.categoryTreeList();
        return CommonResult.success(list);
    }

    @ApiOperation("获取前台商品详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductDetail> detail(@PathVariable Long id) {
        PmsProductDetail productDetail = productService.detail(id);
        return CommonResult.success(productDetail);
    }


    @ApiOperation("根据Id查询商品")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    CommonResult<PmsProduct> selectByProduct(@RequestParam("productId") Long productId) {
        PmsProduct productDetail = productService.selectByProduct(productId);
        return CommonResult.success(productDetail);
    }


}

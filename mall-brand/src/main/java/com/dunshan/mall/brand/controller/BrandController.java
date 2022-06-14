package com.dunshan.mall.brand.controller;

import com.dunshan.mall.brand.domain.HomeContentResult;
import com.dunshan.mall.brand.service.BrandService;
import com.dunshan.mall.common.api.CommonPage;
import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.PmsBrand;
import com.dunshan.mall.model.PmsProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页品牌推荐管理Controller
 * Created by dunshan on 2020/5/15.
 */
@Controller
@Api(tags = "PortalBrandController", description = "前台品牌管理")
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ApiOperation("分页获取推荐品牌")
    @RequestMapping(value = "/recommendList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> recommendList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsBrand> brandList = brandService.recommendList(pageNum, pageSize);
        return CommonResult.success(brandList);
    }

    @ApiOperation("获取品牌详情")
    @RequestMapping(value = "/detail/{brandId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> detail(@PathVariable Long brandId) {
        PmsBrand brand = brandService.detail(brandId);
        return CommonResult.success(brand);
    }

    @ApiOperation("分页获取品牌相关商品")
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> productList(@RequestParam Long brandId,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        CommonPage<PmsProduct> result = brandService.productList(brandId, pageNum, pageSize);
        return CommonResult.success(result);
    }

    @ApiOperation("分页获取品牌相关商品")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ResponseBody
    HomeContentResult content(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        return brandService.content(pageSize, pageNum);
    }


}

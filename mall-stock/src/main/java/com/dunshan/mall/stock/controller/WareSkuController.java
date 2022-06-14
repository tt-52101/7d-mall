package com.dunshan.mall.stock.controller;


import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.PmsSkuStock;
import com.dunshan.mall.stock.service.WareSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品库存
 *
 * @author
 * @email
 * @date
 */
@RestController
@Api(tags = "stockController", description = "前台库存管理")
@RequestMapping("ware/waresku")
public class WareSkuController {

    @Autowired
    private WareSkuService wareSkuService;

    /**
     * 查询sku是否有库存
     *
     * @return
     */
    @ApiOperation(value = "查询sku是否有库存")
    @PostMapping(value = "/hasStock")
    public CommonResult getSkuHasStock(@RequestBody List<Long> skuIds) {
        //skuId stock
        List<PmsSkuStock> vos = wareSkuService.getSkuHasStock(skuIds);
        return CommonResult.success(vos);
    }

    /**
     * 列表
     */
    @ApiOperation(value = "查询库存列表")
    @RequestMapping("/list")
    public CommonResult list(@RequestParam Map<String, Object> params) {
        List<PmsSkuStock> page = wareSkuService.queryPage(params);
        return CommonResult.success(page);
    }

    /**
     * 信息
     */
    @ApiOperation(value = "库存信息")
    @RequestMapping("/info/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        PmsSkuStock wareSku = wareSkuService.getById(id);

        return CommonResult.success(wareSku);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存库存")
    @RequestMapping("/save")
    public CommonResult save(@RequestBody PmsSkuStock pmsSkuStock) {

        return CommonResult.success(wareSkuService.save(pmsSkuStock));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改库存")
    @RequestMapping("/update")
    public CommonResult update(@RequestBody PmsSkuStock pmsSkuStock) {
        return CommonResult.success(wareSkuService.pmsSkuStockId(pmsSkuStock));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除库存")
    @RequestMapping("/delete")
    public CommonResult delete(@RequestBody Long[] ids) {

        return CommonResult.success(wareSkuService.removeByIds(Arrays.asList(ids)));
    }


    /**
     * 根据商品id查询库存数据
     *
     * @param productSkuCode
     * @return
     */
    @ApiOperation(value = "根据sku编码查询库存数据")
    @PostMapping("/query/productId")
    CommonResult<List<PmsSkuStock>> pmsSkuStocksById(@RequestParam("productSkuCode") String productSkuCode) {
        List<PmsSkuStock> list = wareSkuService.getSkuCode(productSkuCode);
        return CommonResult.success(list);
    }

}

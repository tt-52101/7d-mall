package com.dunshan.mall.stock.service.impl;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.mapper.PmsSkuStockMapper;
import com.dunshan.mall.model.PmsSkuStock;
import com.dunshan.mall.model.PmsSkuStockExample;
import com.dunshan.mall.stock.service.WareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author dunshan
 * @program:
 * @description:
 * @date 2022-05-21 21:11:58
 */
@Service
public class WareSkuSeviceImpl implements WareSkuService {

    @Autowired
    private PmsSkuStockMapper skuStockMapper;

    @Override
    public List<PmsSkuStock> getSkuHasStock(List<Long> skuIds) {
        return null;
    }

    @Override
    public List<PmsSkuStock> queryPage(Map<String, Object> params) {
        PmsSkuStockExample example = new PmsSkuStockExample();
        return skuStockMapper.selectByExample(example);
    }

    @Override
    public PmsSkuStock getById(Long id) {
        return skuStockMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(PmsSkuStock pmsSkuStock) {
        return skuStockMapper.insert(pmsSkuStock);
    }

    @Override
    public int pmsSkuStockId(PmsSkuStock pmsSkuStock) {
        return skuStockMapper.updateByPrimaryKey(pmsSkuStock);
    }

    @Override
    public CommonResult removeByIds(List<Long> asList) {
        for (Long aLong : asList) {
            skuStockMapper.deleteByPrimaryKey(aLong);
        }
        return CommonResult.success(1);
    }

    @Override
    public List<PmsSkuStock> getSkuCode(String productSkuCode) {
        PmsSkuStockExample example = new PmsSkuStockExample();
        example.createCriteria().andSkuCodeEqualTo(productSkuCode);
        List<PmsSkuStock> pmsSkuStocks = skuStockMapper.selectByExample(example);
        return pmsSkuStocks;
    }

}

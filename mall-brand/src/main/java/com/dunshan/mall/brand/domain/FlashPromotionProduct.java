package com.dunshan.mall.brand.domain;

import com.dunshan.mall.model.PmsProduct;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 秒杀信息和商品对象封装
 * Created by dunshan on 2019/1/28.
 */
@Getter
@Setter
public class FlashPromotionProduct extends PmsProduct{
    private BigDecimal flashPromotionPrice;
    private Integer flashPromotionCount;
    private Integer flashPromotionLimit;
}

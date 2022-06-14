package com.dunshan.mall.cart.client;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.CartPromotionItem;
import com.dunshan.mall.model.OmsCartItem;
import com.dunshan.mall.model.OmsCartItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description:
 * @author: 李文
 * @create: 2022-05-21 09:33
 **/
@FeignClient("mall-coupon")
@Component
public interface CouponPromotionService {
    /**
     * 计算购物车中的促销活动信息
     *
     * @param omsCartItemVo
     * @return
     */
    @PostMapping("/coupon/promotion/CartPromotion")
    CommonResult<List<CartPromotionItem>> calcCartPromotion(@RequestBody OmsCartItemVo omsCartItemVo);
}


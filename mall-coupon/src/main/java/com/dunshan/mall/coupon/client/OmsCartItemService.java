package com.dunshan.mall.coupon.client;

import com.dunshan.mall.model.CartPromotionItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("mall-cart")
public interface OmsCartItemService {
    /**
     * 获取包含促销活动信息的购物车列表
     */
    @RequestMapping(value = "/list/promotion", method = RequestMethod.GET)
    List<CartPromotionItem> listPromotion(@RequestParam("memberId") Long memberId, @RequestParam("cartIds")  List<Long> cartIds);
}

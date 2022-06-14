package com.dunshan.mall.order.client;


import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.CartPromotionItemVo;
import com.dunshan.mall.model.SmsCouponHistoryDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-coupon")
public interface CouponService {

//    List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartPromotionItemList, Integer type);

    @PostMapping("/coupon/promotion/CartPromotion/list")
    CommonResult<List<SmsCouponHistoryDetail>> listCart(@RequestBody CartPromotionItemVo cartPromotionItemVo);
}

package com.dunshan.mall.order.client;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.CartPromotionItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description:
 * @author: 李文
 * @create: 2022-05-21 07:12
 **/
@FeignClient("mall-cart")
public interface OmsCartItemService {

    /**
     * 获取某个会员的购物车列表,包括促销信息
     *
     * @param id
     * @param cartIds
     * @return
     */
    @RequestMapping(value = "/cart/member/list/promotion", method = RequestMethod.POST)
    CommonResult<List<CartPromotionItem>> listPromotionMember(@RequestParam("id") Long id, @RequestParam("cartIds") List<Long> cartIds);

    /**
     * 删除购物车中的某个商品
     *
     * @param id
     * @param ids
     */
    @RequestMapping(value = "/cart/delete", method = RequestMethod.POST)
    CommonResult delete(@RequestParam("id") Long id, @RequestParam("ids") List<Long> ids);
}


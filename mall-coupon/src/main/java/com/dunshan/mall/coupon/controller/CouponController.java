package com.dunshan.mall.coupon.controller;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.coupon.service.OmsPromotionService;
import com.dunshan.mall.model.*;
import com.dunshan.mall.coupon.domain.SmsCouponHistoryDetail;
import com.dunshan.mall.coupon.client.OmsCartItemService;
import com.dunshan.mall.coupon.service.CouponService;
import com.dunshan.mall.coupon.client.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户优惠券管理Controller
 * Created by dunshan on 2018/8/29.
 */
@Controller
@Api(tags = "UmsMemberCouponController", description = "用户优惠券管理")
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService memberCouponService;
    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsMemberService memberService;

    @Autowired
    OmsPromotionService omsPromotionService;

    @ApiOperation("领取指定优惠券")
    @RequestMapping(value = "/add/{couponId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@PathVariable Long couponId) {
        memberCouponService.add(couponId);
        return CommonResult.success(null, "领取成功");
    }

    @ApiOperation("获取用户优惠券历史列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/listHistory", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCouponHistory>> listHistory(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponHistory> couponHistoryList = memberCouponService.listHistory(useStatus);
        return CommonResult.success(couponHistoryList);
    }

    @ApiOperation("获取用户优惠券列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCoupon>> list(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCoupon> couponList = memberCouponService.list(useStatus);
        return CommonResult.success(couponList);
    }

    @ApiOperation("获取登录会员购物车的相关优惠券")
    @ApiImplicitParam(name = "type", value = "使用可用:0->不可用；1->可用",
            defaultValue = "1", allowableValues = "0,1", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list/cart/{type}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCouponHistoryDetail>> listCart(@PathVariable Long type) {
        Long id = memberService.getCurrentMember().getId();

        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(id, null);
        CartPromotionItemVo cartPromotionItemVo = new CartPromotionItemVo();
        cartPromotionItemVo.setCartPromotionItemList(cartPromotionItemList);
        cartPromotionItemVo.setId(id);   //用户ID
        cartPromotionItemVo.setType(type);
        List<SmsCouponHistoryDetail> couponHistoryList = memberCouponService.listCart(cartPromotionItemVo);
        return CommonResult.success(couponHistoryList);
    }

    @ApiOperation("获取当前商品相关优惠券")
    @RequestMapping(value = "/listByProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCoupon>> listByProduct(@PathVariable Long productId) {
        List<SmsCoupon> couponHistoryList = memberCouponService.listByProduct(productId);
        return CommonResult.success(couponHistoryList);
    }

    @ApiOperation("计算购物车中的促销活动信息")
    @PostMapping("/promotion/CartPromotion")
    @ResponseBody
    CommonResult<List<CartPromotionItem>> calcCartPromotion(@RequestBody OmsCartItemVo omsCartItemVo) {
        List<CartPromotionItem> cartPromotionItems = omsPromotionService.calcCartPromotion(omsCartItemVo.getCartItemList());
        return CommonResult.success(cartPromotionItems);
    }


    @ApiOperation("根据购物车信息获取可用优惠券")
    @ResponseBody
    @PostMapping("/promotion/CartPromotion/list")
    CommonResult<List<SmsCouponHistoryDetail>> listCart(@RequestBody CartPromotionItemVo cartPromotionItemVo) {
        List<SmsCouponHistoryDetail> smsCouponHistoryDetails = memberCouponService.listCart(cartPromotionItemVo);
        return CommonResult.success(smsCouponHistoryDetails);
    }


}

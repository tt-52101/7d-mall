package com.dunshan.mall.coupon.service;

import com.dunshan.mall.coupon.domain.SmsCouponHistoryDetail;
import com.dunshan.mall.model.CartPromotionItem;
import com.dunshan.mall.model.CartPromotionItemVo;
import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.model.SmsCouponHistory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员优惠券管理Service实现类
 */
public interface CouponService {
    /**
     * 会员添加优惠券
     */
    @Transactional
    void add(Long couponId);

    /**
     * 获取优惠券历史列表
     */
    List<SmsCouponHistory> listHistory(Integer useStatus);

    /**
     * 根据购物车信息获取可用优惠券
     * @param cartPromotionItemVo
     * @return
     */
    List<SmsCouponHistoryDetail> listCart(CartPromotionItemVo cartPromotionItemVo);

    /**
     * 获取当前商品相关优惠券
     */
    List<SmsCoupon> listByProduct(Long productId);

    /**
     * 获取用户优惠券列表
     */
    List<SmsCoupon> list(Integer useStatus);
}

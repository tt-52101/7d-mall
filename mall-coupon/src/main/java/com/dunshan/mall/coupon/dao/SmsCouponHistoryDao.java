package com.dunshan.mall.coupon.dao;

import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.coupon.domain.SmsCouponHistoryDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员优惠券领取历史自定义Dao
 * Created by dunshan on 2018/8/29.
 */
public interface SmsCouponHistoryDao {
    /**
     * 获取该用户所有优惠券
     *
     * @param memberId
     * @return
     */
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);

    /**
     * 获取用户优惠券列表
     *
     * @param memberId
     * @param useStatus
     * @return
     */
    List<SmsCoupon> getCouponList(@Param("memberId") Long memberId, @Param("useStatus") Integer useStatus);
}

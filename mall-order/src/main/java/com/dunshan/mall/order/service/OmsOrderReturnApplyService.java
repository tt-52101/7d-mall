package com.dunshan.mall.order.service;

import com.dunshan.mall.order.domain.OmsOrderReturnApplyParam;

/**
 * 订单退货管理Service
 * Created by dunshan on 2018/10/17.
 */
public interface OmsOrderReturnApplyService {
    /**
     * 提交申请
     */
    int create(OmsOrderReturnApplyParam returnApply);
}

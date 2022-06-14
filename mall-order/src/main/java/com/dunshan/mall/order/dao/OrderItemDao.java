package com.dunshan.mall.order.dao;

import com.dunshan.mall.model.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单商品信息自定义Dao
 * Created by dunshan on 2018/9/3.
 */
public interface OrderItemDao {
    int insertList(@Param("list") List<OmsOrderItem> list);

    int batchUserRole(List<OmsOrderItem> list);
}

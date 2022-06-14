package com.dunshan.mall.order.service.impl;

import com.dunshan.mall.mapper.OmsOrderReturnApplyMapper;
import com.dunshan.mall.model.OmsOrderReturnApply;
import com.dunshan.mall.order.domain.OmsOrderReturnApplyParam;
import com.dunshan.mall.order.service.OmsOrderReturnApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 订单退货管理Service实现类
 * Created by dunshan on 2018/10/17.
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {
    @Autowired
    private OmsOrderReturnApplyMapper returnApplyMapper;
    @Override
    public int create(OmsOrderReturnApplyParam returnApply) {
        OmsOrderReturnApply realApply = new OmsOrderReturnApply();
        BeanUtils.copyProperties(returnApply,realApply);
        realApply.setCreateTime(new Date());
        realApply.setStatus(0);
        return returnApplyMapper.insert(realApply);
    }
}

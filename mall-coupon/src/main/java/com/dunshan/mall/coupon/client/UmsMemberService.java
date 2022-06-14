package com.dunshan.mall.coupon.client;

import com.dunshan.mall.model.UmsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("mall-member")
public interface UmsMemberService {

    /**
     * 获取当前登录会员
     */
    @RequestMapping(value = "/sso/currentMember", method = RequestMethod.GET)
    UmsMember getCurrentMember();

}

package com.dunshan.mall.cart.client;

import com.dunshan.mall.model.UmsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("mall-member")
@Component
public interface UmsMemberService {

    @RequestMapping(value = "/sso/currentMember", method = RequestMethod.GET)
    UmsMember getCurrentMember();

}

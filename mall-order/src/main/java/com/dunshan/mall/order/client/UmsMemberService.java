package com.dunshan.mall.order.client;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.UmsMember;
import com.dunshan.mall.model.UmsMemberReceiveAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("mall-member")
@Component
public interface UmsMemberService {


    /**
     * 显示所有收货地址
     *
     * @return
     */
    @RequestMapping(value = "/member/address/list", method = RequestMethod.GET)
    CommonResult<List<UmsMemberReceiveAddress>> list();

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "/sso/currentMember", method = RequestMethod.GET)
    UmsMember getCurrentMember();

    @RequestMapping(value = "/sso/feign/info", method = RequestMethod.GET)
    UmsMember getCurrentMemberAddress();




    /**
     * 收货人信息：姓名、电话、邮编、地址
     *
     * @param id
     * @return member/address
     */
    @RequestMapping(value = "/member/address/{id}/{userId}", method = RequestMethod.GET)
    CommonResult<UmsMemberReceiveAddress> getItem(@PathVariable Long id,@PathVariable Long userId);

    /**
     * 根据会员id修改会员积分
     */
    @RequestMapping(value = "/sso/update/Integration", method = RequestMethod.POST)
    void updateIntegration(@RequestParam("id") Long id, @RequestParam("integration") Integer integration);

    /**
     * 根据会员编号获取会员
     */
    @RequestMapping(value = "/sso/member", method = RequestMethod.POST)
    UmsMember getById(@RequestParam("id") Long id);
}

package com.dunshan.mall.member.controller;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.common.constant.AuthConstant;
import com.dunshan.mall.common.domain.UserDto;
import com.dunshan.mall.model.UmsMember;
import com.dunshan.mall.member.service.UmsMemberService;
import com.dunshan.mall.model.UmsMemberReceiveAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 会员登录注册管理Controller
 * Created by dunshan on 2018/8/3.
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("会员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
        memberService.register(username, password, telephone, authCode);
        return CommonResult.success(null, "注册成功");
    }

    @ApiOperation("会员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String username,
                              @RequestParam String password) {
        return memberService.login(username, password);
    }

    @ApiOperation("获取会员信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult info() {
        UmsMember member = memberService.getCurrentMember();
        return CommonResult.success(member);
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode) {
        memberService.updatePassword(telephone, password, authCode);
        return CommonResult.success(null, "密码修改成功");
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        return memberService.loadUserByUsername(username);
    }


    @ApiOperation("远程获取获取会员信息")
    @RequestMapping(value = "/currentMember", method = RequestMethod.GET)
    @ResponseBody
    public UmsMember currentMember(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(AuthConstant.USER_TOKEN_HEADER);
        logger.info("请求 header{}", header);
        UmsMember member = memberService.getCurrentMember();
        return member;
    }

    /**
     * 远程调用
     * 获取会员信息
     *
     * @return
     */
    @ApiOperation("远程调用获取会员信息")
    @RequestMapping(value = "/feign/info", method = RequestMethod.GET)
    @ResponseBody
    public UmsMember getCurrentMemberAddress() {
        UmsMember currentMembe = memberService.getCurrentMember();
        List<UmsMemberReceiveAddress> list = memberService.list();
        currentMembe.setList(list);
        return currentMembe;
    }


    @ApiOperation("根据会员id修改会员积分")
    @RequestMapping(value = "/update/Integration", method = RequestMethod.POST)
    public void updateIntegration(@RequestParam("id") Long id, @RequestParam("integration") Integer integration) {
        memberService.updateIntegration(id, integration);
    }

    @ApiOperation("根据会员编号获取会员")
    @RequestMapping(value = "/member", method = RequestMethod.POST)
    @ResponseBody
    UmsMember getById(@RequestParam("id") Long id) {
        return memberService.getById(id);
    }

}

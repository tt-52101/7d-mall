package com.dunshan.mall.order.controller;

import cn.hutool.json.JSONUtil;
import com.dunshan.mall.common.api.CommonPage;
import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.common.constant.AuthConstant;
import com.dunshan.mall.common.domain.UserDto;
import com.dunshan.mall.order.domain.ConfirmOrderResult;
import com.dunshan.mall.order.domain.OmsOrderDetail;
import com.dunshan.mall.order.domain.OrderParam;
import com.dunshan.mall.order.service.OmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 订单管理Controller
 * Created by dunshan on 2018/8/30.
 */
@Controller
@Api(tags = "OmsPortalOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OmsOrderController.class);

    @Autowired
    private OmsOrderService portalOrderService;

    @ApiOperation("根据购物车信息生成确认单信息")
    @RequestMapping(value = "/generateConfirmOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ConfirmOrderResult> generateConfirmOrder(@RequestBody List<Long> cartIds, HttpServletRequest request) {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if (StringUtils.isEmpty(userStr)) {
            return CommonResult.failed("请重新登录");
        }
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        logger.info("请求用户为：{}", JSONUtil.toJsonStr(userDto));
        Long id = userDto.getId();
        logger.info("当前用户Id :{}", id);
        ConfirmOrderResult confirmOrderResult = portalOrderService.generateConfirmOrder(cartIds,id);
        return CommonResult.success(confirmOrderResult);
    }

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult generateOrder(@RequestBody OrderParam orderParam, HttpServletRequest request) {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if (StringUtils.isEmpty(userStr)) {
            return CommonResult.failed("请重新登录");
        }
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        logger.info("请求用户为：{}", JSONUtil.toJsonStr(userDto));
        Long id = userDto.getId();
        logger.info("当前用户Id :{}", id);
        Map<String, Object> result = portalOrderService.generateOrder(orderParam, id);
        if ("用户查询失败".equals(result.get("user"))) {
            return CommonResult.failed("下单失败");
        }
        return CommonResult.success(result, "下单成功");
    }

    @ApiOperation("用户支付成功的回调")
    @RequestMapping(value = "/paySuccess", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult paySuccess(@RequestParam Long orderId, @RequestParam Integer payType) {
        Integer count = portalOrderService.paySuccess(orderId, payType);
        if (count == 0) {
            return CommonResult.failed();
        }
        return CommonResult.success(count, "支付成功");
    }

    @ApiOperation("自动取消超时订单")
    @RequestMapping(value = "/cancelTimeOutOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult cancelTimeOutOrder() {
        portalOrderService.cancelTimeOutOrder();
        return CommonResult.success(null);
    }

    @ApiOperation("取消单个超时订单")
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult cancelOrder(Long orderId) {
        portalOrderService.sendDelayMessageCancelOrder(orderId);
        return CommonResult.success(null);
    }

    @ApiOperation("按状态分页获取用户订单列表")
    @ApiImplicitParam(name = "status", value = "订单状态：-1->全部；0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭",
            defaultValue = "-1", allowableValues = "-1,0,1,2,3,4", paramType = "query", dataType = "int")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderDetail>> list(@RequestParam Integer status,
                                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        CommonPage<OmsOrderDetail> orderPage = portalOrderService.list(status, pageNum, pageSize);
        return CommonResult.success(orderPage);
    }

    @ApiOperation("根据ID获取订单详情")
    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsOrderDetail> detail(@PathVariable Long orderId) {
        OmsOrderDetail orderDetail = portalOrderService.detail(orderId);
        return CommonResult.success(orderDetail);
    }

    @ApiOperation("用户取消订单")
    @RequestMapping(value = "/cancelUserOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult cancelUserOrder(Long orderId) {
        portalOrderService.cancelOrder(orderId);
        return CommonResult.success(null);
    }

    @ApiOperation("用户确认收货")
    @RequestMapping(value = "/confirmReceiveOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult confirmReceiveOrder(Long orderId) {
        portalOrderService.confirmReceiveOrder(orderId);
        return CommonResult.success(null);
    }

    @ApiOperation("用户删除订单")
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteOrder(Long orderId) {
        portalOrderService.deleteOrder(orderId);
        return CommonResult.success(null);
    }
}

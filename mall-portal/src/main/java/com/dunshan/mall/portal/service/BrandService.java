package com.dunshan.mall.portal.service;


import com.dunshan.mall.portal.domain.HomeContentResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("mall-brand")
public interface BrandService {


    /**
     * 首页内容页信息展示
     *
     * @return
     */
    @RequestMapping(value = "/brand/content", method = RequestMethod.GET)
    HomeContentResult content(@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);
}

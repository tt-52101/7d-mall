package com.dunshan.mall.cart.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.dunshan.mall.cart.client.CouponPromotionService;
import com.dunshan.mall.cart.client.ProductService;
import com.dunshan.mall.cart.client.SkuStockSevice;
import com.dunshan.mall.cart.client.UmsMemberService;
import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.mapper.OmsCartItemMapper;
import com.dunshan.mall.mapper.PmsSkuStockMapper;
import com.dunshan.mall.model.*;
import com.dunshan.mall.cart.domain.CartProduct;
import com.dunshan.mall.cart.service.OmsCartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车管理Service实现类
 * Created by dunshan on 2018/8/2.
 */
@Service
public class OmsCartItemServiceImpl implements OmsCartItemService {
    private static final Logger logger = LoggerFactory.getLogger(OmsCartItemServiceImpl.class);
    @Autowired
    private OmsCartItemMapper cartItemMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private CouponPromotionService promotionService;
    @Autowired
    private UmsMemberService memberService;

    @Autowired
    private PmsSkuStockMapper skuStockMapper;

    @Autowired
    SkuStockSevice skuStockSevice;


    @Override
    public int add(OmsCartItem cartItem) {
        int count;
        UmsMember currentMember = memberService.getCurrentMember();
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        OmsCartItem existCartItem = getCartItem(cartItem);
        if (existCartItem == null) {
            cartItem.setCreateDate(new Date());
            count = cartItemMapper.insert(cartItem);
        } else {
            cartItem.setModifyDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateByPrimaryKey(existCartItem);
        }
        return count;
    }

    /**
     * 增加购物车
     *
     * @param productSkuCode 库存商品编号
     * @param quantity       商品数量
     * @return
     */
    @Override
    public int addCart(String productSkuCode, Integer quantity) {
        int count;
        List<PmsSkuStock> pmsSkuStocks = null;
        //远程获取库存数据
        CommonResult<List<PmsSkuStock>> listCommonResult = skuStockSevice.pmsSkuStocksById(productSkuCode);
        if (listCommonResult.getData() != null && listCommonResult.getData().get(0).getId() != null) {
            pmsSkuStocks = listCommonResult.getData();
        } else {
            PmsSkuStockExample example = new PmsSkuStockExample();
            example.createCriteria().andSkuCodeEqualTo(productSkuCode);
            pmsSkuStocks = skuStockMapper.selectByExample(example);
            if (pmsSkuStocks == null) {
                return 0;
            }
        }

        PmsSkuStock pmsSkuStock = pmsSkuStocks.get(0);
        /**
         * 获取商品
         */
        CommonResult<PmsProduct> pmsProductCommonResult = productService.selectByProduct(pmsSkuStock.getProductId());

        PmsProduct pmsProduct = pmsProductCommonResult.getData();

        OmsCartItem cartItem = new OmsCartItem();
        UmsMember currentMember = memberService.getCurrentMember();
        if (currentMember == null) {
            return 0;
        }

        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        cartItem.setProductId(pmsSkuStock.getProductId());
        cartItem.setProductSkuId(pmsSkuStock.getId());
        cartItem.setProductSkuCode(productSkuCode);
        cartItem.setQuantity(quantity);
        cartItem.setProductSkuCode(pmsSkuStock.getSkuCode());
        cartItem.setPrice(pmsProduct.getPrice());
        cartItem.setProductPic(pmsProduct.getPic());
        cartItem.setProductName(pmsProduct.getName());
        cartItem.setProductSubTitle(pmsProduct.getSubTitle());
        cartItem.setCreateDate(new Date());
        cartItem.setModifyDate(new Date());
        cartItem.setProductCategoryId(pmsProduct.getProductCategoryId());
        cartItem.setProductBrand(pmsProduct.getBrandName());
        cartItem.setProductSn(pmsProduct.getProductSn());
        cartItem.setProductAttr(pmsSkuStock.getSpData());
        OmsCartItem existCartItem = getCartItem(cartItem);
        if (existCartItem == null) {
            cartItem.setCreateDate(new Date());
            count = cartItemMapper.insert(cartItem);
        } else {
            cartItem.setModifyDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateByPrimaryKey(existCartItem);
        }

        return count;
    }

    /**
     * 根据会员id,商品id和规格获取购物车中商品
     */
    private OmsCartItem getCartItem(OmsCartItem cartItem) {
        OmsCartItemExample example = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = example.createCriteria().andMemberIdEqualTo(cartItem.getMemberId())
                .andProductIdEqualTo(cartItem.getProductId()).andDeleteStatusEqualTo(0);
        if (!StringUtils.isEmpty(cartItem.getProductSkuId())) {
            criteria.andProductSkuIdEqualTo(cartItem.getProductSkuId());
        }
        List<OmsCartItem> cartItemList = cartItemMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }

    @Override
    public List<OmsCartItem> list(Long memberId) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andDeleteStatusEqualTo(0).andMemberIdEqualTo(memberId);
        return cartItemMapper.selectByExample(example);
    }

    @Override
    public List<CartPromotionItem> listPromotion(Long memberId, List<Long> cartIds) {
        List<OmsCartItem> cartItemList = list(memberId);
        if (CollUtil.isNotEmpty(cartIds)) {
            cartItemList = cartItemList.stream().filter(item -> cartIds.contains(item.getId())).collect(Collectors.toList());
        }
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cartItemList)) {
            OmsCartItemVo omsCartItemVo = new OmsCartItemVo();
            omsCartItemVo.setCartItemList(cartItemList);
            logger.info("远程请求优惠劵 {}", JSONObject.toJSONString(omsCartItemVo));
            cartPromotionItemList = promotionService.calcCartPromotion(omsCartItemVo).getData();
        }
        return cartPromotionItemList;
    }

    @Override
    public int updateQuantity(Long id, Long memberId, Integer quantity) {
        OmsCartItem cartItem = new OmsCartItem();
        cartItem.setQuantity(quantity);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andDeleteStatusEqualTo(0)
                .andIdEqualTo(id).andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(cartItem, example);
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andIdIn(ids).andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(record, example);
    }

    @Override
    public CartProduct getCartProduct(Long productId) {
        return productService.getCartProduct(productId);
    }

    @Override
    public int updateAttr(OmsCartItem cartItem) {
        //删除原购物车信息
        OmsCartItem updateCart = new OmsCartItem();
        updateCart.setId(cartItem.getId());
        updateCart.setModifyDate(new Date());
        updateCart.setDeleteStatus(1);
        cartItemMapper.updateByPrimaryKeySelective(updateCart);
        cartItem.setId(null);
        add(cartItem);
        return 1;
    }

    @Override
    public int clear(Long memberId) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(record, example);
    }
}

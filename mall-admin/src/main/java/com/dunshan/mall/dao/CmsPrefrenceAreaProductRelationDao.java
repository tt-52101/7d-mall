package com.dunshan.mall.dao;

import com.dunshan.mall.model.CmsPrefrenceAreaProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义优选和商品关系操作Dao
 * Created by dunshan on 2018/4/26.
 */
public interface CmsPrefrenceAreaProductRelationDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);
}

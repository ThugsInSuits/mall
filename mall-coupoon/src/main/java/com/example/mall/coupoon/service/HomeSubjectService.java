package com.example.mall.coupoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.utils.PageUtils;
import com.example.mall.coupoon.entity.HomeSubjectEntity;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author cmp
 * @email meipengchen6@gmail.com
 * @date 2023-08-11 23:26:42
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.example.mall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.PageUtils;
import com.example.common.utils.Query;

import com.example.mall.product.dao.CategoryDao;
import com.example.mall.product.entity.CategoryEntity;
import com.example.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> queryPageWithTree(Map<String, Object> params) {
        // 1.查询所有的商品分类信息
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        // 2.拆分商品信息为树形结构
        List<CategoryEntity> list = categoryEntities.stream().filter(p -> p.getParentCid() == 0)
                .map(p -> {
                    p.setChildrens(getCatetoryChildren(p,categoryEntities));
                    return p;
                }).sorted((p1, p2) -> {
                    return (p1 == null ? 0 : p1.getSort()) - (p2.getSort() == null ? 0 : p2.getSort());
                }).collect(Collectors.toList());

        return list;
    }

    /**
     * 逻辑批量删除
     * @param list
     */
    @Override
    public void removeCatetoryByIds(List<Long> list) {
        //1. 检查该数据是否在其他业务中被使用

        //2.
        baseMapper.deleteBatchIds(list);
    }

    private List<CategoryEntity> getCatetoryChildren(CategoryEntity entity,List<CategoryEntity> categoryEntities) {
        List<CategoryEntity> collect = categoryEntities.stream().filter(category -> {
            return category.getParentCid().equals(entity.getCatId());
        }).map(category -> {
            category.setChildrens(getCatetoryChildren(category,categoryEntities));
            return category;
        }).sorted((category1,category2) -> {
            return (category1.getSort() == null ? 0: category1.getSort()) - (category2.getSort() == null ? 0: category2.getSort());
        }).collect(Collectors.toList());
        return collect;
    }


}
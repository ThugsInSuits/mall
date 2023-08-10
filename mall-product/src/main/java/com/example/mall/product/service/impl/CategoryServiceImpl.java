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
        // 1. 查询所有的商品分类信息
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);

        // 2.将商品分类信息拆解为树形结构
        List<CategoryEntity> list = categoryEntityList.stream().filter(p -> p.getParentCid() == 0)
                .map(p->{

                    p.setChildList(getCategoryChildrens(p,categoryEntityList));
                    return p;
                })
                .sorted((p1,p2)->{
                    return (p1.getSort() == null ? 0 : p1.getSort())-(p2.getSort() == null ? 0 : p2.getSort());
                })
                .collect(Collectors.toList());
        return list;
    }

    /**
     * 查找大类下的所有小类
     * @param p
     * @param categoryEntityList
     * @return
     */
    private List<CategoryEntity> getCategoryChildrens(CategoryEntity p, List<CategoryEntity> categoryEntityList) {
        List<CategoryEntity> collection = categoryEntityList.stream().filter(m ->{
            return p.getParentCid() == m.getCatId();
        }).map(n->{
            n.setChildList(getCategoryChildrens(n,categoryEntityList));
            return n;
        })
        .sorted((p1,p2)->{
            return (p1.getSort() == null ? 0 : p1.getSort())-(p2.getSort() == null ? 0 : p2.getSort());
        })
        .collect(Collectors.toList());
        return collection;
    }

}
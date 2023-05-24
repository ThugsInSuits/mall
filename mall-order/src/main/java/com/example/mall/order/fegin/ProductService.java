package com.example.mall.order.fegin;

import com.example.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 指明服务中心的名称
 */
@FeignClient(name = "mall-product")
public interface ProductService {
    @GetMapping("product/brand/queryAllBrand")
    public R queryAllBrand();
}

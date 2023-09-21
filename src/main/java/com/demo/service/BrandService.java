package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.pojo.Brand;

import java.util.List;

public interface BrandService {

    //新增品牌数据
    boolean addBrand(Brand brand);

    //删除品牌数据
    boolean deleteBrand(Integer brandId);

    //修改品牌数据
    boolean updateBrand(Brand brand);

    //按id查询品牌数据
    Brand findBrandById(Integer brandId);

    //按条件分页查询品牌数据
    IPage<Brand> findOptionsBrands(Integer currentPage,Integer pageSize,Brand brand);

}

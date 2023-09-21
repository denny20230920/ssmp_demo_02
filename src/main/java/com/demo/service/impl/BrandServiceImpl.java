package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.controller.result.Code;
import com.demo.exception.BusinessException;
import com.demo.mapper.BrandMapper;
import com.demo.pojo.Brand;
import com.demo.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    //定义一个缓存数据HashMap
    HashMap<Integer,Brand> cache = new HashMap<>();

    /**
     * 新增品牌数据
     * @param brand
     * @return
     */
    @Override
    public boolean addBrand(Brand brand) {

        log.info("请求参数:"+brand);

        if (brand == null){
            log.error("参数错误:"+brand);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误");
        }

        if (brand.getBrandName() == null || brand.getBrandName() == ""){
            log.error("品牌名称不能为空:"+brand);
            throw new BusinessException(Code.BUSINESS_BRANG_NAME_NULL,"品牌名称不能为空");
        }

        int insert = brandMapper.insert(brand);
        log.info("响应参数:"+insert);

        return insert>0;
    }

    /**
     * 删除品牌数据
     * @param brandId
     * @return
     */
    @Override
    public boolean deleteBrand(Integer brandId) {

        log.info("请求参数:"+brandId);

        if (brandId<0){
            log.error("参数错误:"+brandId);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误");
        }

        int deleteById = brandMapper.deleteById(brandId);

        log.info("响应数据:"+deleteById);

        return deleteById>0;
    }

    /**
     * 修改品牌数据
     * @param brand
     * @return
     */
    @Override
    public boolean updateBrand(Brand brand) {

        log.info("请求参数:"+brand);

        if (brand == null){
            log.error("参数错误:"+brand);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误");
        }

        if (brand.getId() == null){
            log.error("品牌Id不能为空:"+brand);
            throw new BusinessException(Code.BUSINESS_BRANG_NAME_NULL,"品牌名称不能为空");
        }

        //修改品牌查询一下数据是否存在
        Brand brand1 = brandMapper.selectById(brand.getId());
        if (brand1 == null){
            log.error("品牌数据不存在"+brand1);
            throw new BusinessException(Code.BUSINESS_BRANG_DATA_ERR,"品牌数据不存在，请刷新重试");
        }

        int updateById = brandMapper.updateById(brand);

        log.info("响应数据:"+updateById);

        return updateById>0;
    }

    /**
     * 按id查询品牌数据
     * @param brandId
     * @return
     */
    @Override
    //在brands缓存空间中生成key为brandId的缓存
    @Cacheable(value = "brands",key = "#brandId")
    public Brand findBrandById(Integer brandId) {

        log.info("请求参数:"+brandId);

        if (brandId<0){
            log.error("参数错误:"+brandId);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误");
        }

        Brand brand = cache.get(brandId);

        if (brand == null){
            brand = brandMapper.selectById(brandId);
            cache.put(brandId,brand);
        }

        log.info("响应数据:"+brand);

        return brand;
    }

    /**
     * 按条件分页查询品牌数据
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public IPage<Brand> findOptionsBrands(Integer currentPage, Integer pageSize, Brand brand) {

        log.info("请求参数:"+"当前页码"+currentPage+",每页数量"+pageSize+"查询品牌条件"+brand);

        if (currentPage<0){
            log.error("参数错误:"+currentPage);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误");
        }

        if (pageSize<0){
            log.error("参数错误:"+pageSize);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误");
        }

        IPage<Brand> page = new Page(currentPage,pageSize);

        LambdaQueryWrapper<Brand> lqw = new LambdaQueryWrapper<>();

        if (brand == null){
            lqw.like(brand.getBrandName()!= null,Brand::getBrandName,brand.getBrandName());
            lqw.like(brand.getCompanyName()!= null,Brand::getCompanyName,brand.getCompanyName());
            lqw.like(brand.getDescription()!= null,Brand::getDescription,brand.getDescription());
        }

        brandMapper.selectPage(page, lqw);

        //当前页码大于总页码数时重新查询一下
        if (currentPage > page.getPages()){
            currentPage = (int)page.getPages();
            findOptionsBrands(currentPage,pageSize,brand);
        }


        log.info("响应数据:"+page);

        return page;
    }
}

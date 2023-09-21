package com.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.controller.result.Code;
import com.demo.controller.result.JsonResult;
import com.demo.pojo.Brand;
import com.demo.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //新增品牌数据
    @PostMapping
    public JsonResult<Boolean> addBrand(@RequestBody Brand brand){

        boolean bool = brandService.addBrand(brand);

        return new JsonResult<>(bool? Code.SAVE_OK:Code.SAVE_ERR,bool);
    }

    //删除品牌数据
    @DeleteMapping("/{brandId}")
    public JsonResult<Boolean> deleteBrand(@PathVariable Integer brandId){

        boolean bool = brandService.deleteBrand(brandId);

        return new JsonResult<>(bool? Code.SAVE_OK:Code.SAVE_ERR,bool);
    }

    //修改品牌数据
    @PutMapping
    public JsonResult<Boolean> updateBrand(@RequestBody Brand brand){

        boolean bool = brandService.updateBrand(brand);

        return new JsonResult<>(bool? Code.SAVE_OK:Code.SAVE_ERR,bool);
    }

    //按id查询品牌数据
    @GetMapping("/{brandId}")
    public JsonResult<Brand> findBrandById(@PathVariable Integer brandId){

        Brand brandById = brandService.findBrandById(brandId);

        int code = brandId!=null?Code.GET_OK:Code.GET_ERR;
        String msg = brandId!=null?"success":"fail";

        return new JsonResult<>(code,brandById,msg);
    }

    //按条件分页查询品牌数据
    @GetMapping("/{currentPage}/{pageSize}")
    public JsonResult<IPage<Brand>> findOptionsBrands(@PathVariable Integer currentPage,
                                                      @PathVariable Integer pageSize,
                                                      @RequestBody Brand brand){

        IPage<Brand> optionsBrands = brandService.findOptionsBrands(currentPage, pageSize, brand);

        int code = optionsBrands!=null?Code.GET_OK:Code.GET_ERR;
        String msg = optionsBrands.getRecords()!=null?"success":"fail";

        return new JsonResult<>(code,optionsBrands,msg);
    }

}

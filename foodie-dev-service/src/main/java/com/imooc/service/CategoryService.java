package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有一级分类
     * @return 商品分类list
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id 查询二级分类
     * @param rootCatId 分类id
     * @return 二级分类list
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个分类下的6条商品信息数据
     * @param rootCatId 分类id
     * @return 商品list
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}

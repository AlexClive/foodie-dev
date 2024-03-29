package com.imooc.pojo.vo;

import java.util.List;

/*
* 二级分类VO
* */
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;

    // 三级分类 vo
    private List<SubCareGoryVo> subCatList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public List<SubCareGoryVo> getSubCatList() {
        return subCatList;
    }

    public void setSubCatList(List<SubCareGoryVo> subCatList) {
        this.subCatList = subCatList;
    }
}

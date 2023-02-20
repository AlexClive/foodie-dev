package com.imooc.pojo.vo;

/**
 * 用于展示商品评价数量的VO
 */
public class CommentLevelCountsVO {
    private Integer totalCount;
    private Integer goodCount;
    private Integer normalCount;
    private Integer badCount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(Integer normalCount) {
        this.normalCount = normalCount;
    }

    public Integer getBadCount() {
        return badCount;
    }

    public void setBadCount(Integer badCount) {
        this.badCount = badCount;
    }
}
